package book;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

import sellingBook.Book;

public class BookFilter extends ViewerFilter {
	
	private String author = "";
	private String title = "";

	public void setAuthorFilter(String author) {
		if(author==null)
			this.author="";
		this.author=author;
	}
	
	public void setTitleFilter(String title) {
		if(title==null)
			this.title="";
		this.title=title;
	}
	
	@Override
	public boolean select(Viewer arg0, Object arg1, Object arg2) {
		Book book =(Book)arg2;
		if(book.getTitle().toLowerCase().contains(title.toLowerCase()) && book.getAuthor().toLowerCase().contains(author.toLowerCase()))
			return true;
		return false;
	}

}
