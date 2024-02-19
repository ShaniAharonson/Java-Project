package DAO;

import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {
    /**
     * checking if the company exists already in table
     * @param email
     * @param password
     * @return - false 0 and true 1
     * @throws sqlExceptions
     */
    public boolean isCompanyExists(String email, String password) throws sqlExceptions;

    /**
     * function for adding new company to sql table
     * @param company
     * @throws sqlExceptions
     */
    public void addCompany(Company company) throws sqlExceptions;

    /**
     * function to update exists company
     * @param company
     * @throws sqlExceptions
     */
    public void updateCompany(Company company) throws sqlExceptions;

    /**
     * function to delete exists company from sql table
     * @param id
     */
    public void deleteCompany(int id);

    /**
     * function to get all companies
     * @return - all the companies in the table
     * @throws SQLException
     */
    public List<Company> getAllCompanies() throws SQLException;

    /**
     * function to get one compnay by company ID
     * @param CompanyID
     * @return - one company
     * @throws sqlExceptions
     */
    public Company getOneCompany(int CompanyID) throws sqlExceptions;

    /**
     * function to perform company details: name and email.
     * @param name
     * @param email
     */
    public void companyDetails(String name, String email);
    public void addCategory(Category category);
}
