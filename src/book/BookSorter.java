package book;

import org.eclipse.jface.viewers.Viewer;
import org.eclipse.jface.viewers.ViewerComparator;

public class BookSorter extends ViewerComparator{
	
	private int index =5;
	private final Viewer v;
	public BookSorter(Viewer v) {
		this.v=v;
	}
	
	public void setSorterIndex(int index) {
		this.index=index;
		v.refresh();
	}
	
	@Override
	public int compare(Viewer viewer, Object e1, Object e2) {
		Book b1 =(Book)e1;
		Book b2=(Book)e2;
		switch(index) {
		case 0:
			Long toreturn =  (b1.getISBN()-b2.getISBN());
			return toreturn.intValue();
	
			
		case 1:
			return b1.getTitle().toLowerCase().compareTo(b2.getTitle().toLowerCase());
			
		case 2:
			return b1.getAuthor().toLowerCase().compareTo(b2.getAuthor().toLowerCase());
			
			default:return 0;
		}
	}
}
