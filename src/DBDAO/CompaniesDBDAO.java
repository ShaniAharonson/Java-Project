package DBDAO;

import DAO.CompaniesDAO;
import Exceptions.sqlExceptions;
import JavaBeans.Category;
import JavaBeans.Company;
import cls.ConnectionPool;
import cls.DButils;
import cls.SQLcommands;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CompaniesDBDAO implements CompaniesDAO {
    public ConnectionPool getConnectionPool() {
        return connectionPool;
    }

    public void setConnectionPool(ConnectionPool connectionPool) {
        this.connectionPool = connectionPool;
    }

    private ConnectionPool connectionPool;

    @Override
    public boolean isCompanyExists(String email, String password) throws sqlExceptions {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, email);
        params.put(2, password);
        ResultSet resultSet = DButils.runQueryFroResult(SQLcommands.isCompanyExists, params);
        try {
            while (resultSet.next()) {
                return resultSet.getInt("isExists") == 1;
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }
        return false;
    }

    @Override
    public void addCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        //name, email, password
        params.put(1, company.getName());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());


        DButils.runQuery(SQLcommands.addCompany, params);
    }

    @Override
    public void updateCompany(Company company) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(4, company.getId());
        params.put(2, company.getEmail());
        params.put(3, company.getPassword());
        params.put(1, company.getName());
        DButils.runQuery(SQLcommands.updateCompany, params);
        System.out.println("Company updated!");

    }

    @Override
    public void deleteCompany(int id) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, id);
        DButils.runQuery(SQLcommands.deleteCompany, params);
    }

    @Override
    public List<Company> getAllCompanies() throws SQLException {
        List<Company> myList = new ArrayList<>();
        ResultSet resultSet = DButils.runQueryFroResult(SQLcommands.getAllCompanies);
        while (resultSet.next()) {
            int id = resultSet.getInt(1);
            String name = resultSet.getString(2);
            String email = resultSet.getString(3);
            String password = resultSet.getString(4);
            myList.add(new Company(id, name, email, password));
        }
        return myList;
    }

    @Override
    public Company getOneCompany(int CompanyID) throws sqlExceptions {

        Map<Integer, Object> params = new HashMap<>();

        params.put(1, CompanyID);

        ResultSet result = DButils.runQueryFroResult(SQLcommands.getOneCompany, params);

        try {
            while (result.next()) {
                int id = result.getInt(1);
                String name = result.getString(2);
                String email = result.getString(3);
                String password = result.getString(4);
                return new Company(id, name, email, password);
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }

        throw new sqlExceptions();
    }

    @Override
    public void companyDetails(String name, String email) {
        ResultSet result = DButils.runQueryFroResult(SQLcommands.getCompanyDetails);
        try {
            name = result.getString(2);
            email = result.getString(3);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Company name: " + name + " \n email: " + email);
    }

    @Override
    public void addCategory(Category category) {
        Map<Integer, Object> params = new HashMap<>();
        params.put(1, category.ordinal()+1);
        params.put(2, category.name());
        DButils.runQuery(SQLcommands.addCategory, params);
    }
}
