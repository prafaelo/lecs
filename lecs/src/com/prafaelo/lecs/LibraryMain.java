package com.prafaelo.lecs;

import com.prafaelo.lecs.collection.Book;
import com.prafaelo.lecs.collection.BookCopy;
import com.prafaelo.lecs.system.auth.AuthUtil;
import com.prafaelo.lecs.system.auth.Authenticatable;
import com.prafaelo.lecs.system.user.Administrator;
import com.prafaelo.lecs.system.user.Anonymous;
import com.prafaelo.lecs.system.user.Librarian;
import com.prafaelo.lecs.ui.Menu;

public class LibraryMain {

	public static void main(String[] args) throws InterruptedException {
		
		new Administrator();
		
		Menu menu = new Anonymous().authenticate();
		
		while(true){
			
			int opcao=menu.show();
			
			if(opcao==0){
				System.out.println("Até logo!");
				System.exit(0);
			}
			
			if(opcao==1){
				Authenticatable authenticatable = AuthUtil.processLogin();
				menu = authenticatable.authenticate();
			}	
			
			if(opcao==2){
				Book.listAllBooks();
			}		
			
			if(opcao==3){
				Book.listBookByTitle();
			}			
			
			if(opcao==4){
				Book.listBookByGenreTyped();
			}			
			
			if(opcao==5){
				new Book();
			}			
			
			if(opcao==6){
				Book.updateTyped();
			}			
			
			if(opcao==7){
				Book.deleteBookCopyTyped();
			}			
			
			if(opcao==8){
				new Librarian();
			}			
			
			if(opcao==9){
				BookCopy.lend();
			}			
			
			if(opcao==10){
				Book.reserve();
			}
			
			if(opcao==11){
				BookCopy.renewLending();
			}
			
			System.out.println("\nVoltando ao Menu...\n");
			Thread.sleep(1000);
		}
	}

}
