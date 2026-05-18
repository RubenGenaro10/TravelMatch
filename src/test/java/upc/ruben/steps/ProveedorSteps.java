package upc.ruben.steps;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.es.*;
import upc.ruben.model.ProveedorExperiencia;
import upc.ruben.model.TipoProveedor;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Map;

public class ProveedorSteps {
    private ProveedorExperiencia proveedor;

    @Dado("que existe un proveedor registrado con los siguientes datos:")
    public void queExisteUnProveedorRegistrado(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);
        proveedor = new ProveedorExperiencia(data.get("nombre"),
                TipoProveedor.valueOf(data.get("tipo")), data.get("precio"));
    }

    @Cuando("el proveedor publica su servicio con la siguiente información:")
    public void elProveedorPublicaSuServicio(DataTable dataTable) {
        List<Map<String, String>> rows = dataTable.asMaps(String.class, String.class);
        Map<String, String> data = rows.get(0);
        // Simulamos la lógica de publicación
        proveedor.setDescripcion(data.get("descripcion"));
        assertTrue(proveedor.isActivo());
    }

    @Entonces("el servicio debe estar disponible en el catálogo")
    public void elServicioDebeEstarDisponibleEnElCatalogo() {
        assertNotNull(proveedor.getDescripcion());
    }

    @Y("el estado del proveedor debe ser {string}")
    public void elEstadoDelProveedorDebeSer(String estado) {
        if (estado.equals("Activo")) {
            assertTrue(proveedor.isActivo());
        }
    }

    @Cuando("el proveedor recibe {string} reportes de incumplimiento")
    public void elProveedorRecibeReportesDeIncumplimiento(String cantidad) {
        int reportes = Integer.parseInt(cantidad);
        for (int i = 0; i < reportes; i++) {
            proveedor.reportarIncumplimiento();
        }
    }

    @Entonces("el proveedor debe ser bloqueado de la plataforma automáticamente")
    public void elProveedorDebeSerBloqueado() {
        assertFalse(proveedor.isActivo());
    }

    @Y("no podrá publicar nuevos servicios")
    public void noPodraPublicarNuevosServicios() {
        // Lógica de negocio: si no está activo, las acciones fallan
        assertFalse(proveedor.isActivo());
    }

    @Cuando("el servicio se marca como {string}")
    public void elServicioSeMarcaComo(String estado) {
        if (estado.equals("COMPLETADO")) {
            proveedor.confirmarCompletado();
        }
    }

    @Y("el proveedor intenta realizar el cobro")
    public void elProveedorIntentaRealizarElCobro() {
        proveedor.cobrar();
    }

    @Entonces("el resultado del cobro debe ser {string}")
    public void elResultadoDelCobroDebeSer(String resultado) {
        if (resultado.equals("EXITOSO")) {
            assertTrue(proveedor.isCobrado());
        } else {
            assertFalse(proveedor.isCobrado());
        }
    }
}
