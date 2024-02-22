package cls;

public class SQLcommands {
    // create schema
    public static final String CREATE_SCHEMA = "CREATE SCHEMA `javaproject` ";
    public static final String FK_COUPONS_AND_COMPANIES_TO_CATEGORIES1 =
            "ALTER TABLE `javaproject`.`coupons` " +
                    "ADD CONSTRAINT `Categories_FK`" +
                    "  FOREIGN KEY (`category_id`)" +
                    "  REFERENCES `javaproject`.`categories` (`id`)" +
                    "  ON DELETE CASCADE" +
                    "  ON UPDATE CASCADE ";

    public static final String FK_COUPONS_AND_COMPANIES_TO_CATEGORIES2 = " " +
            "ALTER TABLE `javaproject`.`coupons` " +
            "ADD CONSTRAINT `Companies_FK`" +
            "  FOREIGN KEY (`company_id`)" +
            "  REFERENCES `javaproject`.`companies` (`id`)" +
            "  ON DELETE CASCADE" +
            "  ON UPDATE CASCADE";
    public static final String FK_OF_CUSTOMERVSCOUPONS1 =
            "ALTER TABLE `javaproject`.`customers_vs_coupons` " +
                    "ADD INDEX `Coupons_FK_idx` (`coupon_id` ASC) VISIBLE;";
    public static final String FK_OF_CUSTOMERVSCOUPONS2 = " " +
            "ALTER TABLE `javaproject`.`customers_vs_coupons` " +
            "ADD CONSTRAINT `Customers_FK`" +
            "  FOREIGN KEY (`customers_id`)" +
            "  REFERENCES `javaproject`.`customers` (`id`)" +
            "  ON DELETE CASCADE" +
            "  ON UPDATE CASCADE," +
            "ADD CONSTRAINT `Coupons_FK`" +
            "  FOREIGN KEY (`coupon_id`)" +
            "  REFERENCES `javaproject`.`coupons` (`id`)" +
            "  ON DELETE CASCADE" +
            "  ON UPDATE CASCADE;";


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

    public static String getOneCompany = "SELECT * FROM javaproject.companies where id = ?;";
    public static final String getCompanyDetails = "SELECT companies FROM javaproject.companies where id = ?";

    // ************************************ Customers SQL *************************************

    public static String isCustomerExists = "SELECT count(*) as user " +
            "FROM javaproject.customers " +
            "where email = ? and password = ?";

    public static String addCustomer = "INSERT INTO `javaproject`.`customers` " +
            "(`First_Name`, `Last_Name`, `email`, `password`) " +
            "VALUES (?, ?, ?, ?)";

    public static String updateCustomers =
            "UPDATE `javaproject`.`customers` SET `First_Name` = ?, `Last_Name` = ?," +
                    " `email` = ?, `password` = ? WHERE (`id` = ?);";

    public static String deleteCustomer = "delete From javaproject.customers WHERE id = ?";

    public static String getAllCustomers = "SELECT * FROM javaproject.customers";

    public static String getOneCustomer = "SELECT * FROM javaproject.customers where id = ? ";

    // ************************************ Coupons SQL *************************************

    public static String addCoupon = "INSERT INTO `javaproject`.`coupons`" +
            " (`company_id`, `category_id`, `title`, `description`, `start_date`, `end_date`, `amount`, `price`, `image`)" +
            " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public static String updateCoupon = "update javaproject.customers " +
            "SET company_id= ?, category_id= ?, title= ?, description= ?," +
            " start_date = ?, end_date = ?, amount = ?, price = ? WHERE id = ?";

    public static String deleteCoupon = "delete FROM javaproject.coupons where id = ? AND company_id = ?";

    public static String getAllCoupons = "SELECT * FROM javaproject.coupons";

    public static String getOneCoupon = "SELECT * FROM javaproject.coupons where id = ? ";

    public static String addCouponPurchase = "INSERT INTO `javaproject`.`customers_vs_coupons` " +
            "(`customers_id`, `coupon_id`) " + "VALUES (?, ?)";

    public static String deleteCouponPurchase = "delete FROM javaproject.customers_vs_coupons " +
            "where customers_id = ? and coupon_id = ?";

    public static final String getAllCompanyCoupon = "SELECT * FROM javaproject.coupons where company_id = ?";

    public static final String addCategory = "INSERT INTO `javaproject`.`categories` (`id`, `name`) VALUES (?, ?);";
}
