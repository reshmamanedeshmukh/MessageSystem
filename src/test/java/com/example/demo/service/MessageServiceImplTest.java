package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.repository.MessageRepository;
import com.example.demo.repository.UserRepository;


@SpringBootTest(classes = { MessageServiceImplTest.class })
class MessageServiceImplTest {

	@InjectMocks
	private MessageServiceImpl sut;

	@Mock
	MessageRepository messageRepository;

	@Mock
	UserRepository userRepository;

	@BeforeEach
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void createMessageTest() {
		System.out.println("executing createMessageTest");
		User user1 = new User();
		user1.setId(1);
		user1.setRead(false);
		user1.setUid(101L);

		List<User> userList = new ArrayList<>();
		userList.add(user1);

		Message message = new Message();
		message.setId(11);
		message.setTitle("msg1");
		message.setDescription("description1");
		message.setUsers(userList);

		when(messageRepository.save(message)).thenReturn(message);

		assertEquals(message, sut.createMessage(message));

	}

	@Test
	public void getAllUsersBymessagesIdTest() {
		List<User> userList1 = new ArrayList<>();
		List<UserModel> userModel1 = new ArrayList<>();

		User user1 = new User();
		user1.setId(1);
		user1.setRead(false);
		user1.setUid(501);

		User user2 = new User();
		user2.setId(2);
		user2.setRead(false);
		user2.setUid(503);

		userList1.add(user1);
		userList1.add(user2);

		UserModel model1 = new UserModel();
		model1.setUid(user1.getUid());
		model1.setRead(user1.isRead());

		UserModel model2 = new UserModel();
		model2.setUid(user2.getUid());
		model2.setRead(user2.isRead());

		userModel1.add(model1);
		userModel1.add(model2);

		long messageId = 1;
		when(userRepository.findUsersByMessagesId(messageId)).thenReturn(userList1);

		assertEquals(2, sut.getAllUsersBymessagesId(messageId).size());

	}

	@Test
	public void getAllMessagessByUsersIdTest() {
		User user1 = new User();
		user1.setId(1);
		user1.setRead(false);
		user1.setUid(101L);

		List<User> userList = new ArrayList<>();
		userList.add(user1);

		Message message = new Message();
		message.setId(1);
		message.setTitle("msg1");
		message.setDescription("description1");
		message.setUsers(userList);

		List<Message> msgList = new ArrayList<>();
		msgList.add(message);

		Long UId = 101L;
		when(messageRepository.findMessagesByUsersUid(UId)).thenReturn(msgList);
		List<MessageModel> messageModel = new ArrayList<>();

		for (Message message1 : msgList) {
			MessageModel messagemodel = new MessageModel();
			messagemodel.setId(message1.getId());
			messagemodel.setTitle(message1.getTitle());
			messagemodel.setDescription(message1.getDescription());
			List<User> user2 = message1.getUsers();
			for (User us : user2) {
				messagemodel.setRead(us.isRead());

			}
			messageModel.add(messagemodel);
		}

		assertEquals(1, sut.getAllMessagessByUsersId(UId).size());

	}

	@Test
	public void getReadStatustseTest() {
		List<User> userList1 = new ArrayList<>();
		User user3 = new User();
		user3.setId(2);
		user3.setRead(false);
		user3.setUid(102);

		Message m = new Message();
		m.setId(1);
		m.setTitle("Message title");
		m.setUsers(userList1);
		m.setDescription("message description");

		userList1.add(user3);

		long messageId = 1;
		long uId = 102;
		when(userRepository.findUserByMessagesIdAndUid(messageId, uId)).thenReturn(userList1);
		when(messageRepository.findById(messageId)).thenReturn(Optional.of(m));

		assertEquals("Notification message read status updated successfully", sut.getReadStatus(messageId, uId, true));

	}

}
