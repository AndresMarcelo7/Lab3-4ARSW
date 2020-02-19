# Lab3 y 4 ARSW

# Integrantes

- Johan Sebastían Arias
- Andrés Felipe Marcelo
- Simon Marín Mejía.
- Juan Carlos Garcia


What race conditions could occur?

En el caso que se desee actualizar un recurso mediante un método PUT,
debido a que puede darse el caso de que mientras alguien está actualizando el arreglo de blueprints, otro puede estar consultándolo o en casos más extremos modificándolo.

What are the respective critical regions? 

![capture](img/Captur.png)

Lo solucionamos mediante la implementación ConcurrentHashMap en la región critica.  

