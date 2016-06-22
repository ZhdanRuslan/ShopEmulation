package shop;

import entities.Product;
import entities.Status;

import java.net.UnknownHostException;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class BeerShop extends Shop {
    //Double Checked Locking & volatile Singleton
    private static volatile BeerShop instance;

    private BeerShop() throws UnknownHostException {
    }

    public static BeerShop getInstance() throws UnknownHostException {
        BeerShop localInstance = instance;
        if (localInstance == null) {
            synchronized (BeerShop.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new BeerShop();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void run() {
        System.out.print("Adding goods to BeerShop: ");
        for (int i = 1; i < 5; i++) {
            try {
                addProduct("Hike" + i, i, Status.AVAILABLE, "Common");
                addProduct("AnotherOne" + i, i, Status.AVAILABLE, "white");
                addProduct("AnotherTwo" + i, i, Status.AVAILABLE, "other");
            } catch (Exception e) {
                /*NOP*/
            }

        }
    }
}
