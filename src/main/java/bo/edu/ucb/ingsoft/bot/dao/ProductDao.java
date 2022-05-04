package bo.edu.ucb.ingsoft.bot.dao;

import bo.edu.ucb.ingsoft.bot.dto.ProductDto;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ProductDao {
    @Select("SELECT p.product_id as id, p.name as name, p.price as price, p.stock as stock, p.status as status" +
            " FROM \"Product\" p")
    List<ProductDto> findAllProducts();
}
