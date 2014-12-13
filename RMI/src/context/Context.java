package context;

import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class Context {
	
	
	private static String title = "Bonjour";
	private static Integer width = 1024;
	private static Integer height = 768;
	private static Display display = null;
	private static Shell shell = null;
	
	public static Display getDisplay() {
		if(display==null) {
			display = new Display();
		}
		return display;
	}
	
	public static Shell getShell() {
		if(shell == null) {
			shell = new Shell(getDisplay());
			shell.setText(title);
			shell.setSize(width,height);
		}
		return shell;
	}

}
