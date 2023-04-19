package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
	
	List<User> findUsersByMessagesId(long messageId);
	List<User> findUserByMessagesIdAndUid(long messageId, long uId);
	
}
