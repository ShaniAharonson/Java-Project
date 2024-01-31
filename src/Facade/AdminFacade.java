package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDao;
import DAO.CustomersDAO;
import DBDAO.CompaniesDBDAO;
import Exceptions.sqlExceptions;
import JavaBeans.Company;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public class AdminFacade extends ClientFacade {


    public boolean login(String email, String password) {
        if (email.equals("admin@admin.com") && password.equals("admin")) {
            System.out.println("Login successfully");
            return true;
        } else {
            System.out.println("Wrong email or password!");
        }
        return false;
    }


    @Override
    public void addCompany(Integer id, String name, String email, String password) {
        CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
        C
        try {
            synchronized (AdminFacade.class) {
                super.addCompany(id, name, email, password);
                // האם צריך לבדוק ייחודיות של שם החברה והאמייל אם גם ככה הגדרתי אותם יוניק בSQL
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
    }

    @Override
    public void updateCompany(Company company) {
        Company c = new Company();


        super.updateCompany(company);
    }

    @Override
    public void deleteCompany(int id) {
        super.deleteCompany(id);
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        return super.getAllCompanies();
    }

    @Override
    public Company getOneCompany(int CompanyID) {
        return super.getOneCompany(CompanyID);
    }

    @Override
    public void addCustomer(Integer id, String firstName, String lastName, String email, String password) {
        super.addCustomer(id, firstName, lastName, email, password);
    }

    @Override
    public void updateCustomer(Customer customer) {
        super.updateCustomer(customer);
    }

    @Override
    public void deleteCustomer(int id) {
        super.deleteCustomer(id);
    }

    @Override
    public List<Customer> getAllCustomers() throws SQLException {
        return super.getAllCustomers();
    }

    @Override
    public Customer getOneCustomer(int CustomerID) {
        return super.getOneCustomer(CustomerID);
    }
}
