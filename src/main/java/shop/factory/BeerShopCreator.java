package shop.factory;
import shop.BeerShop;
import shop.Shop;

import java.net.UnknownHostException;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class BeerShopCreator implements Creator {

    public Shop getNewShop() throws UnknownHostException {
        return BeerShop.getInstance();
    }
}
