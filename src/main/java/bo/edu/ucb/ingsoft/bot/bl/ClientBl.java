package bo.edu.ucb.ingsoft.bot.bl;

import bo.edu.ucb.ingsoft.bot.dto.ClientDto;

import java.util.ArrayList;
import java.util.List;

public class ClientBl {
    public ClientBl() {

    }

    public List<ClientDto> findLast10PermissionsByChatId(Long chatId) {
        List<ClientDto> result = new ArrayList<>();
        result.add(new ClientDto("Juan", "Perez", "12345678", "0"));
        result.add(new ClientDto("Pablo", "Aguirre", "87654321", "1"));
        return result;
    }
}
