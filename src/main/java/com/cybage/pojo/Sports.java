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

import org.hibernate.validator.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Sports {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int sportId;

	@Column
	@NotBlank
	private String sportName;

	@Column
	private String description;
	@Column
	private String isActive;

	@OneToMany(mappedBy = "sports", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("sports")
	private List<Batches> batches;

	public Sports() {

	}

	public Sports(int sportId, @NotBlank String sportName, String isActive, List<Batches> batches) {
		super();
		this.sportId = sportId;
		this.sportName = sportName;
		this.isActive = isActive;
		this.batches = batches;
	}

	public Sports(@NotBlank String sportName, String isActive) {
		super();
		this.sportName = sportName;
		this.isActive = isActive;
	}

	public int getSportId() {
		return sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public String getSportName() {
		return sportName;
	}

	public void setSportName(String sportName) {
		this.sportName = sportName;
	}

	public String getIsActive() {
		return isActive;
	}

	public void setIsActive(String isActive) {
		this.isActive = isActive;
	}

	public List<Batches> getBatches() {
		return batches;
	}

	public void setBatches(List<Batches> batches) {
		this.batches = batches;
	}

	@Override
	public String toString() {
		return "Sports [sportId=" + sportId + ", sportName=" + sportName + ", isActive=" + isActive + ", batches="
				+ batches + "]";
	}

}
