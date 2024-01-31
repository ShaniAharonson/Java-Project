package DBDAO;

import DAO.CompaniesDAO;
import Exceptions.sqlExceptions;
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
    public void addCompany(Integer id, String name, String email, String password) {
        Map<Integer, Object> params = new HashMap<>();
        //id, name, email, password
        params.put(1, id);
        params.put(2, name);
        params.put(3, email);
        params.put(4, password);

        DButils.runQuery(SQLcommands.addCompany, params);
    }

    @Override
    public void updateCompany(Company company) {
        ResultSet update = DButils.runQueryFroResult(SQLcommands.updateCompany);
        System.out.println(update);

    }

    @Override
    public void deleteCompany(int id) {
        Map<Integer, Object> params = new HashMap<>();
        params.remove(1, id);
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
        Company company = new Company();

        ResultSet result = DButils.runQueryFroResult(SQLcommands.getOneCompany);

        try {
            while (result.next()) {
                company.setId(result.getInt(1));
                company.setName(result.getString(2));
                company.setEmail(result.getString(3));
                company.setPassword(result.getString(4));
            }
        } catch (SQLException err) {
            throw new sqlExceptions(err.getMessage());
        }

        return company;
    }
}
