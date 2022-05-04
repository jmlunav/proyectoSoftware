package bo.edu.ucb.ingsoft.bot.dao;

import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.jvnet.hk2.annotations.Service;


import java.util.List;

@Service
public interface ClientDao {
    @Select("SELECT c.name as name, c.lastname as surname, c.ci as ci, c.status as reserve_status" +
            " FROM 'Client'")
    List<ClientDto> findAllPermissionByBotChatId(String botChatId);
}
