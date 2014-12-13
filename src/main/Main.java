package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import context.Context;
import toolbar.ToolbarManager;
import book.Book;
import book.BookContentProvider;
import book.BookLabelProvider;
import book.BookSorter;

public class Main {

	public static void main(String[] args) {
		
		Shell shell = Context.getShell();
		Display display = Context.getDisplay();
		
		ToolbarManager tbm = new ToolbarManager();
		tbm.init();
		

		TableViewer tableViewer = new TableViewer(shell, SWT.None);
		Table table = tableViewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSize(500, 300);
		table.setLocation(0, 100);
		
		String[] titles = { "ISBN","Titre", "Auteur", "Prix", "Acheter"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.CENTER);
	      column.setText(titles[i]);
	    }

	    List<Book> list = new ArrayList<Book>();
	    for (int i = 0; i < 10; i++) {
	    	list.add(new Book("titre", "autor", new Random().nextLong()));
	    }

	    BookSorter bs = new BookSorter(tableViewer);
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
	      table.getColumn (i).setWidth(200);
	    }
	    
	    
	    table.setSize(shell.getSize().x, 400);
	    tableViewer.setComparator(bs);
	    tableViewer.setContentProvider(new BookContentProvider());
	    tableViewer.setLabelProvider(new BookLabelProvider());
	   	tableViewer.setInput(list);
		shell.open();
		while (!shell.isDisposed()) {
			
		
			if (!display.readAndDispatch())
				display.sleep();
		}
		
		display.dispose();
		tbm.dispose();
	}
}
