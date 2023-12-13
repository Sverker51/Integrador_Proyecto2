/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.registrodeasistencia.interfaces;

import com.utp.registrodeasistencia.model.DetalleAsistencia;
import java.util.List;

/**
 *
 * @author Miguel
 */
public interface DetalleAsistenciaDao {
    public void registrar(DetalleAsistencia detalleAsistencia);
    public void modificar(DetalleAsistencia detalleAsistencia);    
    public List<DetalleAsistencia> listar()throws Exception;
}
