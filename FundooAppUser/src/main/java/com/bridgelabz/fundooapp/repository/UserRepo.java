package com.bridgelabz.fundooapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.bridgelabz.fundooapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {
	User findByEmail(String email);
}
