import Exceptions.sqlExceptions;
import Testing.Test;

import java.sql.SQLException;

public class Program {
    public static void main(String[] args) throws Exception {
        try {
            Test.testAll();
        } catch (SQLException | sqlExceptions e) {
            throw new RuntimeException(e);
        }
    }
}
