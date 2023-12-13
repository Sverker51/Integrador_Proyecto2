/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import java.sql.Timestamp;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class DetallePermisoUsuario {
    private String dni;
    private int permisoId;
    private String motivo;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
}
