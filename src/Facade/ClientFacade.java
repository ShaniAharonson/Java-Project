package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

import java.sql.SQLException;

public abstract class ClientFacade {

    protected CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    protected CustomersDBDAO customersDBDAO = new CustomersDBDAO();
    protected CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

    /**
     * abstract funcion of login for all facades
     * @param email
     * @param password
     * @return - true or false if the login goes successfully
     * @throws SQLException
     */
    public abstract boolean login(String email, String password) throws SQLException;

}

