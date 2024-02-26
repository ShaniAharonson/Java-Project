package Facade;

import Exceptions.AddingCouponException;
import Exceptions.CouponNotFoundException;
import Exceptions.CustomerNotFoundException;
import Exceptions.sqlExceptions;
import IFacades.ICustomer;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import cls.DButils;
import cls.SQLCustomerFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
     *
     * @param email
     * @param password
     * @return - new customer
     * @throws SQLException
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet results = DButils.runQueryFroResult(SQLCustomerFacade.CustomerLogin, params);
        int customerID = -1;
        while (results.next()) {
            customerID = results.getInt(1);
        }
        System.out.println("login successful!");
        setCustomerID(customerID);
        return true;
    }

    /**
     * purchasing coupon
     * @param customerID
     * @param couponID
     * @throws AddingCouponException
     * @throws SQLException
     */
    public void PurchaseCoupon(int customerID, int couponID) throws AddingCouponException, SQLException {

        couponsDBDAO.addCouponPurchase(customerID, couponID);



    }
    /**
     * getting all customer's coupons
     * @param customerID
     * @return - the relevant coupons
     */
    public List<Coupon> getCustomerCoupons(int customerID) {
        List<Coupon> customer_Coupons_purchases = new ArrayList<>();
        try {
            customer_Coupons_purchases = couponsDBDAO.getAllCustomerCoupons(customerID);

        } catch (SQLException e) {
            System.out.println(e.getMessage());
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
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID,Category category) throws SQLException {

        return couponsDBDAO.get_All_Customer_Coupons_From_Specific_Category(getCustomerID(), category);
    }

    /**
     * getting coupon up to max price
     * @param maxPrice
     * @return
     * @throws SQLException
     */
    public List<Coupon> getCouponsByPrice(Double maxPrice) throws SQLException {
        return couponsDBDAO.getCustomerCouponsByPrice(getCustomerID(),maxPrice);
    }

    /**
     * the details of customer
     * @return - customer by its id
     * @throws sqlExceptions
     * @throws CustomerNotFoundException
     */
    public Customer customerDetails() throws sqlExceptions, CustomerNotFoundException {


        return customersDBDAO.getOneCustomer(this.CustomerID);
    }

    /**
     * getting one coupon by id
     * @param couponID
     * @return
     */
    public Coupon getOneCoupon(int couponID) {

        try {
            return couponsDBDAO.getOneCoupon(couponID);
        } catch (sqlExceptions | CouponNotFoundException e) {
            throw new RuntimeException(e);
        }

    }
}
