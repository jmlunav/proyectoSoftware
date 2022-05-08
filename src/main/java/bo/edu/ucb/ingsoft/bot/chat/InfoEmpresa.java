package bo.edu.ucb.ingsoft.bot.chat;

import org.springframework.context.ApplicationContext;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class InfoEmpresa extends AbstractProcess{


    public InfoEmpresa() {
        this.setName("Informacion de la empresa");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        // this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
    }


    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, HhRrLongPollingBot bot) {
        AbstractProcess result = this; //marca que estoy en el mismo proceso
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            diretion(bot, chatId);
        }
        else {
            if (this.getStatus().equals("AWAITING_USER_RESPONSE") ) {
                Message message = update.getMessage();
                if (message.hasText()) {
                    String text = message.getText();
                    int btnfinal= Integer.parseInt(text);
                    switch (btnfinal)
                    {
                        case 1:
                            result = new MenuProcessImpl();
                            break;
                        default: result = new MenuProcessImpl();
                    }
                }

            }
        }



        return result;

    }
    private void diretion(HhRrLongPollingBot bot, Long chatId)
    {
        String urlLP= "https://g.page/TEKTRON_Bolivia?share";
        String urlEA= "https://g.page/TecnoprecoCruceViacha?share";
        String urlCBBA= "https://goo.gl/maps/LzDmvVjcqkTpTzR97";
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Nuestras direcciones son: \r\n");
        sb1.append("\r\n");
        sb1.append("La Paz: Av. Armentia C/ Alto de la Alizanza N 769 \r\n");
        sb1.append(urlLP + " \r\n");
        sb1.append("Horarios de atención: De Lunes a Viernes de 08:00 a 19:00 hrs \r\n");
        sb1.append(" \r\n");
        sb1.append("El Alto: Cruce Viacha (Al lado de Derechos Reales N 588) \r\n");
        sb1.append(urlEA + " \r\n");
        sb1.append("Horarios de atención: De Lunes a Domingo de 08:00 a 19:00 hrs \r\n");
        sb1.append(" \r\n");
        sb1.append("Cohabamba: C/ Titicaca Esq C / Mamá Ocllo cerca de Transito \r\n");
        sb1.append(urlCBBA + " \r\n");
        sb1.append("Horarios de atención: De Lunes a Viernes de 08:00 a 19:00 hrs \r\n");
        sb1.append("\r\n");
        sb1.append("Seleccione: \r\n");
        sb1.append("  1  para volver al Menu Principal\r\n");



        sendStringBuffer(bot, chatId, sb1);
        this.setStatus("AWAITING_USER_RESPONSE");
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
