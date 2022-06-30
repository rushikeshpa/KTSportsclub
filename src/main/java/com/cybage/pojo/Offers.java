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
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.aspectj.weaver.tools.Trace;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Offers {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int offerId;

	@Column
	@NotBlank
	private String name;

	@Column
	private String startDate;

	@Column
	private String endDate;

	@Column
	@NotNull
	private double discount;

	@Column
	private int likes;

	@Column
	private String isActive;

	@OneToMany(mappedBy = "offers", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("offers")
	@JsonIgnore
	private List<Membership> memberships;

	@OneToMany(mappedBy = "offers", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("offers")
	private List<Comments> comments;

	public Offers() {

	}

	public Offers(int offerId, @NotBlank String name, String startDate, String endDate,
			@NotNull @NotBlank double discount, int likes, String isActive, List<Membership> memberships) {
		super();
		this.offerId = offerId;
		this.name = name;
		this.startDate = startDate;
		this.endDate = endDate;
		this.discount = discount;
		this.likes = likes;
		this.isActive = isActive;
		this.memberships = memberships;
	}

	public int getOfferId() {
		return offerId;
	}

	public void setOfferId(int offerId) {
		this.offerId = offerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public double getDiscount() {
		return discount;
	}

	public void setDiscount(double discount) {
		this.discount = discount;
	}

	public String isActive() {
		return isActive;
	}

	public void setActive(String isActive) {
		this.isActive = isActive;
	}

	public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	@Override
	public String toString() {
		return "Offers [offerId=" + offerId + ", name=" + name + ", startDate=" + startDate + ", endDate=" + endDate
				+ ", discount=" + discount + ", likes=" + likes + ", isActive=" + isActive + "]";
	}

}
