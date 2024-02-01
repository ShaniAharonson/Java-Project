package DBDAO;

import DAO.CouponsDao;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;
import cls.ConnectionPool;
import cls.DButils;
import cls.SQLcommands;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CouponsDBDAO implements CouponsDao {
    private ConnectionPool connectionPool;

    @Override
    public void addCoupon(Integer id, Integer companyId, Integer categoryId, String title,
                          String description, Date startDate, Date endDate, Integer amount,
                          Double price) {
        Map<Integer, Object> params = new HashMap<>();
        //id, firstName, lastName, email, password
        params.put(1, id);
        params.put(2, categoryId);
        params.put(3, companyId);
        params.put(4, title);
        params.put(5, description);
        params.put(6, startDate);
        params.put(7, endDate);
        params.put(8, amount);
        params.put(9, price);


        DButils.runQuery(SQLcommands.addCoupon, params);
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        ResultSet update = DButils.runQueryFroResult(SQLcommands.updateCoupon);
        System.out.println(update);
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
        DButils.runQueryFroResult(SQLcommands.deleteCoupon,params);
    }
}
