package Testing;

import DBDAO.CouponsDBDAO;
import Facade.AdminFacade;
import Facade.ClientType;
import Facade.CompanyFacade;
import Facade.CustomerFacade;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;
import cls.LoginManager;

import java.util.Date;

public class Test {
    public static void testAll() throws Exception {
        Thread jobThread = new Thread(() -> LoginManager.getInstance());
        jobThread.start();

        //****************************** Admin Testing ********************************************//
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login(
                "Shani.bolandi@gmail.com", "12345678", ClientType.ADMINISTRATOR);

        adminFacade.addCompany(new Company(1, "Amazon", "amazom@amazon", "1234"));
        System.out.println("add company amazon");

        adminFacade.addCompany(new Company(2, "Apple", "Apple@apple.com", "5678"));
        System.out.println("add company apple");

        adminFacade.addCompany(new Company(3, "cisco", "cisco@cisco.com",
                "9101112"));
        System.out.println("add company cisco");

        adminFacade.addCompany(new Company(4, "Wix", "Wix@wix.com", "505050"));
        System.out.println("add company Wix");

        //get company by id :o)
        Company updatedCompany = adminFacade.getOneCompany(1);
        updatedCompany.setName("AMAZON LLC.");
        adminFacade.updateCompany(updatedCompany);

        adminFacade.deleteCompany(2);

        adminFacade.getAllCompanies();

        adminFacade.getOneCompany(1);

        adminFacade.addCustomer(new Customer(1, "Shani", "Aharonson",
                "shani@shani.com", "303030"));
        System.out.println("add customer shani");
        adminFacade.addCustomer(new Customer(2, "Ofir", "Aharonson",
                "ofir@ofir.com", "404040"));
        System.out.println("add customer ofir");
        adminFacade.addCustomer(new Customer(3, "Ron", "Bolandi",
                "ron-bol@gmail.com", "131313"));
        System.out.println("add customer ron");

        Customer UpdatedCustomer = adminFacade.getOneCustomer(1);
        UpdatedCustomer.setFirstName("OFIR");
        adminFacade.updateCustomer(UpdatedCustomer);
        System.out.println("update completed");
        adminFacade.deleteCustomer(1);
        adminFacade.getAllCustomers();
        adminFacade.getOneCustomer(2);


// *********************************** Company Testing ************************************** //
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login(
                "amazom@amazon", "1234", ClientType.COMPANY);
        companyFacade.addCoupon(new Coupon(1, 3, Category.Food, "pai",
                "pai with apples", new Date(2024 - 02 - 13), new Date(2024 - 05 - 20), 30,
                20.5, "        (" +
                "                       (   )  )" +
                "                        )  ( )" +
                "                        ....." +
                "                     .:::::::::." +
                "                     ~_______/~ "));

        companyFacade.addCoupon(new Coupon(2, 4, Category.Electricity, "Computer",
                "Del computer", new Date(2020 - 01 - 01), new Date(2025 - 01 - 01), 50,
                2000.0,
                "  _" +
                "     |-|  __" +
                "     |=| [Ll]" +
                "     ^ ====`o"));
        Coupon couponToUpdate = new Coupon(3,4,Category.Food,"Cherry",
                "beautiful cherries",new Date(2021-01-13),new Date(2025-01-14),300,50.5,
                " __.--~~.,-.__\n" +
                "   `~-._.-(`-.__`-.\n" +
                "           \\    `~~`\n" +
                "      .--./ \\\n" +
                "     /#   \\  \\.--.\n" +
                "     \\    /  /#   \\\n" +
                " jgs  '--'   \\    /\n" +
                "              '--'");
        companyFacade.updateCoupon(couponToUpdate);
        companyFacade.deleteExistsCoupon(couponToUpdate);
        companyFacade.getAllCompanyCoupon(4)
                .forEach(System.out::println);
        companyFacade.getAllCouponsFromSpecificCategory(4, Category.Electricity)
                .forEach(System.out::println);
        companyFacade.getAllCouponsByPrice(4, 2000.0);
        companyFacade.companyDetails("Wix", "Wix@wix.com");


        // *************************** Customer Testing ************************************//
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login(
                "oshra.bolandi@gmail.com", "707070", ClientType.CUSTOMER);

        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Coupon getOneCoupon = couponsDBDAO.getOneCoupon(1);
        customerFacade.PurchaseCoupon(getOneCoupon);
        customerFacade.getCustomerCoupons(1);
        customerFacade.get_All_Customer_Coupons_From_Specific_Category(1, Category.Food);
        customerFacade.getCouponsByPrice(1, 20.5);
        customerFacade.customerDetails("Shani", "Aharonson", "Shani@shani.com");
    }

}
