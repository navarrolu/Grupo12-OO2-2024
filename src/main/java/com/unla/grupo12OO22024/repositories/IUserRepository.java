package com.unla.grupo12OO22024.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.unla.grupo12OO22024.entities.User;


@Repository("userRepository")
public interface IUserRepository extends JpaRepository<User, Long> {
    
    @Query("SELECT u FROM User u JOIN FETCH u.userRoles WHERE u.username = (:username)")
    public abstract User findByUsernameAndFetchUserRolesEagerly(@Param("username") String username);

    public Optional<User> findByUsername(String username);
    


}
