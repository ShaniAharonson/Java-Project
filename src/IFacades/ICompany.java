package IFacades;

import Exceptions.*;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.util.List;

public interface ICompany {
    public boolean login(String email, String password) throws SQLException;
    public void addCoupon(Coupon coupon) throws SQLException;
    public void updateCoupon(Coupon coupon) throws sqlExceptions;
    public void deleteExistsCoupon(int couponID, int companyID);
    public List<Coupon> getAllCompanyCoupon(int companyID) throws CompaniesNotFoundException;
    public List<Coupon> getAllCouponsFromSpecificCategory(int CompanyID, Category category) throws CouponNotFoundException, CategoryErrorException;
    public List<Coupon> getAllCouponsByPrice(int companyID, Double price) throws SQLException, priceErrorException;
    public Company companyDetails() throws DetailsGetWrong;

}
