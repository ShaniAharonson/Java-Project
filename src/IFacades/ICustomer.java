package IFacades;

import Exceptions.AddingCouponException;
import Exceptions.CustomerNotFoundException;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface ICustomer {
    public boolean login(String email, String password) throws SQLException;
    public void PurchaseCoupon(int CustomerID, int couponID) throws AddingCouponException, SQLException;
    public List<Coupon> getCustomerCoupons(int customerID);
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException;
    public List<Coupon> getCouponsByPrice(Double maxPrice) throws SQLException;
    public Customer customerDetails() throws sqlExceptions, CustomerNotFoundException;
}
