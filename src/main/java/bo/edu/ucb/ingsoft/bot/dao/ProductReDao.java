package bo.edu.ucb.ingsoft.bot.dao;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;

@Service
public interface ProductReDao {
    @Insert("INSERT INTO \"Product_re\" (reserve_id, product_id, lot, status) VALUES (#{reserveid}, #{productid}, " +
            "#{lot}, #{status})")
    public void addProductRe (@Param("reserveid") int reserveid, @Param("productid") int productid,
                              @Param("lot") int lot, @Param("status") String status);
}
