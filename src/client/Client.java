package client;

import java.util.ArrayList;
import java.util.List;

import book.Book;

public class Client {
	
	private static List<Book> basket = new ArrayList<Book>();
	private static String username;
	
	public static void addBook(Book b) {
		basket.add(b);
	}
	
	public static void removeBook(Book b) {
		basket.remove(b);
	}
	
	public static List<Book> getBasket() {
		return basket;
	}
	
	public static void setUsername(String user) {
		username=user;
	}
	
	public static String getUsername() {
		return username;
	}
	
	
}
