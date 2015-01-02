package dialogs;

import java.rmi.RemoteException;

import javax.xml.rpc.ServiceException;

import org.eclipse.jface.dialogs.TitleAreaDialog;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Label;
import org.eclipse.swt.widgets.Link;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.swt.widgets.Text;

import users.Users;
import users.UsersServiceLocator;

public class ConnectionDialog extends TitleAreaDialog{

	private Text passText;
	private Text userText;
	private String username;
	private Users users;
	public ConnectionDialog(Shell parentShell) {
		super(parentShell);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void create() {
		// TODO Auto-generated method stub
		super.create();
		setTitle("Connexion à SellingBook");
		setMessage("Veuillez rentrer vos identifiants pour vous connecter");
		try {
			users = new UsersServiceLocator().getUsers();
		} catch (ServiceException e) {
			setErrorMessage("Connexion au service impossible");
		}
	}
	
	@Override
	protected Control createDialogArea(Composite parent) {
		// TODO Auto-generated method stub
		Composite area= (Composite)super.createDialogArea(parent);
		Composite container = new Composite(area, SWT.NONE);
		
		Label userLabel = new Label(container, SWT.NONE);
		userLabel.setBounds(50, 20, 100, 20);		
		userLabel.setText("Username :");
		
		
		
		Link createaccount = new Link(container, SWT.NONE);
		createaccount.setText("<a>Cliquez ici pour vous creer un compte</a>");
		createaccount.pack();
		createaccount.setLocation(50,90);
		createaccount.addSelectionListener(new SelectionListener() {
			
			@Override
			public void widgetSelected(SelectionEvent arg0) {
				new CreateUserDialog(getShell()).open();
				
			}
			
			@Override
			public void widgetDefaultSelected(SelectionEvent arg0) {
				// TODO Auto-generated method stub
				
			}
		});
		
		userText = new Text(container, SWT.BORDER);
		userText.setBounds(160, 20, 100, 20);
		
		Label passLabel = new Label(container, SWT.NONE);
		passLabel.setBounds(50, 50, 100, 20);		
		passLabel.setText("Mot de passe :");
		
		passText = new Text(container, SWT.BORDER | SWT.PASSWORD);
		passText.setBounds(160, 50, 100, 20);
				
		return area;
	}
	
	
	
	@Override
	protected void okPressed() {
		
		try {
			if( users.isValide(userText.getText(), passText.getText()) ) {
				username=userText.getText();
				super.okPressed();
			}
				
			else
				setErrorMessage("Mauvais mot de passe/Nom d'utilisateur");
		} catch (RemoteException e) {
			setErrorMessage("Connexion au service impossible");
		}
		
		
	}
	
	public String getUsername() {
		return username;
	}

}
