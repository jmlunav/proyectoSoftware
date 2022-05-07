package bo.edu.ucb.ingsoft.bot.bl;

import bo.edu.ucb.ingsoft.bot.dao.ClientDao;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientBl {

    private ClientDao clientDao;

    @Autowired
    public ClientBl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public Integer getIdCLientforChatid (String chatid){
        return clientDao.getidCLientforChatid(chatid);
    }
    public void createClient(String name, String lastname, String ci, String status, String chatid){
        clientDao.createClient(name, lastname, ci, status, chatid);
    }


    public List<ClientDto> findLast10PermissionsByChatId(Long chatId) {
        return clientDao.findAllPermissionByBotChatId(chatId+"");
    }


}
