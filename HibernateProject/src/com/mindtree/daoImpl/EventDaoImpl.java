package com.mindtree.daoImpl;

import java.util.Collection;
import java.util.logging.Logger;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.service.ServiceRegistryBuilder;

import com.mindtree.dao.EventDao;
import com.mindtree.entity.Employee;
import com.mindtree.entity.Event;
import com.mindtree.util.Utility;

/**
 * @author Chetan Pujari
 *
 */
public class EventDaoImpl implements EventDao {
	
	
	private final static Logger LOG = Logger.getLogger(EventDaoImpl.class.getName()); 
	
	/**
	 * 	return SessionFactory
	 */
	public SessionFactory getSessionFactoryInstance(){
		
		Configuration configuration = new Configuration();
		configuration.configure();
		ServiceRegistryBuilder srBuilder = new ServiceRegistryBuilder();
		srBuilder.applySettings(configuration.getProperties());
		ServiceRegistry serviceRegistry = srBuilder.buildServiceRegistry();
		
		return configuration.buildSessionFactory(serviceRegistry);
		
	}

	/**
	 * 	return Event
	 */
	public Event findEventById(Session session, int eventId) {

		Query query = session.createQuery("from Event e where e.id = :eventId");
		query.setParameter("eventId", eventId);
		
		Event event = (Event) query.uniqueResult();
		return event;
	}

	/**
	 * 	saves Event
	 */
	public void saveEvent(Session session) {
		
		Event event = new Event();
		
		System.out.println();
		LOG.info( "Enter the details below:");
		event.setEventName(Utility.getString("Please Enter Event Name: "));
		event.setEventDescription(Utility.getString("Please Enter Description: "));
		
		session.save(event);
		
	}

	/**
	 * 	return Employee
	 */
	public Employee findEmployeeById(Session session, String empId) {

		Query query = session.createQuery("from Employee e where e.empId = :employeeId");
		query.setParameter("employeeId", empId);
		
		Employee employee = (Employee) query.uniqueResult();
		
		return employee;
		
	}

	/**
	 * 	return Event Size
	 */
	public int getAllEvents(Session session) {

		Query query = session.createQuery("from Event");
		
		Collection<Event> events = query.list();

		if(events.size() > 0){
			LOG.info( "The list of available events are below:");
			for(Event event : events){
				LOG.info( "EVENT ID: " + event.getId());
				LOG.info( "EVENT Name: " + event.getEventName());
				LOG.info( "EVENT Description: " + event.getEventDescription());
			}
		}else{
			LOG.info( "There are no events available");
		}
		
		return events.size();
		
	}

	/**
	 * 	saves Employee
	 */
	public Employee saveEmployee(Session session, String empId) {

		Employee employee = findEmployeeById(session, empId);
		
		if(employee == null){
			Employee newEmployee = new Employee();
			
			newEmployee.setEmpId(empId);
			newEmployee.setName(Utility.getString("Please Enter Name: "));
			newEmployee.setJoinDate(Utility.getDate("Please Enter Date of Joining (MM/DD/YYYY): "));
			newEmployee.setEmailId(Utility.getString("Please Enter Email Id: "));
			
			session.save(newEmployee);
			
			employee = newEmployee;
			
		}
		
		return employee;
		
	}

	/**
	 * 	return Event Size
	 */
	public void registerEmployeeForEvent(Session session, int userInputEventId, String userInputMID) {

		Employee employee = findEmployeeById(session, userInputMID);
		Event event = null;
		if(employee != null){
			event = findEventById(session, userInputEventId);
			
			if(event != null){
				event.getEmployees().add(employee);
				employee.getEvents().add(event);
				
				LOG.info( employee.getName() + " has been registered for " + event.getEventName());
			}
		}
	}

	/**
	 * 	return All Employees
	 */
	public void getAllEmployees(Session session) {

		Query query = session.createQuery("from Employee");

		Collection<Employee> employees = query.list();

		if(employees.size() > 0){
			for(Employee employee : employees){
				LOG.info( "MID: " + employee.getEmpId());
				LOG.info( "Name: " + employee.getName());
				LOG.info( "Date Of Joining: " + Utility.formatDateToString(employee.getJoinDate()));
				LOG.info( "Email Id: " + employee.getEmailId());

			}
		}else{
			LOG.info( "\nThere are no Employee records");
		}

	}

}
