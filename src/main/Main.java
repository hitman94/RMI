package main;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import sellingBook.Book;
import sellingBook.SellingBookWS;
import sellingBook.SellingBookWSServiceLocator;
import toolbar.ToolbarManager;
import client.Client;
import context.Context;
import dialogs.ConnectionDialog;

public class Main {

	public static void main(String[] args) {
		
		Shell shell = Context.getShell();
		Display display = Context.getDisplay();
		
		
		ConnectionDialog cd = new ConnectionDialog(shell);
		if(cd.open()!=Window.OK)
			return;
		
		Client.setUsername(cd.getUsername());
		
		Client.loadBasket();
		
		
		ToolbarManager tbm = new ToolbarManager();
		tbm.init();
		
		
		Context.display();
		
		display.dispose();
		tbm.dispose();
		

		
	}
}
