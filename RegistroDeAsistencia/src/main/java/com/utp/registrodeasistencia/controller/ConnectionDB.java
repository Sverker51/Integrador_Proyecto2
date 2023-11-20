/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class ConnectionDB {
    
    protected Connection conexion;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc::mysql://localhost/bd_asistencia";
    
    private final String USER = "admin";
    private final String PASS = "admin";
    
    public void Conectar() throws SQLException, ClassNotFoundException{
        conexion = DriverManager.getConnection(DB_URL, USER, PASS);
        Class.forName(JDBC_DRIVER);
    };
    
    public void Cerrar() throws SQLException{
        if(conexion != null){
            if(!conexion.isClosed()){
                conexion.close();
            }
        } 
    }
}
