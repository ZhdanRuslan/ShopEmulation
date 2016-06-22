package shop.factory;

import entities.Category;
import shop.BeerShop;
import shop.CandyShop;
import shop.Shop;

import java.net.UnknownHostException;
import java.util.Set;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class CandyShopCreator implements Creator {

    public Shop getNewShop() throws UnknownHostException {
        return CandyShop.getInstance();
    }
}
