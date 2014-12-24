package banque;

import java.rmi.RemoteException;

public class BanqueProxy implements banque.Banque {
  private String _endpoint = null;
  private banque.Banque banque = null;
  
  public BanqueProxy() {
    _initBanqueProxy();
  }
  
  public BanqueProxy(String endpoint) {
    _endpoint = endpoint;
    _initBanqueProxy();
  }
  
  private void _initBanqueProxy() {
    try {
      banque = (new banque.BanqueServiceLocator()).getBanque();
      if (banque != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)banque)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (banque != null)
      ((javax.xml.rpc.Stub)banque)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public banque.Banque getBanque() {
    if (banque == null)
      _initBanqueProxy();
    return banque;
  }
  
  public void depotDe(java.lang.String username, double montant) throws RemoteException{
    if (banque == null)
      _initBanqueProxy();
    banque.depotDe(username, montant);
  }
  
  public boolean retraitDe(java.lang.String username, double montant) throws RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.retraitDe(username, montant);
  }
  
  public double valeurDuSolde(java.lang.String username) throws RemoteException{
    if (banque == null)
      _initBanqueProxy();
    return banque.valeurDuSolde(username);
  }
  
  
}