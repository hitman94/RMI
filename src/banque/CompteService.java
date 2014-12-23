/**
 * CompteService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package banque;

public interface CompteService extends javax.xml.rpc.Service {
    public java.lang.String getCompteAddress();

    public banque.Compte getCompte() throws javax.xml.rpc.ServiceException;

    public banque.Compte getCompte(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
