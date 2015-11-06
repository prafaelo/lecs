package com.prafaelo.lecs.collection;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import com.prafaelo.lecs.LibraryUtil;
import com.prafaelo.lecs.system.user.DefaultUser;
import com.prafaelo.lecs.system.user.LibraryUser;

public class Book {
	
	private final int code;
	
	private static int count=0;
	
	private static final Map<Genre, Collection<Book>> books = new HashMap<Genre, Collection<Book>>();
	
	private final Collection<BookCopy> copies = new ArrayList<BookCopy>();
		
	private Genre genre;
	
	private String title;
	
	private Collection<String> authors;
	
	private int year;
	
	private String local;
	
	private String observation;
	
	private LibraryUser reserveUser;
		
	public Book(int qtyCopies, Genre genre, String title, Collection<String> authors, int year, String local, String observations) {
		setGenre(genre);
		setTitle(title);
		setAuthors(authors);
		setYear(year);
		setLocal(local);
		setObservation(observations);
		this.code = ++count;
		if(!insertInBooks()){
			System.err.println("(Error) This book is already created!");
			return;
		}
		setInitialCopies(qtyCopies);
	}
	
	public Book(){
		setGenreTyped();
		setTitleTyped();
		setAuthorsTyped();
		setYearTyped();
		setLocalTyped();
		setObservationTyped();
		this.code = ++count;
		if(!insertInBooks()){
			System.err.println("(Error) This book is already created!");
			return;
		}
		setInitialCopiesTyped();
		System.out.println("\nSuccessfully Registered!");
	}
		
	public static void updateTyped(){
		Book book = Book.getBookTyped();
		
		if(book != null){
			String isUpdate = LibraryUtil.getTypedValue("Do you want to update this book?");
			if(Boolean.parseBoolean(isUpdate)){
				
				StringBuffer updatedValues = new StringBuffer();
				
				boolean isUpdateGenre = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Genre?"));
				if(isUpdateGenre){
					book.setGenreTyped();
					updatedValues.append("|Genre=" + book.getGenre()+"|");
				}

				boolean isUpdateTitle = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Title?"));
				if(isUpdateTitle){
					book.setTitleTyped();
					updatedValues.append("|Title=" + book.getTitle()+"|");
				}

				boolean isUpdateAuthors = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Authors?"));
				if(isUpdateAuthors){
					book.setAuthorsTyped();
					updatedValues.append("|Authors="+book.getAuthors()+"|");
				}

				boolean isUpdateYear = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Year?"));
				if(isUpdateYear){
					book.setYearTyped();
					updatedValues.append("|Year="+book.getYear()+"|");
				}


				boolean isUpdateLocal = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Local?"));
				if(isUpdateLocal){
					book.setLocalTyped();
					updatedValues.append("|Local="+book.getLocal()+"|");
				}


				boolean isUpdateObservation = Boolean.parseBoolean(LibraryUtil.getTypedValue("Update Observation?"));
				if(isUpdateObservation){
					book.setObservationTyped();
					updatedValues.append("|Observation="+book.getObservation()+"|");
				}
				System.out.println("Successfully updated ["+updatedValues+"]");
				return;
			}			
		}
		
		System.out.println("(Error) This book was not updated!");		
	}
	
	public static Collection<Book> getBooksByGenreTyped(){
		Collection<Book> booksFound=new ArrayList<Book>();
		String genreTyped = LibraryUtil.getTypedValue("Enter genre:");
		
		Collection<Book> books = Book.getBooks().get(new Genre(genreTyped));
		if(books!=null){
			booksFound.addAll(books);
			if(booksFound.size()>1)
				System.out.println("Found " + booksFound.size() + ":");
			else
				System.out.println("Found " + booksFound.size() + ":");
				
			for(Book book : booksFound){				
				System.out.println(book);
			}
			return booksFound;
		}			
		System.err.println("(Error) Genre not found!");
		
		return booksFound;
	}
	
	public static Book getBookTyped(){
		Book book=null;
		Collection<Book> booksFound=new ArrayList<Book>();
		
		String genreTyped = LibraryUtil.getTypedValue("Enter genre:");
		String titleTyped = LibraryUtil.getTypedValue("Enter title:");
		
		if(genreTyped.equals("")){
			for(Collection<Book> books : Book.getBooks().values()){
				for(Book bookRead : books){
					if(bookRead.title.equalsIgnoreCase(titleTyped))
					booksFound.add(bookRead);					
				}
			}
		} else {
			Collection<Book> books= Book.getBooks().get(new Genre(genreTyped));
			if(books!=null){
				for(Book bookRead : books){
					if(bookRead.title.equalsIgnoreCase(titleTyped))
						booksFound.add(bookRead);
				}
				
			} else	
				System.err.println("(Error) Genre not found!");
		}
		
		if(booksFound.size()>1){
			System.out.println("Found " + booksFound.size() + ":");
			for(Book bookFound : booksFound){
				System.out.println(bookFound);
			}
			String code = LibraryUtil.getTypedValue("Enter book code:");
			for(Book bookFound : booksFound){
				if(bookFound.code == Integer.parseInt(code)) {
					book = bookFound;
					break;
				}
			}
		} else if(booksFound.size() > 0){
			System.out.println("Found " + booksFound.size() + ":");
			book = booksFound.iterator().next();	
			System.out.println(book);
		} else
			System.err.println("(Error) Book not found!");
		
		return book;
	}
	
	public void delete(){
		Book.books.get(getGenre()).remove(this);
	}
	
	public static void reserve(){
		Book book = Book.getBookTyped();
		
		if(book!=null){
			
			System.out.println("Found " + book.getCopies().size() + " copies:");
			for(BookCopy bookCopy : book.getCopies()){
				System.out.println(bookCopy);
			}
			
			if(book.getAvailableCopiesSize()>0){
				System.err.println("(Error) It is not possible to reserve! Copies available.");
				return;
			}
			
			if(book.getReserveUser()!=null){
				System.err.println("(Error) It is already reserved: " + book.getReserveUser());
				return;
			}
			
			int libraryCode = Integer.parseInt(LibraryUtil.getTypedValue("Enter library code:"));
			LibraryUser libraryUser = LibraryUser.LIBRARYUSERS.get(libraryCode);
			
			if(libraryUser==null){
				System.out.println("User not found! Register:");
				libraryUser = new DefaultUser();
			}
			
			book.setReserveUser(libraryUser);
		}
	}
	
	public int getAvailableCopiesSize(){
		int countAvailableBookCopy=0;
		if(getCopies().size() > 1){
			for(BookCopy bookCopy : getCopies()){
				if(bookCopy.isAvailable()){
					countAvailableBookCopy++;
				}
			}			
		}
		
		if(countAvailableBookCopy>0){
			countAvailableBookCopy--;
		}
		
		if(getReserveUser()!=null){
			if(countAvailableBookCopy>0){
				countAvailableBookCopy--;
			}
		}	
		return countAvailableBookCopy;
	}
	
	public static void deleteBookCopyTyped(){
		
		Book book = Book.getBookTyped();
		
		if(book != null){
			
			if(book.getCopies().size()>0){
				
				System.out.println("Found " + book.getCopies().size() + " copies:");
				for(BookCopy bookCopy : book.getCopies()){
					System.out.println(bookCopy);
				}
				if(book.getAvailableCopiesSize()>0){
					String isDelete = LibraryUtil.getTypedValue("Do you want to delete this book Copy?");
					if(Boolean.parseBoolean(isDelete)){
						
						String codeBookCopy = LibraryUtil.getTypedValue("Enter book Copy code:");
						for(BookCopy bookCopy : book.getCopies()){
							if(bookCopy.getCode() == Integer.parseInt(codeBookCopy)){
								if(!bookCopy.delete()){
									System.err.println("(Error) This book copy was not deleted!");								
								}
								break;
							}
						}
					}				
				} else {
					System.err.println("(Error) There is no copies available!");
				}
			} else {
				System.err.println("(Error) Book Copy not found!");				
			}
			
		}
	}
	
	private boolean insertInBooks(){
		Collection<Book> booksByGenre = Book.getBooks().get(getGenre());
		if(booksByGenre != null){
			if(booksByGenre.contains(this)){
				return false;
			} else {
				booksByGenre.add(this);
			}
		} else {
			booksByGenre = new ArrayList<Book>();
			booksByGenre.add(this);
			Book.getBooks().put(getGenre(), booksByGenre);
		}
		return true;
	}
	
	public static Collection<Book> getAllBooks(){
		Collection<Book> booksFound = new ArrayList<Book>();
		System.out.println("Found " + Book.getBooks().size() + ":");
		for (Collection <Book> books : Book.getBooks().values()){
			for(Book book : books){
				booksFound.add(book);
				System.out.println(book);
			}
		}
		return booksFound;
	}
	
	public static void listAllBooks(){
		Book.getAllBooks();
	}
	
	public static void listBookByTitle(){
		Book.getBookTyped();
	}
	
	public static void listBookByGenreTyped(){
		Book.getBooksByGenreTyped();
	}
	
	public void setGenre(Genre genre) {
		this.genre = genre;
	}
	
	public void setGenreTyped(){
		String genreName = LibraryUtil.getTypedValue("Enter genre:");
		Genre genre = new Genre(genreName);
		setGenre(genre);
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	public void setTitleTyped(){
		String title = LibraryUtil.getTypedValue("Enter title:");
		setTitle(title);
	}

	public Collection<String> getAuthors() {
		return authors;
	}

	public void setAuthors(Collection<String> authors) {
		this.authors = authors;
	}
	
	public void setAuthorsTyped(){
		Collection<String> authors= new ArrayList<String>();
		boolean hasAnotherauthors=false;
		do {
			String author = LibraryUtil.getTypedValue("Enter author:");
			authors.add(author);
			hasAnotherauthors = Boolean.parseBoolean(LibraryUtil.getTypedValue("Another authors?"));			
		} while(hasAnotherauthors);
		setAuthors(authors);
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}
	
	public void setYearTyped(){
		int year = Integer.parseInt(LibraryUtil.getTypedValue("Enter year:"));
		setYear(year);
	}

	public String getObservation() {
		return observation;
	}

	public void setObservation(String observation) {
		this.observation = observation;
	}
	
	public void setObservationTyped(){
		String observation = LibraryUtil.getTypedValue("Enter observation:");
		setObservation(observation);
	}

	public String getLocal() {
		return local;
	}
	
	public void setLocal(String local) {
		this.local = local;
	}
	
	public void setLocalTyped(){
		String local = LibraryUtil.getTypedValue("Enter local:");
		setLocal(local);
	}

	public Genre getGenre() {
		return genre;
	}
	
	public static Map<Genre, Collection<Book>> getBooks() {
		return books;
	}
	
	public Collection<BookCopy> getCopies() {
		return copies;
	}
	
	public void setInitialCopiesTyped(){
		int qtyCopies = Integer.parseInt(LibraryUtil.getTypedValue("Enter quantity copies:"));		
		setInitialCopies(qtyCopies);
	}

	public void setInitialCopies(int qtyCopies){
		for(int i = 0; i< qtyCopies; i++){
			new BookCopy(this);
		}
	}
	public int getCode() {
		return code;
	}

	public static int getCount() {
		return count;
	}

	public LibraryUser getReserveUser() {
		return reserveUser;
	}

	public void setReserveUser(LibraryUser reserveUser) {
		this.reserveUser = reserveUser;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Book [code=").append(code)
		.append(", genre=").append(genre)
		.append(", title=").append(title)
		.append(", copies=").append(copies.size())
		.append(", availableCopies=").append(getAvailableCopiesSize())
		.append(", authors=").append(authors)
		.append(", year=").append(year)
		.append(", local=").append(local)
		.append(", observation=")
		.append(observation).append("]")
		.append(", reserveUser=")
		.append(reserveUser).append("]");
		return builder.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((genre == null) ? 0 : genre.hashCode());
		result = prime * result + ((title == null) ? 0 : title.toLowerCase().hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Book other = (Book) obj;
		if (genre == null) {
			if (other.genre != null)
				return false;
		} else if (!genre.equals(other.genre))
			return false;
		if (title == null) {
			if (other.title != null)
				return false;
		} else if (!title.equalsIgnoreCase(other.title))
			return false;
		return true;
	}
}
