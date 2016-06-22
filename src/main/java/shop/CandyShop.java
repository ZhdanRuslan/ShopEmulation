package shop;

import entities.Category;

import java.net.UnknownHostException;
import java.util.Set;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class CandyShop extends Shop {
    //Double Checked Locking & volatile Singleton
    private static volatile CandyShop instance;

    private CandyShop() throws UnknownHostException {
    }

    public static CandyShop getInstance() throws UnknownHostException {
        CandyShop localInstance = instance;
        if (localInstance == null) {
            synchronized (CandyShop.class) {
                localInstance = instance;
                if (localInstance == null) {
                    instance = localInstance = new CandyShop();
                }
            }
        }
        return localInstance;
    }

    @Override
    public void run() {

    }
}
