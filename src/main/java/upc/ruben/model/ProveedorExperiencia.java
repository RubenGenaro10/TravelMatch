package upc.ruben.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ProveedorExperiencia {
    String id;
    String nombre;
    TipoProveedor tipoProveedor;
    String precio;
    boolean activo;
    private int reportesIncumplimiento = 0;

    // Nuevos atributos según requerimiento
    private String descripcion;
    private List<String> fotos = new ArrayList<>();
    private List<String> fechasDisponibles = new ArrayList<>();
    private boolean servicioCompletado = false;
    private boolean cobrado = false;

    public boolean isServicioCompletado() {
        return servicioCompletado;
    }

    public boolean isCobrado() {
        return cobrado;
    }

    public ProveedorExperiencia(String nombre, TipoProveedor tipoProveedor, String precio) {
        this.nombre = nombre;
        this.tipoProveedor = tipoProveedor;
        this.precio = precio;
        this.activo = true;
        this.fotos = new ArrayList<>();
        this.fechasDisponibles = new ArrayList<>();
    }

    public void reportarIncumplimiento() {
        this.reportesIncumplimiento++;
        if (this.reportesIncumplimiento > 2) {
            this.activo = false;
        }
    }

    public void bloquearProveedor() {
        this.activo = false;
    }

    public void publicarServicio(String descripcion, List<String> fotos, List<String> fechas) {
        this.descripcion = descripcion;
        this.fotos = fotos;
        this.fechasDisponibles = fechas;
    }

    public void confirmarCompletado() {
        this.servicioCompletado = true;
        System.out.println("Servicio del proveedor " + nombre + " marcado como COMPLETADO.");
    }

    public void cobrar() {
        if (servicioCompletado) {
            this.cobrado = true;
            System.out.println("Cobro procesado exitosamente para el proveedor: " + nombre);
        } else {
            System.out.println("Error: No se puede cobrar. El servicio aún no ha sido confirmado como completado.");
        }
    }
}
