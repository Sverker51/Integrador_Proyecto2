/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import java.sql.Time;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class Horario {
    private int horarioId;
    private boolean estado;
    private String descripcion;
    private Time horaInicio;
    private Time horaFin;
}   
