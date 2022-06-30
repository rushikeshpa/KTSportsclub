package com.cybage.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Events {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int eventId;

	@Column
	private String name;

	@Column
	private String startDate;

	@Column
	private String endDate;

	@Column
	private String description;

	@Column
	private String isActive;

	@OneToMany(mappedBy = "events", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("events")
	private List<Users> users;

}
