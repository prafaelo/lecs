package com.prafaelo.lecs.collection;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;

import com.prafaelo.lecs.LibraryUtil;
import com.prafaelo.lecs.system.user.DefaultUser;
import com.prafaelo.lecs.system.user.LibraryUser;

public class BookLending {

	private final LocalDate DATE = LocalDate.now();
	
	private LocalDate returnDate = LocalDate.now().plusDays(15);
	
	private LocalDate deliveryDate;
	
	private final BookCopy BOOKCOPY;
	
	private LibraryUser libraryUser;
	
	public BookLending(BookCopy bookCopy) {
		setLibraryUserTyped();
		BOOKCOPY=bookCopy;
		System.out.println("Sucessfully lended!");
	}
	
	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate() {
		this.returnDate = LocalDate.now().plusDays(15);
	}
	
	public void setReturnDate(int days){
		this.returnDate = this.getReturnDate().plusDays(days);
	}
	
	public String renew(int days){
		
		Book book = getBookCopy().getBook();
		
		if(book.getAvailableCopiesSize() > 0 || book.getReserveUser() == null){
			setReturnDate(days);
			return new String("Book copy renovated!\n"
					        + "Book: " + book.getTitle()
					);
		}
		return new String("Book copy is already reserved!\n"
				        + "Book: " + book.getTitle()
				        );
	}

	public LocalDate getDATE() {
		return DATE;
	}

	public BookCopy getBookCopy() {
		return BOOKCOPY;
	}

	public LocalDate getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(LocalDate deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

	public LibraryUser getLibraryUser() {
		return libraryUser;
	}

	private void setLibraryUser(LibraryUser libraryUser) {
		this.libraryUser = libraryUser;
	}
	
	public void setLibraryUserTyped(){
		String libraryCode = LibraryUtil.getTypedValue("Enter library code:");
		LibraryUser libraryUser = LibraryUser.LIBRARYUSERS.get(Integer.parseInt(libraryCode));
		if(libraryUser == null){
			System.out.println("Library User not register. Register:");
			libraryUser = new DefaultUser();
		}
		setLibraryUser(libraryUser);
	}
	
	public static ArrayList<BookLending> getBookLendingByLibraryCode(int libraryCode) {
		
		ArrayList<BookLending> bookLendings= new ArrayList<BookLending>(); 
		
		for(Collection<Book> books : Book.getBooks().values()){
			for(Book book : books){
				for(BookCopy bookCopy : book.getCopies()){
					if(bookCopy.getLended() != null) {
						BookLending bookLending = bookCopy.getLended();
						if(bookLending.getLibraryUser().getLibraryCode() == libraryCode){
							bookLendings.add(bookLending);
						}						
					}
				}
			}
		}
		
		
		return bookLendings;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookLending [DATE=").append(DATE).append(", returnDate=").append(returnDate)
				.append(", deliveryDate=").append(deliveryDate).append(", libraryUser=").append(libraryUser)
				.append("]");
		return builder.toString();
	}
}
