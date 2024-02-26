package DAO;

import Exceptions.AddingCouponException;
import Exceptions.CouponNotFoundException;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDao {
    /**
     * function for adding coupon to the system
     *
     * @param coupon
     * @throws sqlExceptions
     */
    public void addCoupon(Coupon coupon) throws sqlExceptions;

    /**
     * function to update coupon
     *
     * @param coupon
     */
    public void updateCoupon(Coupon coupon);

    /**
     * fucntion to delete exists coupon
     *
     * @param id
     */
    public void deleteCoupon(int id, int companyID);

    /**
     * function to get all coupons by list
     *
     * @param coupons
     * @return - list of coupons
     * @throws SQLException
     */
    public List<Coupon> getAllCoupons(ArrayList<Coupon> coupons) throws SQLException;

    /**
     * function to get specific coupon by  id
     * @param CouponID
     * @return
     * @throws sqlExceptions
     */
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions, SQLException, CouponNotFoundException;

    /**
     * adding coupon to purchase by customers
     * @param customerID
     * @param couponID
     */
    public void addCouponPurchase(int customerID, int couponID) throws AddingCouponException, SQLException;

    /**
     * deleting coupon to purchase by customers
     * @param customersID
     * @param couponID
     */
    public void deleteCouponPurchase(int customersID, int couponID);

    /**
     * function to get all company coupons by company ID
     * @param companyID
     * @return - company coupons
     * @throws SQLException
     */
    public List<Coupon> getAllCompanyCoupons(int companyID) throws SQLException;

    /**
     * function to get company coupons by a specific category
     * @param companyID
     * @param category
     * @return list of coupons by the same category
     * @throws sqlExceptions
     */
    public List<Coupon> getAllCompanyCouponFromSpecificCategory(int companyID, Category category) throws sqlExceptions;

    /**
     * getting all coupons of company up to max price
     * @param CompanyID
     * @param price
     * @return - the relevant coupons
     * @throws SQLException
     */
    public List<Coupon> getCouponByPrice(int CompanyID, double price) throws SQLException;

    /**
     * getting all customer coupon by customer id
     * @param CustomerID
     * @return - list of coupons
     * @throws SQLException
     */
    public List<Coupon> getAllCustomerCoupons(int CustomerID) throws SQLException;

    /**
     * getting all customer coupons by specific category
     * @param customerID
     * @param category
     * @return  list of coupons
     * @throws SQLException
     */
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException;

    /**
     * getting all customer's coupons up to max price
     * @param customerID
     * @param price
     * @return  the relevant coupons
     * @throws SQLException
     */
    public List<Coupon> getCustomerCouponsByPrice(Integer customerID, Double price) throws SQLException;

    /**
     * deleting expired coupons
     * @throws SQLException
     */
    public void deletingAllCouponsByEndDate() throws SQLException;
}

