package com.bridgelabz.toDoApp.model;

import java.io.Serializable;

import javax.persistence.*;

import org.hibernate.annotations.GenericGenerator;

/**
 * @author bridgeit
 *
 */
@Entity
@Table(name = "ToDoTask_Table")
public class ToDo implements Serializable {
	private static final long serialVersionUID = 1L;
	@Id
	@GenericGenerator(name = "gen", strategy = "increment")
	@GeneratedValue(generator = "gen")
	@Column(name="taskid")
	private int id;
	private String title;
	private String description;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() 
	{
		return description;
	}

	public void setDescription(String description) 
	{
		this.description = description;
	}
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "userid")
	private User user;
	
	public User getUser()
	{
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", description=" + description + ", user=" + user.getId() + "]";
	}

	
}
