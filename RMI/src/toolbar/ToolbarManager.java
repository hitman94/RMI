package toolbar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import context.Context;

public class ToolbarManager {
	
	private List<Resource> resources = new ArrayList<>();
	
	public void init() {
		Shell shell = Context.getShell();
		Display display = Context.getDisplay();
		
		
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
		
		resources.add(imageBtn1);
		resources.add(imageBtn2);
	}
	
	public void dispose() {
		for(Resource r : resources)
			r.dispose();
	}

}
