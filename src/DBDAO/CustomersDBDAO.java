package DBDAO;

import DAO.CustomersDAO;
import Exceptions.sqlExceptions;
import JavaBeans.Customer;
import cls.ConnectionPool;
import cls.DButils;
import cls.SQLCustomerFacade;
import cls.SQLcommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CustomersDBDAO implements CustomersDAO {
    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private ConnectionPool connectionPool;

    @Override
    public boolean isCustomerExists(String email, String password) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = DButils.runQueryFroResult(SQLcommands.isCustomerExists, params);
        return true;
    }

    @Override
    public void addCustomer(Customer customer) {
        Map<Integer, Object> params = new HashMap<>();
        //id, firstName, lastName, email, password
        params.put(1, customer.getId());
        params.put(2, customer.getFirstName());
        params.put(3, customer.getLastName());
        params.put(4, customer.getEmail());
        params.put(5, customer.getPassword());

        DButils.runQuery(SQLcommands.addCustomer, params);
    }

    @Override
    public void updateCustomer(Customer customer) {
        ResultSet update = DButils.runQueryFroResult(SQLcommands.updateCustomers);
        System.out.println(update);
    }

    @Override
    public void deleteCustomer(int id) {
        Map<Integer, Object> params = new HashMap<>();
        params.remove(1, id);
        DButils.runQuery(SQLcommands.deleteCustomer, params);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> myList = new ArrayList<>();
        ResultSet resultSet = DButils.runQueryFroResult(SQLcommands.getAllCustomers);
        while (resultSet.next()) {
            //id, firstName, lastName, email, password
            Integer id = resultSet.getInt(1);
            String First_Name = resultSet.getString(2);
            String Last_Name = resultSet.getString(3);
            String email = resultSet.getString(4);
            String password = resultSet.getString(5);
            myList.add(new Customer(id, First_Name, Last_Name, email, password));
        }
        return myList;
    }

    @Override
    public Customer getOneCustomer(int CustomerID) throws sqlExceptions {
        Customer customer = new Customer();
        ResultSet result = DButils.runQueryFroResult(SQLcommands.getOneCustomer);
        try {
            while (result.next()) {
                customer.setId(result.getInt(1));
                customer.setFirstName(result.getString(2));
                customer.setLastName(result.getString(3));
                customer.setEmail(result.getString(4));
                customer.setPassword(result.getString(5));

            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
        return customer;
    }

    @Override
    public void customerDetails(String firstName, String lastName, String email) {
        ResultSet result = DButils.runQueryFroResult(SQLCustomerFacade.getCustomerDetails);
        try {
            firstName = result.getString(2);
            lastName = result.getString(3);
            email = result.getString(4);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Customer full name: " + firstName + " " + lastName + "\n" +
                "email: " + email);


    }


}

