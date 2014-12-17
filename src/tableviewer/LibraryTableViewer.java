package tableviewer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Label;
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
		
		 
		 /***
		  * 
		  * BARRE DE RECHERCHE AUTEUR / TITRE
		  */
		 
		 Label searchTitleLabel = new Label(Context.getContentGroup(), SWT.None);
		 searchTitleLabel.setBounds(0, 20, 150, 20);
		 searchTitleLabel.setText("Rechercher suivant le titre:");
		 
		 Text searchTitle = new Text(Context.getContentGroup(), SWT.SINGLE | SWT.BORDER);
		 searchTitle.setBounds(150, 20, 150, 20);
		 searchTitle.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				bf.setTitleFilter(searchTitle.getText());
				tableViewer.refresh();
			}
		});
		 
		 Label searchAuthorLabel = new Label(Context.getContentGroup(), SWT.None);
		 searchAuthorLabel.setBounds(400, 20, 150, 20);
		 searchAuthorLabel.setText("Rechercher suivant l'auteur:");
		 
		 Text searchAuthor = new Text(Context.getContentGroup(), SWT.SINGLE | SWT.BORDER);
		 searchAuthor.setBounds(550, 20, 150, 20);
		 searchAuthor.addModifyListener(new ModifyListener() {
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				bf.setAuthorFilter(searchAuthor.getText());
				tableViewer.refresh();
			}
		});
		 
		 
		 
		 /***
		  * 
		  * CREATION DES COLONNES
		  */
		 
		 
		
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
	    list.add(new Book("la pute", "Shakespeare", 1L));
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
