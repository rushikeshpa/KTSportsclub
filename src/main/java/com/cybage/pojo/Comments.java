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
public class Comments {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int id;

	@Column
	private String comments;

	@ManyToOne
	@JoinColumn(name = "userId")
	@JsonIgnoreProperties("comments")
	private Users users;

	@ManyToOne
	@JoinColumn(name = "offerId")
	@JsonIgnoreProperties("comments")
	private Offers offers;

}
