/**
 * Users.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package users;

public interface Users extends java.rmi.Remote {
    public boolean createUser(java.lang.String username, java.lang.String password) throws java.rmi.RemoteException;
    public boolean isValide(java.lang.String usr, java.lang.String pass) throws java.rmi.RemoteException;
}
