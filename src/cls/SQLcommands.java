package cls;

public class SQLcommands {
    // create schema
    public static final String CREATE_SCHEMA = "CREATE SCHEMA IF NOT EXISTS " +DBmanager.SQL_DB;

    // ************************************ Company SQL *************************************
    public static String isCompanyExists = "" +
            "SELECT count(*) as isExists " +
            "FROM javaproject.companies " +
            "where email = ? and password = ?";

    public static String addCompany = "INSERT INTO `javaproject`.`companies` " +
            "(`name`, `email`, `password`) " +
            "VALUES (?, ?, ?)";


    public static String updateCompany =
            "UPDATE javaproject.companies " +
            "SET name = ?, email = ?, password = ? " +
            "where id =?";

    public static String deleteCompany = "DELETE FROM javaproject.companies where id = ? ";

    public static String getAllCompanies = "SELECT * FROM javaproject.companies";

    public static String getOneCompany = "SELECT * FROM javaproject.companies where id = ?";
    public static final String getCompanyDetails = "SELECT companies FROM javaproject.companies where id = ?";

    // ************************************ Customers SQL *************************************

    public static String isCustomerExists = "SELECT count(*) as user " +
            "FROM javaproject.customers " +
            "where email = ? and password = ?";

    public static String addCustomer = "INSERT INTO `javaproject`.`customers` " +
            "(`First_Name`, `Last_Name`, `email`, `password`) " +
            "VALUES (?, ?, ?, ?)";

    public static String updateCustomers =
            "update javaproject.customers" +
            "SET First_Name = ?, Last_Name = ?, email = ?, password = ?" +
            "WHERE id = ?";

    public static String deleteCustomer = "delete From javaproject.customers WHERE id = ?";

    public static String getAllCustomers = "SELECT * FROM javaproject.customers";

    public static String getOneCustomer = "SELECT * FROM javaproject.customers where id = ? ";

    // ************************************ Coupons SQL *************************************

    public static String addCoupon = "INSERT INTO `javaproject`.`coupons`" +
            " (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`)" +
            " VALUES (?, ?, ?, '?, ?, ?, ?, ?)";

    public static String updateCoupon = "update javaproject.customers " +
            "SET company_id= ?, category_id= ?, title= ?, description= ?," +
            " start_date = ?, end_date = ?, amount = ?, price = ? WHERE id = ?";

    public static String deleteCoupon = "delete FROM javaproject.coupons where id = ?";

    public static String getAllCoupons = "SELECT * FROM javaproject.coupons";

    public static String getOneCoupon = "SELECT * FROM javaproject.coupons where id = ? ";

    public static String addCouponPurchase = "INSERT INTO `javaproject`.`customers_vs_coupons` " +
            "(`customers_id`, `coupon_id`) " + "VALUES (?, ?)";

    public static String deleteCouponPurchase = "delete FROM javaproject.customers_vs_coupons " +
            "where customers_id = ? and coupon_id = ?";

    public static final String getAllCompanyCoupon = "SELECT * FROM javaproject.coupons where company_id = ?";



}
