package book;


public class Book {
	private String title;
	private String author;
	private Long isbn;
	private Double price;
	public Book() {
		
	}
	
	public Book(String title,String author, Long isbn,Double price) {
		super();
		this.title=title;
		this.author=author;
		this.isbn=isbn;
		this.price=price;
	}

	
	public Double getPrice() {
		return price;
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
