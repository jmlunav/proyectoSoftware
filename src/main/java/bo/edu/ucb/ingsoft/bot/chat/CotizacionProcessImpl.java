package bo.edu.ucb.ingsoft.bot.chat;

import bo.edu.ucb.ingsoft.bot.bl.*;
import bo.edu.ucb.ingsoft.bot.dto.ProductDto;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.html.WebColors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import com.itextpdf.text.Image;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class CotizacionProcessImpl extends AbstractProcess{

    private int state= 0;
    private ProductBl productBl;
    private List<ProductDto> ProductosSolicitados = new ArrayList<>();

    @Autowired
    public CotizacionProcessImpl(ProductBl productBl){
        this.productBl = productBl;
        this.setName("Solicitud de cotización");
        this.setDefault(false);
        this.setExpires(false);
        this.setStartDate(System.currentTimeMillis()/1000);
        this.setStatus("STARTED");

    }
    @Override
    public AbstractProcess handle(ApplicationContext context, Update update, HhRrLongPollingBot bot) {

        AbstractProcess result = this; // sigo en el mismo proceso.
        Long chatId = update.getMessage().getChatId();

        if (this.getStatus().equals("STARTED")) {
            showCotizaciónMenu(bot, chatId);

        } else if (this.getStatus().equals("AWAITING_USER_RESPONSE")) {
            Message message = update.getMessage();
            if ( message.hasText() ) {

                String text = message.getText();
                try {

                    if (state == 1){
                        addProduct(bot, chatId, text);

                    } else
                    if (state == 2){
                        try {
                            int lot = Integer.parseInt(text);
                            addlotProduct(bot, chatId, lot);
                        }catch (Exception e){
                            System.out.println(e);
                        }

                    }else if(state == 3){
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
                                generateCotazacionPdf(bot, chatId);
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

    private void generateCotazacionPdf(HhRrLongPollingBot bot, Long chatId) {
        Document documento = new Document();
        StringBuffer sb = new StringBuffer();
        int num=1;

        try{
            Image header = Image.getInstance("/ProyectoSoftware/src/main/java/bo/edu/ucb/ingsoft/bot/images/Cabecera.jpg");
            header.scaleToFit(500,1000);
            header.setAlignment(Chunk.ALIGN_CENTER);

            String ruta = System.getProperty("user.home");
            PdfWriter.getInstance(documento, new FileOutputStream(ruta + "/Desktop/Cotización.pdf"));
            documento.open();
            documento.add(header);

            PdfPTable tabla = new PdfPTable(4);
            tabla.addCell("Numero");
            tabla.addCell("Producto");
            tabla.addCell("Precio");
            tabla.addCell("Cantidad");

            for (ProductDto product: ProductosSolicitados){
                tabla.addCell(String.valueOf(num));
                tabla.addCell(product.getName());
                tabla.addCell(String.valueOf(product.getPrice()));
                tabla.addCell(String.valueOf(product.getStock()));
                num++;
            }
            documento.add(tabla);
            documento.close();
            sb.append("Cotizacion creada Exitosamente\r\n");

        }catch (Exception e){
        }
        sendStringBuffer(bot, chatId, sb);
        showCotizaciónMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");

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
        List<ProductDto> productDtoList = productBl.listAllProduct();

        sb.append("LISTA DE PRODUCTOS DISPONIBLES\r\n\n");
        for (ProductDto product: productDtoList){
            sb.append("Id: "+product.getId()+" ");
            sb.append("Nombre: "+product.getName()+" ");
            sb.append("Precio: "+product.getPrice()+"\r\n");
        }
        sb.append("\nPor favor ingrese el id del producto a cotizar:\r\n");
        state = 1;
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void addProduct(HhRrLongPollingBot bot, Long chatId, String text){
        StringBuffer sb = new StringBuffer();

        List<ProductDto> productDtoList = productBl.listAllProduct();
        int Productfind = 0;
        boolean existe = false;
        for (ProductDto product: productDtoList){
            if(product.getId()==Integer.parseInt(text)){
                if(ProductosSolicitados.isEmpty()){
                    sb.append(product.getName()+" añadido a la cotización\r\n");
                    ProductosSolicitados.add(product);
                    state = 2;
                    sb.append("Ingrese la cantidad deseada: \r\n");
                    sendStringBuffer(bot, chatId, sb);
                    Productfind = 1;
                }else{
                    for (ProductDto product2: ProductosSolicitados){
                        if(product2.getId()==Integer.parseInt(text))
                        {
                            existe=true;
                        }
                    }
                    if(existe){
                        sb.append(product.getName()+" ya se encuentra en la cotización\r\n");
                    }else{
                        sb.append(product.getName()+" añadido a la cotización\r\n");
                        ProductosSolicitados.add(product);
                        state = 2;
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
            state = 0;
            sendStringBuffer(bot, chatId, sb);
            showCotizaciónMenu(bot, chatId);
        }


        this.setStatus("AWAITING_USER_RESPONSE");
    }

    private void addlotProduct(HhRrLongPollingBot bot, Long chatId, int text){
        ProductosSolicitados.get(ProductosSolicitados.size()-1).setStock(text);
        state = 0;
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
        state = 3;
        sendStringBuffer(bot, chatId, sb);

        this.setStatus("AWAITING_USER_RESPONSE");
    }

    private void deleteproduct(HhRrLongPollingBot bot, Long chatId, int text){
        ProductosSolicitados.remove(text-1);
        StringBuffer sb = new StringBuffer();
        sb.append("Producto eliminado de la cotización\r\n");
        state = 0;
        sendStringBuffer(bot, chatId, sb);
        showCotizaciónMenu(bot, chatId);
        this.setStatus("AWAITING_USER_RESPONSE");
    }
    private void showListProducts(HhRrLongPollingBot bot, Long chatId){
        StringBuffer sb = new StringBuffer();
        sb.append("Cotizacion:\r\n\n");
        int num = 1;
        double suma = 0;
        for (ProductDto product: ProductosSolicitados){

            sb.append(num+") "+ product.getName()+":"+"\r\n");
            sb.append("\tPrecio: "+ product.getPrice()+" Bs"+"\r\n");
            sb.append("\tCantidad: "+ product.getStock()+""+"\r\n\n");
            num++;
            suma= suma+(product.getPrice())*(product.getStock());
        }
        sb.append("Costo Total de la cotización: "+suma+" Bs"+"\r\n");
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
