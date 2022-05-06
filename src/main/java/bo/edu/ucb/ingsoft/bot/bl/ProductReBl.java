package bo.edu.ucb.ingsoft.bot.bl;

import bo.edu.ucb.ingsoft.bot.dao.ProductReDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductReBl {
    private ProductReDao productReDao;

    @Autowired
    public ProductReBl(ProductReDao productReDao){
        this.productReDao = productReDao;
    }

    public void addProductRe (int reserveid, int productid, int lot, String status){
        productReDao.addProductRe(reserveid, productid, lot, status);
    }
}
