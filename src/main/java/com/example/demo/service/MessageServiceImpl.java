package com.example.demo.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;

@Service
public class MessageServiceImpl implements MessageService {

	@Autowired
	public MessageRepository messageRepository;

	@Autowired
	private UserRepository userRepository;



	@Override
	public Message createMessage(Message message) {
		Message messageOne = messageRepository.save(message);
		return messageOne;
	}

	@Override
	public List<UserModel> getAllUsersBymessagesId(long messageId) {
		List<User> users = null;
		users = userRepository.findUsersByMessagesId(messageId);

		List<UserModel> userModel = new ArrayList<>();
		for (User user : users) {
			UserModel model = new UserModel();
			model.setUid(user.getUid());
			model.setRead(user.isRead());
			userModel.add(model);
		}
		System.out.println("userModel>> " + userModel);
		return userModel;
	}

	@Override
	public List<MessageModel> getAllMessagessByUsersId(long UId) {
		List<Message> messages = messageRepository.findMessagesByUsersUid(UId);
		List<MessageModel> messageModel = new ArrayList<>();

		for (Message message : messages) {
			MessageModel messagemodel = new MessageModel();
			messagemodel.setId(message.getId());
			messagemodel.setTitle(message.getTitle());
			messagemodel.setDescription(message.getDescription());
			List<User> user = message.getUsers();
			for (User user1 : user) {
				messagemodel.setRead(user1.isRead());

			}
			messageModel.add(messagemodel);
		}

		return messageModel;
	}

	@Override
	public String getReadStatus(long messageId, long uId, boolean isRead) {

		List<User> userList = userRepository.findUserByMessagesIdAndUid(messageId, uId);
		Message message = messageRepository.findById(messageId).get();
//		System.out.println("message>> "+message);

		for (User user2 : userList) {

			user2.setRead(isRead);
			userRepository.save(user2);

			// message.getUsers().add(user2);
			messageRepository.save(message);

			return "Notification message read status updated successfully";
		}

		return "Failed";

	}

}
