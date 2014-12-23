package book;

import org.eclipse.jface.viewers.ILabelProviderListener;
import org.eclipse.jface.viewers.ITableLabelProvider;
import org.eclipse.swt.graphics.Image;

import context.Context;

public class BookLabelProvider implements ITableLabelProvider {

	@Override
	public void addListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean isLabelProperty(Object arg0, String arg1) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void removeListener(ILabelProviderListener arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Image getColumnImage(Object arg0, int arg1) {
	
		return null;
	}

	@Override
	public String getColumnText(Object arg0, int arg1) {
		Book book = (Book)arg0;
		switch (arg1) {
		case 0:
			return book.getISBN().toString();
	
			
		case 1:
			return book.getTitle();
			
		case 2:
			return book.getAuthor();
			
		case 3:
			return "pix";
			
		case 4:
			return "2";
			

			default:return null;
		
		}
		
	}

	


}
