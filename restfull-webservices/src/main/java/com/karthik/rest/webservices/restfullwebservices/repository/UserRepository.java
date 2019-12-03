package com.karthik.rest.webservices.restfullwebservices.repository;

import com.karthik.rest.webservices.restfullwebservices.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
}
