package com.prafaelo.lecs.collection;

import java.time.LocalDate;

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

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookLending [DATE=").append(DATE).append(", returnDate=").append(returnDate)
				.append(", deliveryDate=").append(deliveryDate).append(", libraryUser=").append(libraryUser)
				.append("]");
		return builder.toString();
	}
}
