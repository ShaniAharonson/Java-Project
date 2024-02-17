package Facade;

import Exceptions.sqlExceptions;
import IFacades.ICustomer;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import cls.DButils;
import cls.SQLCustomerFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerFacade extends ClientFacade implements ICustomer {
    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    private int CustomerID;

    /**
     * login customer
     * @param email
     * @param password
     * @return - new customer and message of customer ID
     * @throws SQLException
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        ResultSet customerID = DButils.runQueryFroResult(SQLCustomerFacade.CustomerLogin);
        while (customerID.next()) {
            customerID.getInt(1);
            Customer customer = new Customer();
            customer.setPassword(customer.getPassword());
            customer.setEmail(customer.getEmail());
        }
        System.out.println("login successful!");
        return true;
    }

    /**
     * purchasing new coupon
     * first  getting coupon by ID
     * second getting all coupons
     * third checking coupon details
     * @param coupon
     */
    public void PurchaseCoupon(Coupon coupon) {
        coupon = new Coupon();
        try {
            // getting one coupon by ID
            coupon = couponsDBDAO.getOneCoupon(coupon.getId());
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        }
        List<Coupon> customersCoupons = new ArrayList<>();
        try {
            // getting all coupons
            customersCoupons = couponsDBDAO.getAllCustomerCoupons(coupon.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (customersCoupons.contains(coupon)) {
            // checking if coupon does not valid
            if ((coupon.getEndDate().after(new Date(String.valueOf(LocalDate.now()))))) {
                if (coupon.getAmount() > 0) {
                    coupon.setAmount(coupon.getAmount() - 1); // changing the amount of coupon
                    couponsDBDAO.updateCoupon(coupon); // updating details of coupon
                    couponsDBDAO.addCoupon(coupon); //now they cant purchase the same coupon
                    /*  I added it to the table and if it will appear there, it will pop an error
                    of duplicate rows */
                    System.out.println("You purchase a coupon! " + coupon.getTitle());

                }
            }
        }

    }

    /**
     * getting all customer's coupons
     * @param customerID
     * @return - the relevant coupons
     */
    public List<Coupon> getCustomerCoupons(int customerID) {
        List<Coupon> customer_Coupons_purchases = new ArrayList<>();
        try {
            customer_Coupons_purchases.add((Coupon) couponsDBDAO.getAllCustomerCoupons(customerID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer_Coupons_purchases;
    }

    /**
     * getting all customer coupons by specific category
     * @param customerID
     * @param category
     * @return - relevant coupons from the specific category
     * @throws SQLException
     */
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException {
        return couponsDBDAO.get_All_Customer_Coupons_From_Specific_Category(customerID, category);
    }

    /**
     * getting coupons up to max price
     * @param customerID
     * @param price
     * @return the relevant coupons
     * @throws SQLException
     */
    public List<Coupon> getCouponsByPrice(Integer customerID, Double price) throws SQLException {
        return couponsDBDAO.getCustomerCouponsByPrice(customerID, price);
    }

    /**
     * customer detalis by customerID
     * @param firstName
     * @param lastName
     * @param email
     */
    public void customerDetails(String firstName, String lastName, String email) {
        customersDBDAO.customerDetails(firstName, lastName, email);
    }


}
