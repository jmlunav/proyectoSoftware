package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.ProductBl;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;
import org.springframework.boot.web.servlet.server.Session;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CotizacionProcessImpl extends AbstractProcess{

    private int statereserve = 0;
    private List<ProductDto> ProductosSolicitados = new ArrayList<>();

    public CotizacionProcessImpl(){
        this.setName("Solicitud de cotización");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        //this.setUserData(new HashMap<>());
        this.setStatus("STARTED");

    }
    @Override
    public AbstractProcess handle(Update update, HhRrLongPollingBot bot) {

        AbstractProcess result = this; // sigo en el mismo proceso.
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            showCotizaciónMenu(bot, chatId);

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
                                selectedProductmessage(bot, chatId);
                                break;
                            case 2 :
                                deleteproductmessage(bot, chatId);
                                break;
                            case 3:
                                showListProducts(bot, chatId);
                                break;
                            case 4:

                                break;
                            case 5:
                                result = new MenuProcessImpl();
                                break;
                            default: showCotizaciónMenu(bot, chatId);
                        }
                    }

                } catch (NumberFormatException ex) {
                    showCotizaciónMenu(bot, chatId);
                }
                // continuar con el proceso seleccionado
            } else { // Si me enviaron algo diferente de un texto.
                showCotizaciónMenu(bot, chatId);

            }
        }
        return result;

    }

    private void showCotizaciónMenu(HhRrLongPollingBot bot, Long chatId) {

        StringBuffer sb = new StringBuffer();
        sb.append("MENU COTIZACION - BOT Metal Corp\r\n\n");
        sb.append("1. Seleccionar productos\r\n");
        sb.append("2. Eliminar productos\r\n");
        sb.append("3. Ver Lista\r\n");
        sb.append("4. Generar Cotización\r\n");
        sb.append("5. Cancelar\r\n\n");
        sb.append("Elija una opción:\r\n");

        sendStringBuffer(bot, chatId, sb);


        this.setStatus("AWAITING_USER_RESPONSE");
    }

    private void selectedProductmessage(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        ProductBl productBl = new ProductBl();
        List<ProductDto> productDtoList = productBl.listproduct();

        sb.append("LISTA DE PRODUCTOS DISPONIBLES\r\n\n");
        for (ProductDto product: productDtoList){
                sb.append("Id: "+product.getId()+" ");
                sb.append("Nombre: "+product.getName()+" ");
                sb.append("Precio: "+product.getPrice()+"\r\n");
        }
        sb.append("\nPor favor ingrese el id del producto a cotizar:\r\n");
        statereserve = 1;
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void addProduct(HhRrLongPollingBot bot, Long chatId, String text){
        StringBuffer sb = new StringBuffer();

        ProductBl productBl = new ProductBl();
        List<ProductDto> productDtoList = productBl.listproduct();
        int Productfind = 0;
        boolean existe = false;
        for (ProductDto product: productDtoList){
            if(product.getId().equals(text)){
                if(ProductosSolicitados.isEmpty()){
                    sb.append(product.getName()+" añadido a la cotización\r\n");
                    ProductosSolicitados.add(product);
                    statereserve = 2;
                    sb.append("Ingrese la cantidad deseada: \r\n");
                    sendStringBuffer(bot, chatId, sb);
                    Productfind = 1;
                }else{
                    for (ProductDto product2: ProductosSolicitados){
                        if(product2.getId().equals(text))
                        {
                           existe=true;
                        }
                    }
                    if(existe){
                        sb.append(product.getName()+" ya se encuentra en la cotización\r\n");
                    }else{
                        sb.append(product.getName()+" añadido a la cotización\r\n");
                        ProductosSolicitados.add(product);
                        statereserve = 2;
                        sb.append("Ingrese la cantidad deseada: \r\n");
                    }
                    sendStringBuffer(bot, chatId, sb);
                    if(existe)selectedProductmessage(bot, chatId);
                    Productfind = 1;
                }
            }
        }
        if(Productfind == 0){
            sb.append("Producto no encontrado\r\n");
            statereserve = 0;
            sendStringBuffer(bot, chatId, sb);
            showCotizaciónMenu(bot, chatId);
        }


        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void addlotProduct(HhRrLongPollingBot bot, Long chatId, int text){
        ProductosSolicitados.get(ProductosSolicitados.size()-1).setLot(text+"");
        statereserve = 0;
        StringBuffer sb = new StringBuffer();
        sb.append("Producto añadido a la cotización\r\n");
        sendStringBuffer(bot, chatId, sb);
        showCotizaciónMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void deleteproductmessage(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Por favor seleccione el producto a eliminar:\r\n\n");

        int num = 1;
        for (ProductDto product: ProductosSolicitados){

            sb.append(num+") "+ product.getName()+"\r\n");
            num++;
        }
        statereserve = 3;
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void deleteproduct(HhRrLongPollingBot bot, Long chatId, int text){
        ProductosSolicitados.remove(text-1);
        StringBuffer sb = new StringBuffer();
        sb.append("Producto eliminado de la cotización\r\n");
        statereserve = 0;
        sendStringBuffer(bot, chatId, sb);
        showCotizaciónMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }

    private void showListProducts(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("lista de productos:\r\n");
        int num = 1;
        for (ProductDto product: ProductosSolicitados){

            sb.append(num+") "+ product.getName()+":"+"\r\n");
            sb.append("\tPrecio: "+ product.getPrice()+""+"\r\n");
            sb.append("\tCantidad: "+ product.getLot()+""+"\r\n");
            num++;
        }
        sendStringBuffer(bot, chatId, sb);
        showCotizaciónMenu(bot, chatId);
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
