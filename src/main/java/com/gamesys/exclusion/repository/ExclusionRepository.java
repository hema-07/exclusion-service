package com.gamesys.exclusion.repository;

import com.gamesys.exclusion.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ExclusionRepository extends CrudRepository<User, Integer> {

    @Query("SELECT a FROM User a WHERE a.userStatus = 'B'")
    List<User> findByStatus();
}
