package bo.edu.ucb.ingsoft.bot.dto;

public class ProductReDto {
    private int id;
    private int reserve_id;
    private int product_id;
    private int lot;
    private String status;

    public ProductReDto(int id, int reserve_id, int product_id, int lot, String status) {
        this.id = id;
        this.reserve_id = reserve_id;
        this.product_id = product_id;
        this.lot = lot;
        this.status = status;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getReserve_id() {
        return reserve_id;
    }

    public void setReserve_id(int reserve_id) {
        this.reserve_id = reserve_id;
    }

    public int getProduct_id() {
        return product_id;
    }

    public void setProduct_id(int product_id) {
        this.product_id = product_id;
    }

    public int getLot() {
        return lot;
    }

    public void setLot(int lot) {
        this.lot = lot;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
