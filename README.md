# Caso TravelMatch
Un grupo de emprendedores en tecnología y turismo decidió crear una plataforma digital que permita
automatizar la planificación de viajes personalizados, conectando a viajeros, agencias de viaje y
proveedores de experiencias (hoteles, guías turísticos, operadores locales). Así nació TravelMatch, una
solución integral que busca facilitar la organización de viajes a medida según preferencias, presupuesto
y fechas.

TravelMatch tiene 3 stakeholders principales:
1. Viajeros que desean planificar viajes personalizados.
2. Agencias de viajes que diseñan itinerarios a medida y gestionan reservas.
3. Proveedores de experiencias como hoteles, guías y operadores turísticos que brindan servicios
   locales

La plataforma permite construir, cotizar, reservar y seguir el estado de un viaje de forma digital. A
continuación, se detallan las funcionalidades por stakeholder:

### Para los viajeros:
- Deben registrarse con su DNI y correo electrónico.
- Pueden crear un perfil de viaje con destino deseado, fechas, presupuesto y preferencias (cultural,
gastronómico, aventura, etc.).
- Visualizan itinerarios sugeridos por agencias y eligen uno para reservar.
- Pueden chatear con la agencia dentro de la plataforma para resolver dudas o hacer cambios.
- Realizan el pago de manera segura y reciben notificaciones de cada etapa del viaje.
- Al finalizar el viaje, pueden calificar a la agencia y a cada proveedor de experiencia.
### Para las agencias de viaje:
- Se registran con RUC, razón social y representante legal.
- Reciben solicitudes de viaje y pueden enviar itinerarios personalizados a los viajeros.
- Cada itinerario contiene una serie de actividades, alojamientos y transportes.
- Deben registrar los proveedores de experiencia asociados a cada viaje.
- Gestionan el estado de cada viaje (pendiente, reservado, en curso, finalizado).
- Emiten facturas y generan reportes de satisfacción del cliente.
### Para los proveedores de experiencias:
- Pueden registrarse como hoteles, restaurantes, guías, o tours de aventura.
- Publican sus servicios con fotos, descripción, precios y fechas disponibles.
- Los servicios pueden ser seleccionados por agencias como parte de itinerarios.
- Solo pueden cobrar una vez el servicio ha sido confirmado como completado.
- Si reciben más de dos reportes de incumplimiento, el proveedor es bloqueado de la plataforma


## Preguntas
### Pregunta 1: UML Class Diagram (5 p.)
Elabore un UML class diagram, considerando las clases para el dominio de la solución, priorizando las
clases críticas e indicando los atributos y métodos representativos para cada clase.
### Pregunta 2: Unit test & Integration test (5 p.)
Para el UML class diagram elaborado, seleccione una clase que incorpore el main business rule (No
funciones generales como un login de usuario, por ejemplo), que contenga múltiples escenarios y
elabore 3 pruebas unitarias y 1 prueba integral priorizando la comprobación de las business rules.
Utilice para ello pseudocódigo o un lenguaje de programación que sea parte de su stack.
Se recomienda explicar brevemente sus pruebas. Sustentar por qué se consideran claves para el negocio
y explicar qué se está haciendo.
### Pregunta 3: Feature, Scenarios, Data Tables & Examples (5 p.)
Seleccione uno de los features dentro del alcance de la solución y utilizando la sintaxis de Gherkin
(Given-When-Then), redacte el Feature, los Scenarios y también debe incluir Data Tables y/o
Examples. Puede utilizar Cucumber o Specflow.
### Pregunta 4: Step Definition Implementation (5 p.)
Para el feature redactado en la pregunta anterior, implemente los Step Definition generados para las
pruebas de sistema del FrontEnd o RESTful API, utilizando pseudocódigo o un lenguaje de
programación.


## Prompt
- 1.El codigo generado debe de ser sencillo orientado a la eficiencia de alguien codificando sin ia(clases,enum) y metodos en las mismas clases. Osea no orientado a las buenas practicas de que sea escalable. Mientras mas sencillo mejor.
- 2.Debes de generar un codigo en el main.java para verficar si corre bien.
- 3.La creacion del .puml debe seguir las siguientes relaciones si se ve necesario:
Extensión	<|--	Especialización de una clase en una jerarquía
Implementación	<|..	Realización de una interfaz mediante una clase
Composición	*--	La parte no puede existir sin el todo
Agregación	o--	La parte puede existir independientemente del todo
Dependencia	-->	El objeto utiliza otro objeto
Dependencia	..>	Una forma más débil de dependencia
- 4.Los test deben de seguir el patron AAA.
- 5.Debes de usar mockito si lo ves necesario.
- 6.No es necesario pedir permiso para verificar los test, simplemente toma accion en la creacion de ellos.
