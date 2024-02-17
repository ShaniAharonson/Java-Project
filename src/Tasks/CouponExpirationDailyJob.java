package Tasks;

import DBDAO.CouponsDBDAO;
import lombok.Getter;
import lombok.Setter;

public class CouponExpirationDailyJob implements Runnable {
    private CouponsDBDAO couponsDBDAO = new CouponsDBDAO();
    @Setter
    @Getter
    private boolean quit = false;

// constructor
    public CouponExpirationDailyJob(CouponsDBDAO couponsDBDAO, boolean quit) {
        this.couponsDBDAO = couponsDBDAO;
        this.quit = quit;
    }
// implements runnable
    @Override
    public void run() {

        //ArrayList<Coupon> coupons = new ArrayList<>();

        while (!quit) {
            try {
                if (quit) {
                    stop();
                }
                //couponsDBDAO.getAllCouponsByEndDate(coupons); <= O(n)
                //zeev : delete from coupon where couponDate<curDate(); <= O(1)
                try {
                    Thread.sleep(1000 * 60 * 60 * 40);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }

            } catch (RuntimeException e) {
                throw new RuntimeException(e);
            }
        }
    }
                public void stop () {
                    this.quit = true;

                }

            }


