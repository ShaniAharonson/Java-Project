package Testing;

import Exceptions.*;
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
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class Test {
    public static void testAll() {
        Thread jobThread = new Thread(() -> LoginManager.getInstance());
        //  jobThread.start();

        try {
            AdminTesting();
        } catch (CouponNotFoundException e) {
            throw new RuntimeException(e);
        }
        try {
            CompanyTesting();
        } catch (CategoryErrorException | CouponNotFoundException | DetailsGetWrong | CompaniesNotFoundException e) {
            throw new RuntimeException(e);
        }

        try {
            CustomerTesting();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


         System.out.println("Stopping thread!");
         System.exit(0);

        try {
            ConnectionPool.getInstance().closeAllConnections();
        } catch (InterruptedException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void CustomerTesting() throws SQLException {
        CustomerFacade customerFacade = null;
        try {
            customerFacade = (CustomerFacade) LoginManager.getInstance().login(
                    "ofir@ofir.com", "404040", ClientType.CUSTOMER);
            System.out.println("CUSTOMER LOGGING!!!!");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }


        try {
  //          customerFacade.PurchaseCoupon(2,2);
  //          customerFacade.PurchaseCoupon(3,7);
            customerFacade.PurchaseCoupon(3,10);
        } catch (AddingCouponException e) {
            System.out.println(e.getMessage());
        }
        customerFacade.getOneCoupon(customerFacade.getCustomerID());
        System.out.println("Customers purchasing coupon!");


        System.out.println();



        List<Coupon> couponArrayList = customerFacade.getCustomerCoupons(3);
        System.out.println("Get Customer coupons:");
        System.out.println(couponArrayList);
        System.out.println();

        System.out.println("All customer coupons from specific category:");
        try {
            customerFacade.get_All_Customer_Coupons_From_Specific_Category(2,Category.Food)
                    .forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Get all coupons by price:");
        try {
            customerFacade.getCouponsByPrice( 7.0).forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        try {
            System.out.println("customer details:  " + customerFacade.customerDetails());
        } catch (sqlExceptions | CustomerNotFoundException e) {
            System.out.println(e.getMessage());
        }

    }

    private static void CompanyTesting() throws CategoryErrorException, CouponNotFoundException, DetailsGetWrong, CompaniesNotFoundException {
        CompanyFacade companyFacade = null;
        try {

            companyFacade = (CompanyFacade) LoginManager.getInstance().login(
                    "cisco@cisco.com", "9101112", ClientType.COMPANY);
            System.out.println("COMPANY LOGGING!!!");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // adding new coupons
        try {
            companyFacade.addCoupon(new Coupon(3, 2, "pai", "pai with apples",
                    Date.valueOf(LocalDate.of(2020, 7, 4)),
                    Date.valueOf(LocalDate.of(2024, 7, 4)),
                    30, 20.5, "image"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyFacade.addCoupon(new Coupon(3, 2, "chocolate", "the best chocolate ever!",
                    Date.valueOf(LocalDate.of(2019, 9, 24)),
                    Date.valueOf(LocalDate.of(2026, 9, 24)),
                    70, 4.9, "image1"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyFacade.addCoupon(new Coupon(5, 3, "vacation in greece", "have fun in RODOS",
                    Date.valueOf(LocalDate.of(2021, 6, 20)),
                    Date.valueOf(LocalDate.of(2024, 8, 30)),
                    2500, 5000.0, "image2"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyFacade.addCoupon(new Coupon(5, 3, "vacation in Italy", "have fun in TOSCANA!",
                    Date.valueOf(LocalDate.of(2023, 5, 30)),
                    Date.valueOf(LocalDate.of(2024, 5, 30)),
                    5000, 6500.5, "image6"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyFacade.addCoupon(new Coupon(2, 1, "screen", "Del screen",
                    Date.valueOf(LocalDate.of(2022, 10, 10)),
                    Date.valueOf(LocalDate.of(2024, 10, 30)),
                    30, 20.5, "image3"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            companyFacade.addCoupon(new Coupon(4, 2, "BBB", "Deal burger",
                    Date.valueOf(LocalDate.of(2022, 4, 5)),
                    Date.valueOf(LocalDate.of(2024, 5, 5)),
                    55, 100.9, "image4"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        try {
            companyFacade.addCoupon(new Coupon(2, 1, "Computer",
                    "Del computer", Date.valueOf(LocalDate.of(2019, 2, 4))
                    , Date.valueOf(LocalDate.of(2027, 7, 4)), 100, 2000.0, "image5"));
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        // updating coupon
        Coupon updateCoupon = null;
        try {
            updateCoupon = companyFacade.getOneCoupon(2);
        } catch (CouponNotFoundException e) {
            System.out.println(e.getMessage());
        }
        updateCoupon.setAmount(150);

        // delete existing coupon
        companyFacade.deleteExistsCoupon(1, 3);

        // printing all coupons of one company
        System.out.println("the coupons of the comapany:");
        companyFacade.getAllCompanyCoupon(4)
                .forEach(System.out::println);

        // getting all coupons from a specific category
        System.out.println("the coupons from specific category:");
        companyFacade.getAllCouponsFromSpecificCategory(4,Category.Food)
                .forEach(System.out::println);

        // getting all coupons by max price
        System.out.println();
        System.out.println("the coupons by price:");
        try {
            companyFacade.getAllCouponsByPrice(4, 2000.0)
                    .forEach(System.out::println);
        } catch (SQLException | priceErrorException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("company details:  " + companyFacade.companyDetails());
        System.out.println();

    }

    private static void AdminTesting() throws CouponNotFoundException {
        AdminFacade adminFacade = new AdminFacade();
        try {
            adminFacade = (AdminFacade) LoginManager.getInstance().login(adminFacade.getAdminEmail(),
                    adminFacade.getAdminPass(), ClientType.ADMINISTRATOR);
            System.out.println("ADMIN LOGGING!!");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println();
        try {
            adminFacade.addCompany(new Company("Amazon", "amazom@amazon", "1234"));
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("add company amazon");
        System.out.println();

        try {
            adminFacade.addCompany(new Company("Apple", "Apple@apple.com", "5678"));
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("add company apple");
        System.out.println();

        try {
            adminFacade.addCompany(new Company("cisco", "cisco@cisco.com",
                    "9101112"));
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("add company cisco");
        System.out.println();

        try {
            adminFacade.addCompany(new Company("Wix", "Wix@wix.com", "505050"));
        } catch (sqlExceptions e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        System.out.println("add company Wix");
        System.out.println();

        // get company by id :o

        Company updatedCompany = null;
        try {
            updatedCompany = adminFacade.getOneCompany(4);
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        }

        updatedCompany.setPassword("444444");
        try {
            adminFacade.updateCompany(updatedCompany);
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        }

        try {
            adminFacade.deleteCompany(1);
            System.out.println("company deleted");
            System.out.println();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            adminFacade.getAllCompanies();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            adminFacade.getOneCompany(2);
            System.out.println("the company that gets: ");
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        }
        adminFacade.addCustomer(new Customer(0, "Shani", "Aharonson", "shani@shani.com", "303030"));
        System.out.println("shani");
        System.out.println();
        adminFacade.addCustomer(new Customer(0, "Ofir", "Aharonson",
                "ofir@ofir.com", "404040"));


        System.out.println("add customer ofir");
        System.out.println();
        adminFacade.addCustomer(new Customer(0, "Ron", "Bolandi",
                "ron-bol@gmail.com", "131313"));
        System.out.println("add customer ron");
        System.out.println();

        Customer UpdatedCustomer = null;
        UpdatedCustomer = adminFacade.getOneCustomer(1);
        UpdatedCustomer.setFirstName("OFIR");
        try {
            adminFacade.updateCustomer(UpdatedCustomer);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        System.out.println("update completed");
        System.out.println();
        //adminFacade.deleteCustomer(1);
        adminFacade.addCategory(Category.Electricity);
        System.out.println("Adding electricity category");
        System.out.println();
        adminFacade.addCategory(Category.Food);
        System.out.println("Adding food category");
        System.out.println();
        adminFacade.addCategory(Category.Restaurant);
        System.out.println("Adding restaurant category");
        System.out.println();
        adminFacade.addCategory(Category.Vacation);
        System.out.println("Adding vacation category");
        System.out.println();
        try {
            System.out.println("getting all customers:");
            adminFacade.getAllCustomers().forEach(System.out::println);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        // System.out.println(customers);
        System.out.println("getting one customer: ");
        System.out.println();
        Customer getOne = adminFacade.getOneCustomer(1);

        System.out.println(getOne);


    }


}




