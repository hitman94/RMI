/**
 * Banque.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package banque;

public interface Banque extends java.rmi.Remote {
    public double valeurDuSolde(java.lang.String username) throws java.rmi.RemoteException;
    public boolean retraitDe(java.lang.String username, double montant) throws java.rmi.RemoteException;
    public void depotDe(java.lang.String username, double montant) throws java.rmi.RemoteException;
}
