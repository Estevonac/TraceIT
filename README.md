# TraceIT
Trabajo práctico Programación 2
TraceIT
 
Introducción a la Programación II
Trabajo Práctico Especial
2do Cuatrimestre 2020
 
Objetivo
 
Se desea implementar un servicio para el monitoreo colaborativo de la propagación de eventos en una sociedad.
 
Requerimientos del TP
 
1.	Gestión de Usuarios:
a.	Habrá 2 tipos de usuarios, los administradores y los ciudadanos.
b.	Se requiere que exista un ABM de usuarios administradores
c.	Los ciudadanos se deben registrar por sus propios medios, se registran con un número de teléfono celular, por única vez, e ingresan su número de CUIL, el cual es cruzado con un dataset (ficticio) de la ANSES para validar el usuario a través del CUIL y Celular.
d.	Los administradores tienen que tener acceso para poder bloquear y desbloquear ciudadanos, de acuerdo a las situaciones que se enuncian más adelante en este enunciado.
2.	Persistencia
a.	Toda la información tiene que estar persistida en archivos.
b.	Se requiere la implementación de acceso a datos con uso de generics.
c.	Se requiere que la persistencia sea reemplazable por implementaciones con Collections en memoria.
3.	Gestión de eventos:
a.	Los administradores darán de alta distintos tipos de eventos (en general utilizado para síntomas de enfermedades), consistiendo simplemente de un nombre de evento y con 0 a N eventos relacionados.
b.	Los ciudadanos tienen que poder indicar que han tenido contacto estrecho, aceptando mutuamente dicha relación, como en una red social, pero en vez de relaciones de amistad aquí uno de los usuarios solicita que se asiente un encuentro con otro de los usuarios, y el otro acepta. Esto se indica ingresando CUIL o Celular del otro usuario, fecha desde y fecha hasta. Un usuario que tenga 5 solicitudes rechazadas pasa a ser bloqueado y auditado por los administradores.
c.	Un usuario tiene que poder registrar presencia de síntomas para sí mismo, indicando el tipo de evento, y puede darlo de baja en cualquier momento.
4.	Monitoreo:
a.	Se tiene que poder ver en un mapa usando unfolding o similar (https://github.com/tillnagel/unfolding) los eventos a medida que ocurren. Esto puede hacerse en una app desktop que se comunique por archivos con la app de consola, o puede programarse todo en modo desktop, indistintamente.
b.	El sistema contará por zona con un tablero de estadísticas, que debe recordarse si se cierra y se vuelve a abrir el sistema. La zona se conoce a través del dataset de ANSES, por cada usuario.
i.	Se deben ver los 3 eventos más comunes por zona.
ii.	Debe advertirse en tiempo real a cada usuario cada vez que uno de sus contactos tenga al menos 2 síntomas al menos un mismo día y dentro de un margen de tiempo de 48 horas con respecto a un contacto estrecho entre dichos ciudadanos.
iii.	Deben informarse los brotes que el sistema detecte en tiempo real, y a su vez registrarlos en forma de ranking ordenado descendentemente de acuerdo a la cantidad de involucrados. Se considera que ocurre un brote cuando existe propagación de eventos hasta 2 grados de distancia (por ejemplo, A contagia a B y B contagia a C con 47 horas de diferencia), y al menos 5 o más personas. 

Plagio
 
Se pueden consultar ideas y estrategias de implementación con otros grupos, pero no se puede copiar el código. El plagio entre grupos será penalizado con la desaprobación del trabajo práctico de cada grupo involucrado.
 
Entregables
 
El material a entregar se detalla a continuación:
 
● Informe que detalle el diseño elegido y su justificación.
● Versión digital del código fuente
● Script de compilación para generar el ejecutable (jar) y su ejecución
 
 
Plan de trabajo
 
Se harán 3 entregas a modo de demo, para validar desarrollo, concepto y evolución del sistema.
Los grupos deberán ser de 4 a 6 personas.
Cada entrega tendrá una calificación que será utilizada para la nota final del TP.
 
La nota del TP será de la siguiente manera: 20% entrega 1 + 30% entrega 2 + 50% entrega final.

La fecha de inicio es el 28/09/2020.
Conformación de los grupos, fecha límite, el 01/10/2020.
La entrega 1 será el 13/10/2020. Se podrán hacer ajustes hasta el 19/10/2020. Debe existir el repositorio git con una propuesta inicial de solución.
La entrega 2 será el 26/10/2020.
La entrega 3 (final y defensa oral) será el 09/11/2020. Recuperatorios: 2 semanas adicionales.
 
Consideraciones Técnicas

Deberá usarse un repositorio git propio de cada grupo y se deberá dar acceso a la cátedra.
Deberán realizarse tests con JUnit.


