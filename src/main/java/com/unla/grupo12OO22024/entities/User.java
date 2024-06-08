package com.unla.grupo12OO22024.entities;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.type.LocalDateType;

@Entity
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long id;

    @Column(name = "username", unique = true, nullable = false, length = 45)
    private String username;

    @Column(name = "password",  nullable = false, length = 60)
    private String password;

    @Column(name = "baja")
    private boolean enabled;

    // resolver importacion

    @Column (name = "createdat")
    @CreationTimestamp
    private LocalDateType createdAt;
    
    @Column (name = "updateat")
    @UpdateTimestamp
    private LocalDateTime updateAt;
    

    @OneToMany (fetch = FetchType.LAZY, mappedBy = "user")
    private Set<UserRole> userRoles = new HashSet<UserRole>();

}
