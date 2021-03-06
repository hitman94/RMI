package toolbar;

import java.util.ArrayList;
import java.util.List;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.graphics.Resource;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import tableviewer.BasketTableViewer;
import tableviewer.LibraryTableViewer;
import walletpanel.Wallet;
import context.Context;

public class ToolbarManager {
	
	private List<Resource> resources = new ArrayList<>();
	
	public void init() {
		Shell shell = Context.getShell();
		Display display = Context.getDisplay();
		
		
		
		ToolBar toolbar = new ToolBar(shell, SWT.FLAT);
		toolbar.setSize(shell.getSize().x, 100);
		toolbar.setLocation(shell.getSize().x/2 - 128, 0);

		Image imageBtn1 = new Image(display, getClass().getResourceAsStream("/images/basket.png"));
		ToolItem btn1 = new ToolItem(toolbar, SWT.PUSH);
		btn1.setImage(imageBtn1);
		btn1.setText("Mon Panier");
		
		btn1.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Context.clearContent();
				BasketTableViewer btv = new BasketTableViewer();
				btv.display();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		

		Image imageBtn2 = new Image(display, getClass().getResourceAsStream("/images/books.png") );
		ToolItem btn2 = new ToolItem(toolbar, SWT.PUSH);
		btn2.setImage(imageBtn2);
		btn2.setText("Bibliothèque");
		
		btn2.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Context.clearContent();
				LibraryTableViewer ltb = new LibraryTableViewer();
				ltb.display();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		Image imageBtn3 = new Image(display, getClass().getResourceAsStream("/images/wallet.png"));
		ToolItem btn3 = new ToolItem(toolbar, SWT.PUSH);
		btn3.setImage(imageBtn3);
		btn3.setText("Mon porte monnaie");
		
		btn3.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				Context.clearContent();
				Wallet w = new Wallet();
				w.display();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		resources.add(imageBtn1);
		resources.add(imageBtn2);
		resources.add(imageBtn3);
	}
	
	public void dispose() {
		for(Resource r : resources)
			r.dispose();
	}

}
