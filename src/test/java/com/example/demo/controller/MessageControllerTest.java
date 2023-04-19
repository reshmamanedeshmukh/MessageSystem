package com.example.demo.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.model.MessageModel;
import com.example.demo.model.UserModel;
import com.example.demo.service.MessageService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(value = MessageController.class)
public class MessageControllerTest {

	@MockBean
	private MessageService messageService;

	@Autowired
	private MockMvc mockMvc;

	@Test
	public void createMessageTest() throws Exception {

		User user1 = new User();
		user1.setId(1);
		user1.setRead(false);
		user1.setUid(101L);

		List<User> userList = new ArrayList<>();
		userList.add(user1);

		Message message = new Message();
		message.setId(1L);
		message.setTitle("msg1");
		message.setDescription("description1");
		message.setUsers(userList);

		when(messageService.createMessage(ArgumentMatchers.any())).thenReturn(message);

		ObjectMapper objectMapper = new ObjectMapper();
		ResultActions perform = mockMvc.perform(post("/messages").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(message)));

		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();

		int status = response.getStatus();

		assertEquals(201, status);

	}

	@Test
	public void getAllUsersBymessagesIdTest() throws Exception {
		List<UserModel> userModel1 = new ArrayList<>();
		UserModel model1 = new UserModel();
		model1.setUid(101);
		model1.setRead(false);

		userModel1.add(model1);

		long messageId = 1;

		when(messageService.getAllUsersBymessagesId(messageId)).thenReturn(userModel1);
		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions perform = mockMvc.perform(get("/messages/1/users").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(userModel1)));

		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);

	}

	@Test
	public void getAllMessagessByUsersIdTest() throws Exception {
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

		long UId = 101;
		when(messageService.getAllMessagessByUsersId(UId)).thenReturn(messageModel);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions perform = mockMvc.perform(get("/users/101/messages").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(messageModel)));

		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);

	}

	@Test
	public void getReadStatusTest() throws Exception {
		List<User> userList1 = new ArrayList<>();
		User user3 = new User();
		user3.setId(2);
		user3.setRead(false);
		user3.setUid(102);

		long messageId = 1;
		long uId = 102;

		userList1.add(user3);

		String str = "Notification message read status updated successfully";

		when(messageService.getReadStatus(messageId, uId, true)).thenReturn(str);

		ObjectMapper objectMapper = new ObjectMapper();

		ResultActions perform = mockMvc.perform(patch("/messages/1/users/102/true")
				.contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(userList1)));

		MvcResult andReturn = perform.andReturn();
		MockHttpServletResponse response = andReturn.getResponse();

		int status = response.getStatus();

		assertEquals(200, status);

	}
}
