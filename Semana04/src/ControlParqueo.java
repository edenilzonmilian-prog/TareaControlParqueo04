import java.util.Scanner;

public class ControlParqueo {


    static Scanner entrada = new Scanner(System.in);

    public static void main(String[] args) {

        System.out.println("==========================================");
        System.out.println("       SISTEMA DE CONTROL DE PARQUEO");
        System.out.println("==========================================");
        System.out.println("Estudiante: Hans Douglas Edenilzon Alvarado Milián");
        System.out.println("Carné: 9941-25-17016");
        System.out.println("==========================================");

        int cantidadVehiculos;


        do {
            System.out.print("Ingrese la cantidad de vehículos a registrar: ");
            cantidadVehiculos = entrada.nextInt();

            if (cantidadVehiculos <= 0) {
                System.out.println("Error: la cantidad debe ser mayor que cero.");
            }

        } while (cantidadVehiculos <= 0);

        // Contadores
        int cantidadMotocicletas = 0;
        int cantidadAutomoviles = 0;
        int cantidadPickups = 0;
        int cantidadTicketsPerdidos = 0;

        // Acumulador
        double totalRecaudado = 0;

        // Para determinar el pago más alto
        double pagoMasAlto = 0;
        String placaPagoMasAlto = "";

        // Ciclo para procesar vehículos
        for (int i = 1; i <= cantidadVehiculos; i++) {

            System.out.println("\n========== VEHÍCULO #" + i + " ==========");

            entrada.nextLine(); // Limpiar buffer

            // Placa
            System.out.print("Ingrese el número de placa: ");
            String placa = entrada.nextLine();

            // Tipo de vehículo
            int tipoVehiculo;

            do {
                System.out.println("\nTipos de vehículo:");
                System.out.println("1. Motocicleta");
                System.out.println("2. Automóvil");
                System.out.println("3. Pickup o camioneta");
                System.out.print("Seleccione el tipo de vehículo: ");

                tipoVehiculo = entrada.nextInt();

                if (tipoVehiculo < 1 || tipoVehiculo > 3) {
                    System.out.println("Error: seleccione una opción entre 1 y 3.");
                }

            } while (tipoVehiculo < 1 || tipoVehiculo > 3);

            // Horas estacionadas
            int horas;

            do {
                System.out.print("Ingrese las horas estacionadas: ");
                horas = entrada.nextInt();

                if (horas <= 0) {
                    System.out.println("Error: las horas deben ser mayores que cero.");
                }

            } while (horas <= 0);

            // Ticket perdido
            char ticketPerdido;

            do {
                System.out.print("¿Perdió el ticket? (S/N): ");
                ticketPerdido = entrada.next().toUpperCase().charAt(0);

                if (ticketPerdido != 'S' && ticketPerdido != 'N') {
                    System.out.println("Error: solamente puede ingresar S o N.");
                }

            } while (ticketPerdido != 'S' && ticketPerdido != 'N');

            // Obtener tarifa
            double tarifa = obtenerTarifa(tipoVehiculo);

            // Calcular subtotal
            double subtotal = horas * tarifa;

            // Calcular descuento
            double descuento = calcularDescuento(subtotal, horas);

            // Determinar recargo
            double recargo = 0;

            if (ticketPerdido == 'S') {
                recargo = 50;
                cantidadTicketsPerdidos++;
            }


            double total;

            if (ticketPerdido == 'S') {
                total = calcularPago(horas, tarifa, recargo) - descuento;
            } else {
                total = calcularPago(horas, tarifa) - descuento;
            }


            switch (tipoVehiculo) {
                case 1:
                    cantidadMotocicletas++;
                    break;

                case 2:
                    cantidadAutomoviles++;
                    break;

                case 3:
                    cantidadPickups++;
                    break;
            }


            totalRecaudado += total;

            // Verificar pago más alto
            if (total > pagoMasAlto) {
                pagoMasAlto = total;
                placaPagoMasAlto = placa;
            }


            mostrarComprobante(
                    placa,
                    tipoVehiculo,
                    horas,
                    tarifa,
                    subtotal,
                    descuento,
                    recargo,
                    total
            );
        }

        // Resumen final
        mostrarResumen(
                cantidadMotocicletas,
                cantidadAutomoviles,
                cantidadPickups,
                cantidadTicketsPerdidos,
                totalRecaudado,
                pagoMasAlto,
                placaPagoMasAlto
        );

        entrada.close();
    }


    public static double obtenerTarifa(int tipoVehiculo) {

        switch (tipoVehiculo) {
            case 1:
                return 5.00;

            case 2:
                return 8.00;

            case 3:
                return 12.00;

            default:
                return 0;
        }
    }


    public static String obtenerNombreVehiculo(int tipoVehiculo) {

        switch (tipoVehiculo) {
            case 1:
                return "Motocicleta";

            case 2:
                return "Automóvil";

            case 3:
                return "Pickup o camioneta";

            default:
                return "Desconocido";
        }
    }


    public static double calcularDescuento(double subtotal, int horas) {

        if (horas > 8) {
            return subtotal * 0.15;
        }

        return 0;
    }


    public static double calcularPago(int horas, double tarifa) {

        return horas * tarifa;
    }


    public static double calcularPago(int horas, double tarifa, double recargo) {

        return (horas * tarifa) + recargo;
    }


    public static void mostrarComprobante(
            String placa,
            int tipoVehiculo,
            int horas,
            double tarifa,
            double subtotal,
            double descuento,
            double recargo,
            double total) {

        System.out.println("\n========== COMPROBANTE ==========");
        System.out.println("Placa: " + placa);
        System.out.println("Tipo: " + obtenerNombreVehiculo(tipoVehiculo));
        System.out.println("Horas estacionado: " + horas);
        System.out.printf("Tarifa por hora: Q%.2f%n", tarifa);
        System.out.printf("Subtotal: Q%.2f%n", subtotal);
        System.out.printf("Descuento: Q%.2f%n", descuento);
        System.out.printf("Recargo por ticket perdido: Q%.2f%n", recargo);
        System.out.printf("TOTAL: Q%.2f%n", total);
        System.out.println("=================================");
    }


    public static void mostrarResumen(
            int motocicletas,
            int automoviles,
            int pickups,
            int ticketsPerdidos,
            double totalRecaudado,
            double pagoMasAlto,
            String placaPagoMasAlto) {

        System.out.println("\n\n==========================================");
        System.out.println("          RESUMEN DE LA JORNADA");
        System.out.println("==========================================");

        System.out.println("Cantidad de motocicletas: " + motocicletas);
        System.out.println("Cantidad de automóviles: " + automoviles);
        System.out.println("Cantidad de pickups/camionetas: " + pickups);
        System.out.println("Cantidad de tickets perdidos: " + ticketsPerdidos);

        System.out.printf("Total de dinero recaudado: Q%.2f%n", totalRecaudado);

        System.out.printf(
                "Pago más alto: Q%.2f - Placa: %s%n",
                pagoMasAlto,
                placaPagoMasAlto
        );

        System.out.println("==========================================");
        System.out.println("        FIN DEL PROGRAMA");
        System.out.println("==========================================");
    }
}