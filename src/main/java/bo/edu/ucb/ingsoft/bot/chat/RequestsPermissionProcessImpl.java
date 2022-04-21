package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.chat.widgets.AbstractWidget;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import java.util.HashMap;

public class RequestsPermissionProcessImpl extends AbstractProcess {
/*Aqui vamos a registrar al cliente*/
    private int ci =0;
      String nombre= "";
     String apellido= "";
private int sw=0;


    public RequestsPermissionProcessImpl() {
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
    public AbstractProcess handle(Update update, HhRrLongPollingBot bot) {
        AbstractProcess result = this; //marca que estoy en el mismo proceso
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            registrarCarnet(bot, chatId);

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
                                    System.out.println("guardamos el carnet: " + ci);
                                    registrarnombre(bot,chatId,text);

                                } catch (NumberFormatException ex) {
                                    registrarCarnet(bot, chatId);
                                    System.out.println("aqui encontro un error");
                                }
                            break;
                        case 11:
                            try {
                                nombre= text;

                            }catch (NumberFormatException ex) {
                                System.out.println("aqui encontro un error");
                            } finally {
                                System.out.println("guardamos el nombre: "+nombre);
                                registrarapellido(bot,chatId,text);
                            }

                            break;
                        case 22:
                            sw =33;
                            apellido= text;
                            System.out.println("guardamos el apellido: "+apellido);
                            mostrarDatos(bot,chatId);
                            break;
                    }

                    int btnfinal= Integer.parseInt(text);
                    switch (btnfinal)
                    {
                        case 1:
                            result = new MenuProcessImpl();
                            break;
                        case 2:
                            registrarCarnet(bot,chatId);
                            break;
                    }


                }

            }
        }

        return result;
    }
    //aqui registramos el apellido del cliente
    private void registrarapellido(HhRrLongPollingBot bot, Long chatId, String text) {

        System.out.println("entramos en la funcion apellido");

        StringBuffer sb = new StringBuffer();
        sb.append("Registro al cliente \r\n");
        sb.append("Por favor ingresa tu apellido \r\n");
        sendStringBuffer(bot, chatId, sb);
        sw=22;
        this.setStatus("AWAITING_USER_RESPONSE");

    }
    //aqui registramos el nombre del cliente
    private void registrarnombre(HhRrLongPollingBot bot, Long chatId,String text) {

        StringBuffer sb = new StringBuffer();
        sb.append("Registro al cliente \r\n");
        sb.append("Por favor ingresa tu nombre \r\n");
        sendStringBuffer(bot, chatId, sb);
        sw=11;
        this.setStatus("AWAITING_USER_RESPONSE");


    }

    //Aqui vamos a registrar al cliente: carnet
    private void registrarCarnet (HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Registro al cliente \r\n");
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
        sb1.append("1. Guardar y volver al Menu Principal\r\n");
        sb1.append("2. Editar \r\n");
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


        /*


        //mostramos sus datos
        sb.append("Muchas gracias sus datos son \r\n");
        sb.append("Carnet: "+ci+ " \r\n");
        sb.append("Nombre: "+nombre+ " \r\n");
        sb.append("Apellido: "+apellido+ " \r\n");
        sb.append("Carnet: "+celular+ " \r\n");
        sb.append("Presione 1 para volver al menu principal \r\n");
        sb.append("Presione 2 para salir \r\n");

        this.setStatus("AWAITING_USER_RESPONSE");
        //Aqui esperamos si escribe la opcion 1 o la opcion 2
        MenuProcessImpl menu = new MenuProcessImpl();

        if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            // Estamos esperando por un numero 1 o 2
            Message message = update.getMessage();
            if (message.hasText()) {
                // Intentamos transformar en número
                String text = message.getText(); // El texto contiene letras
                try {
                    int opcion = Integer.parseInt(text); //convertimos a numero
                    switch (opcion) {
                        case 1:
                            menu.showMainMenu(bot, chatId);
                            break;
                        case 2:
                            sb.append("Ten un excelente día \r\n");
                            ;
                            break;
                        default:
                            menu.showMainMenu(bot, chatId);
                    }
                } catch (NumberFormatException ex) {
                    menu.showMainMenu(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                menu.showMainMenu(bot, chatId);
            }
        }


        try {
           // bot.execute(sendMessage);
        } catch (Exception ex) {
            // relanzamos la excepción
            throw new RuntimeException(ex);
        }
        */
}


