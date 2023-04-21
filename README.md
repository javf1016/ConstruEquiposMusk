# ConstruEquiposMusk
## 1. Analice la información recibida, documente y justifique los supuestos que usted adicione.
- Supuesto sobre la estructura del proyecto: Es posible que estés siguiendo una estructura de proyecto típica de Spring MVC, que incluye componentes como controladores, servicios y modelos.
- Supuesto sobre la funcionalidad implementada: Puedes asumir que las funcionalidades implementadas en el proyecto se han desarrollado de acuerdo con los requisitos establecidos y las especificaciones funcionales definidas. Esto implica que las funcionalidades implementadas funcionan correctamente y cumplen con las expectativas del proyecto.
- Se asume que el sistema debe contar con un sistema de autenticación y autorización para garantizar la seguridad de la información.
- Se asume que el sistema debe contar con una interfaz para que los usuarios puedan interactuar con las funcionalidades del sistema.
## 2. En base a la información recibida, redacte los casos de uso (simples) o historias de usuario que considere necesarios para suplir la necesidad de negocio que le han solicitado.
- Asignar equipo a un cliente: Como usuario del sistema, quiero poder asignar un equipo del inventario a un cliente que tenga un contrato activo, de manera que pueda registrar la fecha de inicio y fin del periodo del alquiler del equipo, y seleccionar si el cliente requiere o no servicio de transporte para llevar y recoger el equipo.
- Recoger equipo de un cliente: Como usuario del sistema, quiero poder registrar cuando un cliente devuelve un equipo, de manera que pueda organizar el proceso de recolección si este incluido como parte del contrato, o bien registrar que el cliente se encarga de llevarlo. Una vez recibido el equipo, un supervisor debe verificar el estado en el que entrega el equipo y se debe actualizar su estado de alquiler y su estado de mantenimiento.
- Realizar mantenimiento preventivo: Como usuario del sistema, quiero poder programar el mantenimiento preventivo de un equipo que está en óptimas condiciones, de manera que pueda ponerse en alquiler luego del mantenimiento, que usualmente toma 1 día.
- Reparar equipo averiado: Como usuario del sistema, quiero poder enviar un equipo al taller si presenta algún tipo de daños, de manera que se pueda realizar la reparación correspondiente. Una vez reparado, se debe actualizar su estado de alquiler y se debe notificar al cliente para que asuma los costos adicionales por la reparación.
#### Adicionales
- Agregar un equipo: Como administrador del sistema, quiero poder agregar un equipo, para tener mas opciones ala hora de asignarlos.
- Visualizar equipos disponibles: Como usuario del sistema, quiero poder visualizar que equipos se encuentran disponibles, para ver las opciones y si cumplen con mis necesidades.
- Visualizar equipos en mantenimiento: Como administrador del sistema, quiero poder visualizar que equipos se encuentran en mantenimiento, para ver las opciones y si cumplen con mis necesidades.
## 3. Cree la base de datos correspondiente, consulte previamente cual debe ser el motor a utilizar.
- Se ha creado en Postgresql, la configuracion se encuentra en el application.properties
## 4. Cree el api rest que permita realizar las funcionalidades relacionadas a los casos de uso definidos, normalmente se usa spring boot.
- Crear un contrato:
  - Endpoint: POST http://localhost:8080/contratos
  - Body (raw, JSON):
  - {"cliente": {"clienteId": 1},"contratoActivo": true,"incluyeTransporte": false}
  !(https://github.com/javf1016/Images/blob/main/CrearContratoExito.PNG)
