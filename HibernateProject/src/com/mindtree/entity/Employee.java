package com.mindtree.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * @author Chetan Pujari
 *
 */
@Entity
@Table(name="EMPLOYEES")
public class Employee implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name="MID")
	private String id;
	
	@Column(name="EMPLOYEE_NAME")
	private String empName;
	
	@Column(name="JOIN_DATE")
	@Temporal(TemporalType.DATE)
	private Date joinDate;
	
	@Column(name="EMPLOYEE_EMAIL_ID")
	private String emailId;
	
	@ManyToMany(mappedBy="employees")
	private Set<Event> events = new HashSet<Event>();

	public Employee() {
	
	}

	public String getEmpId() {
		return id;
	}

	public void setEmpId(String empId) {
		this.id = empId;
	}

	public String getName() {
		return empName;
	}

	public void setName(String name) {
		this.empName = name;
	}

	public Date getJoinDate() {
		return joinDate;
	}

	public void setJoinDate(Date date) {
		this.joinDate = date;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Set<Event> getEvents() {
		return events;
	}

	public void setEvents(Set<Event> events) {
		this.events = events;
	}

}
