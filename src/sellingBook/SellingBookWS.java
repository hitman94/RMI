/**
 * SellingBookWS.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package sellingBook;

public interface SellingBookWS extends java.rmi.Remote {
    public void test() throws java.rmi.RemoteException;
    public void serialize(java.lang.String username, sellingBook.Book[] basket) throws java.rmi.RemoteException;
    public sellingBook.Book[] getBooksThatContain(java.lang.String title) throws java.rmi.RemoteException;
    public sellingBook.Book removeBook(long ISBN) throws java.rmi.RemoteException;
    public sellingBook.Book getBookByISBN(long ISBN) throws java.rmi.RemoteException;
    public sellingBook.Book getBookByTitle(java.lang.String title) throws java.rmi.RemoteException;
    public sellingBook.Book[] getBooksByAuthor(java.lang.String author) throws java.rmi.RemoteException;
    public sellingBook.Book[] getAllBooks() throws java.rmi.RemoteException;
    public sellingBook.Book[] unserialize(java.lang.String username) throws java.rmi.RemoteException;
    public void addBook(long isbn, java.lang.String title, java.lang.String authour, double price, int nbExemplaire) throws java.rmi.RemoteException;
}
