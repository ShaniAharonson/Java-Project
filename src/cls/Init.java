package cls;

public class Init {
    public static void main(String[] args) {

        // calling to the functions
        createSchema();
        createCompanyTable();
        createCustomersTable();
        createCategoriesTable();
        createCouponsTable();
        createCustomersVsCoupons();
        foreignKeysToComapniesAndCategories1();
        foreignKeysToComapniesAndCategories2();
        fkOfCustomerVSCoupons1();
        fkOfCustomerVSCoupons2();



    }

    // functions to initialize tables
    private static void createSchema() {
        DButils.runQuery(SQLcommands.CREATE_SCHEMA);
    }

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

    private static void foreignKeysToComapniesAndCategories1() {
        DButils.runQuery(SQLcommands.FK_COUPONS_AND_COMPANIES_TO_CATEGORIES1);
    }

    private static void foreignKeysToComapniesAndCategories2() {
        DButils.runQuery(SQLcommands.FK_COUPONS_AND_COMPANIES_TO_CATEGORIES2);
    }

    private static void fkOfCustomerVSCoupons1() {
        DButils.runQuery(SQLcommands.FK_OF_CUSTOMERVSCOUPONS1);
    }

    private static void fkOfCustomerVSCoupons2() {
        DButils.runQuery(SQLcommands.FK_OF_CUSTOMERVSCOUPONS2);
    }


}

