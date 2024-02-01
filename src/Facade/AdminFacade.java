package Facade;

import Exceptions.sqlExceptions;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminFacade extends ClientFacade {


    @Override
    public boolean isCompanyExists(String email, String password) throws sqlExceptions {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Login successfully");
            return true;
        } else {
            System.out.println("Wrong email or password!");
        }
        return false;
    }

    @Override
    public void addCompany(Integer id, String name, String email, String password) throws sqlExceptions {

        try {

        } catch (sqlExceptions exceptions) {
            throw new sqlExceptions(exceptions.getMessage());
        }
    }

    @Override
    public void updateCompany(Company company) throws sqlExceptions {
        try {

        } catch (sqlExceptions err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    @Override
    public void deleteCompany(int id) {

    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        return getAllCompanies();
    }

    @Override
    public Company getOneCompany(int CompanyID) throws sqlExceptions {
        return null;
    }

    @Override
    public void addCoupon(Integer id, Integer companyId, Integer categoryId, String title, String description, Date startDate, Date endDate, Integer amount, Double price) {

    }

    @Override
    public void updateCoupon(Coupon coupon) {

    }

    @Override
    public void deleteCoupon(int id) {

    }

    @Override
    public List<Coupon> getAllCoupons(ArrayList<Coupon> coupons) throws SQLException {
        return null;
    }

    @Override
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions {
        return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {

    }

    @Override
    public void deleteCouponPurchase(int customersID, int couponID) {

    }

    @Override
    public boolean isCustomerExists(String email, String password) {
        return false;
    }

    @Override
    public void addCustomer(Integer id, String firstName, String lastName, String email, String password) {

    }

    @Override
    public void updateCustomer(Customer customer) {

    }

    @Override
    public void deleteCustomer(int id) {

    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return null;
    }

    @Override
    public Customer getOneCustomer(int CustomerID) throws sqlExceptions {
        return null;
    }
}