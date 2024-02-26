package Facade;

import Exceptions.CouponNotFoundException;
import Exceptions.CustomerNotFoundException;
import Exceptions.sqlExceptions;
import IFacades.IAdmin;
import JavaBeans.Category;
import JavaBeans.Company;
import JavaBeans.Coupon;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade implements IAdmin {
    public String getAdminEmail() {
        return adminEmail;
    }

    public String getAdminPass() {
        return adminPass;
    }

    private final String adminEmail = "admin@admin.com";
    private final String adminPass = "admin";

    /**
     * login function by administer
     *
     * @param email
     * @param password
     * @return - true for success and false if not
     */
    public boolean login(String email, String password) {
        if (email.equals(adminEmail) && password.equals(adminPass)) {
            System.out.println("Login successfully");
            return true;
        } else {
            System.out.println("Wrong email or password!");
            return false;
        }
    }

    /**
     * adding company while company doesn't exist yet
     *
     * @param company
     * @throws sqlExceptions
     * @throws SQLException
     */
    public void addCompany(Company company) throws sqlExceptions, SQLException {

        if (companiesDBDAO.getAllCompanies().contains(company.getName())) {
            if (companiesDBDAO.getAllCompanies().contains(company.getEmail())) {
                System.out.println("Company already exists!");
            }
        } else {
            companiesDBDAO.addCompany(company);
        }

    }

    /**
     * updating company
     * @param company
     * @throws sqlExceptions
     */
    public void updateCompany(Company company) throws sqlExceptions {
        companiesDBDAO.updateCompany(company);
    }

    /**
     * delete company
     * @param companyID
     * @throws SQLException
     */
    public void deleteCompany(int companyID) throws SQLException {

        companiesDBDAO.deleteCompany(companyID);

    }


    /**
     * getting all companies from sql table
     * @return - all companies
     * @throws SQLException
     */
    public List<Company> getAllCompanies() throws SQLException {
        return companiesDBDAO.getAllCompanies();

    }

    /**
     * getting one company by ID
     * @param companyID
     * @return - the specific company
     * @throws sqlExceptions
     */
    public Company getOneCompany(int companyID) throws sqlExceptions {
        return companiesDBDAO.getOneCompany(companyID);
    }

    /**
     * adding customer
     * @param customer
     * @throws sqlExceptions
     */

    public void addCustomer(Customer customer) {
        customersDBDAO.addCustomer(customer);
    }

    /**
     * updating customer
     * @param customer
     * @throws Exception
     * @throws SQLException
     */
    public void updateCustomer(Customer customer){
        customersDBDAO.updateCustomer(customer);
    }

    /**
     * deleting customer
     * @param customerID
     * @throws SQLException
     */
    public void deleteCustomer(int customerID) throws SQLException {

        customersDBDAO.deleteCustomer(customerID);
    }

    /**
     * getting all customers from sql table
     * @return
     * @throws SQLException
     */
    public List<Customer> getAllCustomers() throws SQLException {
        return customersDBDAO.getAllCustomers();
    }

    /**
     * getting one customer by his ID
     * @param customerID
     * @return
     * @throws sqlExceptions
     */
    public Customer getOneCustomer(int customerID) throws CouponNotFoundException {
        try {
            return customersDBDAO.getOneCustomer(customerID);
        } catch (sqlExceptions e) {
            System.out.println(e.getMessage());
        } catch (CustomerNotFoundException e) {
            System.out.println("Customer id was not found");
        }
        throw new CouponNotFoundException("cannot find customer by this id");
    }

    /**
     *  getting one coupon by id
     * @param CouponID
     * @return - the relevant coupon
     * @throws sqlExceptions
     */
    public Coupon getOneCoupon(int CouponID) throws sqlExceptions, CouponNotFoundException {
        return couponsDBDAO.getOneCoupon(CouponID);
    }

    /**
     * adding category to category sql table
     * @param category
     */
    public void addCategory(Category category){
        companiesDBDAO.addCategory(category);
        //System.out.println("i am insinde");

    }
}