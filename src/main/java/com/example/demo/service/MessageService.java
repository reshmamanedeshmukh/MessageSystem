package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.Message;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;

public interface MessageService {

	public Message createMessage(Message message);

	public List<UserModel> getAllUsersBymessagesId(long messageId);

	public List<MessageModel> getAllMessagessByUsersId(long UId);

	public String getReadStatus(long messageId, long uId, boolean isRead);
	// public String updateMessageReadStatus(Long messageId,Long UId,Boolean
	// isRead);

}
