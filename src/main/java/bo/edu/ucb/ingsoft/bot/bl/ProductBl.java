package bo.edu.ucb.ingsoft.bot.bl;
import bo.edu.ucb.ingsoft.bot.dao.ProductDao;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductBl {

    private ProductDao productDao;
    List<ProductDto> result = new ArrayList<>();

    @Autowired
    public ProductBl(ProductDao productDao){
        this.productDao = productDao;
    }
    public List<ProductDto> listAllProduct(){
        return productDao.findAllProducts();
    }

    public List<ProductDto> listproduct() {
        return result;
    }
}
