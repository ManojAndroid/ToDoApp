package com.bridgelabz.toDoApp.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
@Entity
@Table(name="Token_Table")
public class Token implements Serializable
{

   private static final long serialVersionUID = 1L;
   @Id
   @GenericGenerator(name="gen",strategy="increment")
   @GeneratedValue(generator="gen")
   @Column(name="tokenid")
    private int id;
    private String accesstoken;
    private String refreshtoken;
    private Date createdtime;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name="userid")
    private User user;
	public String getAccesstoken()
	{
		return accesstoken;
	}
	public void setAccesstoken(String accesstoken)
	{
		this.accesstoken = accesstoken;
	}
	public String getRefreshtoken()
	{
		return refreshtoken;
	}
	public void setRefreshtoken(String refreshtoken)
	{
		this.refreshtoken = refreshtoken;
	}
	public Date getCreatedtime() {
		return createdtime;
	}
	public void setCreatedtime(Date createdtime)
	{
		this.createdtime = createdtime;
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	@Override
	public String toString() 
	{
		return "Token [accesstoken=" + accesstoken + ", refreshtoken=" + refreshtoken + ", createdtime=" + createdtime
				+ "]";
	}
	
	
	
}
