/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Miguel
 */
public class ConnectionDB {
    
    public Connection conexion;
    private final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    private final String DB_URL = "jdbc:mysql://localhost:3306/bd_asistencia";
    
    private final String USER = "root";
    private final String PASS = "L1nux";
    
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

    public PreparedStatement prepareStatement(String sql) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public void close() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
