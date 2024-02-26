package cls;

import Facade.*;

import java.sql.SQLException;

public class LoginManager {
    private static LoginManager instance = null;
// singleTon class
    // to get one instance of login each time

    private LoginManager(){
        System.out.printf("%s instance was invoked\n", this.getClass().getSimpleName());
    }

    public static LoginManager getInstance() {
        if (instance == null){
            synchronized (LoginManager.class){
                if (instance == null){
                    instance = new LoginManager();
                }
            }
        }
        return instance;
        // return new Login manager
    }

    /**
     * login by client type
     * @param email
     * @param password
     * @param type
     * @return - the facade login
     */
    public ClientFacade login(String email, String password, ClientType type) throws SQLException {
        ClientFacade clientFacade = null;
        switch (type) {
            case ADMINISTRATOR:
                clientFacade = new AdminFacade();
                break;
            case COMPANY:
                clientFacade = new CompanyFacade();
                break;
            case CUSTOMER:
                clientFacade = new CustomerFacade();
                break;
        }
        boolean result  = clientFacade.login(email,password);
        if(result){
            return clientFacade;
        }
        throw new IllegalStateException("Unexpected value: " + type);
    }
}
