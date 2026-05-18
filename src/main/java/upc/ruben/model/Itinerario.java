package upc.ruben.model;

import java.util.ArrayList;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Itinerario {
    private String id;
    private String actividad;
    private String alojamiento;
    private String transporte;
    private String fecha;
    @Builder.Default
    private List<ProveedorExperiencia> proveedoresInvolucrados = new ArrayList<>();

    public void agregarProveedor(ProveedorExperiencia proveedor) {
        this.proveedoresInvolucrados.add(proveedor);
    }
}
