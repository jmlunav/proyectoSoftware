package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.ClientBl;
import bo.edu.ucb.ingsoft.bot.bl.PermissionBl;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import bo.edu.ucb.ingsoft.bot.dto.PermissionDto;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.List;

public class ReserveProcessImpl extends AbstractProcess{

    public ReserveProcessImpl(){
        this.setName("Iniciar Reservacion");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        //this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
    }
    @Override
    public AbstractProcess handle(Update update, HhRrLongPollingBot bot) {
        Long chatId = update.getMessage().getChatId();
        ClientBl clientBl = new ClientBl();
        List<ClientDto> clientDtoList = clientBl.findLast10PermissionsByChatId(chatId);
        StringBuffer sb = new StringBuffer();
        sb.append("Este año has reservado " ).append(clientDtoList.size());
        sb.append(" permisos. Bajo el siguiente detalle\r\n");
        for(ClientDto client: clientDtoList) {
            sb.append(client.toString()).append("\n\r");
        }
        sendStringBuffer(bot, chatId, sb);
        return new MenuProcessImpl();
    }

    @Override
    public AbstractProcess onError() {
        return null;
    }

    @Override
    public AbstractProcess onSuccess() {
        return null;
    }

    @Override
    public AbstractProcess onTimeout() {
        return null;
    }
}
