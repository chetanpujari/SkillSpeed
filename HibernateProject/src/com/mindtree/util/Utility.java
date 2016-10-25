package com.mindtree.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.mindtree.daoImpl.EventDaoImpl;

public class Utility {

	private final static Logger LOG = Logger.getLogger(EventDaoImpl.class.getName()); 
		
	public static String getString(String message) {

		Scanner scanner= new Scanner(System.in);
		LOG.info( message);
		return scanner.nextLine();

	}

	public static int getInt(String message){

		Scanner scanner= new Scanner(System.in);
		LOG.info( message);
		int n = scanner.nextInt();
		return n;

	}

	public static Date getDate(String message){

		Scanner scanner= new Scanner(System.in);
		LOG.info( message);
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");
		Date date = new Date();

		try {
			date = sdf.parse(scanner.nextLine());
		} catch (ParseException e) {
			LOG.log(Level.WARNING, "Exception message = " + e.getMessage());
		}

		return date;

	}
	
	public static String formatDateToString(Date date){
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MMM-yyyy");
		return sdf.format(date);
		
	}

}
