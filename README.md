# Proyecto Ingenieria del Software
Integrantes del equipo:

 1. Gutber Luis Blanco Gomez
 2. Teddy Leyder Aruquipa Peralta 
 3. José Marcos Luna Vasquez

## Introducción
El presente proyecto está dirigido al desarrollo de un bot en la plataforma Telegram, el cual dispondrá de diferentes funcionalidades, que tendrá como objetivo apoyar en la venta de productos de la empresa Lunvas.
## Problemática
La empresa Lunvas, cuya actividad principal es la venta de productos para construcción de obra fina, está expandiendo su negocio con la apertura de 2 sucursales nuevas, por lo cual observaron que cuentan con los siguientes problemas:

* Debido a la alta cantidad de mensajes no pueden responder a todas las consultas que realiza el cliente.
* Al contar con varios productos, se requiere de un sistema de inventarios.
* No se puede realizar una reserva de un producto en línea. 

También se puede observar que los clientes cuentan con los siguientes problemas:

* Demora en la respuesta a sus consultas.
* La mayoría de las consultas son solicitudes de cotizaciones de cierto tipo y cantidad de productos.
* No saber la sucursal en la que se encuentra su producto.
* No tener la posibilidad de hacer una reserva del producto deseado.
 
## Valor

### Tangible
* Contar con un servicio de reserva de productos.
* Automatización en el proceso de consultas y reservas.
* Mayor control del inventario.
* Poder responder rápidamente las consultas de los clientes
* Poder entregar rápidamente las cotizaciones que solicitan los clientes
### Intangible
* Mejorar la imagen de la empresa.
* Mejorar el proceso de ventas.
* Mejor atencion y experiencia al cliente.
## Ejemplo del funcionamiento del Bot
### Menú del Cliente
```
Cliente: Hola
Bot: Hola, bienvenido a la empresa Lunvas. Por favor, seleccione una opción:
1. Información de la empresa (Ubicación, horarios de atención) 
2. Ver lista de precios de los productos
3. Realizar una reserva
4. Realizar cotización
5. Salir
```
Si selecciona la opción 2:
```
Bot: (Mostrar productos)
1. Aceros
2. Azulejos
3. Tuberias
4. Aluminio
5. Pinturas
6. Buscar producto
7. Atrás
```
Si escoge la opcion 6: (Buscar producto)
```
Bot: Por favor, introduzca el nombre del producto o el código
Cliente: Pintura Monopol Rojo
Bot: Disponemos de las siguientes opciones:
1. Pintura Monopol Rojo 5l
2. Pintura Monopol Rojo Premium
3. Atrás
```
Si selecciona la opción 1:
```
Bot: Pintura Monopol Rojo 5L
     Precio: 80Bs.
     Disponible en: Sucursal 2
     Detalles: Pintura en agua, marca Monopol ideal para pintar paredes de cuartos o terminados en plancha fina. Cuenta con 5 litros.
```
 ## Ejemplo 2
 ### Menú del cliente
 ```
 Cliente: Hola
 Bot: Hola, bienvenido a la empresa Lunvas.
 Por favor seleccione una opción:
 1. Información de la empresa (Ubicación, horarios de atención) 
2. Ver lista de precios de los productos
3. Realizar una reserva
4. Realizar cotización
5. Salir
 ``` 
 Si el cliente selecciona: 4
 ```
 bot: Perfecto! Escoge la opción de la cotización que busca:
 1. Accesorios para vidrio templado puerta batiente
 2. Accesorios para vidrio templado puerta corrediza
 3. Pizarras 
 4. Otros
 5. Volver al menú Principal 
 ```
 El cliente selecciona ahora la opción Nº 1

 ```
 Bot: Muy bien! Por favor Ingrese a que nombre irá la cotización
 Cliente: José Luna
 Bot: Perfecto! Tiene código de cliente preferente? 
 1. Sí 
 2. No
 Cliente: 1
 Bot: Super! Ingrese su codigo
 Cliente: jmluna
 Bot: Perfecto! aqui tiene su cotización con los descuentos correspondientes por ser cliente preferente.
 COTIZACION.PDF
 Bot: ¿Desea algo más?
 1. Sí
 2. No
 Cliente: 2
 Bot: Muchas gracias por la preferencia, que tenga una excelente jornada.  
 ```