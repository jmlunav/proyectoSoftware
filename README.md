# Proyecto Ingeniería del Software
Integrantes del equipo:

 1. Gutber Luis Blanco Gomez
 2. Teddy Leyder Aruquipa Peralta 
 3. José Marcos Luna Vasquez

## Introducción
El presente proyecto está dirigido al desarrollo de un bot en la plataforma Telegram, el cual dispondrá de diferentes funcionalidades, que tendrán como objetivo principal apoyar en la venta de productos de la empresa Metal Corp.
## Problemática
La empresa Metal Corp, cuya actividad principal es la venta de productos para construcción de obra fina, está expandiendo su negocio con la apertura de 2 nuevas sucursales, por lo cual observaron que cuentan con los siguientes problemas:

* Debido a la alta cantidad de mensajes no pueden responder a todas las consultas.
* Al contar con varios productos, se requiere de un sistema de inventarios.
* No se puede realizar una reserva de un producto en linea.

También se puede observar que los clientes cuentan con los siguientes problemas:

* Demora en la respuesta a sus consultas.
* No saber la sucursal en la que se encuentra su producto.
* No tener la posibilidad de hacer una reserva del producto deseado.
 
## Valor

### Tangible
* Contar con un servicio de reserva de productos.
* Automatización en el proceso de consultas y reservas.
* Mayor control del inventario.
### Intangible
* Mejorar la imagen de la empresa.
* Mejorar el proceso de ventas.
* Mejorar la atencion y proporcinarle una experiencia mucho mas satisfactoria al cliente.
## Ejemplo del funcionamiento del Bot
### Menú del Cliente
``` 
Cliente: Hola
Bot: Hola, bienvenido a Metal Corp. Por favor, seleccione una opción:
1. Información de la empresa
2. Ver lista de productos
3. Realizar una reserva
4. Salir
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
Si escoge la opcion 6:
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