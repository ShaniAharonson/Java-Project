package Facade;

import Exceptions.sqlExceptions;
import IFacades.ICustomer;
import JavaBeans.Category;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import cls.DButils;
import cls.SQLCustomerFacade;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class CustomerFacade extends ClientFacade implements ICustomer {
    public int getCustomerID() {
        return CustomerID;
    }

    public void setCustomerID(int customerID) {
        CustomerID = customerID;
    }

    private int CustomerID;

    @Override
    public boolean login(String email, String password) throws SQLException {
        ResultSet customerID = DButils.runQueryFroResult(SQLCustomerFacade.CustomerLogin);
        while (customerID.next()) {
            customerID.getInt(1);
            Customer customer = new Customer();
            customer.setPassword(customer.getPassword());
            customer.setEmail(customer.getEmail());
        }
        System.out.println("login successful!");
        return true;
    }

    public void PurchaseCoupon(Coupon coupon) {
        coupon = new Coupon();
        try {
            coupon = couponsDBDAO.getOneCoupon(coupon.getId());
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        }
        List<Coupon> customersCoupons = new ArrayList<>();
        try {
            customersCoupons = couponsDBDAO.getAllCustomerCoupons(coupon.getId());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        if (customersCoupons.contains(coupon)) {
            if ((coupon.getEndDate().after(new Date(System.currentTimeMillis())))) {
                if (coupon.getAmount() > 0) {
                    coupon.setAmount(coupon.getAmount() - 1);
                    couponsDBDAO.updateCoupon(coupon);
                    couponsDBDAO.addCoupon(coupon); // כדי שלא יוכלו לרכוש את אותו קופון שוב
                    //  הוספתי אותו לטבלה וכך אם יופיע אותו שם שוב, זה יתן התראה בSQL
                    System.out.println("You purchase a coupon! " + coupon.getTitle());

                }
            }
        }

    }

    public List<Coupon> getCustomerCoupons(int customerID) {
        List<Coupon> customer_Coupons_purchases = new ArrayList<>();
        try {
            customer_Coupons_purchases.add((Coupon) couponsDBDAO.getAllCustomerCoupons(customerID));
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return customer_Coupons_purchases;
    }

    public List<Coupon> get_All_Customer_Coupons_From_Specific_Category(int customerID, Category category) throws SQLException {
        return couponsDBDAO.get_All_Customer_Coupons_From_Specific_Category(customerID, category);
    }

    public List<Coupon> getCouponsByPrice(Integer customerID, Double price) throws SQLException {
        return couponsDBDAO.getCustomerCouponsByPrice(customerID, price);
    }

    public void customerDetails(String firstName, String lastName, String email) {
        customersDBDAO.customerDetails(firstName, lastName, email);
    }


}
