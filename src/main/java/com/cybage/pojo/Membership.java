package com.cybage.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.ManyToAny;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Membership {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int membershipId;

	@Column
	private String startDate;

	@Column
	private String endDate;

	@Column
	private String status;

	@Column
	private double offerPrice;

	@ManyToOne
	@JsonIgnoreProperties("memberships")
	@JoinColumn(name = "userId")
	private Users users;

	@ManyToOne
	@JsonIgnoreProperties("memberships")
	@JoinColumn(name = "batchId")
	private Batches batches;

	
	@ManyToOne
	@JsonIgnoreProperties("memberships")
	@JoinColumn(name = "offerId")
	private Offers offers;

	public Membership() {
		// TODO Auto-generated constructor stub
	}

	public Membership(int membershipId, String startDate, String endDate, String status, double offerPrice, Users users,
			Batches batches, Offers offers) {
		super();
		this.membershipId = membershipId;
		this.startDate = startDate;
		this.endDate = endDate;
		this.status = status;
		this.offerPrice = offerPrice;
		this.users = users;
		this.batches = batches;
		this.offers = offers;
	}

	public int getMembershipId() {
		return membershipId;
	}

	public void setMembershipId(int membershipId) {
		this.membershipId = membershipId;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public double getOfferPrice() {
		return offerPrice;
	}

	public void setOfferPrice(double offerPrice) {
		this.offerPrice = offerPrice;
	}

	public Users getUsers() {
		return users;
	}

	public void setUsers(Users users) {
		this.users = users;
	}

	public Batches getBatches() {
		return batches;
	}

	public void setBatches(Batches batches) {
		this.batches = batches;
	}

	public Offers getOffers() {
		return offers;
	}

	public void setOffers(Offers offers) {
		this.offers = offers;
	}

	@Override
	public String toString() {
		return "Membership [membershipId=" + membershipId + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", status=" + status + ", offerPrice=" + offerPrice + ", users=" + users + ", batches=" + batches
				+ ", offers=" + offers + "]";
	}

}
