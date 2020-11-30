package Tuition.pojos;

public class Message {
	
	private String contents;
	String sender;
	private int recipientId;
	private int messageId;
	
	
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public int getMessageId() {
		return messageId;
	}
	public void setMessageId(int messageId) {
		this.messageId = messageId;
	}
	public String getContents() {
		return contents;
	}
	public void setContents(String contents) {
		this.contents = contents;
	}
	
	public int getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(int recipientId) {
		this.recipientId = recipientId;
	}
	
	public Message(String contents, String sender, int recipientId) {
		super();
		this.contents = contents;
		this.sender = sender;
		this.recipientId = recipientId;
	}
	@Override
	public String toString() {
		return "Message [contents=" + contents + ", sender=" + sender + ", recipientId=" + recipientId + "]";
	}
	
	
}
