package com.cybage.pojo;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
 
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table
public class Users implements UserDetails {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column
	private int userId;

	@Column
	private String name;

	@Column(unique = true)
	@Email
	private String email;

	@Column
	@NotNull
	@NotBlank
	private String password;

	@Column
	private String gender;

//	@Column
//	private String role;

	//user many roles;
		@OneToMany(cascade=CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "user")
		@JsonIgnore
		private Set<UserRole>userRoles=new HashSet<>();
		
	
	@Column
	private boolean isActive=true;

	@Column
	private int count;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("users")
	@JsonIgnore
	private List<Membership> memberships;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("users")
	private List<Logs> logs;

	@OneToMany(mappedBy = "users", cascade = CascadeType.ALL)
	@JsonIgnoreProperties("users")
	private List<Comments> comments;
	
	@ManyToOne
	@JoinColumn(name = "eventId")
	@JsonIgnoreProperties("users")
	private Events events;

	public Users() {

	}

	 
	 

	public Users(int userId, String name, @Email String email, @NotNull @NotBlank String password, String gender,
			Set<UserRole> userRoles, boolean isActive, int count, List<Membership> memberships, List<Logs> logs,
			List<Comments> comments) {
		super();
		this.userId = userId;
		this.name = name;
		this.email = email;
		this.password = password;
		this.gender = gender;
		this.userRoles = userRoles;
		this.isActive = isActive;
		this.count = count;
		this.memberships = memberships;
		this.logs = logs;
		this.comments = comments;
	}




	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

 

	public Set<UserRole> getUserRoles() {
		return userRoles;
	}

	public void setUserRoles(Set<UserRole> userRoles) {
		this.userRoles = userRoles;
	}

	public boolean getIsActive() {
		return isActive;
	}

	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<Membership> getMemberships() {
		return memberships;
	}

	public void setMemberships(List<Membership> memberships) {
		this.memberships = memberships;
	}

	 

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", name=" + name + ", email=" + email + ", password=" + password
				+ ", gender=" + gender + ", userRoles=" + userRoles + ", isActive=" + isActive + ", count=" + count
				+ ", memberships=" + memberships + ", logs=" + logs + ", comments=" + comments + "]";
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		Set<Authority> set = new HashSet<>();
		this.userRoles.forEach(userRole->{
			set.add(new Authority(userRole.getRole().getRoleName()));
		});
		return set;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return this.name;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		if(this.getCount()<=2)
		{return true;}
		else
		{
			return false;
		}
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
	
	
}
