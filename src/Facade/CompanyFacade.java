package Facade;

import Exceptions.*;
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
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = DButils.runQueryFroResult(SQLCompanyFacade.companyLogin, params);
        int companyId = -1;
        while (resultSet.next()) {

            companyId = resultSet.getInt(1);
        }
        setCompanyID(companyId);
        System.out.println("Company ID is: " + companyID);
        // getting company ID if login was successful
        return true;
    }

    /**
     * adding coupon to company
     * @param coupon
     * @throws SQLException
     */
    public void addCoupon(Coupon coupon) throws SQLException {

        couponsDBDAO.addCoupon(coupon);
    }

    /**
     * update coupon of company
     * @param coupon
     */
    public void updateCoupon(Coupon coupon) {
        couponsDBDAO.updateCoupon(coupon);
    }

    /**
     * delete coupon of company
     * @param couponID
     * @param companyID
     */
    public void deleteExistsCoupon(int couponID, int companyID) {

        couponsDBDAO.deleteCoupon(couponID, getCompanyID());

    }

    /**
     * getting all coupons of company
     * @param companyID
     * @return
     * @throws CompaniesNotFoundException
     */
    public List<Coupon> getAllCompanyCoupon(int companyID) throws CompaniesNotFoundException {
        try {
            return couponsDBDAO.getAllCompanyCoupons(companyID);
        } catch (SQLException e) {
            throw new CompaniesNotFoundException("Error with getting companies");
        }
    }

    /**
     * getting all coupons of company by category
     * @param CompanyID
     * @param category
     * @return - the relevant coupons from category
     * @throws CategoryErrorException
     */
    public List<Coupon> getAllCouponsFromSpecificCategory(int CompanyID, Category category) throws CategoryErrorException {
        try {
            return couponsDBDAO.getAllCompanyCouponFromSpecificCategory(companyID, category);
        } catch (sqlExceptions e) {
            throw new CategoryErrorException("Category not exists");
        }
    }

    /**
     * getting all coupons of company by max price
     * @param companyID
     * @param price
     * @return
     * @throws SQLException
     * @throws priceErrorException
     */
    public List<Coupon> getAllCouponsByPrice(int companyID, Double price) throws SQLException, priceErrorException {
        try {
            return couponsDBDAO.getCouponByPrice(companyID, price);
        } catch (SQLException err) {
            try {
                throw new sqlExceptions(err.getMessage());
            } catch (sqlExceptions e) {
                throw new priceErrorException("there is no coupons up to this price");
            }
        }
    }

    /**
     * getting company details
     * @return - details of the relevant company
     * @throws DetailsGetWrong
     */
    public Company companyDetails() throws DetailsGetWrong {
        try {
            return companiesDBDAO.getOneCompany(getCompanyID());
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());

        }
        throw new DetailsGetWrong("fail getting details");
    }

    /**
     * getting one coupon of company
     * @param couponID
     * @return - the specific coupon
     * @throws CouponNotFoundException
     */
    public Coupon getOneCoupon(int couponID) throws CouponNotFoundException {
        try {
            return couponsDBDAO.getOneCoupon(couponID);
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        }
        throw new CouponNotFoundException("no coupon id");
    }
}
