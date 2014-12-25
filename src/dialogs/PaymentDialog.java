package dialogs;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

public class PaymentDialog extends TitleAreaDialog {

	
	
	
	public PaymentDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		setTitle("Paiement de vos achats");
		setMessage("Selectionnez la devise pour payer vos achat");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite area= (Composite)super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		
		Label userLabel = new Label(container, SWT.NONE);
		userLabel.setBounds(50, 5, 100, 20);		
		userLabel.setText("Montant total :");
		
		Label c = new Label(container, SWT.NONE);
		c.setBounds(50, 25, 100, 20);		
		c.setText("Montant total 1:");
		
		Label k = new Label(container, SWT.NONE);
		k.setBounds(50, 45, 100, 20);		
		k.setText("Montant total 2:");
		
		return area;
	}

}
