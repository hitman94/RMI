package main;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Table;
import org.eclipse.swt.widgets.TableColumn;
import org.eclipse.swt.widgets.ToolBar;
import org.eclipse.swt.widgets.ToolItem;

import context.Context;
import toolbar.ToolbarManager;
import book.Book;
import book.BookContentProvider;
import book.BookLabelProvider;
import book.BookSorter;

public class Main {

	public static void main(String[] args) {
		
		Shell shell = Context.getShell();
		Display display = Context.getDisplay();
		
		ToolbarManager tbm = new ToolbarManager();
		tbm.init();
		
		
		Context.display();
		
		display.dispose();
		tbm.dispose();
	}
}
