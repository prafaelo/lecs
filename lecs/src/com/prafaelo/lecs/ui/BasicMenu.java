package com.prafaelo.lecs.ui;

import com.prafaelo.lecs.LibraryUtil;

public class BasicMenu implements Menu{

	@Override
	public int show() {
		System.out.println("\n===== menu ====");
		System.out.println("0 - Exit");
		System.out.println("1 - Login");
		System.out.println("2 - List all books");
		System.out.println("3 - List book by title");
		System.out.println("4 - List book by genre");
		System.out.println("===============");
		
		int opcao=Integer.parseInt(LibraryUtil.getTypedValue("Enter your option:"));
		return opcao;
	}

}
