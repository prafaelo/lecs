package com.prafaelo.lecs.ui;

import com.prafaelo.lecs.LibraryUtil;

public class LibrarianMenu implements Menu{

	@Override
	public int show() {
		System.out.println("\n===== menu ====");
		System.out.println("0 - Exit");
		System.out.println("1 - Logout -> Login");
		System.out.println("2 - List all books");
		System.out.println("3 - List books by title");
		System.out.println("4 - List books by genre");
		System.out.println("5 - Register book");
		System.out.println("6 - Update book");
		System.out.println("7 - Delete book copy");
		System.out.println("8 - Register new librarian account");
		System.out.println("9 - Lend book copy");
		System.out.println("10 - Reserve book");
		System.out.println("11 - Renew book copy");
		System.out.println("12 - Register new Library User");
		System.out.println("===============");
		
		int opcao=Integer.parseInt(LibraryUtil.getTypedValue("\nEnter your option: "));
		return opcao;
	}

}
