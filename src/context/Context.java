package context;

import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Group;
import org.eclipse.swt.widgets.Shell;

import client.Client;

public class Context {
	
	
	private static String title = "MLV Books";
	private static Integer width = 1024;
	private static Integer height = 768;
	private static Display display = null;
	private static Shell shell = null;
	private static Group contentGroup = null;
	private static Group bottomGroup = null;
	
	public static Display getDisplay() {
		if(display==null) {
			display = new Display();
			
		}
		return display;
	}
	
	public static Shell getShell() {
		if(shell == null ) {
			shell = new Shell(getDisplay());
			shell.setText(title);
			shell.setSize(width,height);
			contentGroup =new Group(shell,SWT.None);
			contentGroup.setBounds(0, 100, width, 500);
			bottomGroup = new Group(shell,SWT.NONE);
			bottomGroup.setBounds(0,600,width,300);
		}
		return shell;
	}
	
	public static Group getContentGroup() {
		return contentGroup;
	}
	
	public static Group getBottomGroup() {
		return bottomGroup;
	}
	
	public static void clearContent() {
		for(Control c :contentGroup.getChildren())
			c.dispose();
		for(Control c :bottomGroup.getChildren())
			c.dispose();
	}
	
	public static void display() {
		shell.open();
		while (!shell.isDisposed()) {
			
		
			if (!display.readAndDispatch())
				display.sleep();
		}
		Client.saveBasket();
	}
}
