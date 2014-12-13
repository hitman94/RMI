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

import book.Book;
import book.BookContentProvider;
import book.BookLabelProvider;
import book.BookSorter;

public class Main {

	public static void main(String[] args) {
		Display display = new Display();
		Shell shell = new Shell(display);
		shell.setText("Bonjour");
		shell.setSize(1024, 768);
		
		
		
//		Label label = new Label(shell, SWT.NONE);
//		label.setText("Bonjour!");
//		label.setSize(100,25);
		
//		Text text = new Text(shell, SWT.NONE);
//		text.setSize(100,25);
		
		ToolBar toolbar = new ToolBar(shell, SWT.FLAT);
		toolbar.setSize(shell.getSize().x, 100);
		toolbar.setLocation(shell.getSize().x/2 - 64, 0);

		Image imageBtn1 = new Image(display, "images/basket.png");
		ToolItem btn1 = new ToolItem(toolbar, SWT.PUSH);
		btn1.setImage(imageBtn1);
		btn1.setText("Mon Panier");

		Image imageBtn2 = new Image(display, "images/books.png");
		ToolItem btn2 = new ToolItem(toolbar, SWT.PUSH);
		btn2.setImage(imageBtn2);
		btn2.setText("Bibliothèque");

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
			
		//imageBtn1.dispose();
		display.dispose();
	}
}
