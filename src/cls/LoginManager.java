package cls;

import Facade.*;

public class LoginManager {
    private static LoginManager instance = null;

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

    public ClientFacade login(String email, String password, ClientType type) {
        return switch (type) {
            case ADMINISTRATOR -> new AdminFacade();
            case COMPANY -> new CompanyFacade();
            case CUSTOMER -> new CustomerFacade();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
