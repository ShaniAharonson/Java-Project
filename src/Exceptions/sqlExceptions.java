package Exceptions;

public class sqlExceptions extends Exception {

    public sqlExceptions() {
        super.getMessage();

    }

    /**
     * sql exception by error code
     * @param message
     */
    public sqlExceptions(String message) {
        switch (message) {
            case "1364":
                System.out.println("Error Code: 1364: Field 'name' doesn't have a default value");
                break;
            case "1062":
                System.out.println("Error Code: 1062: Duplicate entry");
                break;
            case "1644":
                System.out.println("Error Code: 1644: Updates are not allowed on this table");
                break;
            case "1366":
                System.out.println("Incorrect integer value");
                break;
            case "1452":
                System.out.println("Cannot add or update a child row");
                break;
            default:
                System.out.println(message);
        }


    }
}