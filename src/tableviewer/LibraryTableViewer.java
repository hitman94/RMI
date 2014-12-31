package tableviewer;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ModifyEvent;
import org.eclipse.swt.events.ModifyListener;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.TableItem;
import org.eclipse.swt.widgets.Text;

import sellingBook.Book;
import sellingBook.SellingBookWS;
import sellingBook.SellingBookWSServiceLocator;
import book.BookContentProvider;
import book.BookFilter;
import book.BookLabelProvider;
import book.BookSorter;
import client.Client;
import context.Context;

public class LibraryTableViewer {
	
	public void display() {
		Shell shell = Context.getShell();
		TableViewer tableViewer = new TableViewer(Context.getContentGroup(), SWT.FULL_SELECTION | SWT.V_SCROLL | SWT.NO_SCROLL);
		
		BookFilter bf = new BookFilter();
		
		Table table = tableViewer.getTable();
	
		 table.setHeaderVisible(true);
		 table.setLinesVisible(true);
		 table.setSize(Context.getContentGroup().getSize().x - 25, Context.getContentGroup().getSize().y-50);
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
			 
			 SellingBookWS sb=null;
			
			@Override
			public void modifyText(ModifyEvent arg0) {
				try {
					if(sb==null)
						sb = new SellingBookWSServiceLocator().getSellingBookWS();
					
					Book[] list = sb.getBooksThatContain(searchTitle.getText());
					
					tableViewer.setInput(list);
					tableViewer.refresh();
				} catch (ServiceException | RemoteException e1) {
					new MessageDialog(Context.getShell(), "Erreur", null, "Impossible de contacter la bibliothèque", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
					
				}
			}
		});
		 
		 Label searchAuthorLabel = new Label(Context.getContentGroup(), SWT.None);
		 searchAuthorLabel.setBounds(400, 20, 150, 20);
		 searchAuthorLabel.setText("Rechercher suivant l'auteur:");
		 
		 Text searchAuthor = new Text(Context.getContentGroup(), SWT.SINGLE | SWT.BORDER);
		 searchAuthor.setBounds(550, 20, 150, 20);
		 searchAuthor.addModifyListener(new ModifyListener() {
			
			 SellingBookWS sb=null;
			 
			@Override
			public void modifyText(ModifyEvent arg0) {
				 
					try {
						if(sb==null)
							sb = new SellingBookWSServiceLocator().getSellingBookWS();
						
						Book[] list = sb.getBooksByAuthor(searchAuthor.getText());
						tableViewer.setInput(list);
						tableViewer.refresh();
					} catch (ServiceException | RemoteException e1) {
						new MessageDialog(Context.getShell(), "Erreur", null, "Impossible de contacter la bibliothèque", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
						
					}				
			}
		});
		 
		 
		 
		 /***
		  * 
		  * CREATION DES COLONNES
		  */
		 
		 
		
		//Creation des colonnes du tablea
		String[] titles = { "ISBN","Titre", "Auteur", "Prix","Quantité restante"};
	    for (int i = 0; i < titles.length; i++) { 
	    	
	      TableColumn column = new TableColumn(table, SWT.CENTER);
	      column.setText(titles[i]);     
	      
	    }
	    
	    
	    
	    
	    
	    
	    
	    
//	    List<Book> list = new ArrayList<Book>();
//	    for (int i = 0; i < 20; i++) {
//	    	list.add(new Book(new Random().nextLong(),"titre", new Random().nextDouble()%10,"autor" ));
//	    }
	    
//	    SellingBookWS sb;
//		try {
//			sb = new SellingBookWSServiceLocator().getSellingBookWS();
//			sb.test();
//			 Book[] listBook = sb.getAllBooks();
//			 for(Book b: listBook)
//				 list.add(b);
//		} catch (ServiceException e1) {
//			// TODO Auto-generated catch block
//			e1.printStackTrace();
//		} catch (RemoteException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	   
	    
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
	      table.getColumn (i).setWidth(table.getSize().x/titles.length + 2);
	    }
	    
	    
	    tableViewer.setComparator(bs);
	    tableViewer.addFilter(bf);
	    tableViewer.setContentProvider(new BookContentProvider());
	    tableViewer.setLabelProvider(new BookLabelProvider());
	   //	tableViewer.setInput(list);
	
	   	
	   	
	   	
	   	final Button addToBasket = new Button(Context.getBottomGroup(),SWT.PUSH);
	   	addToBasket.setText("Ajouter le livre au panier");
	   	addToBasket.setBounds(800,20,150,32);
	   	addToBasket.setEnabled(false);
	   	addToBasket.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				TableItem item = table.getItem(table.getSelectionIndex());
				Book b = (Book)item.getData();
				MessageDialog md = new MessageDialog(Context.getShell(), "Ajout du livre", null, "Voulez vous vraiment ajouter \""+b.getTitle()+"\" de "+b.getAuthor()+" a votre panier ?", MessageDialog.CONFIRM, new String[]{"Oui","Non"}, 0);
				if(md.open()==0)
					Client.addBook(b);
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	   	
	    
	   	table.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				addToBasket.setEnabled(true);
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
	    
	     
	}

}
