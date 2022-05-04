package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.ClientBl;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.List;

@Service
public class QueryPastRequestsProcessImpl extends AbstractProcess {


    private ClientBl clientBl;


    @Autowired
    public QueryPastRequestsProcessImpl(ClientBl clientBl) {
        this.clientBl = clientBl;
        this.setName("Consultar solicitudes pasadas");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        //this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
    }

    // Retornar un Widget con la información de los permisos solicitados
//    @Override
//    public AbstractWidget onInit() {
//        return null;
//    }

    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, HhRrLongPollingBot bot) {
        Long chatId = update.getMessage().getChatId();

        List<ClientDto> permissionList = clientBl.findLast10PermissionsByChatId(chatId);
        StringBuffer sb = new StringBuffer();
        System.out.println("aaawwwwwwwwwwwwwwwwww");
        sb.append("Este año has solicitado " ).append(permissionList.size());
        sb.append(" permisos. Bajo el siguiente detalle\r\n");
        for(ClientDto permission: permissionList) {
            sb.append(permission.toString()).append("\n\r");
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
