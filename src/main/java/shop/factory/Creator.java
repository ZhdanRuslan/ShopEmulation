package shop.factory;

import entities.Category;
import shop.Shop;

import java.net.UnknownHostException;
import java.util.Set;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public interface Creator {
    public abstract Shop getNewShop() throws UnknownHostException;
}
