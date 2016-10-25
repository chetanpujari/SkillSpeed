package com.mindtree.dao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.mindtree.entity.Employee;
import com.mindtree.entity.Event;

/**
 * @author Chetan Pujari
 */
public interface EventDao {
	
	public SessionFactory getSessionFactoryInstance();

	public Event findEventById(Session session, int eventId);
	
	public void saveEvent(Session session);
	
	public int getAllEvents(Session session);
	
	public Employee findEmployeeById(Session session, String empId);
	
	public Employee saveEmployee(Session session, String empId);
	
	public void registerEmployeeForEvent(Session session, int eventId, String empId);
	
	public void getAllEmployees(Session session);
}
