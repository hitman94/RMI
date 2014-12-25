package tableviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;

import client.Client;
import book.Book;
import book.BookContentProvider;
import book.BookLabelProvider;
import book.BookSorter;
import context.Context;
import dialogs.PaymentDialog;

public class BasketTableViewer {
	
	public void display() {
		Shell shell = Context.getShell();
		TableViewer tableViewer = new TableViewer(Context.getContentGroup(),SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.NO_SCROLL);
		Table table = tableViewer.getTable();

		table.setHeaderVisible(true);
		table.setLinesVisible(true);
		table.setSize(Context.getContentGroup().getSize().x - 25, Context.getContentGroup().getSize().y);
		
		String[] titles = { "ISBN","Titre", "Auteur", "Prix"};
	    for (int i = 0; i < titles.length; i++) {
	      TableColumn column = new TableColumn(table, SWT.CENTER);
	      column.setText(titles[i]);
	      
	    }

	    List<Book> list = new ArrayList<Book>();
	    for (int i = 0; i < 50; i++) {
	    	list.add(new Book("titre", "autor", new Random().nextLong()));
	    }

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
	    tableViewer.setContentProvider(new BookContentProvider());
	    tableViewer.setLabelProvider(new BookLabelProvider());
	   	tableViewer.setInput(Client.getBasket());
	   	
	   	
	   	final Button pay = new Button(Context.getBottomGroup(),SWT.PUSH);
	   	pay.setText("Passer à la caisse");
	   	pay.pack();
	   	pay.setLocation(700,20);
	   	pay.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				PaymentDialog pd = new PaymentDialog(Context.getShell());
				pd.open();
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   	
	   	final Button removeFromBasket = new Button(Context.getBottomGroup(),SWT.PUSH);
	   	removeFromBasket.setText("Supprimer ce livre du panier");
	   	removeFromBasket.pack();
	   	removeFromBasket.setLocation(800, 20);
	   	removeFromBasket.setEnabled(false);
	   	removeFromBasket.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem item = table.getItem(table.getSelectionIndex());
				Book b = (Book)item.getData();
				MessageDialog md = new MessageDialog(Context.getShell(), "Suppression du livre", null, "Voulez vous vraiment supprimer \""+b.getTitle()+"\" de "+b.getAuthor()+" de votre panier ?", MessageDialog.CONFIRM, new String[]{"Oui","Non"}, 0);
				if(md.open()==0) {
					Client.removeBook(b);
					tableViewer.refresh();
				}
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   	
	   	table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				removeFromBasket.setEnabled(true);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   	
	}

}
