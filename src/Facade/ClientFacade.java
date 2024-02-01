package Facade;

import DAO.CompaniesDAO;
import DAO.CouponsDao;
import DAO.CustomersDAO;

public abstract class ClientFacade implements CompaniesDAO, CouponsDao, CustomersDAO {
    protected CompaniesDAO companiesDAO;
    protected CustomersDAO customersDAO;
    protected CouponsDao couponsDao;

    public static boolean login(String email, String password, ClientType type) {
        switch (type) {
            case ADMIN:
                new AdminFacade();
            case COMPANY:
                new CompanyFacade();
            case CUSTOMER:
                new CustomerFacade();
        }
        return false;
    }
}

