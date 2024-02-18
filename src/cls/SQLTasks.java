package cls;

public class SQLTasks {
    public static final String getCouponsByEndDate = " " +
            "DELETE FROM javaproject.coupons where end_date<curdate()";
}
