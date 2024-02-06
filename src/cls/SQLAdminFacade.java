package cls;

public class SQLAdminFacade {
    public static String updateExistingCompany =
            "DELIMITER //" +
            "CREATE TRIGGER prevent_update" +
            "BEFORE UPDATE ON your_table" +
            "FOR EACH ROW" +
            "BEGIN" +
            "    SIGNAL SQLSTATE '45000'" +
            "    SET MESSAGE_TEXT = 'Updates are not allowed on this table'" +
            "END;" +
            "//" +
            "DELIMITER ;";

    public static String returnAllCompanies = "SELECT * FROM javaproject.companies";

    public static String returnSpecificCompany = "SELECT * FROM javaproject.companies where id = ?";
    public static String updateExistingCustomer =" "+
            "DELIMITER //" +
            "CREATE TRIGGER prevent_update_customers" +
            "BEFORE UPDATE ON customers" +
            "FOR EACH ROW" +
            "BEGIN" +
            "    SIGNAL SQLSTATE '45000'" +
            "    SET MESSAGE_TEXT = 'Updates are not allowed on this table'" +
            "END;" +
            "//" +
            "DELIMITER ";

    public static final String deleteExistingCustomer =
            "DELETE FROM javaproject.customers where id = ?";

    public static final String returnAllCustomers = "SELECT * FROM javaproject.customers";

    public static final String returnOneCustomer = "SELECT * FROM javaproject.customers where id = ?";
}