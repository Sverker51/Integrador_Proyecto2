/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import com.mysql.cj.x.protobuf.MysqlxNotice;
import com.utp.registrodeasistencia.interfaces.UsuarioDao;
import com.utp.registrodeasistencia.model.Usuario;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class UsuarioDaoImpl extends ConnectionDB implements UsuarioDao{
    
    private static final Logger logger = Logger.getLogger(UsuarioDaoImpl.class.getName());

    @Override
    public void registrar(Usuario usuario) throws Exception{
        try{
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO usuario (dni, estado, contrasenia, nombre, apellido, correo, rol) VALUES (?, ?, ?, ?, ?, ?, ?)");
                st.setString(1, usuario.getDni());
                st.setBoolean(2, usuario.getEstado());
                st.setString(3, usuario.getContrasenia());
                st.setString(4, usuario.getNombre());
                st.setString(5, usuario.getApellido());
                st.setString(6, usuario.getCorreo());
                st.setString(7, usuario.getRol());                
                st.executeUpdate();
        }catch(Exception ex){
            logger.log(Level.SEVERE, "ERROR EN LA CLASE REGISTRAR - USUARIO", ex);
        } finally{
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Usuario usuario) throws Exception{
        
    }

    @Override
    public void desactivar(Usuario usuario) throws Exception{
        
    }

    @Override
    public List<Usuario> listar() throws Exception{
        return null;
    }
    
}
