package com.unla.grupo12OO22024.entities;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotEmpty;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @NotEmpty(message = "El nombre de usuario es obligatorio")
    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @NotEmpty(message = "La contraseña es obligatoria")
    @Column(name = "password", nullable = false, length = 60)
    private String password;

    @Column(name = "baja")
    private boolean enabled;

    @Column(name = "createdat")
    @CreationTimestamp
    private LocalDate createdAt;

    @Column(name = "updateat")
    @UpdateTimestamp
    private LocalDateTime updateAt;

    @OneToMany(mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<UserRole>();


    public User(Long id, String username, String password, boolean enabled, LocalDate createdAt, LocalDateTime updateAt, Set<UserRole> userRoles) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.enabled = enabled;
        this.createdAt = createdAt;
        this.updateAt = updateAt;
        this.userRoles = userRoles;
    }

    public User() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUsername() {
        return this.username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isEnabled() {
        return this.enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    
    public LocalDate getCreatedAt() {
		return this.createdAt;
	}

    public void setCreatedAt(LocalDate createdAt) {
		this.createdAt = createdAt;
	}

    public LocalDateTime getUpdateAt() {
		return this.updateAt;
	}

    public void setUpdateAt(LocalDateTime updateAt) {
		this.updateAt = updateAt;
	}

    public Set<UserRole> getUserRoles() {
		return this.userRoles;
	}

    public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}
    
}
