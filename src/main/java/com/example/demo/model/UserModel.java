package com.example.demo.model;

public class UserModel {

	private long uid;

	private boolean isRead;

	public long getUid() {
		return uid;
	}

	public void setUid(long uid) {
		this.uid = uid;
	}

	public boolean isRead() {
		return isRead;
	}

	public void setRead(boolean isRead) {
		this.isRead = isRead;
	}

	@Override
	public String toString() {
		return "UserModel [uid=" + uid + ", isRead=" + isRead + "]";
	}

}
