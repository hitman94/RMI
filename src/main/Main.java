package main;


import org.eclipse.jface.window.Window;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

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
		
		Image m = new Image(Context.getDisplay(),"images/bigbook.png");
		Label label = new Label(Context.getContentGroup(),SWT.CENTER);
		label.setBounds(250, 1, 512, 512);
		label.setImage(m);
		
		
		ToolbarManager tbm = new ToolbarManager();
		tbm.init();
		
		
		Context.display();
		
		display.dispose();
		tbm.dispose();
		

		
	}
}
