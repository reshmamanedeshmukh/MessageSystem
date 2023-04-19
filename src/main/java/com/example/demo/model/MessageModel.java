package com.example.demo.model;

public class MessageModel {

	private long id;

	private String title;

	private String description;

	private boolean isRead;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "MessageModel [id=" + id + ", title=" + title + ", description=" + description + ", isRead=" + isRead
				+ "]";
	}

}
