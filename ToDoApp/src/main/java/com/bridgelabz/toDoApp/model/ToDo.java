package com.bridgelabz.toDoApp.model;

import java.io.Serializable;
import java.util.Date;

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
	@Column(name="Title")
	private String title;
	@Column(name="Descriptioin")
	private String description;
	@Column(name="NoteColor")
	private String notecolor;
	@Column(name="Reminder")
	private Date reminder;
	@Column(name="Archive")
	private boolean archive;
	@Column(name="Trash")
	private boolean trash;

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
	public String getNotecolor() {
		return notecolor;
	}

	public void setNotecolor(String notecolor) {
		this.notecolor = notecolor;
	}
	

	public Date getReminder() {
		return reminder;
	}

	public void setReminder(Date reminder) {
		this.reminder = reminder;
	}
	

	public boolean isArchive() {
		return archive;
	}

	public void setArchive(boolean archive) {
		this.archive = archive;
	}

	public boolean isTrash() {
		return trash;
	}

	public void setTrash(boolean trash) {
		this.trash = trash;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", description=" + description + ", notecolor=" + notecolor
				+ ", reminder=" + reminder + ", archive=" + archive + ", trash=" + trash + ", user=" + user + "]";
	}

	
}
