package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.ClientBl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;



@Service
public class RegisterClientProcessImpl extends AbstractProcess {
/*Aqui vamos a registrar al cliente*/
    private int ci =0;
    String nombre= "";
    String apellido= "";
    private int sw=0;
    private ClientBl clientBl;
    private Logger logger = LoggerFactory.getLogger(RegisterClientProcessImpl.class);
    @Autowired
    public RegisterClientProcessImpl(ClientBl clientBl) {
        this.clientBl = clientBl;
        this.setName("Iniciar registro del cliente");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
       // this.setUserData(new HashMap<>());
        this.setStatus("STARTED");
    }

    /*Aqui haremos la interaccion del usuario y el bot
    * va a registrar el nombre, apellido, celular, carnet*/
    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, HhRrLongPollingBot bot) {
        AbstractProcess result = this; //marca que estoy en el mismo proceso
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            if(clientBl.getIdCLientforChatid(chatId+"")==null){
                registrarCarnet(bot, chatId);

            }else {
                ClienteRegistrado(bot, chatId);
                result = new MenuProcessImpl();
            }

        } else {
            if (this.getStatus().equals("AWAITING_USER_RESPONSE") ) {
                Message message = update.getMessage();
                if (message.hasText()) {
                    String text = message.getText();
                    switch (sw)
                    {
                        case 0:
                            try {
                                int aux = Integer.parseInt(text); // convertimos a un numero entero
                                ci = aux;
                                logger.info("guardamos el carnet: " + ci);
                                registrarnombre(bot,chatId,text);
                            } catch (NumberFormatException ex) {
                                registrarCarnet(bot, chatId);
                                logger.error("aqui encontro un error");
                            }
                            break;
                        case 11:
                            try {
                                nombre= text;
                            }catch (NumberFormatException ex) {
                                logger.error("aqui encontro un error");
                            } finally {
                                logger.info("guardamos el nombre: "+nombre);
                                registrarapellido(bot,chatId,text);
                            }
                            break;
                        case 22:
                            sw =33;
                            apellido= text;
                            logger.info("guardamos el apellido: "+apellido);
                            mostrarDatos(bot,chatId);
                            break;
                    }

                    int btnfinal= Integer.parseInt(text);
                    switch (btnfinal)
                    {
                        case 1:
                            registrarCliente(bot, chatId);
                            result = new MenuProcessImpl();
                            break;
                        case 2:
                            result = new MenuProcessImpl();
                            break;
                    }
                }
            }
        }

        return result;
    }

    private void registrarCliente(HhRrLongPollingBot bot, Long chatId){
        clientBl.createClient(nombre, apellido, ci +"", "3", chatId+"");
        StringBuffer sb = new StringBuffer();
        sb.append("Registro exitoso \r\n");
        sendStringBuffer(bot, chatId, sb);
    }
    private void ClienteRegistrado(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("USTED YA SE ENCUENTRA REGISTRADO \r\n");
        sendStringBuffer(bot, chatId, sb);
    }
    //aqui registramos el apellido del cliente
    private void registrarapellido(HhRrLongPollingBot bot, Long chatId, String text) {

        logger.info("entramos en la funcion apellido");

        StringBuffer sb = new StringBuffer();
        sb.append("Por favor ingresa tu apellido \r\n");
        sendStringBuffer(bot, chatId, sb);
        sw=22;
        this.setStatus("AWAITING_USER_RESPONSE");

    }
    //aqui registramos el nombre del cliente
    private void registrarnombre(HhRrLongPollingBot bot, Long chatId,String text) {

        StringBuffer sb = new StringBuffer();

        sb.append("Por favor ingresa tu nombre \r\n");
        sendStringBuffer(bot, chatId, sb);
        sw=11;
        this.setStatus("AWAITING_USER_RESPONSE");


    }

    //Aqui vamos a registrar al cliente: carnet
    private void registrarCarnet (HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Registro de cliente \r\n");
        sb.append("Por favor ingresa tu numero de carnet \r\n");
        sendStringBuffer(bot, chatId, sb);
        sw= 0;
        this.setStatus("AWAITING_USER_RESPONSE");

    }


    //aqui va a mostrar datos
    private void mostrarDatos (HhRrLongPollingBot bot, long chatId)
    {
        StringBuffer sb1 = new StringBuffer();
        sb1.append("Muchas gracias sus datos son \r\n");
        sb1.append("Carnet: " + ci + " \r\n");
        sb1.append("Nombre: "+ nombre + " \r\n");
        sb1.append("Apellido: "+ apellido + " \r\n");
        sb1.append("\r\n");
        sb1.append("Elija una opción:\r\n");
        sb1.append("1. Guardar y volver al Menu Principal\r\n");
        sb1.append("2. Cancelar \r\n");

        sendStringBuffer(bot, chatId, sb1);
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


