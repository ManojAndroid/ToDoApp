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
	@Lob
	@Column(name="Image")
	private String image;
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
	@Column(name="WebScrapingTitle")
	private String webscripingtitle;
	@Column(name="WebScrapingImage")
	private String webscripingimage;
	@Column(name="WebScrapingHost")
	private String webscripinghost;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}
	

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
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

	public String getWebscripingtitle() {
		return webscripingtitle;
	}

	public void setWebscripingtitle(String webscripingtitle) {
		this.webscripingtitle = webscripingtitle;
	}
	
	public String getWebscripingimage() {
		return webscripingimage;
	}

	public void setWebscripingimage(String webscripingimage) {
		this.webscripingimage = webscripingimage;
	}
	

	public String getWebscripinghost() {
		return webscripinghost;
	}

	public void setWebscripinghost(String webscripinghost) {
		this.webscripinghost = webscripinghost;
	}

	@Override
	public String toString() {
		return "ToDo [id=" + id + ", title=" + title + ", image=" + image + ", description=" + description
				+ ", notecolor=" + notecolor + ", reminder=" + reminder + ", archive=" + archive + ", trash=" + trash
				+ ", webscripingtitle=" + webscripingtitle + ", webscripingimage=" + webscripingimage
				+ ", webscripinghost=" + webscripinghost + ", user=" + user + "]";
	}


	
}
