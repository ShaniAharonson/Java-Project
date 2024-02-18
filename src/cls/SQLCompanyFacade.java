package cls;

public class SQLCompanyFacade {

    public static final String companyLogin =
            "SELECT id as CompanyID " +
                    "FROM javaproject.companies " +
                    "WHERE email = ? AND password = ?";

    public static String updateCoupon = "CREATE TRIGGER trigger_name " +
            "BEFORE UPDATE ON javaproject.coupons FOR EACH ROW    \n" +
            "         BEGIN    " +
            "        END;     ";
    public static final String getGetAllCompaniesCouponFromSpecificCategory =" "+
            "SELECT * FROM javaproject.coupons " +
            "where company_id = ? AND category_id = ?";
    public static final String getCouponsByPrice = "SELECT * FROM javaproject.coupons where company_id = ? AND price <= ?";
}
