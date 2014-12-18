/**
 * UsersService.java
 *
 * This file was auto-generated from WSDL
 * by the Apache Axis 1.4 Apr 22, 2006 (06:55:48 PDT) WSDL2Java emitter.
 */

package users;

public interface UsersService extends javax.xml.rpc.Service {
    public java.lang.String getUsersAddress();

    public users.Users getUsers() throws javax.xml.rpc.ServiceException;

    public users.Users getUsers(java.net.URL portAddress) throws javax.xml.rpc.ServiceException;
}
