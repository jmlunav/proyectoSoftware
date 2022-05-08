package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.*;
import bo.edu.ucb.ingsoft.bot.dto.ClientDto;
import bo.edu.ucb.ingsoft.bot.dto.PermissionDto;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;
import bo.edu.ucb.ingsoft.bot.dto.ReserveDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class ReserveProcessImpl extends AbstractProcess{

    private int statereserve = 0;
    private ProductBl productBl;
    private ReserveBl reserveBl;
    private ProductReBl productReBl;
    private ClientBl clientBl;
    private List<ProductDto> carrito = new ArrayList<>();

    @Autowired
    public ReserveProcessImpl(ProductBl productBl, ReserveBl reserveBl, ProductReBl productReBl, ClientBl clientBl){
        this.productBl = productBl;
        this.reserveBl = reserveBl;
        this.productReBl = productReBl;
        this.clientBl = clientBl;
        this.setName("Iniciar Reservacion");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        //this.setUserData(new HashMap<>());
        this.setStatus("STARTED");

    }
    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, HhRrLongPollingBot bot) {

        AbstractProcess result = this; // sigo en el mismo proceso.
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            if(clientBl.getIdCLientforChatid(chatId+"")==null){
                ClientRegisterMessage(bot, chatId);
                result = new MenuProcessImpl();
            }else {
                showReserveMenu(bot, chatId);
            }
        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            // Estamos esperando por un numero 1 o 2
            Message message = update.getMessage();
            if ( message.hasText() ) {
                // Intentamos transformar en número

                String text = message.getText(); // El texto contiene asdasdas
                try {

                    if (statereserve == 1){
                        addProduct(bot, chatId, text);

                    } else
                    if (statereserve == 2){
                        try {
                            int lot = Integer.parseInt(text);
                            addlotProduct(bot, chatId, lot);
                            //temporalmente
                            //showReserveMenu(bot, chatId);
                        }catch (Exception e){
                            System.out.println(e);
                        }

                    }else if(statereserve == 3){
                        try {
                            int numdelete = Integer.parseInt(text);
                            deleteproduct(bot, chatId, numdelete);

                        }catch (Exception e){
                            System.out.println(e);
                        }
                    }
                    else {
                        int opcion = Integer.parseInt(text);

                        switch (opcion){
                            case 1 :
                                addProductmessage(bot, chatId);
                                break;
                            case 2 :
                                deleteproductmessage(bot, chatId);
                                break;
                            case 3:
                                sendRequestReserve(bot, chatId);
                                break;
                            case 4:
                                showListProducts(bot, chatId);
                                break;
                            case 5:
                                result = new MenuProcessImpl();
                                break;
                            default: showReserveMenu(bot, chatId);
                        }
                    }

                } catch (NumberFormatException ex) {
                    showReserveMenu(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                showReserveMenu(bot, chatId);

            }
        }
        return result;

    }

    private void ClientRegisterMessage(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("USTED NO SE ENCUENTRA REGISTRADO \r\n");
        sb.append("Para usar esta función debe registrarse. \r\n");
        sb.append("(pulse cualquier tecla para volver al menu principal)\r\n");
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("STARTED");
    }
    private void showReserveMenu(HhRrLongPollingBot bot, Long chatId) {

        StringBuffer sb = new StringBuffer();
        sb.append("MENU RESERVA - BOT Metal Corp\r\n");
        sb.append("1. Agregar productos\r\n");
        sb.append("2. Eliminar productos\r\n");
        sb.append("3. Enviar Solicitud\r\n");
        sb.append("4. Ver lista\r\n");
        sb.append("5. Cancelar\r\n");
        sb.append("Elija una opción:\r\n");

        /*sendStringBuffer(bot, chatId, sb);
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId.toString());
        sendMessage.setText("<b>bold</b>");
        sendMessage.setParseMode("HTML");
        try {
            bot.sendMyMessage(sendMessage);
        }catch (Exception e){

        }*/
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }

    private void addProductmessage(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Por favor ingrese el id del producto a reservar:\r\n");
        statereserve = 1;
        List<ProductDto> productDtoList = productBl.listAllProduct();

        sb.append("LISTA DE PRODUCTOS DISPONIBLES\r\n\n");
        for (ProductDto product: productDtoList){
            sb.append("Id: "+product.getId()+" ");
            sb.append("Nombre: "+product.getName()+" ");
            sb.append("Precio: "+product.getPrice()+"\r\n");
        }
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void addProduct(HhRrLongPollingBot bot, Long chatId, String text){
        StringBuffer sb = new StringBuffer();
        List<ProductDto> productDtoList = productBl.listAllProduct();
        int Productfind = 0;
        for (ProductDto product: productDtoList){
            if(product.getId()==Integer.parseInt(text)){
                sb.append("Producto añadido al carrito:\r\n");
                sb.append("Id: "+product.getId()+"\r\n");
                sb.append("Nombre: "+product.getName()+"\r\n");
                sb.append("Precio: "+product.getPrice()+"\r\n");
                Productfind = 1;
                carrito.add(product);
                statereserve = 2;
                sb.append("Producto encontrado, ingrese la cantidad a reservar: \r\n");
                sendStringBuffer(bot, chatId, sb);
            }
        }
        if(Productfind == 0){
            sb.append("Producto no encontrado\r\n");
            statereserve = 0;
            sendStringBuffer(bot, chatId, sb);
            showReserveMenu(bot, chatId);
        }


        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void addlotProduct(HhRrLongPollingBot bot, Long chatId, int text){
        carrito.get(carrito.size()-1).setStock(text);
        statereserve = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("Producto añadido al carrito\r\n");
        sendStringBuffer(bot, chatId, sb);
        showReserveMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void deleteproductmessage(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Por favor ingrese la opción del producto a eliminar:\r\n");

        int num = 1;
        sb.append("lista de productos:\r\n");
        for (ProductDto product: carrito){

            sb.append(num+") "+ product.getName()+"\r\n");
            num++;
        }
        statereserve = 3;
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void deleteproduct(HhRrLongPollingBot bot, Long chatId, int text){
        carrito.remove(text-1);
        StringBuffer sb = new StringBuffer();
        sb.append("Producto eliminado:\r\n");
        statereserve = 0;
        sendStringBuffer(bot, chatId, sb);
        showReserveMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void sendRequestReserve(HhRrLongPollingBot bot, Long chatId){
        if(carrito.size()==0){
            StringBuffer sb = new StringBuffer();
            sb.append("La lista de productos reservados esta vacia. POR  FAVOR AÑADA UN PRODUCTO\n");
            sendStringBuffer(bot, chatId, sb);
            showReserveMenu(bot, chatId);
        }else{
            StringBuffer sb = new StringBuffer();
            int num = 1;
            sb.append("lista de productos:\r\n");
            List<ReserveDto> reservetoList = reserveBl.listAllReserve();
            for (ProductDto product: carrito){
                productReBl.addProductRe(reservetoList.size()+1, product.getId(), product.getStock(), "1");
                sb.append(num+") "+ product.getName()+"|    Cantidad: "+product.getStock()+"\r\n");
                num++;
            }
            Date fecha = new Date();
            reserveBl.CreateReserve(1, fecha);
            sb.append("La solicitud de reserva fue enviada.\r\n");
            sb.append("Por favor, recoja sus productos en un plazo maximo de 5 dias a partir de la fecha\r\n");
            sendStringBuffer(bot, chatId, sb);

            this.setStatus("AWAITING_USER_RESPONSE");
            MenuProcessImpl mn = new MenuProcessImpl();
        }


    }
    private void showListProducts(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("lista de productos:\r\n");
        int num = 1;
        for (ProductDto product: carrito){

            sb.append(num+") "+ product.getName()+":"+"\r\n");
            sb.append("\tPrecio: "+ product.getPrice()+""+"\r\n");
            sb.append("\tCantidad: "+ product.getStock()+""+"\r\n");
            num++;
        }
        sendStringBuffer(bot, chatId, sb);
        showReserveMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }

   /* private void showReserveMenu2(HhRrLongPollingBot bot, Long chatId) {
        ClientBl clientBl = new ClientBl();
        List<ClientDto> clientDtoList = clientBl.findLast10PermissionsByChatId(chatId);
        StringBuffer sb = new StringBuffer();
        sb.append("MENU RESERVA - BOT Metal Corp\r\n");
        sb.append("1. Agregar productos\r\n");
        sb.append("2. Eliminar productos\r\n");
        sb.append("3. Enviar Solicitud\r\n");
        sb.append("4. Cancelar\r\n");
        sb.append("Elija una opción:\r\n");

        for(ClientDto client: clientDtoList) {
            sb.append(client.toString()).append("\n\r");
        }
        sendStringBuffer(bot, chatId, sb);
        this.setStatus("AWAITING_USER_RESPONSE");

    }*/

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
