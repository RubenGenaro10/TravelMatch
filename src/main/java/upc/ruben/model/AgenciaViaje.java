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
public class AgenciaViaje {
    private String id;
    private String nombre;
    private String RUC;
    private String RazonSocial;
    private String RepresentanteLegal;
    private List<Itinerario> itinerariosOfrecidos = new ArrayList<>();
    private List<ProveedorExperiencia> proveedoresRegistrados = new ArrayList<>();
    private List<String> facturasEmitidas = new ArrayList<>();
    private EstadoViaje estadoViaje;


    public AgenciaViaje(String nombre, String RUC, String RazonSocial, String RepresentanteLegal) {
        this.nombre = nombre;
        this.RUC = RUC;
        this.RazonSocial = RazonSocial;
        this.RepresentanteLegal = RepresentanteLegal;
        this.estadoViaje = EstadoViaje.PENDIENTE; // Estado inicial del viaje
        this.itinerariosOfrecidos = new ArrayList<>();
        this.proveedoresRegistrados = new ArrayList<>();
        this.facturasEmitidas = new ArrayList<>();
    }

    public void agregarItinerario(Itinerario itinerario) {
        this.itinerariosOfrecidos.add(itinerario);
    }

    public void actualizarEstadoViaje(EstadoViaje nuevoEstado) {
        this.estadoViaje = nuevoEstado;
    }

    public void registrarProveedor(ProveedorExperiencia proveedor) {
        this.proveedoresRegistrados.add(proveedor);
    }

    public void enviarItinerario(Viajero viajero, Itinerario itinerario) {
        System.out.println("Enviando itinerario personalizado a: " + viajero.getNombre());
    }

    public void emitirFactura(String detalle) {
        String factura = "Factura-" + (facturasEmitidas.size() + 1) + ": " + detalle;
        this.facturasEmitidas.add(factura);
        System.out.println("Factura emitida: " + factura);
    }

    public void generarReporteSatisfaccion() {
        System.out.println("Generando reporte de satisfacción para la agencia: " + nombre);
    }
}
