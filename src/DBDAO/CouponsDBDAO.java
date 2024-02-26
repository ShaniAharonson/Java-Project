package DBDAO;

import DAO.CouponsDao;
import Exceptions.AddingCouponException;
import Exceptions.CouponNotFoundException;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Coupon;
import cls.*;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
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
        params.put(1, coupon.getCompanyID());
        params.put(2, coupon.getCategory());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());


        DButils.runQuery(SQLcommands.addCoupon, params);
    }

    @Override
    public void updateCoupon(Coupon coupon) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(2, coupon.getCategory());
        params.put(1, coupon.getCompanyID());
        params.put(3, coupon.getTitle());
        params.put(4, coupon.getDescription());
        params.put(5, coupon.getStartDate());
        params.put(6, coupon.getEndDate());
        params.put(7, coupon.getAmount());
        params.put(8, coupon.getPrice());
        params.put(9, coupon.getImage());
        DButils.runQuery(SQLcommands.updateCoupon, params);

    }

    @Override
    public void deleteCoupon(int id, int companyID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        params.put(2, companyID);
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
            int category = resultSet.getInt(3);
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
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions, CouponNotFoundException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, CouponID);
        ResultSet result = DButils.runQueryFroResult(SQLcommands.getOneCoupon, params);
        try {
            while (result.next()) {
                int ID = result.getInt(1);
                int companyID = result.getInt(2);
                int category = result.getInt(3);
                String title = result.getString(4);
                String description = result.getString(5);
                Date StartDate = result.getDate(6);
                Date EndDate = result.getDate(7);
                int Amount = result.getInt(8);
                double Price = result.getDouble(9);
                String image = result.getString(10);
                return new Coupon(ID, companyID, category, title, description, StartDate,
                        EndDate, Amount, Price, image);

            }
        } catch (SQLException e) {
            throw new CouponNotFoundException("cannot find coupon!");
        }
      return null;
    }

    @Override
    public void addCouponPurchase(int customerID, int couponID) throws AddingCouponException, SQLException {
        //get the relevant coupon by copun id -> sql
        //SQLcommands.getOneCoupon
        //get the coupon that the customer wants to buy
//        Coupon coupon = new Coupon();
        Map<Integer, Object> paramsFind = new HashMap<>();
        paramsFind.put(1, couponID);

        ResultSet results = DButils.runQueryFroResult(SQLcommands.getOneCoupon, paramsFind);
        //create the coupon from result
        while (results.next()) {
            int ID = results.getInt(1);
            int companyID = results.getInt(2);
            int category = results.getInt(3);
            String title = results.getString(4);
            String description = results.getString(5);
            Date StartDate = results.getDate(6);
            Date EndDate = results.getDate(7);
            int Amount = results.getInt(8);
            double Price = results.getDouble(9);
            String image = results.getString(10);
            Coupon coupon = new Coupon(ID, companyID, category, title, description, StartDate,
                    EndDate, Amount, Price, image);

            Map<Integer, Object> params = new HashMap<>();
            params.put(1, customerID);
            params.put(2, couponID);

            DButils.runQuery(SQLcommands.addCouponPurchase, params);

            if ((coupon.getEndDate().after(Date.valueOf(LocalDate.now())))) {
                if (coupon.getAmount() > 0) {
                    coupon.setAmount(coupon.getAmount() - 1); // changing the amount of coupon
                    updateCoupon(coupon); // updating details of coupon
                    addCoupon(coupon);

                    System.out.println("You purchase a coupon! " + coupon.getTitle());

                } else {
                    throw new AddingCouponException("Cannot adding coupon!");
                }

            }
        }
    }

    @Override
    public void deleteCouponPurchase(int customersID, int couponID) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customersID);
        params.put(2, couponID);
        DButils.runQueryFroResult(SQLcommands.deleteCouponPurchase, params);
    }

    @Override
    public List<Coupon> getAllCompanyCoupons(int companyID) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        ResultSet theCoupons = DButils.runQueryFroResult(SQLcommands.getAllCompanyCoupon, params);
        List<Coupon> myList = new ArrayList<>();
        while (theCoupons.next()) {
            Integer id = theCoupons.getInt(1);
            companyID = theCoupons.getInt(2);
            int category = theCoupons.getInt(3);
            String title = theCoupons.getString(4);
            String description = theCoupons.getString(5);
            Date startDate = theCoupons.getDate(6);
            Date endDate = theCoupons.getDate(7);
            Integer amount = theCoupons.getInt(8);
            Double price = theCoupons.getDouble(9);
            String image = theCoupons.getString(10);
            myList.add(new Coupon(id, companyID, category, title, description,
                    startDate, endDate, amount, price, image));

        }
        return myList;
    }

    @Override
    public List<Coupon> getAllCompanyCouponFromSpecificCategory(int companyID, Category category) throws sqlExceptions {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, companyID);
        params.put(2, category.ordinal()+1);
        List<Coupon> couponsFromCategory = new ArrayList<>();
        ResultSet coupons = DButils.runQueryFroResult(SQLCompanyFacade.getGetAllCompaniesCouponFromSpecificCategory, params);
        try {
            while (coupons.next()) {
                Integer id = coupons.getInt(1);
                companyID = coupons.getInt(2);
                int categoryID = coupons.getInt(3);
                String title = coupons.getString(4);
                String description = coupons.getString(5);
                Date startDate = coupons.getDate(6);
                Date endDate = coupons.getDate(7);
                Integer amount = coupons.getInt(8);
                Double price = coupons.getDouble(9);
                String image = coupons.getString(10);
                couponsFromCategory.add(new Coupon(id, companyID, categoryID, title, description,
                        startDate, endDate, amount, price, image));
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
        return couponsFromCategory;
    }

    @Override
    public List<Coupon> getCouponByPrice(int CompanyID, double price) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, CompanyID);
        params.put(2, price);
        List<Coupon> couponsByPrice = new ArrayList<>();
        ResultSet coupons = DButils.runQueryFroResult(SQLCompanyFacade.getCouponsByPrice,params);
        while (coupons.next()) {
            Integer id = coupons.getInt(1);
            CompanyID = coupons.getInt(2);
            int categoryID = coupons.getInt(3);
            String title = coupons.getString(4);
            String description = coupons.getString(5);
            Date startDate = coupons.getDate(6);
            Date endDate = coupons.getDate(7);
            Integer amount = coupons.getInt(8);
            price = coupons.getDouble(9);
            String image = coupons.getString(10);
            couponsByPrice.add(new Coupon(id, CompanyID, categoryID, title, description,
                    startDate, endDate, amount, price, image));
        }
        return couponsByPrice;
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int CustomerID) throws SQLException {

        Map<Integer, Object> params = new HashMap<>();
        params.put(1, CustomerID);
        // Result set - contains a list of coupon IDs for this customer
        ResultSet results = DButils.runQueryFroResult(SQLCustomerFacade.getAllCustomerCoupons, params);
        List<Coupon> myList = new ArrayList<>();
        //int couponId = -1;
        while (results.next()) {
             Integer id = results.getInt(1);
            int CompanyID = results.getInt(2);
            int categoryID = results.getInt(3);
            String title = results.getString(4);
            String description = results.getString(5);
            Date startDate = results.getDate(6);
            Date endDate = results.getDate(7);
            Integer amount = results.getInt(8);
            Double price = results.getDouble(9);
            String image = results.getString(10);
            Coupon tempCoupon = (new Coupon(id, CompanyID, categoryID, title, description,
                    startDate, endDate, amount, price, image));
            myList.add(tempCoupon);

        }
        return myList;
    }
    @Override
    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, category.ordinal()+1);
        ResultSet coupon = DButils.runQueryFroResult(SQLCustomerFacade.getGetAllCustomerCouponsFromSpecificCategory,params);
        List<Coupon> couponsFromCategory = new ArrayList<>();
        while (coupon.next()) {
            Integer id = coupon.getInt(1);
            int CompanyID = coupon.getInt(2);
            int categoryID = coupon.getInt(3);
            String title = coupon.getString(4);
            String description = coupon.getString(5);
            Date startDate = coupon.getDate(6);
            Date endDate = coupon.getDate(7);
            Integer amount = coupon.getInt(8);
            Double price = coupon.getDouble(9);
            String image = coupon.getString(10);
            Coupon tempCoupon = (new Coupon(id, CompanyID, categoryID, title, description,
                    startDate, endDate, amount, price, image));
            couponsFromCategory.add(tempCoupon);
        }
        return couponsFromCategory;
    }

    @Override
    public List<Coupon> getCustomerCouponsByPrice(Integer customerID, Double price) throws SQLException {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, customerID);
        params.put(2, price);
        ResultSet coupons = DButils.runQueryFroResult(SQLCustomerFacade.getCouponByPrice,params);
        List<Coupon> couponsByPrice = new ArrayList<>();
        while (coupons.next()) {
            int id = coupons.getInt(1);
            int companyId = coupons.getInt(2);
            int categoryID = coupons.getInt(3);
            String title = coupons.getString(4);
            String description = coupons.getString(5);
            Date startDate = coupons.getDate(6);
            Date endDate = coupons.getDate(7);
            int amount = coupons.getInt(8);
            price = coupons.getDouble(9);
            String image = coupons.getString(10);
            Coupon tempCoupon = (new Coupon(id, companyId, categoryID, title, description, startDate,
                    endDate, amount, price, image));
            couponsByPrice.add(tempCoupon);
        }
        return couponsByPrice;
    }

    @Override
    public void deletingAllCouponsByEndDate() throws SQLException {
        DButils.runQuery(SQLTasks.getCouponsByEndDate);


    }

}





