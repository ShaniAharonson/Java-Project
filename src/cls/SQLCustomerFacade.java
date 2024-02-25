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
            "   SELECT coupons.id, coupons.company_id, coupons.category_id, coupons.title, " +
            "coupons.description, coupons.start_date, coupons.end_date, coupons.amount, coupons.price, " +
            "coupons.image" +
            " FROM javaproject.customers_vs_coupons join javaproject.coupons " +
            "            ON coupons.id = customers_vs_coupons.coupon_id" +
            "            WHERE customers_vs_coupons.customers_id =  ? AND coupons.category_id = ?";

    public static final String getCouponByPrice = " " +
            "SELECT coupons.id, coupons.company_id, coupons.category_id, coupons.title," +
            "            coupons.description, coupons.start_date, coupons.end_date, coupons.amount, coupons.price, " +
            "            coupons.image FROM javaproject.customers_vs_coupons join javaproject.coupons" +
            "            ON coupons.id = customers_vs_coupons.coupon_id " +
            "            WHERE customers_vs_coupons.customers_id = ? AND coupons.price <= ?;";

    public static final String getCustomerDetails = " " +
            "SELECT * FROM javaproject.customers where id = ? ";
}
