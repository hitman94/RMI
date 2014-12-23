package main;

import org.eclipse.jface.window.Window;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import client.Client;
import toolbar.ToolbarManager;
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
		
		ToolbarManager tbm = new ToolbarManager();
		tbm.init();
		
		
		Context.display();
		
		display.dispose();
		tbm.dispose();
	}
}
