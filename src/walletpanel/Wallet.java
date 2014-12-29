package walletpanel;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.MessageDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Button;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Text;

import banque.Banque;
import banque.BanqueServiceLocator;
import client.Client;
import context.Context;

public class Wallet {
	
	private Double solde = 0.0;
	private Banque b;
	private boolean connected=true;
	
	private void init() {
		try {
			b = new BanqueServiceLocator().getBanque();
			solde = b.valeurDuSolde(Client.getUsername());
			if(solde==null)
				solde=0.;
		} catch (ServiceException | RemoteException e) {
			connected=false;
			new MessageDialog(Context.getShell(), "Erreur", null, "Impossible de se connecter à la banque", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
		}
		
	}
	
	public void display() {
		
		init();
		Label amountLabel = new Label(Context.getContentGroup(),SWT.NONE);
		amountLabel.setText("Solde de votre compte:");
		amountLabel.pack();
		amountLabel.setLocation(50, 50);
		
		Label amount = new Label(Context.getContentGroup(),SWT.NONE);
		amount.setText(solde.toString());
		amount.pack();
		amount.setLocation(250, 50);
		
		Label addLabel = new Label(Context.getContentGroup(),SWT.NONE);
		addLabel.setText("Ajouter de l'argent à votre compte:");
		addLabel.pack();
		addLabel.setLocation(50, 75);
		
		Text add = new Text(Context.getContentGroup(),SWT.BORDER);
		add.pack();
		add.setLocation(250, 75);
		
		Button addButton = new Button(Context.getContentGroup(),SWT.PUSH);
		addButton.setText("Ajouter!");
		addButton.pack();
		addButton.setLocation(350, 75);
		if(!connected)
			addButton.setEnabled(false);
		addButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					Double toAdd = new Double(add.getText());
					b.depotDe(Client.getUsername(), toAdd);
					amount.setText( ((Double)b.valeurDuSolde(Client.getUsername())).toString() );
					amount.pack();
					
				} catch(NumberFormatException e) {
					new MessageDialog(Context.getShell(), "Erreur", null, "Veuillez indiquer un nombre!", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
				} catch (RemoteException e) {
					new MessageDialog(Context.getShell(), "Erreur", null, "Connexion à la banque impossible", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		
		Label withdrawLabel = new Label(Context.getContentGroup(),SWT.NONE);
		withdrawLabel.setText("Retirer de l'argent à votre compte:");
		withdrawLabel.pack();
		withdrawLabel.setLocation(50, 100);
		
		Text withdraw = new Text(Context.getContentGroup(),SWT.BORDER);
		withdraw.pack();
		withdraw.setLocation(250, 100);
		
		Button withdrawButton = new Button(Context.getContentGroup(),SWT.PUSH);
		withdrawButton.setText("Retirer!");
		withdrawButton.pack();
		withdrawButton.setLocation(350, 100);
		if(!connected)
			withdrawButton.setEnabled(false);
		
		withdrawButton.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				try {
					Double toWithdraw = new Double(withdraw.getText());
					
					if(b.retraitDe(Client.getUsername(), toWithdraw))
						amount.setText( ((Double)b.valeurDuSolde(Client.getUsername())).toString() );
					else
						new MessageDialog(Context.getShell(), "Erreur", null, "Vous ne pouvez retirer autant d'argent", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
					
					
					
				} catch(NumberFormatException e) {
					new MessageDialog(Context.getShell(), "Erreur", null, "Veuillez indiquer un nombre!", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
				} catch (RemoteException e) {
					new MessageDialog(Context.getShell(), "Erreur", null, "Connexion à la banque impossible", MessageDialog.ERROR, new String[]{"OK"}, 0).open();
				}
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
	}

}
