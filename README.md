# Trabajo Práctico Integrador
## Pronósticos Deportivos
### Introducción
Nos han solicitado el desarrollo de un programa de Pronósticos Deportivos.
Un pronóstico deportivo consta de un posible resultado de un partido (que un equipo gane,
pierda o empate), propuesto por una persona que está participando de una competencia
contra otras.
Cada partido tendrá un resultado. Este resultado se utilizará para otorgar puntos a los
participantes de la competencia según el acierto de sus pronósticos.
Finalmente, quien gane la competencia será aquella persona que sume mayor cantidad de
puntos.
### Consigna
La propuesta del trabajo práctico consiste en implementar un programa de consola que dada
la información de resultados de partidos e información de pronósticos, ordene por puntaje
obtenido a los participantes.
### Alcance
En este trabajo práctico nos limitaremos a pronosticar los resultados de los partidos, sin
importar los goles ni la estructura del torneo (si es grupo, eliminatoria u otro); simplemente se
sumarán puntos y se obtendrá un listado final.

![image](https://user-images.githubusercontent.com/128620505/227181698-c7268905-5793-4a27-84a2-ede0f5d1a469.png)

## Entrega 1
A partir del esquema original propuesto, desarrollar un programa que lea un archivo de
partidos y otro de resultados, el primero correspondiente a una ronda y el otro que contenga
los pronósticos de una persona1. Cada ronda debe tener una cantidad fija de partidos, por
ejemplo 2. El programa debe:

* Estar subido en un repositorio de GIT
* Tomar como argumento 2 rutas a cada archivo que se necesita
* Al leer las líneas de los archivos debe instanciar objetos de las clases propuestas
* Debe imprimir por pantalla el puntaje de la persona

### Importante
Se debe considerar la forma de identificar los partidos de forma unívoca para su correcto
procesamiento. Está permitido modificar la estructura del archivo si así lo considera.

![image](https://user-images.githubusercontent.com/128620505/227182303-6522ecc1-8ce0-45cd-93bf-75ec326d439b.png)
