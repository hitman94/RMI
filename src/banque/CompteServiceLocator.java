/**
 * CompteServiceLocator.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package banque;

public class CompteServiceLocator extends org.apache.axis.client.Service implements banque.CompteService {

    public CompteServiceLocator() {
    }


    public CompteServiceLocator(org.apache.axis.EngineConfiguration config) {
        super(config);
    }

    public CompteServiceLocator(java.lang.String wsdlLoc, javax.xml.namespace.QName sName) throws javax.xml.rpc.ServiceException {
        super(wsdlLoc, sName);
    }

    // Use to get a proxy class for Compte
    private java.lang.String Compte_address = "http://localhost:8080/AccountService/services/Compte";

    public java.lang.String getCompteAddress() {
        return Compte_address;
    }

    // The WSDD service name defaults to the port name.
    private java.lang.String CompteWSDDServiceName = "Compte";

    public java.lang.String getCompteWSDDServiceName() {
        return CompteWSDDServiceName;
    }

    public void setCompteWSDDServiceName(java.lang.String name) {
        CompteWSDDServiceName = name;
    }

    public banque.Compte getCompte() throws javax.xml.rpc.ServiceException {
       java.net.URL endpoint;
        try {
            endpoint = new java.net.URL(Compte_address);
        }
        catch (java.net.MalformedURLException e) {
            throw new javax.xml.rpc.ServiceException(e);
        }
        return getCompte(endpoint);
    }

    public banque.Compte getCompte(java.net.URL portAddress) throws javax.xml.rpc.ServiceException {
        try {
            banque.CompteSoapBindingStub _stub = new banque.CompteSoapBindingStub(portAddress, this);
            _stub.setPortName(getCompteWSDDServiceName());
            return _stub;
        }
        catch (org.apache.axis.AxisFault e) {
            return null;
        }
    }

    public void setCompteEndpointAddress(java.lang.String address) {
        Compte_address = address;
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        try {
            if (banque.Compte.class.isAssignableFrom(serviceEndpointInterface)) {
                banque.CompteSoapBindingStub _stub = new banque.CompteSoapBindingStub(new java.net.URL(Compte_address), this);
                _stub.setPortName(getCompteWSDDServiceName());
                return _stub;
            }
        }
        catch (java.lang.Throwable t) {
            throw new javax.xml.rpc.ServiceException(t);
        }
        throw new javax.xml.rpc.ServiceException("There is no stub implementation for the interface:  " + (serviceEndpointInterface == null ? "null" : serviceEndpointInterface.getName()));
    }

    /**
     * For the given interface, get the stub implementation.
     * If this service has no port for the given interface,
     * then ServiceException is thrown.
     */
    public java.rmi.Remote getPort(javax.xml.namespace.QName portName, Class serviceEndpointInterface) throws javax.xml.rpc.ServiceException {
        if (portName == null) {
            return getPort(serviceEndpointInterface);
        }
        java.lang.String inputPortName = portName.getLocalPart();
        if ("Compte".equals(inputPortName)) {
            return getCompte();
        }
        else  {
            java.rmi.Remote _stub = getPort(serviceEndpointInterface);
            ((org.apache.axis.client.Stub) _stub).setPortName(portName);
            return _stub;
        }
    }

    public javax.xml.namespace.QName getServiceName() {
        return new javax.xml.namespace.QName("http://DefaultNamespace", "CompteService");
    }

    private java.util.HashSet ports = null;

    public java.util.Iterator getPorts() {
        if (ports == null) {
            ports = new java.util.HashSet();
            ports.add(new javax.xml.namespace.QName("http://DefaultNamespace", "Compte"));
        }
        return ports.iterator();
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(java.lang.String portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        
if ("Compte".equals(portName)) {
            setCompteEndpointAddress(address);
        }
        else 
{ // Unknown Port Name
            throw new javax.xml.rpc.ServiceException(" Cannot set Endpoint Address for Unknown Port" + portName);
        }
    }

    /**
    * Set the endpoint address for the specified port name.
    */
    public void setEndpointAddress(javax.xml.namespace.QName portName, java.lang.String address) throws javax.xml.rpc.ServiceException {
        setEndpointAddress(portName.getLocalPart(), address);
    }

}
