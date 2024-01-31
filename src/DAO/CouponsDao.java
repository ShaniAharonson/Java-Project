package DAO;

import Exceptions.sqlExceptions;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface CouponsDao {
    public void addCoupon(Integer id, Integer companyId, Integer categoryId, String title,
                          String description, Date startDate, Date endDate, Integer amount, Double price);
    public void updateCoupon(Coupon coupon);
    public void deleteCoupon(int id);
    public List<Coupon> getAllCoupons(ArrayList<Coupon> coupons) throws SQLException;
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions;
    public void addCouponPurchase(int customerID, int couponID);
    public void deleteCouponPurchase(int customersID, int couponID);
}
