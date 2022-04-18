package bo.edu.ucb.ingsoft.bot.dto;

public class ProductDto {
    private String id;
    private String name;
    private String price;
    private String lot;

    public ProductDto(String id, String name, String price, String lot) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.lot = lot;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLot() {
        return lot;
    }

    public void setLot(String lot) {
        this.lot = lot;
    }
}