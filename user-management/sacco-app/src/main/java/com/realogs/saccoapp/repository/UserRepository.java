package com.realogs.saccoapp.repository;

import com.realogs.saccoapp.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);
    @Query(value = "select u.userName from User u where u.id in (:pIdList)",nativeQuery = true)
    List<String> findByIdList(@Param("pIdList") List<Long> idList);
}
