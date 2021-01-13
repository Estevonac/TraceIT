# TraceIT
Trabajo práctico Programación 2

Introducción a la Programación II
Trabajo Práctico Especial
2do Cuatrimestre 2020
 Equipo:
        Ignacio Estevo
        
<h1>Diseño </h1>
<h2>Usuarios</h2>
Existen 2 tipos de usuarios, como se requería. Los administradores y los ciudadanos. Los administradores se registran con usuario y contraseña mientras que los ciudadanos con CUIL y numero de teléfono.
<h2>Eventos</h2>
En cuanto a los eventos, decidimos modelar la clase enfermedad en la cual se pueden crear diversas enfermedades con sus síntomas asociados. La consideramos presente en un ciudadano si aunque sea posee 2 de estos síntomas. Los contactos con personas son avisados por el ciudadano y confirmado por el/ los otro/s ciudadano/s involucrado/s. Para realizar esto, generamos una solicitud de encuentro para que el otro ciudadano acepte o rechace y luego generamos el contacto en caso de haber sido aceptada. Si un ciudadano tiene un sintoma y se junta con otro que no lo tiene, el ciudadano sin síntoma tiene un 65% de contagiarse. Si un ciudadano tiene 5 contactos rechazados (un ciudadano B no reconoce haber tenido contacto con otro ciudadano A), este pasa a ser bloqueado hasta que el administrador lo desbloquee. Ademas, si un ciudadano esta enfermo tambien pasa a estar bloqeado, ya que debe aislarse y no puede tener otros contactos.
  Un encuentro consiste en una lista de participantes (Ciudadanos), un horario de inicio y fin y una zona de encuentro. Está asociado a la clase SolicitudEncuentro ya que esta contiene las solicitudes enviadas a los ciudadanos.

<h2>Brotes</h2>
 Los brotes son detectados automáticamente cuando el sistema registra a 5 o más enfermos en un lapso de 48 horas en cierta zona. También puede ocurrir directamente dentro de un encuentro. Estos datos se obtienen cuando los ciudadanos asisten a un encuentro o cuando manualmente ingresan sus sintomas.
<h2>Fechas</h2>
Para el manejo de las fechas usamos una clase propia Fecha. Consta de mes, día, hora y minuto y un método que obtiene la diferencia en horas entre 2 fechas
  
<h2>Tests</h2>
  Se están implementando tests con Junit en la carpeta Testers. Se usan asserts para evaluar los metodos.
      
<h2>Persistencia</h2>
La persistencia está siendo implementada por medio de una interfaz Gestor de Usuarios, la cual posee 3 métodos default para que sean implementados por las clases que la necesiten. Estos son leer, escribir y modificar. Permite hacerlo en distintos archivos, ya que la información está separada según su tipo en diversos ficheros para mejor comprensión.
De esta manera evitamos el uso de managers para cada clase que los requiera.

