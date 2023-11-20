/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class Usuario {
    private String dni;
    private Boolean estado;
    private String contrasenia;
    private String nombre;
    private String apellido;
    private String correo;
    private String rol;    
}
