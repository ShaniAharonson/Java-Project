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
import cls.ConnectionPool;
import cls.LoginManager;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void testAll() throws Exception {
        Thread jobThread = new Thread(() -> LoginManager.getInstance());
        jobThread.start();

        //****************************** Admin Testing ********************************************//
        AdminFacade adminFacade = (AdminFacade) LoginManager.getInstance().login(
                "admin@admin.com", "admin", ClientType.ADMINISTRATOR);

        adminFacade.addCompany(new Company("Amazon", "amazom@amazon", "1234"));
        System.out.println("add company amazon");

        adminFacade.addCompany(new Company("Apple", "Apple@apple.com", "5678"));
        System.out.println("add company apple");

        adminFacade.addCompany(new Company("cisco", "cisco@cisco.com",
                "9101112"));
        System.out.println("add company cisco");

        adminFacade.addCompany(new Company("Wix", "Wix@wix.com", "505050"));
        System.out.println("add company Wix");

        // get company by id :o

        Company updatedCompany = adminFacade.getOneCompany(4);

        updatedCompany.setPassword("444444");
        adminFacade.updateCompany(updatedCompany);

        adminFacade.deleteCompany(1);

        adminFacade.getAllCompanies();

        adminFacade.getOneCompany(2);

        adminFacade.addCustomer(new Customer(0, "Shani", "Aharonson", "shani@shani.com", "303030"));
        System.out.println("add customer shani");
        adminFacade.addCustomer(new Customer(0, "Ofir", "Aharonson",
                "ofir@ofir.com", "404040"));
        System.out.println("add customer ofir");
        adminFacade.addCustomer(new Customer(0, "Ron", "Bolandi",
                "ron-bol@gmail.com", "131313"));
        System.out.println("add customer ron");

        Customer UpdatedCustomer = adminFacade.getOneCustomer(1);
        UpdatedCustomer.setFirstName("OFIR");
        adminFacade.updateCustomer(UpdatedCustomer);
        System.out.println("update completed");
        //adminFacade.deleteCustomer(1);
        List<Customer> customers = adminFacade.getAllCustomers();
        System.out.println(customers);
        Customer getOne = adminFacade.getOneCustomer(2);
        System.out.println(getOne);


// *********************************** Company Testing ************************************** //
        CompanyFacade companyFacade = (CompanyFacade) LoginManager.getInstance().login(
                "Wix@wix.com", "444444", ClientType.COMPANY);
        // adding category to Categories Table
        adminFacade.addCategory(Category.Restaurant);

        // adding new coupons
        companyFacade.addCoupon(new Coupon(3, 0, "pai", "pai with apples",
                Date.valueOf(LocalDate.of(2020, 7, 4)),
                Date.valueOf(LocalDate.of(2024, 7, 4)),
                30, 20.5, "image"));

        companyFacade.addCoupon(new Coupon(3, 0, "chocolate", "the best chocolate ever!",
                Date.valueOf(LocalDate.of(2019, 9, 24)),
                Date.valueOf(LocalDate.of(2026, 9, 24)),
                70, 4.9, "image1"));

        companyFacade.addCoupon(new Coupon(5, 3, "vacation in greece", "have fun in RODOS",
                Date.valueOf(LocalDate.of(2021, 6, 20)),
                Date.valueOf(LocalDate.of(2024, 8, 30)),
                2500, 5000.0, "image2"));

        companyFacade.addCoupon(new Coupon(5, 3, "vacation in Italy", "have fun in TOSCANA!",
                Date.valueOf(LocalDate.of(2023, 5, 30)),
                Date.valueOf(LocalDate.of(2024, 5, 30)),
                5000, 6500.5, "image6"));

        companyFacade.addCoupon(new Coupon(2, 1, "screen", "Del screen",
                Date.valueOf(LocalDate.of(2022, 10, 10)),
                Date.valueOf(LocalDate.of(2024, 10, 30)),
                30, 20.5, "image3"));

        companyFacade.addCoupon(new Coupon(4, 2, "BBB", "Deal burger",
                Date.valueOf(LocalDate.of(2022, 4, 5)),
                Date.valueOf(LocalDate.of(2024, 5, 5)),
                55, 100.9, "image4"));
        companyFacade.addCoupon(new Coupon(2, 1, "Computer",
                "Del computer", Date.valueOf(LocalDate.of(2019, 2, 4))
                , Date.valueOf(LocalDate.of(2027, 7, 4)), 100, 2000.0, "image5"));

        // updating coupon
        Coupon updateCoupon = adminFacade.getOneCoupon(18);
        updateCoupon.setAmount(150);

        // delete existing coupon
        companyFacade.deleteExistsCoupon(1, 3);

        // printing all coupons of one company
        System.out.println("the coupons of the comapany:");
        companyFacade.getAllCompanyCoupon(4)
                .forEach(System.out::println);

        // getting all coupons from a specific category
        System.out.println("the coupons from specific category:");
        companyFacade.getAllCouponsFromSpecificCategory(4, Category.Electricity)
                .forEach(System.out::println);

        // getting all coupons by max price
        System.out.println("the coupons by price:");
        companyFacade.getAllCouponsByPrice(4, 2000.0)
                .forEach(System.out::println);
        System.out.println("company details:  "+ companyFacade.companyDetails());


        // *************************** Customer Testing ************************************//
        CustomerFacade customerFacade = (CustomerFacade) LoginManager.getInstance().login(
                "ofir@ofir.com", "404040", ClientType.CUSTOMER);

        CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
        Coupon getOneCoupon = couponsDBDAO.getOneCoupon(1);
        System.out.println("Get one coupon:");
        System.out.println(getOneCoupon);

        couponsDBDAO.addCouponPurchase(customerFacade.getCustomerID(), getOneCoupon.getId());
// need to change sql query for function - get all customers coupons
        System.out.println("List of customer's coupons:");
        List<Coupon> couponArrayList = customerFacade.getCustomerCoupons(3);
        System.out.println("Get Customer coupons:");
        System.out.println(couponArrayList);

        System.out.println("All customer coupons from specific category:");
        customerFacade.get_All_Customer_Coupons_From_Specific_Category(Category.Vacation)
                .forEach(System.out::println);

        System.out.println("Get all coupons by price:");
        customerFacade.getCouponsByPrice(1, 20.5).forEach(System.out::println);
        System.out.println();
        System.out.println("customer details:  " + customerFacade.customerDetails());



        System.out.println("Stopping thread!");
            System.exit(0);

        ConnectionPool.getInstance().closeAllConnections();
    }

}

