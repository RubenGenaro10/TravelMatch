package upc.ruben.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Viajero {

    private String id;
    private String nombre;
    private String DNI;
    private String email;
    private String telefono;
    private String destinoDeseado;
    private String fechaViaje;
    private String presupuesto;
    private String preferenciasViaje;
    private AgenciaViaje agenciaViajeSeleccionada;
    private int puntajeAgenciaViaje;
    private List<ProveedorExperiencia> proveedoresContratados = new ArrayList<>();
    private List<String> historialChats = new ArrayList<>();
    private List<String> notificaciones = new ArrayList<>();
    private boolean pagoRealizado = false;

    public Viajero( String nombre, String DNI, String email, String telefono) {
        this.nombre = nombre;
        this.DNI = DNI;
        this.email = email;
        this.telefono = telefono;
        this.proveedoresContratados = new ArrayList<>();
    }

    public void actualizarPerfilViaje(String destinoDeseado, String fechaViaje, String presupuesto, String preferenciasViaje) {
        this.destinoDeseado = destinoDeseado;
        this.fechaViaje = fechaViaje;
        this.presupuesto = presupuesto;
        this.preferenciasViaje = preferenciasViaje;
    }

    //calificar experiencia
    public void calificarExperiencia(ProveedorExperiencia proveedor, int calificacion) {
        System.out.println("Viajero " + nombre + " calificó al proveedor " + proveedor.getNombre() + " con " + calificacion);
    }
    //seleccionar agencia de viaje
    public void seleccionarAgenciaViaje(AgenciaViaje agencia) {
        this.agenciaViajeSeleccionada = agencia;
    }

    public void agregarProveedor(ProveedorExperiencia proveedor) {
        this.proveedoresContratados.add(proveedor);
    }

    public void calificarAgenciaViaje(int puntaje) {
        this.puntajeAgenciaViaje = puntaje;
    }

    public void chatearConAgencia(String mensaje) {
        this.historialChats.add("Tú: " + mensaje);
        System.out.println("Mensaje enviado a la agencia: " + mensaje);
    }

    public void realizarPago(double monto) {
        this.pagoRealizado = true;
        recibirNotificacion("Pago de " + monto + " realizado con éxito.");
    }

    public void recibirNotificacion(String mensaje) {
        this.notificaciones.add(mensaje);
        System.out.println("NOTIFICACIÓN para " + nombre + ": " + mensaje);
    }

    public void elegirItinerarioYReservar(Itinerario itinerario) {
        if (agenciaViajeSeleccionada != null) {
            System.out.println("Itinerario elegido: " + itinerario.getActividad() + ". Reservando...");
            agenciaViajeSeleccionada.actualizarEstadoViaje(EstadoViaje.RESERVADO);
            recibirNotificacion("Tu viaje ha sido RESERVADO.");
        }
    }
}
