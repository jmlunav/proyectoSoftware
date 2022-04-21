package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.chat.widgets.AbstractWidget;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

public class RequestsPermissionProcessImpl extends AbstractProcess {
/*Aqui vamos a registrar al cliente*/
    private int ci;
      String nombre= "";
     String apellido= "";
     String celular = "";

    public RequestsPermissionProcessImpl() {
        this.setName("Iniciar registro del cliente");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        this.setStatus("STARTED");
    }

    // Retornar un Widget Solicitando Fecha Inicio
//    @Override
//    public AbstractWidget onInit() {
//        return null;
//    }

    /*Aqui haremos la interaccion del usuario y el bot
    * va a registrar el nombre, apellido, celular, carnet*/
    @Override
    public AbstractProcess handle(Update update, HhRrLongPollingBot bot) {
        AbstractProcess result = this; //marca que estoy en el mismo proceso
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            registrarCarnet(bot, chatId);

        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            Message message=update.getMessage();

            if (message.hasText() ) {
                String text = message.getText();
                try {
                    int opcion = Integer.parseInt(text);

                    ci = Integer.parseInt(text); //Atrapamos el mensaje y convertimos a un numero entero
                    System.out.println("guardamos el carnet: " + ci);
                StringBuffer sb1 = new StringBuffer();
                sb1.append("Muchas gracias sus datos son \r\n");
                sb1.append("Carnet: " + ci + " \r\n");
                sendStringBuffer(bot, chatId, sb1);

                } catch (NumberFormatException ex) {
                    registrarCarnet(bot, chatId);
                }
            } else {
                registrarCarnet(bot, chatId);
            }
        }
        /*
        StringBuffer sb = new StringBuffer();
        if (this.getStatus().equals("AWAITING_USER_RESPONSE") ) {
            sb.append("Ingrese el numero de carnet : \r\n");
            sendStringBuffer(bot, chatId, sb);
            //this.setStatus("AWAITING_USER_RESPONSE");
            System.out.println("esperamos que ingrese su carnet");
            Message message1=update.getMessage();

            if (message1.hasText() ) {
                String auxci = message1.getText();
                ci = Integer.parseInt(auxci); //Atrapamos el mensaje y convertimos a un numero entero
                System.out.println("guardamos el carnet: " + ci);
            }

            StringBuffer sb1 = new StringBuffer();
            sb1.append("Muchas gracias sus datos son \r\n");
            sb1.append("Carnet: " + ci + " \r\n");
            sendStringBuffer(bot, chatId, sb1);
        }

        else {
            StringBuffer sb1 = new StringBuffer();
            sb1.append("Muchas gracias sus datos son \r\n");
            sb1.append("Carnet: " + ci + " \r\n");
            sendStringBuffer(bot, chatId, sb1);

        }
        this.setStatus("AWAITING_USER_RESPONSE");
        */
        /*aqui ingresará su nombre*/
       /*
        sb.append("Ingrese su nombre por favor : \r\n");
        sendStringBuffer(bot, chatId, sb);
        this.setStatus("AWAITING_USER_RESPONSE");
        if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            System.out.println("esperamos que ingrese su nombre");
            Message message = update.getMessage();
            if ( message.hasText() ) {
                nombre = message.getText(); // guardamos el nombre
                System.out.println("guardamos el nombre: " + nombre);
            }
        }

        //aqui ingresará su apellido
        sb.append("Ingrese su apellido por favor : \r\n");
        this.setStatus("AWAITING_USER_RESPONSE");
        if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            System.out.println("esperamos que ingrese su apellido");
            Message message = update.getMessage();
            apellido= message.getText(); // guardamos el apellido
            System.out.println("guardamos el apellido: "+apellido);
        }

        //aqui ingresará su celular
        sb.append("Ingrese su numero de celular por favor : \r\n");
        this.setStatus("AWAITING_USER_RESPONSE");
        if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            System.out.println("esperamos que ingrese su numero de celular");
            Message message = update.getMessage();
            celular= message.getText(); // guardamos el numero de celular
            System.out.println("guardamos el numero de celular: "+celular);
        }
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
        return result;
    }

    //Aqui vamos a registrar al cliente: carnet
    private void registrarCarnet (HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Registro al cliente \r\n");
        sb.append("Por favor ingresa tu numero de carnet \r\n");
        sendStringBuffer(bot, chatId, sb);
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


