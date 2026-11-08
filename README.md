# 🏋️ Gestión de Gimnasio - GestionGymJava

## Descripción

GestionGymJava es un proyecto desarrollado en Java que busca facilitar la gestión y administración de un gimnasio.

El sistema permite organizar la información y realizar operaciones relacionadas con la administración del gimnasio.

## Tecnologías utilizadas

- Java
- MySQL 
- NetBeans IDE

## Cómo abrir el proyecto

1. Descargar o clonar este repositorio.
2. Abrir NetBeans.
3. Ir a **File → Open Project**.
4. Seleccionar la carpeta del proyecto `gymm`.
5. Esperar a que NetBeans cargue los archivos.
6. Ejecutar el proyecto.

## BASE DE DATOS
Conexión con Java

Abrir el archivo Conexion.java, ubicado en el paquete DAO.

Configurar los datos de conexión según la instalación local de MySQL:

Contraseña: colocar la contraseña configurada en MySQL.

private static final String PASSWORD = "Tucontraseña";

Por ejemplo, si MySQL no tiene contraseña, dejar el campo de contraseña vacío ("").

Importante: si aparece un error de conexión, comprobar que MySQL Server esté iniciado y que el usuario y la contraseña sean correctos.
