package DAO;

import Exceptions.sqlExceptions;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO {
    public boolean isCustomerExists (String email, String password);
    public void addCustomer(Customer customer) throws sqlExceptions;
    public void updateCustomer(Customer customer) throws sqlExceptions;
    public void deleteCustomer(int id);
    public List<Customer> getAllCustomers() throws SQLException;
    public Customer getOneCustomer(int CustomerID) throws sqlExceptions;
    public void customerDetails(String firstName, String lastName , String email);
}
