## Tarea Control de Parqueo

## Datos del estudiante

**Nombre:** Hans Douglas Eddenilzon Alvarado Milián  
**Carné:** 9941-25-17016

## Descripción

El programa Control de Parqueo permite registrar varios vehículos y calcular el pago correspondiente según el tipo de vehículo, las horas estacionadas y si el conductor perdió el ticket.

El sistema utiliza las tarifas establecidas para motocicletas, automóviles y pickups o camionetas. También aplica un descuento del 15% cuando el vehículo permanece más de 8 horas y agrega un recargo de Q50.00 cuando se pierde el ticket.

Al finalizar, el programa muestra un resumen general de la jornada con la cantidad de vehículos registrados por tipo, cantidad de tickets perdidos, total recaudado y el vehículo que realizó el pago más alto.

## Métodos creados

### obtenerTarifa(int tipoVehiculo)

Determina la tarifa por hora según el tipo de vehículo:

- Motocicleta: Q5.00
- Automóvil: Q8.00
- Pickup o camioneta: Q12.00

### obtenerNombreVehiculo(int tipoVehiculo)

Devuelve el nombre correspondiente al tipo de vehículo seleccionado.

### calcularDescuento(double subtotal, int horas)

Calcula un descuento del 15% sobre el subtotal cuando el vehículo permanece más de 8 horas.

### calcularPago(int horas, double tarifa)

Calcula el pago cuando el conductor no perdió el ticket.

### calcularPago(int horas, double tarifa, double recargo)

Calcula el pago cuando existe un recargo por ticket perdido.

### mostrarComprobante(...)

Muestra los datos del vehículo, subtotal, descuento, recargo y total a pagar.

### mostrarResumen(...)

Muestra los resultados generales de la jornada.

## Sobrecarga

La sobrecarga se utiliza en el método `calcularPago`.

Existen dos métodos con el mismo nombre pero diferente cantidad de parámetros:

```java
calcularPago(int horas, double tarifa)
