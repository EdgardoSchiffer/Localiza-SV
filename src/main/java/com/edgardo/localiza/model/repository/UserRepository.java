package com.edgardo.localiza.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.edgardo.localiza.model.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}