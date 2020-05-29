package com.challenge.repository;

import com.challenge.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Query("SELECT u FROM User u JOIN u.candidates c JOIN c.id.acceleration a WHERE a.name = ?1")
    public List<User> findByAccelerationName(String accelerationName);

    @Query("SELECT u FROM User u JOIN u.candidates c JOIN c.id.acceleration a WHERE c.id.company = ?1")
    public List<User> findByCompanyId(Long companyId);
}
