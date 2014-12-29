package sellingBook;

public class SellingBookWSProxy implements sellingBook.SellingBookWS {
  private String _endpoint = null;
  private sellingBook.SellingBookWS sellingBookWS = null;
  
  public SellingBookWSProxy() {
    _initSellingBookWSProxy();
  }
  
  public SellingBookWSProxy(String endpoint) {
    _endpoint = endpoint;
    _initSellingBookWSProxy();
  }
  
  private void _initSellingBookWSProxy() {
    try {
      sellingBookWS = (new sellingBook.SellingBookWSServiceLocator()).getSellingBookWS();
      if (sellingBookWS != null) {
        if (_endpoint != null)
          ((javax.xml.rpc.Stub)sellingBookWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
        else
          _endpoint = (String)((javax.xml.rpc.Stub)sellingBookWS)._getProperty("javax.xml.rpc.service.endpoint.address");
      }
      
    }
    catch (javax.xml.rpc.ServiceException serviceException) {}
  }
  
  public String getEndpoint() {
    return _endpoint;
  }
  
  public void setEndpoint(String endpoint) {
    _endpoint = endpoint;
    if (sellingBookWS != null)
      ((javax.xml.rpc.Stub)sellingBookWS)._setProperty("javax.xml.rpc.service.endpoint.address", _endpoint);
    
  }
  
  public sellingBook.SellingBookWS getSellingBookWS() {
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS;
  }
  
  public void test() throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    sellingBookWS.test();
  }
  
  public void serialize(java.lang.String username, sellingBook.Book[] basket) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    sellingBookWS.serialize(username, basket);
  }
  
  public sellingBook.Book[] unserialize(java.lang.String username) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.unserialize(username);
  }
  
  public void addBook(long isbn, java.lang.String title, java.lang.String authour, double price) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    sellingBookWS.addBook(isbn, title, authour, price);
  }
  
  public sellingBook.Book[] getAllBooks() throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.getAllBooks();
  }
  
  public sellingBook.Book[] getBooksByAuthor(java.lang.String author) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.getBooksByAuthor(author);
  }
  
  public sellingBook.Book getBookByISBN(long ISBN) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.getBookByISBN(ISBN);
  }
  
  public sellingBook.Book getBookByTitle(java.lang.String title) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.getBookByTitle(title);
  }
  
  public sellingBook.Book removeBook(long ISBN) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.removeBook(ISBN);
  }
  
  public sellingBook.Book[] getBooksThatContain(java.lang.String title) throws java.rmi.RemoteException{
    if (sellingBookWS == null)
      _initSellingBookWSProxy();
    return sellingBookWS.getBooksThatContain(title);
  }
  
  
}