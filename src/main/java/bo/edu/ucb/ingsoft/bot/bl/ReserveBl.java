package bo.edu.ucb.ingsoft.bot.bl;

import bo.edu.ucb.ingsoft.bot.dao.ReserveDao;
import bo.edu.ucb.ingsoft.bot.dto.ReserveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ReserveBl {
    private ReserveDao reserveDao;

    @Autowired
    public ReserveBl(ReserveDao reserveDao){
        this.reserveDao = reserveDao;
    }

    public void CreateReserve(int clientid, Date date){
        reserveDao.createReserve(clientid, date);
    }

    public List<ReserveDto> listAllReserve(){
        return reserveDao.listAllReserve();
    }
}
