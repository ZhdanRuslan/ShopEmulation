package shop;

import DAO.MongoDAO;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.client.MongoCollection;
import entities.Category;
import entities.Product;
import entities.Status;
import org.bson.Document;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.net.UnknownHostException;
import java.util.*;

import static DAO.MongoDAO.getInstance;


/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */

public abstract class Shop implements Runnable {

    Set<Category> categories = new HashSet<Category>();

    MongoDAO mongo;
    MongoCollection collection;
    protected Shop() throws UnknownHostException {
        mongo = getInstance();
    }

    public void addCategory(String name) throws Exception {
        collection = mongo.createNewCollection(name);
    }

    public void addProduct(String title, double price, Status status, String category) throws Exception {
        Map<String, String> map = new HashMap();
        map.put("title", title);
        map.put("price", String.valueOf(price));
        map.put("status", status.toString());
        map.put("category", category);
        BasicDBObject basicDBObject = new BasicDBObject(map);
        mongo.saveToDB(category, basicDBObject);
    }

    public void changeStatus(Product product, Status newStatus) throws Exception {
        collection.updateOne(product.toDocument(), new Document("$set", new Document("status", newStatus.toString())));
    }

    public void upThePrice(Product product, double price) {
        price = new BigDecimal(price).setScale(3, RoundingMode.HALF_UP).doubleValue();
        collection.updateOne(product.toDocument(), new Document("$set", new Document("price", price)));
    }

    DBCollection getProductsByCategory(String category) throws Exception {
        return mongo.getCollectionByName(category);
    }
}
