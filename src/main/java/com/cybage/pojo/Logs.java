package com.cybage.pojo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Logs {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int logId;

	@Column
	private String description;

	@Column
	private String startDate;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties("logs")
	private Users users;

	public Logs() {

	}

	public Logs(int logId, String description, String startDate, Users users) {
		super();
		this.logId = logId;
		this.description = description;
		this.startDate = startDate;
		this.users = users;
	}

	public Logs(String description, String startDate, Users users) {
		super();
		this.description = description;
		this.startDate = startDate;
		this.users = users;
	}

	public int getLogId() {
		return logId;
	}

	public void setLogId(int logId) {
		this.logId = logId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	@Override
	public String toString() {
		return "Logs [logId=" + logId + ", description=" + description + ", startDate=" + startDate + ", users=" + users
				+ "]";
	}

}
