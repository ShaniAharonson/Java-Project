package Facade;

import Exceptions.sqlExceptions;
import IFacades.ICompany;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import cls.DButils;
import cls.SQLCompanyFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompanyFacade extends ClientFacade implements ICompany {
    private int companyID;

    public int getCompanyID() {
        return companyID;
    }

    public void setCompanyID(int companyID) {
        this.companyID = companyID;
    }

    /**
     * login function for company
     *
     * @param email
     * @param password
     * @return - true or false
     * @throws SQLException
     */
    @Override
    public boolean login(String email, String password) throws SQLException {
        Map<Integer,Object> params = new HashMap<>();
        params.put(1,email);
        params.put(2,password);
        ResultSet resultSet = DButils.runQueryFroResult(SQLCompanyFacade.companyLogin,params);
        int companyId = -1;
        while (resultSet.next()) {
            companyId = resultSet.getInt(1);
        }
        setCompanyID(companyId);
        System.out.println("Company ID is: " + companyID);
        // getting company ID if login was successful
        return true;
    }


    public void addCoupon(Coupon coupon) throws SQLException {
        couponsDBDAO.addCoupon(coupon);
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

    /**
     * @param companyID
     * @param price
     * @return
     * @throws SQLException - if we got an sql exception for any reason
     */
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

    /**
     * getting company details
     * @param email
     * @param password
     * @return
     */
    public Company companyDetails(String email, String password) {
        try {
            if (login(email, password)) {
                try {
                    return companiesDBDAO.getOneCompany(getCompanyID());
                } catch (sqlExceptions e) {
                    throw new RuntimeException(e);
                }
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return null;
    }
}