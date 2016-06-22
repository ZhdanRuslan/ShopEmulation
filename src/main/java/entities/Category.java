package entities;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Ruslan Zhdan on 21.06.2016.
 */
public class Category {
    private String name;
    private List<Product> products = new ArrayList<Product>();

    public Category(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void registerNewProduct(String name) {
        if (name != null)
            products.add(new Product(name));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category = (Category) o;

        if (!getName().equals(category.getName())) return false;
        return getProducts().equals(category.getProducts());

    }

    @Override
    public int hashCode() {
        int result = getName().hashCode();
        result = 31 * result + getProducts().hashCode();
        return result;
    }
}
