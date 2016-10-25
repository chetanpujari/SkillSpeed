package com.mindtree.main;

import java.util.logging.Logger;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import com.mindtree.dao.EventDao;
import com.mindtree.daoImpl.EventDaoImpl;
import com.mindtree.util.Utility;

public class EventRegistrationMain {

	SessionFactory sessionFactory;
	private final static Logger LOG = Logger.getLogger(EventRegistrationMain.class.getName());

	public void setup() {

		EventDao eventDAO = new EventDaoImpl();
		sessionFactory = eventDAO.getSessionFactoryInstance();
	}

	private static void registerEmployee(Session session) {

		int userInput = 0;
		String userEnteredEmpId = null;

		EventDao eventDAO = new EventDaoImpl();

		int eventSize = eventDAO.getAllEvents(session);

		eventDAO.getAllEmployees(session);

		if (eventSize > 0) {
			userInput = Utility.getInt("Please Enter the Event Id:");
			userEnteredEmpId = Utility.getString("Please Enter the Employee ID: ");
			eventDAO.registerEmployeeForEvent(session, userInput, userEnteredEmpId);
		}

	}

	private static void getListOfEmployee(Session session) {

		EventDao eventDao = new EventDaoImpl();

		eventDao.getAllEmployees(session);

	}

	private static void addEvent(Session session) {

		EventDao eventManagerDAO = new EventDaoImpl();

		eventManagerDAO.saveEvent(session);

	}

	private static void addEmployee(Session session) {

		EventDao eventManagerDAO = new EventDaoImpl();
		LOG.info("Please enter the Employee details:");
		String empId = Utility.getString("Please Enter Mid: ");
		eventManagerDAO.saveEmployee(session, empId);

	}

	private static int showMenu() {

		System.out.println("\nEVENT MANAGEMENT SYSTEM");
		System.out.println("1. Register employee for Events");
		System.out.println("2. Display All Employees");
		System.out.println("3. Add an Event");
		System.out.println("4. Add an Employee");

		return Utility.getInt("Please choose the action: ");

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		EventRegistrationMain eventMain = new EventRegistrationMain();
		eventMain.setup();

		int input = 0;
		String continueKey = null;

		do {

			input = showMenu();

			Session session = eventMain.sessionFactory.openSession();
			Transaction transaction = session.beginTransaction();

			switch (input) {
			case 1:
				registerEmployee(session);
				break;

			case 2:
				getListOfEmployee(session);
				break;

			case 3:
				addEvent(session);
				break;

			case 4:
				addEmployee(session);
				break;

			default:
				System.out.println("Wrong option selected");
				break;
			}

			transaction.commit();
			session.close();

			continueKey = Utility.getString("\nDo you want to perform any more actions (Yes/No): ");

		} while (continueKey.equalsIgnoreCase("Y") || continueKey.equalsIgnoreCase("Yes"));
	}
}
