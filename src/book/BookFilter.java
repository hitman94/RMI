package book;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerFilter;

public class BookFilter extends ViewerFilter {
	
	private String author = "";

	public void setAuthorFilter(String author) {
		if(author==null)
			this.author="";
		this.author=author;
	}
	
	@Override
	public boolean select(Viewer arg0, Object arg1, Object arg2) {
		Book book =(Book)arg2;
		if(book.getAuthor().toLowerCase().contains(author.toLowerCase()))
			return true;
		return false;
	}

}
