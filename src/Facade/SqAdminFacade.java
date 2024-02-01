package Facade;

public class SqAdminFacade {
    public static String updateExitingCompany =
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

    public String returnAllCompanies = "SELECT * FROM javaproject.companies";
}
