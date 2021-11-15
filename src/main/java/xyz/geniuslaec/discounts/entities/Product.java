package xyz.geniuslaec.discounts.entities;

public class Product {
    private String name;
    private Money price;
    private ProductType productType;

    public Product(String name, Money price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public Product(){
        name = "";
        price = new Money();
        productType = null;
    }

    public String getName() {
        return name;
    }

    public Money getPrice() {
        return price;
    }

    public void setProductType(ProductType productType) {
        this.productType = productType;
    }

    public ProductType getProductType() {
        return productType;
    }
}
