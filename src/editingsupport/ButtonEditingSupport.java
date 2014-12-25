package editingsupport;

import org.eclipse.jface.viewers.CellEditor;
import org.eclipse.jface.viewers.ComboBoxCellEditor;
import org.eclipse.jface.viewers.DialogCellEditor;
import org.eclipse.jface.viewers.EditingSupport;
import org.eclipse.jface.viewers.TableViewer;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;

import context.Context;

public class ButtonEditingSupport extends EditingSupport{
	
	private TableViewer table;
	private ButtonCellEditor editor;
	
	private class ButtonCellEditor extends DialogCellEditor {
		
		public ButtonCellEditor(Composite parent) {
			super(parent);
			
		}

		@Override
		protected Button createButton(Composite parent) {
			Button b = new Button(parent, SWT.CENTER | SWT.PUSH);
			b.setBounds(0, 0, 32, 32);
			b.setImage(new Image(Context.getDisplay(), "images/addtobasket.png"));
			
			return b;
		}
		
		@Override
		protected Control createContents(Composite cell) {
			
			Label label = new Label(cell, SWT.None);
			label.setSize( 20, cell.getSize().y);
			label.setText("Cliquez sur le bouton pour ajouter ce livre au panier");
			
			return label;
		}
		
		
		@Override
		protected Object openDialogBox(Control arg0) {
			System.out.println("button pressé");
			return null;
		}
		
	}
	
	public ButtonEditingSupport(TableViewer viewer) {
		super(viewer);
		
		this.table= viewer;
		
		this.editor=new ButtonCellEditor(viewer.getTable());
	}

	@Override
	protected boolean canEdit(Object arg0) {
		return true;
	}

	@Override
	protected CellEditor getCellEditor(Object arg0) {
		return editor;
	}

	@Override
	protected Object getValue(Object arg0) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	protected void setValue(Object arg0, Object arg1) {
		
	}

}
