package DAO;

import Exceptions.sqlExceptions;
import JavaBeans.Company;

import java.sql.SQLException;
import java.util.List;

public interface CompaniesDAO {
    public boolean isCompanyExists(String email, String password) throws sqlExceptions;

    public void addCompany(Company company) throws sqlExceptions;

    public void updateCompany(Company company) throws sqlExceptions;

    public void deleteCompany(int id);

    public List<Company> getAllCompanies() throws SQLException;

    public Company getOneCompany(int CompanyID) throws sqlExceptions;

    public void companyDetails(String name, String email);
}
