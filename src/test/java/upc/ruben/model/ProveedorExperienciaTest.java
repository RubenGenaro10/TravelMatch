package upc.ruben.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

public class ProveedorExperienciaTest {

    private ProveedorExperiencia proveedor;

    @BeforeEach
    void setUp() {
        proveedor = new ProveedorExperiencia("Guía Los Andes", TipoProveedor.GUIA_TURISTICO, "50 USD");
    }

    @Test
    @DisplayName("Escenario 1: El proveedor debe permanecer activo con 2 reportes o menos")
    void testProveedorSigueActivoConDosReportes() {
        // Arrange
        // (Viene del setUp)

        // Act
        proveedor.reportarIncumplimiento();
        proveedor.reportarIncumplimiento();

        // Assert
        assertTrue(proveedor.isActivo(), "El proveedor debería seguir activo con 2 reportes.");
        assertEquals(2, proveedor.getReportesIncumplimiento());
    }

    @Test
    @DisplayName("Escenario 2: El proveedor debe ser bloqueado automáticamente al tercer reporte")
    void testProveedorBloqueadoAlTercerReporte() {
        // Arrange (Configurado en setUp)

        // Act
        proveedor.reportarIncumplimiento(); // 1
        proveedor.reportarIncumplimiento(); // 2
        proveedor.reportarIncumplimiento(); // 3

        // Assert
        assertFalse(proveedor.isActivo(), "El proveedor debe estar bloqueado (activo = false) tras 3 reportes.");
    }

    @Test
    @DisplayName("Escenario 3: El proveedor no puede cobrar si el servicio no ha sido completado")
    void testCobroFallidoSiNoEstaCompletado() {
        // Arrange
        // (Estado inicial: completado es false)

        // Act
        proveedor.cobrar();

        // Assert
        assertFalse(proveedor.isServicioCompletado());
        assertFalse(proveedor.isCobrado());
    }

    @Test
    @DisplayName("Prueba Integral: Flujo de confirmación y cobro exitoso")
    void testIntegracionConfirmacionYCobro() {
        // Arrange: Proveedor activo y servicio publicado
        proveedor.publicarServicio("Tour 1", Collections.singletonList("foto.jpg"), Collections.singletonList("2024-10-10"));

        // Act: La agencia confirma que el servicio terminó y el proveedor intenta cobrar
        proveedor.confirmarCompletado();
        proveedor.cobrar();

        // Assert: Verificar consistencia del estado final
        assertTrue(proveedor.isServicioCompletado(), "El servicio debería marcarse como completado.");
        assertTrue(proveedor.isActivo(), "El proveedor debería seguir activo si no hay reportes.");
        assertTrue(proveedor.isCobrado(), "El proveedor debería haber cobrado.");
    }

    @Test
    @DisplayName("Escenario 4: Calificación de experiencia por parte del viajero")
    void testCalificacionExperiencia() {
        // Arrange
        Viajero viajero = new Viajero("Ruben", "77777777", "ruben@test.com", "999888777");

        // Act & Assert
        // El método calificarExperiencia solo imprime por consola en la versión actual del código
        // pero validamos que no lance excepciones al ejecutarse.
        assertDoesNotThrow(() -> viajero.calificarExperiencia(proveedor, 5));
    }

    @Test
    @DisplayName("Escenario 5: Cambio de estado del viaje por parte de la agencia")
    void testCambioEstadoViaje() {
        // Arrange
        AgenciaViaje agencia = new AgenciaViaje("Viajes UPC", "20123456789", "UPC SAC", "Ruben");

        // Act
        agencia.actualizarEstadoViaje(EstadoViaje.EN_CURSO);

        // Assert
        assertEquals(EstadoViaje.EN_CURSO, agencia.getEstadoViaje(), "El estado del viaje debería ser EN_CURSO.");

        agencia.actualizarEstadoViaje(EstadoViaje.FINALIZADO);
        assertEquals(EstadoViaje.FINALIZADO, agencia.getEstadoViaje(), "El estado del viaje debería ser FINALIZADO.");
    }

    @Test
    @DisplayName("Escenario 6: Prueba Integral - Flujo completo del viaje con todos los stakeholders")
    void testFlujoCompletoTravelMatch() {
        // 1. ARRANGE: Configuración de todos los actores
        // Viajero
        Viajero viajero = new Viajero("Juan Perez", "12345678", "juan@gmail.com", "999888777");

        // Agencia
        AgenciaViaje agencia = new AgenciaViaje("Viajes Machupicchu", "20123456789", "Viajes S.A.C.", "Carlos R.");

        // Proveedores
        ProveedorExperiencia hotel = new ProveedorExperiencia("Hotel Sol", TipoProveedor.HOTEL, "100 USD");
        ProveedorExperiencia guia = new ProveedorExperiencia("Guía Local", TipoProveedor.GUIA_TURISTICO, "50 USD");

        // Itinerario
        Itinerario itinerario = Itinerario.builder()
                .id("IT-001").actividad("City Tour").alojamiento(hotel.getNombre()).fecha("2024-12-25")
                .build();
        itinerario.agregarProveedor(hotel);
        itinerario.agregarProveedor(guia);

        // 2. ACT: Ejecución del flujo de negocio
        // Registro y Selección
        viajero.seleccionarAgenciaViaje(agencia);
        agencia.agregarItinerario(itinerario);
        viajero.elegirItinerarioYReservar(itinerario);

        // Ejecución y Pago
        agencia.actualizarEstadoViaje(EstadoViaje.EN_CURSO);
        viajero.realizarPago(150.0);
        agencia.emitirFactura("Venta Itinerario IT-001");

        // Finalización de servicios
        hotel.confirmarCompletado();
        guia.confirmarCompletado();
        hotel.cobrar();
        guia.cobrar();

        // Cierre del viaje
        agencia.actualizarEstadoViaje(EstadoViaje.FINALIZADO);
        viajero.calificarExperiencia(hotel, 5);
        viajero.calificarAgenciaViaje(5);

        // 3. ASSERT: Verificaciones finales
        assertAll(
                () -> assertEquals(EstadoViaje.FINALIZADO, agencia.getEstadoViaje(), "El viaje debe estar marcado como FINALIZADO"),
                () -> assertTrue(viajero.isPagoRealizado(), "El pago del viajero debe estar registrado"),
                () -> assertTrue(hotel.isCobrado(), "El hotel debe haber podido cobrar tras completar el servicio"),
                () -> assertTrue(guia.isCobrado(), "El guía debe haber podido cobrar tras completar el servicio"),
                () -> assertEquals(5, viajero.getPuntajeAgenciaViaje(), "La calificación de la agencia debe ser la asignada")
        );
    }
}
