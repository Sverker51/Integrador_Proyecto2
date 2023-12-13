/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.utp.registrodeasistencia.interfaces;

import com.utp.registrodeasistencia.model.Horario;
import java.util.List;

/**
 *
 * @author Miguel
 */
public interface HorarioDao {
    public void registrar(Horario horario) throws Exception;;
    public void modificar(Horario horario) throws Exception;;
    public void desactivar(Horario horario)throws Exception;;
    public List<Horario> listar()throws Exception;
    public Horario getHorarioById(int id) throws Exception;
}
