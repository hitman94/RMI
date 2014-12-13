package book;


public class Book {
	private String title;
	private String author;
	private Long isbn;
	
	public Book() {
		
	}
	
	public Book(String title,String author, Long isbn) {
		super();
		this.title=title;
		this.author=author;
		this.isbn=isbn;
	}

	
	public String getTitle() {
			return this.title;
	}
	public String getAuthor() {
			return this.author;
	}	
	public Long getISBN() {
		return this.isbn;
	}
}
