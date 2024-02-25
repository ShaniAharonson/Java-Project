package IFacades;

import Exceptions.CouponNotFoundException;
import Exceptions.sqlExceptions;
import JavaBeans.Company;
import JavaBeans.Customer;

import java.sql.SQLException;
import java.util.List;

public interface IAdmin {
    public boolean login(String email, String password);
    public void addCompany(Company company) throws sqlExceptions, SQLException;
    public void updateCompany(Company company) throws sqlExceptions;
    public void deleteCompany(int companyID) throws SQLException;
    public List<Company> getAllCompanies() throws SQLException;
    public Company getOneCompany(int companyID) throws sqlExceptions;
    public void addCustomer(Customer customer) throws sqlExceptions;
    public void updateCustomer(Customer customer) throws Exception, SQLException;
    public void deleteCustomer(int customerID) throws SQLException;
    public List<Customer> getAllCustomers() throws SQLException;
    public Customer getOneCustomer(int customerID) throws sqlExceptions, CouponNotFoundException;
}
