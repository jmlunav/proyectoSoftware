# Proyecto Ingenieria del Software

## Introducci√n
El presente proyecto est√° dirigido al desarrollo de un bot en la plataforma Telegram, el cual dispondr√°de diferent funcionalidades, que tendr√°como objetivo apoyar en la venta de productos de la empresa Metal Corp.

## Problem√°tica
La empresa Metal Corp. dedicada a la venta de productos para la construcci√≥n, es√ expandiendo su negocio con la apertura de 2 sucursales nuevas, por lo cual observaron que cuentan con los siguientes problemas:

* Debido a la alta cantidad de mensajes no pueden responder a todas las consultas.
* Al contar con varios productos, se requiere de un sistema de inventarios.
* No se puede realizar una reserva de un producto en linea.

Tambi√n se observ√≥que los clientes cuentan con los siguientes problemas:
* Demora en la respuesta a sus consultas.
* No saber en qu√©sucursal se encuentra su producto.
* No tener la posibilidad de hacer una reserva.
 
## Valor

### Tangible
* Contar con un servicio de reserva de productos.
* Automatizaci√n en el proceso de consultas y reservas.
* Mayor control del inventario.
### Intangible
* Mejorar la imagen de la empresa.
* Mejorar el proceso de ventas.

## Ejemplo del funcionamiento del Bot
### Men√∫ de Cliente
``` 
Cliente: Hola
Bot: Hola, bienvenido a Metal Corp. Por favor, seleccione una opci√n:
1. Informaci√n de la empresa
2. Ver lista de productos
3. Realizar una reserva
4. Salir
```
Si selecciona la opci√n 2:
```
Bot: (Mostrar productos)
1. Aceros
2. Azulejos
3. Tuberias
4. Aluminio
5. Pinturas
6. Buscar producto
7. Atr√s
```
Si escoge la opcion 6:
```
Bot: Por favor, introduzca el nombre del producto o el c√digo
Cliente: Pintura Monopol Rojo
Bot: Disponemos de las siguientes opciones:
1. Pintura Monopol Rojo 5l
2. Pintura Monopol Rojo Premium
3. Atr√s
```
Si selecciona la opci√n 1:
```
Bot: Pintura Monopol Rojo 5L
     Precio: 80Bs.
     Disponible en: Sucursal 2
     Detalles: Pintura en agua, marca Monopol ideal para pintar paredes de cuartos o terminados en plancha fina. Cuenta con 5 litros.
```

