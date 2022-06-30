package com.cybage.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Batches {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int batchId;

	@Column
	private String name;

	@Column
	private String startTime;

	@Column
	private String endTime;

	@Column
	@NotNull
	private int size;

	@Column
	private String description;

	@Column
	private double fees;

	@Column
	private String isActive;

	@ManyToOne
	@JoinColumn(name = "sportId", nullable = false)
	@JsonIgnoreProperties("batches")
	private Sports sports;

	@OneToMany(mappedBy = "batches", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("batches")
	@JsonIgnore
	private List<Membership> memberships;

	public Batches() {

	}

	public Batches(int batchId, String name, String startTime, String endTime, @NotNull @NotBlank int size,
			String description, @NotEmpty double fees, String isActive, Sports sports, List<Membership> memberships) {
		super();
		this.batchId = batchId;
		this.name = name;
		this.startTime = startTime;
		this.endTime = endTime;
		this.size = size;
		this.description = description;
		this.fees = fees;
		this.isActive = isActive;
		this.sports = sports;
		this.memberships = memberships;
	}

	public int getBatchId() {
		return batchId;
	}

	public void setBatchId(int batchId) {
		this.batchId = batchId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public double getFees() {
		return fees;
	}

	public void setFees(double fees) {
		this.fees = fees;
	}

	public String isActive() {
		return isActive;
	}

	public void setActive(String isActive) {
		this.isActive = isActive;
	}

	public Sports getSports() {
		return sports;
	}

	public void setSports(Sports sports) {
		this.sports = sports;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	@Override
	public String toString() {
		return "Batches [batchId=" + batchId + ", name=" + name + ", startTime=" + startTime + ", endTime=" + endTime
				+ ", size=" + size + ", description=" + description + ", fees=" + fees + ", isActive=" + isActive
				+ ", sports=" + sports + "]";
	}

}
