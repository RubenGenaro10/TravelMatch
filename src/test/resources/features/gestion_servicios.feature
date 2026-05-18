# language: es
@Proveedor
Característica: Gestión de Servicios y Calidad de los Proveedores
  Como administrador de TravelMatch
  Quiero supervisar el comportamiento de los proveedores
  Para asegurar que solo los proveedores confiables participen en la plataforma

  Antecedentes:
    Dado que existe un proveedor registrado con los siguientes datos:
      | nombre         | tipo           | precio |
      | Tours Aventura | TOUR_AVENTURA  | 100    |

  Escenario: Publicación exitosa de un nuevo servicio
    Cuando el proveedor publica su servicio con la siguiente información:
      | descripcion  | fotos        | fechas                |
      | Trekking Inca | trek1.jpg    | 2024-12-01,2024-12-05 |
    Entonces el servicio debe estar disponible en el catálogo
    Y el estado del proveedor debe ser "Activo"

  Escenario: Bloqueo automático del proveedor al alcanzar el límite de incumplimientos
    Cuando el proveedor recibe "3" reportes de incumplimiento
    Entonces el proveedor debe ser bloqueado de la plataforma automáticamente
    Y no podrá publicar nuevos servicios

  Esquema del escenario: Validación de cobro condicionado al estado del servicio
    Cuando el servicio se marca como "<estado_servicio>"
    Y el proveedor intenta realizar el cobro
    Entonces el resultado del cobro debe ser "<resultado>"

    Ejemplos:
      | estado_servicio | resultado |
      | COMPLETADO      | EXITOSO   |
      | PENDIENTE       | FALLIDO   |
