package Facade;

import DBDAO.CouponsDBDAO;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;
import cls.DButils;
import cls.SQLCompanyFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) {

        ResultSet companyID = DButils.runQueryFroResult(SQLCompanyFacade.companyLogin);
        while (true) {
            try {
                if (!companyID.next()) {
                    System.out.println("There is no user with this ID");
                    break;
                }

            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
            try {
                int CompanyID = companyID.getInt(1);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
        System.out.println("Company ID is: " + companyID);
        // getting company ID if login was successful
        return true;
    }

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    private int companyID;

    public void addCoupon(Coupon coupon) throws SQLException {
        if (companiesDBDAO.getAllCompanies().contains(coupon.getCompanyID())) {

            try {
                throw new Exception("You cannot add coupon with the same name");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            couponsDBDAO.addCoupon(coupon);
        }
    }

    public void updateCoupon(Coupon coupon) throws sqlExceptions {
        try {
            couponsDBDAO.updateCoupon(coupon);
        } catch (SQLException err) {
            throw new sqlExceptions("update coupon was failed!");
        }
    }

    public void deleteExistsCoupon(Coupon coupon) {

        try {
            couponsDBDAO.deleteCoupon(coupon.getId());

        } catch (SQLException err) {
            try {
                throw new sqlExceptions(err.getMessage());
            } catch (sqlExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }

    public List<Coupon> getAllCompanyCoupon(int companyID){
        try {
            return couponsDBDAO.getAllCompanyCoupons(companyID);
        } catch (SQLException err){
            try {
                throw new sqlExceptions(err.getMessage());
            } catch (sqlExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }
    public List<Coupon> getAllCouponsFromSpecificCategory(int CompanyID, Category category){
        try {
            return couponsDBDAO.getAllCompanyCouponFromSpecificCategory(companyID,category);
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        }
    }
public List<Coupon> getAllCouponsByPrice(int companyID, Double price) throws SQLException {
        try {
            return couponsDBDAO.getCouponByPrice(companyID, price);
        }catch (SQLException err){
            try {
                throw new sqlExceptions(err.getMessage());
            } catch (sqlExceptions e) {
                throw new RuntimeException(e);
            }
        }
}


}
