package com.bridgelabz.toDoApp.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "Collaborator")
public class Collaborator implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Id
	@GenericGenerator(name="gen",strategy="increment")
	@GeneratedValue(generator = "gen")
	@Column(name="CollId")
	private int id;
	@ManyToOne(optional = false)
	@JoinColumn(name = "ownerId")
	private User owner;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShareWithId")
	private User sharewith;
	
	@ManyToOne(optional = false)
	@JoinColumn(name = "ShareNoteId")
	private ToDo sharenoteid;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public User getOwner() {
		return owner;
	}
	public void setOwner(User owner) {
		this.owner = owner;
	}
	public User getSharewith() {
		return sharewith;
	}
	public void setSharewith(User sharewith) {
		this.sharewith = sharewith;
	}
	public ToDo getSharenoteid() {
		return sharenoteid;
	}
	public void setSharenoteid(ToDo sharenoteid) {
		this.sharenoteid = sharenoteid;
	}
	@Override
	public String toString() {
		return "Collaborator [id=" + id + ", owner=" + owner + ", sharewith=" + sharewith + ", sharenoteid="
				+ sharenoteid + "]";
	}
	
}
