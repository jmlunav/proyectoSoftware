package bo.edu.ucb.ingsoft.bot.dao;

import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Service;


import java.util.List;

@Service
public interface ClientDao {
    @Select("SELECT c.name as name, c.lastname as surname, c.ci as ci, c.status as reserve_status" +
            " FROM \"Client\" c")
    List<ClientDto> findAllPermissionByBotChatId(String botChatId);

    @Select("SELECT client_id FROM \"Client\" WHERE chatid = #{chatid}")
    Integer getidCLientforChatid(@Param("chatid") String chatid);

    @Insert("INSERT INTO \"Client\" (name, lastname, ci, status, chatid) VALUES (#{name}, #{lastname}, #{ci}, " +
            "#{status}, #{chatid})")
    void createClient(@Param("name") String name, @Param("lastname") String lastname, @Param("ci") String ci,
                      @Param("status") String status, @Param("chatid") String chatid);
}
