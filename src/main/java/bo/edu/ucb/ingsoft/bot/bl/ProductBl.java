package bo.edu.ucb.ingsoft.bot.bl;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;

import java.util.ArrayList;
import java.util.List;
public class ProductBl {
    List<ProductDto> result = new ArrayList<>();

    public ProductBl() {
        /*result.add(new ProductDto("1", "Pintura blanca", "25", "0"));
        result.add(new ProductDto("2", "Pintura verde", "45", "0"));
        result.add(new ProductDto("3", "Pintura ploma", "55", "0"));
        result.add(new ProductDto("4", "Compresora de Aire", "70", "0"));
        result.add(new ProductDto("5", "Taladro", "90", "0"));
        result.add(new ProductDto("6", "Martillo", "22", "0"));
        result.add(new ProductDto("7", "Manija 96 mm de aluminio", "15", "0"));
        result.add(new ProductDto("8", "Escalera", "47", "0"));
        result.add(new ProductDto("9", "Nivel", "21", "0"));
        result.add(new ProductDto("10", "Alicate", "12", "0"));*/
    }

    public List<ProductDto> listproduct() {
        return result;
    }
}
