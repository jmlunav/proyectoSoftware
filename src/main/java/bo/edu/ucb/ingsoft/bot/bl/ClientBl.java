package bo.edu.ucb.ingsoft.bot.bl;

import bo.edu.ucb.ingsoft.bot.dao.ClientDao;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import org.jvnet.hk2.annotations.Service;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Service
public class ClientBl {

    private ClientDao clientDao;

    @Autowired
    public ClientBl(ClientDao clientDao) {
        this.clientDao = clientDao;
    }



    public List<ClientDto> findLast10PermissionsByChatId2(Long chatId) {
        return clientDao.findAllPermissionByBotChatId(chatId+"");
    }

    public List<ClientDto> findLast10PermissionsByChatId(Long chatId) {
        List<ClientDto> result = new ArrayList<>();
        result.add(new ClientDto("Juan", "Perez", "12345678", "0"));
        result.add(new ClientDto("Pablo", "Aguirre", "87654321", "1"));
        return result;
    }
}
