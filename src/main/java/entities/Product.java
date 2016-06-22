package entities;
import org.bson.Document;
import org.bson.types.ObjectId;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */

public class Product {

    private ObjectId id;
    private String title;
    private double price;
    private Status status;
    private String category;

    public Product(String title) {
        this.title = title;
    }

    public Product(Document document) {
        id = document.getObjectId("_id");
        title = document.getString("title");
        price = document.getDouble("price");
        category = document.getString("category");

        String status = document.getString("status");

        switch (status) {
            case "absent":
                this.status = Status.ABSENT;
                break;
            case "available":
                this.status = Status.AVAILABLE;
                break;
            case "expected":
                this.status = Status.EXPECTED;
                break;
        }
    }

    public ObjectId getId() {
        return id;
    }

    public void setId(ObjectId id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Document toDocument() {

        return new Document()
                .append("_id", id)
                .append("title", title)
                .append("price", price)
                .append("status", status.toString())
                .append("category", category);
    }
}