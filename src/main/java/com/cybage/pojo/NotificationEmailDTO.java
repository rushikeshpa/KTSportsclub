package com.cybage.pojo;

public class NotificationEmailDTO {

	private String subject;
	private String recipient;
	private String body;
	public NotificationEmailDTO() {
		// TODO Auto-generated constructor stub
	}
	public NotificationEmailDTO(String subject, String recipient, String body) {
		super();
		this.subject = subject;
		this.recipient = recipient;
		this.body = body;
	}
	public String getSubject() {
		return subject;
	}
	public void setSubject(String subject) {
		this.subject = subject;
	}
	public String getRecipient() {
		return recipient;
	}
	public void setRecipient(String recipient) {
		this.recipient = recipient;
	}
	public String getBody() {
		return body;
	}
	public void setBody(String body) {
		this.body = body;
	}
	@Override
	public String toString() {
		return "NotificationEmailDTO [subject=" + subject + ", recipient=" + recipient + ", body=" + body + "]";
	}
	
	

}
