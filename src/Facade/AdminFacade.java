package Facade;

import Exceptions.sqlExceptions;
import IFacades.IAdmin;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade implements IAdmin {

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

    public void addCompany(Company company) throws sqlExceptions, SQLException {

        if (companiesDBDAO.getAllCompanies().contains(company.getName()))
            if (companiesDBDAO.getAllCompanies().contains(company.getEmail())) {
                System.out.println("Company already exists!");
            } else {
                companiesDBDAO.addCompany(company);
            }

    }

    public void updateCompany(Company company) throws sqlExceptions {
        companiesDBDAO.updateCompany(company);
    }

    public void deleteCompany(int companyID) throws SQLException {
        // Company company  = new Company();
        List<Coupon> companyCoupons = couponsDBDAO.getAllCompanyCoupons(companyID);
        for (Coupon c : companyCoupons) {
            couponsDBDAO.deleteCoupon(c.getId());
        }
        companiesDBDAO.deleteCompany(companyID);

    }
// Customers coupons will delete because of cascading in foreign key


    public List<Company> getAllCompanies() throws SQLException {
        return companiesDBDAO.getAllCompanies();

    }


    public Company getOneCompany(int companyID) throws sqlExceptions {
        return companiesDBDAO.getOneCompany(companyID);
    }


    public void addCustomer(Customer customer) throws sqlExceptions {
        try {
            if (customersDBDAO.getAllCustomers().contains(customer.getEmail())){
                System.out.println("Customer already exists!");
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        customersDBDAO.addCustomer(customer);
    }

    public void updateCustomer(Customer customer) throws Exception, SQLException {
        customersDBDAO.updateCustomer(customer);
    }

    public void deleteCustomer(int customerID) throws SQLException {
        List<Coupon> customerCoupons = couponsDBDAO.getAllCustomerCoupons(customerID);
        for (Coupon c : customerCoupons) {
            couponsDBDAO.deleteCoupon(c.getId());
        }
        customersDBDAO.deleteCustomer(customerID);
    }

    public List<Customer> getAllCustomers() throws SQLException {
        return customersDBDAO.getAllCustomers();
    }

    public Customer getOneCustomer(int customerID) throws sqlExceptions {
        return customersDBDAO.getOneCustomer(customerID);
    }
}