package com.prafaelo.lecs;

import java.util.Scanner;

public class LibraryUtil {

	private static Scanner scan = new Scanner(System.in);
	
	private LibraryUtil() {
	}
	
	public static String getTypedValue(String question){
		System.out.println(question);
		String msgRead = scan.nextLine();

		return msgRead;
	}
}
