package cls;

public class SQLCustomerFacade {
    public static final String CustomerLogin = " " +
            "SELECT id as CustomerID " +
            "FROM javaproject.customers " +
            "where email = ? and password = ?";
    public static final String getAllCustomerCoupons = " " +
            "SELECT coupon_id as CouponID " +
            "FROM javaproject.customers_vs_coupons " +
            "where customers_id = ?";

    public static final String getGetAllCustomerCouponsFromSpecificCategory = " " +
            "SSELECT coupons.title FROM javaproject.customers_vs_coupon join javaproject.coupons" +
            " ON coupons.id = customers_vs_coupons.coupon_id WHERE customers_vs_coupons.customers_id = ? " +
            "AND coupons.category_id = ?;";

    public static final String getCouponByPrice = " " +
            "SSELECT coupons.title FROM javaproject.customers_vs_coupon join javaproject.coupons " +
            "ON coupons.id = customers_vs_coupons.coupon_id " +
            "WHERE customers_vs_coupons.customers_id = ? AND coupons.price <= ?;";

    public static final String getCustomerDetails = " " +
            "SELECT * FROM javaproject.customers where id = ? ";
}
