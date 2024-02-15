package IFacades;

import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface ICompany {
    public boolean login(String email, String password) throws SQLException;
    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws sqlExceptions;
    public void deleteExistsCoupon(Coupon coupon);
    public List<Coupon> getAllCompanyCoupon(int companyID);
    public List<Coupon> getAllCouponsFromSpecificCategory(int CompanyID, Category category);
    public List<Coupon> getAllCouponsByPrice(int companyID, Double price) throws SQLException;
    public void companyDetails(String name, String email);

}
