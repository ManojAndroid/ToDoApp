package com.bridgelabz.toDoApp.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Set;

import javax.persistence.*;
import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "User_Table")
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name="userid")
	private int id;
	private String firstname;
	private String lastname;
	private String email;
	private String mobile;
	private String password;
	private String useremailtoken;
	private boolean status;
	@Lob
	@Column(columnDefinition="mediumblob")
	private String profile;
	

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	 
	public String  getProfile() {
		return profile;
	}

	public void setProfile(String string) {
		this.profile = string;
	}
	

	public String getUseremailtoken() {
		return useremailtoken;
	}

	public void setUseremailtoken(String useremailtoken) {
		this.useremailtoken = useremailtoken;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", firstname=" + firstname + ", lastname=" + lastname + ", email=" + email
				+ ", mobile=" + mobile + ", password=" + password + ", useremailtoken=" + useremailtoken + ", status="
				+ status + ", profile=" + profile + "]";
	}

	

}
