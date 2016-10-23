# Programación Funcional en Scala

Este repositorio contiene el material utilizado durante el curso de programación funcional en Scala impartido por Habla Computing.

En este documento también se recogen instrucciones relativas a la instalación de software, y otros aspectos necesarios para el correcto seguimiento del curso.

## Material del curso

Las explicaciones durante las sesiones del curso alternarán el uso de transparencias y ejemplos de programación "en vivo". Las transparencias estarán disponibles en los siguientes enlaces al comienzo de cada sesión:

* [Presentación](presentacion.pdf)
* [Tema 1. ¿Qué es la programación funcional? HOFs](tema1-hofs/HOFs.pdf)
* Tema 2. TypeClasses
* Tema 3. Las funciones puras. Lenguajes
* Tema 4. Introducción a Spark
* [Referencias](REFS.md)

Tanto el código "en vivo" como el historial de la consola de comandos se encontrarán también disponibles en este repositorio al término de cada sesión.

## Réplica y clonación del repositorio

Para facilitar las correcciones a los ejercicios propuestos durante el curso se recomienda hacer un [fork](https://help.github.com/articles/fork-a-repo/#fork-an-example-repository) de este repositorio en la cuenta Github del alumno, y clonar localmente vuestra propia versión del repositorio (instalando previamente [git](https://git-scm.com/)).

En este [documento](InstruccionesGithub.pdf) se explican paso a paso las actividades de configuración de vuestro repositorio, así como las operaciones de git que utilizaréis más comúnmente y el procedimiento para solicitar la corrección de ejercicios. 

## Instalación de software

#### Librerías de Scala y compilador

Prerrequisitos: es necesario que tengáis instalado Java en vuestra máquina. Recomendamos JDK (o JRE) versión 7 u 8.

Este repositorio contiene una copia de `sbt`, la herramienta de builds de Scala más común. Una vez clonado localmente el repositorio, mediante el siguiente comando se descargarán todas las dependencias necesarias para compilar los programas del curso (las librerías [scalaz](https://github.com/scalaz/scalaz/tree/v7.2.0-M5), [cats](https://github.com/non/cats/tree/v0.3.0), etc., y el propio compilador de Scala):  

```bash
$ cd funcourseinscala
$ ./sbt ~update
```

#### Editores

Con respecto al editor, durante las sesiones del curso utilizaremos el editor [Sublime](http://www.sublimetext.com/), pero, por supuesto, podéis utilizar cualquier otro editor (Atom, VI, emacs) o IDE (eclipse, intellij, etc. - véase el paso 3 [aquí](http://www.scala-lang.org/download/)) de vuestra elección.

## Comunicación

#### Correo electrónico

El correo electrónico del coordinador del curso es: [juanmanuel.serrano@hablapps.com](mailto:juanmanuel.serrano@hablapps.com)

#### Chat del curso 

Para resolver dudas y fomentar la colaboración entre los alumnos, se dispone del siguiente chat moderado por el equipo de Habla Computing:

https://gitter.im/hablapps/funcourseinscala4

#### Twitter

El hashtag "oficial" del curso es el siguiente: `#funcourseinscala`

#### Red wifi

El usuario y la constraseña de la red WIFI para su uso en el aula son las siguientes:
* Red: eduroam
* Usuario: hablapps
* Constraseña: `Dici@18.`       (punto final incluido)

## Localización y fecha

El curso comenzará el viernes 14 de octubre, en horario de 16:00 a 20:00 horas, en la sala 1.1.A.01 situada en la primera planta del [Parque Tecnológico de la UC3M](https://www.google.com/maps?q=Parque+Tecnol%C3%B3gico,+Av+Gregorio+Peces+Barba,+28919+Legan%C3%A9s,+Madrid).

El aparcamiento en las inmedicaciones del parque no tiene ningún problema. Para aquellos que vengáis en transporte público, tenéis la opción de llegar mediante una combinación de [tren+autobús](https://www.google.com/maps/d/u/0/edit?mid=za8zleNzZrnc.kkG6K8Rm3_wA), o solo [autobús](https://www.google.com/maps/d/u/0/edit?mid=za8zleNzZrnc.kdbrfsWhyV_Q). [Aquí](http://portal.uc3m.es/portal/page/portal/investigacion/parque_cientifico/localizacion/transporte_publico) tenéis también información de cómo llegar.
