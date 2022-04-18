package bo.edu.ucb.ingsoft.bot.bl;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
public class ProductBl {
    List<ProductDto> result = new ArrayList<>();

    public ProductBl() {
        result.add(new ProductDto("1", "Pintura blanca", "25", "0"));
        result.add(new ProductDto("2", "Pintura verde", "45", "0"));
        result.add(new ProductDto("3", "Pintura plomo", "55", "0"));
    }

    public List<ProductDto> listproduct() {
        return result;
    }
}
