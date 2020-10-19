# TraceIT
Trabajo práctico Programación 2
TraceIT
 
Introducción a la Programación II
Trabajo Práctico Especial
2do Cuatrimestre 2020
 
Equipo:
        Elio Molas
        Julieta Szuldman
        Tais Marambio
        Agustin Bazan
        Ignacio Estevo
        
        
  En cuanto al diseño de la aplicación optamos por tener 2 tipos de usuarios, como se requería. Los administradores y los ciudadanos. Los administradores se registran con usuario y contraseña mientras que los ciudadanos con CUIL y numero de telefono. En cuanto a los eventos, decidimos modelar solo el Covid en este caso. Esta enfermedad tiene ciertos sintomas y la consideramos presente en un ciudadano si aunque sea posee 2 de estos sintomas. Los contactos con personas son avisados por el ciudadano y confirmado por el otro ciudadano involucrado. Si un ciudadano tiene un sintoma y se junta con otro que no lo tiene, el ciudadano sin sintoma ahora posee también el sintoma que obtuvo en el encuentro estrecho. Si un ciudadano tiene 5 contactos rechazados (un ciudadano B no reconoce haber tenido encuentro con otro ciudadano A), este pasa a ser bloqueado hasta que el administrador lo desbloquee.
  
  Los brotes son detectados automaticamente cuando el sistema registra a 5 o mas enfermos en un lapso de 48 horas. Estos datos se obtienen cuando los ciudadanos registran un encuentro estrecho o cuando manualmente ingresan sus sintomas.
  
  Se implementaran excepciones para así ayudar a que el usuario entienda lo que está pasando en el programa y también se implementaran tests con Junit para probar las funcionalidades en distintos escenarios.
  
  Con el uso del mapa (Unfolding), podremos ver la densidad y puntos de mayor contagios. Junto con él tendremos un ranking donde se muestren las zonas con mayor cantidad de contagios.
      
      La información va a estar persistida en distintos documentos de texto separados según su uso para una lectura mas facil. Estamos trabajando en esto aunque todavia decidimos no incluirlo en la entrega hasta que esté funcional.
