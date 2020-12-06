package Tuition.pojos;

public class Message {
	
	private String contents;
	String sender;
	private String recipientId;
	private int messageId;
	private String subject;
	
	
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
	
	public String getRecipientId() {
		return recipientId;
	}
	public void setRecipientId(String recipientId) {
		this.recipientId = recipientId;
	}
	
	
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public Message(String contents, String sender, String recipientId, String subject) {
		super();
		this.contents = contents;
		this.sender = sender;
		this.recipientId = recipientId;
		this.subject = subject;
	}
	@Override
	public String toString() {
		return "Message [contents=" + contents + ", sender=" + sender + ", recipientId=" + recipientId + "]";
	}
	
	
}
