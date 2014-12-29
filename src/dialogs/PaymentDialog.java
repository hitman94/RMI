package dialogs;

import java.rmi.RemoteException;
import java.util.Set;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Combo;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Shell;

import sellingBook.Book;
import NET.webserviceX.www.Currency;
import NET.webserviceX.www.CurrencyConvertorLocator;
import NET.webserviceX.www.CurrencyConvertorSoap;
import client.Client;

public class PaymentDialog extends TitleAreaDialog {

	
	private Double price = 0.0d;
	
	public PaymentDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void create() {
		super.create();
		
		setTitle("Paiement de vos achats");
		setMessage("Selectionnez la devise pour payer vos achat");
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		for(Book b: Client.getBasket()) {
			price+=b.getPrice();
		}
		
		Composite area= (Composite)super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		
		Label eurlabel = new Label(container, SWT.NONE);
		eurlabel.setBounds(50, 5, 150, 20);		
		eurlabel.setText("Montant total en euros :");
		
		Label pricelabel = new Label(container, SWT.NONE);
		pricelabel.setBounds(250, 5, 100, 20);		
		pricelabel.setText(price.toString());
		
		
		Label conversion = new Label(container, SWT.NONE);
		conversion.setBounds(50, 45, 150, 20);		
		conversion.setText("Selectionnez votre monnaie:");
		
		final Combo currencyList = new Combo(container, SWT.READ_ONLY);
		currencyList.setBounds(250, 45, 75, 25);
		Set<java.util.Currency> setC=java.util.Currency.getAvailableCurrencies();
		for(java.util.Currency c: setC)
			currencyList.add(c.getCurrencyCode());
		
		Label total = new Label(container, SWT.NONE);
		total.setBounds(50, 75, 150, 20);		
		total.setText("Total :");
		
		final Label totalResult = new Label(container, SWT.NONE);
		totalResult.setBounds(250, 75, 150, 20);		
		totalResult.setText(price.toString()+" EUR");
		
		
		currencyList.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					CurrencyConvertorSoap cur = new CurrencyConvertorLocator().getCurrencyConvertorSoap();
					
					String curtarget = currencyList.getItem(currencyList.getSelectionIndex());
					
					double convRate=cur.conversionRate(Currency.fromString("EUR"), Currency.fromString(curtarget));
					
					totalResult.setText( ((Double)(price*convRate)).toString()+" "+curtarget);
					setErrorMessage(null);
					
				} catch(IllegalArgumentException e) {
					setErrorMessage("Monnaie non prise en charge");
				
				} catch (ServiceException | RemoteException e) {
					setErrorMessage("Connexion au service de conversion de monnaie impossible");
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		
		return area;
	}
	
	public Double getPrice() {
		return price;
	}

}
