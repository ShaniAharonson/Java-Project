package Facade;

import Exceptions.sqlExceptions;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import cls.DButils;
import cls.SQLAdminFacade;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminFacade extends ClientFacade {

    public boolean login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Login successfully");
            return true;
        } else if (email == null || password == null) {
            System.out.println("are you an admin?");
            return false;
        } else {
            System.out.println("Wrong email or password!");
            return false;
        }
    }

    public void addCompany(Integer id, String name, String email, String password) throws sqlExceptions {

        try {

        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    public void updateCompany(Company company) throws sqlExceptions {
        try {
            DButils.runQuery(SQLAdminFacade.updateExistingCompany);
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    public void deleteCompany(int id) {
// coupons will delete because of cascading in foreign key
    }

    public List<Company> getAllCompanies() throws SQLException {
        List<Company> myList = new ArrayList<>();
        DButils.runQueryFroResult(SQLAdminFacade.returnAllCompanies);
        return myList;
    }


    public Company getOneCompany(int CompanyID) throws sqlExceptions {
        return (Company) DButils.runQueryFroResult(SQLAdminFacade.returnSpecificCompany);
    }


    public void addCustomer(Integer id, String firstName, String lastName, String email, String password) throws sqlExceptions {
        try {
            Customer customer = new Customer();
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    public void updateCustomer(Customer customer) throws sqlExceptions {
        try {

            DButils.runQuery(SQLAdminFacade.updateExistingCustomer);
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    public void deleteCustomer(int id) {
        DButils.runQuery(SQLAdminFacade.deleteExistingCustomer);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        List<Customer> customersList = new ArrayList<>();
        DButils.runQueryFroResult(SQLAdminFacade.returnAllCustomers);
        return customersList;
    }

     public void getOneCustomer(int CustomerID) throws sqlExceptions {
        DButils.runQueryFroResult(SQLAdminFacade.returnOneCustomer);
    }
}