package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDao;
import DAO.CustomersDAO;

public abstract class ClientFacade implements CompaniesDAO, CouponsDao, CustomersDAO {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDao couponsDao;
}
