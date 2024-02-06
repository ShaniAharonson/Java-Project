package Facade;

import DBDAO.CompaniesDBDAO;
import DBDAO.CouponsDBDAO;
import DBDAO.CustomersDBDAO;

public abstract class ClientFacade {
    protected CompaniesDBDAO companiesDBDAO = new CompaniesDBDAO();
    protected CustomersDBDAO customersDBDAO = new CustomersDBDAO();
    protected CouponsDBDAO couponsDBDAO = new CouponsDBDAO();

    public abstract boolean login(String email, String password) ;

}

