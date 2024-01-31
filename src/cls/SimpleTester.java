package cls;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

public class SimpleTester {
    public static void main(String[] args) {
        //createCompanyTable();
        //createCustomersTable();
        //createCategoriesTable();
        //createCouponsTable();
        //createCustomersVsCoupons();

    }
    private static void createCompanyTable() {
 DButils.runQuery(SQLcommands.CREATE_COMPANY_TABLE);
    }

    private static void createCustomersTable() {
        DButils.runQuery(SQLcommands.CREATE_CUSTOMERS_TABLE);
    }

    private static void createCategoriesTable() {
        DButils.runQuery(SQLcommands.CREATE_CATEGORIES_TABLE);
    }

    private static void createCouponsTable() {
        DButils.runQuery(SQLcommands.CREATE_COUPONS_TABLE);
    }

    private static void createCustomersVsCoupons() {
        DButils.runQuery(SQLcommands.CREATE_CUSTOMERS_VS_COUPONS_TABLE);
    }


    }


