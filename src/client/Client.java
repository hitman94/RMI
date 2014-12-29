package client;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.MessageDialog;

import context.Context;
import sellingBook.Book;
import sellingBook.SellingBookWS;
import sellingBook.SellingBookWSServiceLocator;

public class Client implements Serializable{

	private static final long serialVersionUID = 5153545285566963487L;
	private static List<Book> basket = new ArrayList<Book>();
	private static String username;
	
	public static void addBook(Book b) {
		basket.add(b);
	}
	
	public static void removeBook(Book b) {
		basket.remove(b);
	}
	
	public static List<Book> getBasket() {
		return basket;
	}
	
	public static void cleanBasket() {
		basket.clear();
		saveBasket();
	}
	
	public static void setUsername(String user) {
		username=user;
	}
	
	public static String getUsername() {
		return username;
	}
	
	public static void loadBasket() {
		SellingBookWS sb;
		try {
			sb = new SellingBookWSServiceLocator().getSellingBookWS();
			Book[] listBakset = sb.unserialize(Client.getUsername());
			if(listBakset != null) {
				for(Book b : listBakset) {
					if(b!=null) {
						Client.addBook(b);
					}
				}
			}
			else
				System.err.println("renvoit de null");
				
		} catch ( RemoteException e) {
			new MessageDialog(Context.getShell(), "Erreur", null, "Votre panier n'a pas pu etre chargé", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
		} catch (ServiceException e) {
			new MessageDialog(Context.getShell(), "Erreur", null, "Votre panier n'a pas pu etre chargé 1", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
		}
	}
	
	public static void saveBasket() {
		SellingBookWS sb;
		if(Client.getBasket().size()>0) {
			try {
				sb = new SellingBookWSServiceLocator().getSellingBookWS();

				Book[] array= new Book[Client.getBasket().size()];
				array = Client.getBasket().toArray(array);
				for(Book b : array) {
						System.out.println(b.getAuthor());
				}
				sb.serialize(Client.getUsername(), array);
					
			} catch (ServiceException | RemoteException e) {
				System.err.println("impossible de sauvegarder le panier");
			}
		}
	}
	
}
