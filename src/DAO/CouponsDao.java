package DAO;

import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDao {
    public void addCoupon(Coupon coupon) throws sqlExceptions;

    public void updateCoupon(Coupon coupon);

    public void deleteCoupon(int id);

    public List<Coupon> getAllCoupons(ArrayList<Coupon> coupons) throws SQLException;

    public Coupon getOneCoupon(int CouponID) throws sqlExceptions;

    public void addCouponPurchase(int customerID, int couponID);

    public void deleteCouponPurchase(int customersID, int couponID);

    public List<Coupon> getAllCompanyCoupons(int companyID);

    public List<Coupon> getAllCompanyCouponFromSpecificCategory(int companyID, Category category) throws sqlExceptions;

    public List<Coupon> getCouponByPrice(Integer CompanyID, double price) throws SQLException;
}

