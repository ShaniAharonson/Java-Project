package Tasks;

import DBDAO.CouponsDBDAO;
import JavaBeans.Coupon;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.ArrayList;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    private boolean quit = true;

    public boolean isQuit() {
        return quit;
    }

    public void setQuit(boolean quit) {
        this.quit = quit;
    }


    public CouponExpirationDailyJob(CouponsDBDAO couponsDBDAO, boolean quit) {
        this.couponsDBDAO = couponsDBDAO;
        this.quit = quit;
    }

    @Override
    public void run() {

        ArrayList<Coupon> coupons = new ArrayList<>();
        couponsDBDAO.getAllCouponsByEndDate(coupons);

        while (quit) {
            try {
                Thread.sleep(1000 * 60 * 60 * 40);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    public void stop() {
        while (!quit) {
            break;
        }

    }
}
