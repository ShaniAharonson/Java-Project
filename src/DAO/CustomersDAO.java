package DAO;

import Exceptions.CustomerNotFoundException;
import Exceptions.sqlExceptions;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface CustomersDAO {
    /**
     * checking if customer already exists
     * @param email
     * @param password
     * @return - false 0 and true 1
     */
    public boolean isCustomerExists (String email, String password);

    /**
     * adding new customer to the system
     * @param customer
     * @throws sqlExceptions
     */
    public void addCustomer(Customer customer) throws sqlExceptions;

    /**
     * function to update a specific customer
     * @param customer
     * @throws sqlExceptions
     */
    public void updateCustomer(Customer customer) throws sqlExceptions;

    /**
     * deleting customer by giving its ID
     * @param id
     */
    public void deleteCustomer(int id);

    /**
     * function for getting all customers
     * @return - list of customers
     * @throws SQLException
     */
    public List<Customer> getAllCustomers() throws SQLException;

    /**
     * function for getting one customer by ID
     * @param CustomerID
     * @return - the relevant customer
     * @throws sqlExceptions
     */
    public Customer getOneCustomer(int CustomerID) throws sqlExceptions, CustomerNotFoundException;

    /**
     * function to perform customer details
     * @param firstName
     * @param lastName
     * @param email
     */
    public void customerDetails(String firstName, String lastName , String email);
}
