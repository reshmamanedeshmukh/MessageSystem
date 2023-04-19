package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.Message;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.MessageService;

@RestController
public class MessageController {

	@Autowired
	private MessageService messageservice;

	@PostMapping("/messages")
	public ResponseEntity<String> createMessage(@RequestBody Message message) {
		message = messageservice.createMessage(message);
		return new ResponseEntity<>("notificationId :" + message.getId(), HttpStatus.CREATED);
	}

	@GetMapping("/messages/{messagesId}/users")
	public ResponseEntity<List<UserModel>> getAllUsersBymessagesId(
			@PathVariable(value = "messagesId") Long messagesId) {
		List<UserModel> users = messageservice.getAllUsersBymessagesId(messagesId);

		return new ResponseEntity<>(users, HttpStatus.OK);
	}

	@GetMapping("/users/{uId}/messages")
	public ResponseEntity<List<MessageModel>> getAllMessagessByUsersId(@PathVariable(value = "uId") Long uId) {

		List<MessageModel> messages = messageservice.getAllMessagessByUsersId(uId);

		return new ResponseEntity<>(messages, HttpStatus.OK);

	}

	@PatchMapping("/messages/{messagesId}/users/{uId}/{isRead}")
	public ResponseEntity<String> getReadStatus(@PathVariable(value = "messagesId") Long messageId,
			@PathVariable(value = "uId") Long uId, @PathVariable Boolean isRead) {

		String status = messageservice.getReadStatus(messageId, uId, isRead);

		return new ResponseEntity<String>(status, HttpStatus.OK);
	}

}
