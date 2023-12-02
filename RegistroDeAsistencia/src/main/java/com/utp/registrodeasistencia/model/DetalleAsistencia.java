/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.model;

import com.utp.registrodeasistencia.controller.ConnectionDB;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.swing.JOptionPane;
import lombok.Data;

/**
 *
 * @author Miguel
 */
@Data
public class DetalleAsistencia {
    
    private String dni;
    private int horarioId;
    private Timestamp fechaHoraIngreso;
    private Timestamp fechaHoraSalida;
    private int minutosTardanza;
    private Usuario usuario;
    private Horario horario;

   
}

