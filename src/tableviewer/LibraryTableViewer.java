package tableviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.jface.viewers.ViewerFilter;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.Text;

import book.Book;
import book.BookContentProvider;
import book.BookFilter;
import book.BookLabelProvider;
import book.BookSorter;
import context.Context;

public class LibraryTableViewer {
	
	public void display() {
		Shell shell = Context.getShell();
		TableViewer tableViewer = new TableViewer(Context.getContentGroup(), SWT.V_SCROLL | SWT.NO_SCROLL);
		
		BookFilter bf = new BookFilter();
		
		Table table = tableViewer.getTable();
		 table.setHeaderVisible(true);
		 table.setLinesVisible(true);
		 table.setSize(Context.getContentGroup().getSize().x - 25, Context.getContentGroup().getSize().y);
		 table.setLocation(0, 50);
		
		 Text searchTitle = new Text(Context.getContentGroup(), SWT.SINGLE | SWT.BORDER);
		 searchTitle.setBounds(0, 20, 150, 20);
		 searchTitle.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				//System.out.println(searchTitle.getText());
				bf.setAuthorFilter(searchTitle.getText());
				tableViewer.refresh();
			}
		});
		 
		
		//Creation des colonnes du tablea
		String[] titles = { "ISBN","Titre", "Auteur", "Prix","Quantité restante", "Acheter"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.CENTER);
	      column.setText(titles[i]);
	      
	    }

	    List<Book> list = new ArrayList<Book>();
	    for (int i = 0; i < 10; i++) {
	    	list.add(new Book("titre", "autor", new Random().nextLong()));
	    }
	    
	    list.add(new Book("les misérables", "Victor hugo", 1L));
	    list.add(new Book("moliere", "Shakespeare", 1L));
	    list.add(new Book("rien", "jean jean", 1L));
	    list.add(new Book("demain noublie jamais", "007", 1L));
	    
	    final BookSorter bs = new BookSorter(tableViewer);
	    for (int i=0; i<titles.length; i++) {
	    	final int index=i;
	    	table.getColumn(i).addSelectionListener(new SelectionListener() {
				
				@Override
				public void widgetSelected(SelectionEvent e) {
					bs.setSorterIndex(index);
					
				}
				
				@Override
				public void widgetDefaultSelected(SelectionEvent e) {
					// TODO Auto-generated method stub
					
				}
			});
	      table.getColumn (i).setWidth(table.getSize().x/titles.length);
	    }
	    
	   
	    tableViewer.setComparator(bs);
	    tableViewer.addFilter(bf);
	    tableViewer.setContentProvider(new BookContentProvider());
	    tableViewer.setLabelProvider(new BookLabelProvider());
	   	tableViewer.setInput(list);
	   	
	}

}
