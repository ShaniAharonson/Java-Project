package DBDAO;

import DAO.CouponsDao;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;
import cls.ConnectionPool;
import cls.DButils;
import cls.SQLCustomerFacade;
import cls.SQLcommands;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDao {
    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private ConnectionPool connectionPool;

    @Override
    public void addCoupon(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        //id, firstName, lastName, email, password
        params.put(1, coupon.getId());
        params.put(2, coupon.getCategory());
        params.put(3, coupon.getCompanyID());
        params.put(4, coupon.getTitle());
        params.put(5, coupon.getDescription());
        params.put(6, coupon.getStartDate());
        params.put(7, coupon.getEndDate());
        params.put(8, coupon.getAmount());
        params.put(9, coupon.getPrice());
        params.put(10, coupon.getImage());


        DButils.runQuery(SQLcommands.addCoupon, params);
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        ResultSet update = DButils.runQueryFroResult(SQLcommands.updateCoupon);
        System.out.println("The updated coupon: " + update);
    }

    @Override
    public void deleteCoupon(int id) {
        Map<Integer, Object> params = new HashMap<>();
        params.remove(1, id);
        DButils.runQuery(SQLcommands.deleteCoupon, params);
    }

    @Override
    public List<Coupon> getAllCoupons(ArrayList<Coupon> coupons) throws SQLException {
        List<Coupon> myList = new ArrayList<>();
        ResultSet resultSet = DButils.runQueryFroResult(SQLcommands.getAllCoupons);
        while (resultSet.next()) {
            //Integer id, Integer companyID, Category category, String title,
            //String description, Date startDate, Date endDate,
            //int amount, Double price, String image
            Integer id = resultSet.getInt(1);
            Integer companyID = resultSet.getInt(2);
            Category category = (Category) resultSet.getObject(3);
            String title = resultSet.getString(4);
            String description = resultSet.getString(5);
            Date startDate = resultSet.getDate(6);
            Date endDate = resultSet.getDate(7);
            Integer amount = resultSet.getInt(8);
            Double price = resultSet.getDouble(9);
            String image = resultSet.getString(10);

            myList.add(new Coupon(id, companyID, category, title, description,
                    startDate, endDate, amount, price, image));

        }
        return myList;
    }

    @Override
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions {
        Coupon coupon = new Coupon();
        ResultSet result = DButils.runQueryFroResult(SQLcommands.getOneCoupon);
        try {
            while (result.next()) {
                coupon.setId(result.getInt(1));
                coupon.setCompanyID(result.getInt(2));
                coupon.setCategory((Category) result.getObject(3));
                coupon.setTitle(result.getString(4));
                coupon.setDescription(result.getString(5));
                coupon.setStartDate(result.getDate(6));
                coupon.setEndDate(result.getDate(7));
                coupon.setAmount(result.getInt(8));
                coupon.setPrice(result.getDouble(9));
                coupon.setImage(result.getString(10));

            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
        return coupon;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, couponID);

        DButils.runQuery(SQLcommands.addCouponPurchase, params);
    }

    @Override
    public void deleteCouponPurchase(int customersID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.remove(1, customersID);
        params.remove(2, couponID);
        DButils.runQueryFroResult(SQLcommands.deleteCoupon, params);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyID) throws SQLException {
        List<Coupon> companyCoupon = new ArrayList<>();
        ResultSet theCoupons = DButils.runQueryFroResult(SQLcommands.getAllCompanyCoupon);
        while (theCoupons.next()) {
            companyCoupon.add((Coupon) theCoupons);
        }
        return companyCoupon;
    }

    @Override
    public List<Coupon> getAllCompanyCouponFromSpecificCategory(int companyID, Category category) throws sqlExceptions {
        List<Coupon> couponsFromCategory = new ArrayList<>();
        ResultSet coupons = DButils.runQueryFroResult(SQLcommands.getGetAllCompaniesCouponFromSpecificCategory);
        try {
            while (coupons.next()) {
                companyID = coupons.getInt(2);
                category = (Category) coupons.getObject(3);
                couponsFromCategory.add((Coupon) coupons);
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
        return couponsFromCategory;
    }

    @Override
    public List<Coupon> getCouponByPrice(Integer CompanyID, double price) throws SQLException {
        List<Coupon> couponsByPrice = new ArrayList<>();
        ResultSet coupons = DButils.runQueryFroResult(SQLcommands.getCouponsByPrice);
        while (coupons.next()) {
            CompanyID = coupons.getInt(2);
            price = coupons.getDouble(9);
            couponsByPrice.add((Coupon) coupons);
        }
        return couponsByPrice;
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int CustomerID) throws SQLException {
        List<Coupon> myList = new ArrayList<>();
        ResultSet theCoupons = DButils.runQueryFroResult(SQLCustomerFacade.getAllCustomerCoupons);
        while (theCoupons.next()) {
            myList.add((Coupon) theCoupons);
        }
        return myList;


    }

    @Override
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException {
        List<Coupon> couponsFromCategory = new ArrayList<>();
        ResultSet coupon = DButils.runQueryFroResult(SQLCustomerFacade.getGetAllCustomerCouponsFromSpecificCategory);
        while (coupon.next()){
            couponsFromCategory.add((Coupon) coupon);
        }
        return couponsFromCategory;
    }

    @Override
    public List<Coupon> getCustomerCouponsByPrice(Integer customerID, Double price) throws SQLException {
        List<Coupon> couponsByPrice = new ArrayList<>();
        ResultSet coupons = DButils.runQueryFroResult(SQLCustomerFacade.getCouponByPrice);
        customerID = coupons.getInt(1);
        price = coupons.getDouble(9);
        couponsByPrice.add((Coupon) coupons);

        return couponsByPrice;
    }
}


