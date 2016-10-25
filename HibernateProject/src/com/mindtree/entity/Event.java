/**
 * 
 */
package com.mindtree.entity;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

/**
 * @author Chetan Pujari
 *
 */
@Entity
@Table(name="EVENTS")
public class Event implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="EVENT_ID")
	private int eventId;
	
	@Column(name="EVENT_NAME")
	private String eventName;
	
	@Column(name="EVENT_DESC")
	private String description;
	
	@ManyToMany
	@Cascade(CascadeType.ALL)
	@JoinTable(
			name="EVENT_EMPLOYEE",
			joinColumns={ @JoinColumn(name="EVENTID") },
			inverseJoinColumns={ @JoinColumn(name="MID") }
	)
	private Set<Employee> employees = new HashSet<Employee>();

	public Event() {
		
	}

	public int getId() {
		return eventId;
	}

	public void setId(int id) {
		this.eventId = id;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getEventDescription() {
		return description;
	}

	public void setEventDescription(String eventDescription) {
		this.description = eventDescription;
	}

	public Set<Employee> getEmployees() {
		return employees;
	}

	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
	}
}
