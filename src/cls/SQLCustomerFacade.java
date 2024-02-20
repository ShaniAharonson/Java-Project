package cls;

public class SQLCustomerFacade {
    public static final String CustomerLogin = " " +
            "SELECT id as CustomerID " +
            "FROM javaproject.customers " +
            "where email = ? and password = ?";
    public static final String getAllCustomerCoupons = " " +
            "SELECT * FROM  javaproject.coupons " +
            "JOIN javaproject.customers_vs_coupons ON coupons.id = customers_vs_coupons.coupon_id " +
            "WHERE customers_id = ?";

    public static final String getGetAllCustomerCouponsFromSpecificCategory = " " +
            "SELECT coupons.title FROM javaproject.customers_vs_coupons join javaproject.coupons " +
            "ON coupons.id = customers_vs_coupons.coupon_id " +
            "WHERE customers_vs_coupons.customers_id = ? AND coupons.category_id = ?";

    public static final String getCouponByPrice = " " +
            "SELECT coupons.title FROM javaproject.customers_vs_coupons join javaproject.coupons " +
            "ON coupons.id = customers_vs_coupons.coupon_id " +
            "WHERE customers_vs_coupons.customers_id = ? AND coupons.price <= ?;";

    public static final String getCustomerDetails = " " +
            "SELECT * FROM javaproject.customers where id = ? ";
}
