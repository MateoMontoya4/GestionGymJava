-- ============================================================
-- Sistema de Gestión de Gimnasio - esquema de base de datos
-- Motor: MySQL 8 / MariaDB (por ejemplo, el que trae XAMPP)
-- Se ejecuta una sola vez (por ejemplo desde phpMyAdmin o MySQL Workbench).
-- Los nombres de tabla y columna son los que usan los DAO.
-- ============================================================

CREATE DATABASE IF NOT EXISTS SistemaGimnasio
    CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
USE SistemaGimnasio;

-- Entidad 1: Socio
CREATE TABLE IF NOT EXISTS socio (
    id_socio         INT          NOT NULL AUTO_INCREMENT,
    nombre           VARCHAR(100) NOT NULL,
    dni              INT          NOT NULL,
    telefono         VARCHAR(30),
    email            VARCHAR(100),
    apt_medico       VARCHAR(10),
    estado_membresia VARCHAR(20),
    PRIMARY KEY (id_socio),
    UNIQUE KEY uq_socio_dni (dni)          -- dos socios no pueden tener el mismo DNI
) ENGINE=InnoDB;

-- Entidad 2: Actividad
CREATE TABLE IF NOT EXISTS actividad (
    id_actividad  INT           NOT NULL AUTO_INCREMENT,
    nombre_clase  VARCHAR(100)  NOT NULL,
    cupos         INT           NOT NULL,
    horarios      VARCHAR(100),
    profesor      VARCHAR(100),
    precio        DECIMAL(10,2) NOT NULL DEFAULT 0,
    PRIMARY KEY (id_actividad)
) ENGINE=InnoDB;

-- Entidad 3: Clase = inscripción de un socio a una actividad (une las dos anteriores)
CREATE TABLE IF NOT EXISTS clase (
    id_clase      INT NOT NULL AUTO_INCREMENT,
    id_actividad  INT NOT NULL,
    id_socio      INT NOT NULL,
    PRIMARY KEY (id_clase),
    CONSTRAINT fk_clase_actividad FOREIGN KEY (id_actividad) REFERENCES actividad (id_actividad),
    CONSTRAINT fk_clase_socio     FOREIGN KEY (id_socio)     REFERENCES socio (id_socio)
) ENGINE=InnoDB;
