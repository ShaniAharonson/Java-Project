package Facade;

import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import cls.DButils;
import cls.SQLCompanyFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class CompanyFacade extends ClientFacade {
    @Override
    public boolean login(String email, String password) throws SQLException {

        ResultSet companyID = DButils.runQueryFroResult(SQLCompanyFacade.companyLogin);
        while (companyID.next()) {
            companyID.getInt(1);
            Company company = new Company();
            company.setPassword(company.getPassword());
            company.setEmail(company.getEmail());
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
                throw new Exception("Coupon already exists!");
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else {
            couponsDBDAO.addCoupon(coupon);
        }
    }

    public void updateCoupon(Coupon coupon) throws sqlExceptions {
        couponsDBDAO.updateCoupon(coupon);
    }

    public void deleteExistsCoupon(Coupon coupon) {

        couponsDBDAO.deleteCoupon(coupon.getId());

    }

    public List<Coupon> getAllCompanyCoupon(int companyID) {
        try {
            return couponsDBDAO.getAllCompanyCoupons(companyID);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Coupon> getAllCouponsFromSpecificCategory(int CompanyID, Category category) {
        try {
            return couponsDBDAO.getAllCompanyCouponFromSpecificCategory(companyID, category);
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        }
    }

    public List<Coupon> getAllCouponsByPrice(int companyID, Double price) throws SQLException {
        try {
            return couponsDBDAO.getCouponByPrice(companyID, price);
        } catch (SQLException err) {
            try {
                throw new sqlExceptions(err.getMessage());
            } catch (sqlExceptions e) {
                throw new RuntimeException(e);
            }
        }
    }
    public void companyDetails(String name, String email) {
        companiesDBDAO.companyDetails(name,email);
    }

}
