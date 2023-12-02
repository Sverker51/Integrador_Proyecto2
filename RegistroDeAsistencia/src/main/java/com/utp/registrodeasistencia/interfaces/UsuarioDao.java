/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.registrodeasistencia.interfaces;

import com.utp.registrodeasistencia.model.Usuario;
import java.util.List;

/**
 *
 * @author Miguel
 */
public interface UsuarioDao {
    public void registrar(Usuario usuario)throws Exception;
    public void modificar(Usuario usuario)throws Exception;
    public void desactivar(Usuario usuario)throws Exception;
    public List<Usuario> listar()throws Exception;
    public String iniciarSesion(Usuario usuario)throws Exception;
    public Usuario obtenerUsuarioPorDni(String dni)throws Exception;
}
