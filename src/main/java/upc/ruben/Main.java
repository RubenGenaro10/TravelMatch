package upc.ruben;

import upc.ruben.model.*;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        System.out.println("--- Verificando TravelMatch: Flujo Completo ---");

        // 1. Registro de Stakeholders
        Viajero viajero = new Viajero("Juan Perez", "12345678", "juan@gmail.com", "999888777");
        AgenciaViaje agencia = new AgenciaViaje("Viajes Machupicchu", "20123456789", "Viajes Machupicchu S.A.C.", "Carlos Ruiz");

        ProveedorExperiencia hotel = new ProveedorExperiencia("Hotel Sol", TipoProveedor.HOTEL, "100 USD");
        ProveedorExperiencia guia = new ProveedorExperiencia("Guia Local Juan", TipoProveedor.GUIA_TURISTICO, "50 USD");

        // 2. Proveedores publican servicios
        hotel.publicarServicio("Hotel 5 estrellas con vista al valle", Arrays.asList("foto1.jpg", "foto2.jpg"), Arrays.asList("2024-07-15", "2024-07-16"));
        guia.publicarServicio("Guía experto en historia inca", Arrays.asList("guia.jpg"), Arrays.asList("2024-07-16"));
        System.out.println("Proveedores publicaron sus servicios.");

        // 3. Agencia diseña itinerario usando los proveedores
        Itinerario itinerario = Itinerario.builder()
                .id("IT-001")
                .actividad("Tour Valle Sagrado")
                .alojamiento(hotel.getNombre())
                .transporte("Bus privado")
                .fecha("2024-07-16")
                .build();
        itinerario.agregarProveedor(hotel);
        itinerario.agregarProveedor(guia);
        agencia.agregarItinerario(itinerario);

        // 4. Viajero consulta, elige y reserva
        viajero.seleccionarAgenciaViaje(agencia);
        viajero.chatearConAgencia("¿El hotel tiene wifi?");
        viajero.elegirItinerarioYReservar(itinerario);

        // 5. Gestión del Viaje y Pago
        agencia.actualizarEstadoViaje(EstadoViaje.EN_CURSO);
        viajero.realizarPago(150.0);
        agencia.emitirFactura("Pago total del viaje IT-001");

        // 6. REGLA DE NEGOCIO: Cobro de Proveedores
        System.out.println("\nIntentando cobrar sin completar servicio...");
        hotel.cobrar(); // Debe fallar

        System.out.println("\nConfirmando servicios completados...");
        hotel.confirmarCompletado();
        guia.confirmarCompletado();

        System.out.println("\nIntentando cobrar ahora...");
        hotel.cobrar(); // Debe tener éxito
        guia.cobrar();

        // 7. Calificaciones y Reporte Final
        viajero.calificarExperiencia(hotel, 5);
        viajero.calificarAgenciaViaje(4);
        agencia.actualizarEstadoViaje(EstadoViaje.FINALIZADO);
        agencia.generarReporteSatisfaccion();

        System.out.println("\n--- Resumen Final ---");
        System.out.println("Estado del viaje: " + agencia.getEstadoViaje());
        System.out.println("Proveedor " + hotel.getNombre() + " cobrado: " + hotel.isCobrado());
    }
}