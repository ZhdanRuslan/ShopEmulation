import com.mongodb.*;
import shop.factory.Creator;
import shop.BeerShop;
import shop.CandyShop;
import shop.Shop;
import shop.factory.BeerShopCreator;
import shop.factory.CandyShopCreator;

import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class Main {
    //thread execution delay in milliseconds
    private static final int EXEC_DELAY = 10000;

    public static void main(String[] args) throws UnknownHostException {

        Creator[] shops = {
                new BeerShopCreator(),
                new CandyShopCreator()
        };

        ExecutorService executor = Executors.newFixedThreadPool(shops.length);
        try {
            System.out.println("Launching stores' threads...");
            for (int i = 0; i < shops.length; i++) {
                if (i > 0) Thread.sleep(EXEC_DELAY);
                executor.execute(shops[i].getNewShop());
            }
            executor.shutdown();
            executor.awaitTermination(Integer.MAX_VALUE, TimeUnit.SECONDS);
            System.out.println("Store threads finished.");

        } catch (InterruptedException ignored) {
            System.err.println("Error: thread interrupted.");
            System.exit(1);
        }
    }
}
