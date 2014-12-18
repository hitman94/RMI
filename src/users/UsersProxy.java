package users;

public class UsersProxy implements users.Users {
  private String _endpoint = null;
  private users.Users users = null;
  
  public UsersProxy() {
    _initUsersProxy();
  }
  
  public UsersProxy(String endpoint) {
    _endpoint = endpoint;
    _initUsersProxy();
  }
  
  private void _initUsersProxy() {
    try {
      users = (new users.UsersServiceLocator()).getUsers();
      if (users != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)users)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)users)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (users != null)
      ((javax.xml.rpc.Stub)users)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public users.Users getUsers() {
    if (users == null)
      _initUsersProxy();
    return users;
  }
  
  public boolean createUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException{
    if (users == null)
      _initUsersProxy();
    return users.createUser(username, password);
  }
  
  public boolean isValide(java.lang.String usr, java.lang.String pass) throws java.rmi.RemoteException{
    if (users == null)
      _initUsersProxy();
    return users.isValide(usr, pass);
  }
  
  
}