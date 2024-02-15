package IFacades;

import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface ICustomer {
    public boolean login(String email, String password) throws SQLException;
    public void PurchaseCoupon(Coupon coupon);
    public List<Coupon> getCustomerCoupons(int customerID);
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException;
    public List<Coupon> getCouponsByPrice(Integer customerID, Double price) throws SQLException;
    public void customerDetails(String firstName, String lastName, String email);
}
