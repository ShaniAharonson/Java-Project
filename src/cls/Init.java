package cls;

public class Init {
    public static void main(String[] args) {
        // calling to the functions

        //createCompanyTable();
        //createCustomersTable();
        //createCategoriesTable();
        //createCouponsTable();
        //createCustomersVsCoupons();

    }
// functions to initialize tables
    private static void createCompanyTable() {
 DButils.runQuery(SQLTableCommands.CREATE_COMPANY_TABLE);
    }

    private static void createCustomersTable() {
        DButils.runQuery(SQLTableCommands.CREATE_CUSTOMERS_TABLE);
    }

    private static void createCategoriesTable() {
        DButils.runQuery(SQLTableCommands.CREATE_CATEGORIES_TABLE);
    }

    private static void createCouponsTable() {
        DButils.runQuery(SQLTableCommands.CREATE_COUPONS_TABLE);
    }

    private static void createCustomersVsCoupons() {
        DButils.runQuery(SQLTableCommands.CREATE_CUSTOMERS_VS_COUPONS_TABLE);
    }


    }


