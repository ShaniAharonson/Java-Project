package Exceptions;

public class sqlExceptions extends Exception{
    public sqlExceptions() {
    }

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
            default:
                System.out.println(message);
        }


    }
}