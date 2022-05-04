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
        String urlLP= "https://www.google.com.bo/maps/place/Tektron+Bolivia+Ltda./@-16.4918009,-68.1363446,19z/data=!4m13!1m7!3m6!1s0x915f200b6dcff557:0x970cdf5958d68a47!2sAve+Armentia,+La+Paz!3b1!8m2!3d-16.4897973!4d-68.1382572!3m4!1s0x915f21254691ea65:0x47adf96ae8cce832!8m2!3d-16.4918171!4d-68.136188?hl=es";
        String urlEA= "https://www.google.com.bo/maps/place/Tecnopreco+-+El+Alto+(Sucursal+Cruce+Viacha)/@-16.5163231,-68.167228,19.6z/data=!4m12!1m6!3m5!1s0x915edfa01a8b2b19:0x70c09b5eb1db4d70!2sTecnopreco+-+El+Alto+(Sucursal+Cruce+Viacha)!8m2!3d-16.5164208!4d-68.1675599!3m4!1s0x915edfa01a8b2b19:0x70c09b5eb1db4d70!8m2!3d-16.5164208!4d-68.1675599?hl=es";
        String urlCBBA= "https://www.google.com.bo/maps/place/RIEGOTEC+SUCURSAL+2/@-17.405789,-66.1676549,18.82z/data=!4m12!1m6!3m5!1s0x93e37392f0c85b35:0xa52989d40e6cb177!2sPolic%C3%ADa+Boliviana+Oficina+de+Transito!8m2!3d-17.4052193!4d-66.1678322!3m4!1s0x93e37344b74d82a7:0x3a7b5e1bc742d958!8m2!3d-17.405834!4d-66.1664155?hl=es";
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Bienvenido!!\r\n");
        sb1.append("Nuestras direcciones son: \r\n");
        sb1.append("La Paz: Av. Armentia C/ Alto de la Alizanza N 769 \r\n");
        sb1.append("\r\n");
        sb1.append(urlLP + " \r\n");
        sb1.append("Horarios de atención: De Lunes a Viernes de 08:00 a 19:00 hrs \r\n");
        sb1.append(" \r\n");
        sb1.append("El Alto: Cruce Viacha (Al lado de Derechos Reales N 588) \r\n");
        sb1.append("\r\n");
        sb1.append(urlEA + " \r\n");
        sb1.append("Horarios de atención: De Lunes a Domingo de 08:00 a 19:00 hrs \r\n");
        sb1.append(" \r\n");
        sb1.append("Cohabamba: C/ Titicaca Esq C / Mamá Ocllo cerca de Transito \r\n");
        sb1.append("\r\n");
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
