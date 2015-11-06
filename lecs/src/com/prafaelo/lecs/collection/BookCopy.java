package com.prafaelo.lecs.collection;

import java.time.LocalDate;

import com.prafaelo.lecs.LibraryUtil;
import com.prafaelo.lecs.system.user.LibraryUser;

public class BookCopy {

	private static int count=0;
	
	private Book book;
	
	private final int code;
	
	private BookLending lent;

	public BookCopy(Book book) {
		setBook(book);
		this.code=++count;
	}
	
	public boolean delete(){
		if(book.getCopies().size()>1){
			if(lent == null || lent.getDeliveryDate() != null && !lent.getDeliveryDate().isBefore(LocalDate.now())){
				book.getCopies().remove(this);
				System.out.println("Successfully deleted!");
				return true;
			}
			System.err.println("(Error) It is already lent!");
			return false;
		}
		System.err.println("(Error) There is only one copy!");
		return false;	
	}
	
	public boolean isAvailable(){
		if(book.getCopies().size()>1){
			if(lent == null || lent.getDeliveryDate() != null && !lent.getDeliveryDate().isBefore(LocalDate.now())){
				return true;
			}
		}		
		return false;
	}
	
	public static void lend(){

		Book book = Book.getBookTyped();
		
		if(book!=null){
			
			System.out.println("Found " + book.getCopies().size() + " copies:");
			for(BookCopy bookCopy : book.getCopies()){
				System.out.println(bookCopy);
			}
			if(book.getAvailableCopiesSize()>0){
				
				for(BookCopy bookCopy : book.getCopies()){
					if(bookCopy.lent == null || bookCopy.lent.getDeliveryDate() != null && !bookCopy.lent.getDeliveryDate().isBefore(LocalDate.now())){
						bookCopy.setLended(new BookLending(bookCopy));
						return;
					}
				}
				System.err.println("(Error) It is already lent!");
				return;				
				
			}
			System.err.println("(Error) There is only one copy!");		
		}
	}

	public static void renewLendingTyped(){
		Book book = Book.getBookTyped();
		
		if(book!=null){
			System.out.println("Found " + book.getCopies().size() + " copies:");
			for(BookCopy bookCopy : book.getCopies()){
				System.out.println(bookCopy);
			}
			if(book.getAvailableCopiesSize() >0){
				int libraryCode = Integer.parseInt(LibraryUtil.getTypedValue("Enter library code:"));
				LibraryUser libraryUser = LibraryUser.LIBRARYUSERS.get(libraryCode);
				
				if(libraryUser!=null){
					for(BookCopy bookCopy : book.getCopies()){
						if(!bookCopy.isAvailable()){
							
							BookLending bookLending = bookCopy.getLended();
							if(bookLending!= null){
								if(bookLending.getLibraryUser().equals(libraryUser)){									
									bookCopy.getLended().setReturnDate();
									System.out.println("Book copy renovated!");
									return;
								}
							}
						}
					}
					System.err.println("(Error) There is no lending!");
					
				} else {
					System.err.println("(Error) User not found!");
				}
			} else {
				System.err.println("(Error) There is no copies available!");
			}
		}
		System.err.println("(Error) The book was not renovated!");
	}
	
	public Book getBook() {
		return book;
	}

	private void setBook(Book book) {
		book.getCopies().add(this);
		this.book = book;
	}

	public int getCode() {
		return code;
	}
	
	public BookLending getLended() {
		return lent;
	}

	public void setLended(BookLending lended) {
		this.lent = lended;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("BookCopy [code=").append(code)
		.append(", Genre=").append(book.getGenre())
		.append(", Title=").append(book.getTitle())
		.append(", lended=").append(lent).append("]");
		return builder.toString();
	}
}
