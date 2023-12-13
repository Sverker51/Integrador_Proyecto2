/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.utp.registrodeasistencia.controller;

import com.utp.registrodeasistencia.interfaces.HorarioDao;
import com.utp.registrodeasistencia.model.Horario;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Miguel
 */
public class HorarioDaoImple extends ConnectionDB implements HorarioDao {

    private static final Logger logger = Logger.getLogger(UsuarioDaoImpl.class.getName());

    @Override
    public void registrar(Horario horario) throws Exception {
        try {
            this.Conectar();
            PreparedStatement st = this.conexion.prepareStatement("INSERT INTO horario (estado, descripcion, hora_inicio, hora_fin, area) values (?, ?, ?, ?, ?)");

            st.setBoolean(1, horario.isEstado());
            st.setString(2, horario.getDescripcion());
            st.setTime(3, horario.getHoraInicio());
            st.setTime(4, horario.getHoraFin());
            st.setString(5, horario.getArea());
            st.executeUpdate();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, "Error en el metodo Registrar Horario", ex);
        } finally {
            this.Cerrar();
        }
    }

    @Override
    public void modificar(Horario horario) throws Exception {
        String sql = "UPDATE horario SET estado = ?, descripcion = ?, hora_inicio = ?, hora_fin = ?, area = ? WHERE horario_id = ?";

        try {
            this.Conectar();
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);

            preparedStatement.setBoolean(1, horario.isEstado());
            preparedStatement.setString(2, horario.getDescripcion());
            preparedStatement.setTime(3, horario.getHoraInicio());
            preparedStatement.setTime(4, horario.getHoraFin());
            preparedStatement.setString(5, horario.getArea());
            preparedStatement.setInt(6, horario.getHorarioId());

            int filasModificadas = preparedStatement.executeUpdate();

            if (filasModificadas > 0) {
                System.out.println("Horario modificado exitosamente.");
            } else {
                System.out.println("No se encontró el horario con ID " + horario.getHorarioId());
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void desactivar(Horario horario) throws Exception {

    }

    @Override
    public List<Horario> listar() throws Exception {
        List<Horario> horarios = new ArrayList<>();
        String sql = "SELECT * FROM horario";
        try {
            this.Conectar();
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                Horario horario = new Horario();
                horario.setHorarioId(resultSet.getInt("horario_id"));
                horario.setEstado(resultSet.getBoolean("estado"));
                horario.setDescripcion(resultSet.getString("descripcion"));
                horario.setHoraInicio(resultSet.getTime("hora_inicio"));
                horario.setHoraFin(resultSet.getTime("hora_fin"));
                horario.setArea(resultSet.getString("area"));

                horarios.add(horario);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horarios;
    }

    public Time obtenerHora(String horaString) {
        Time horaEnTime = null;
        try {
            // Crear un objeto SimpleDateFormat para analizar la cadena de hora
            SimpleDateFormat formatoHora = new SimpleDateFormat("hh:mm a", Locale.ENGLISH);
            // Parsear la cadena y obtener un objeto Date
            Date date = formatoHora.parse(horaString);
            // Convertir el objeto Date a un objeto Time
            horaEnTime = new Time(date.getTime());
            System.out.println("Hora en formato Time: " + horaEnTime);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return horaEnTime;
    }

    @Override
    public Horario getHorarioById(int id) throws Exception {
        Horario horario = new Horario();
        String sql = "SELECT * FROM horario where horario_id = ? LIMIT 1";
        try {
            this.Conectar();
            PreparedStatement preparedStatement = this.conexion.prepareStatement(sql);
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();            
            
            while (resultSet.next()) {                
                horario.setHorarioId(resultSet.getInt("horario_id"));
                horario.setEstado(resultSet.getBoolean("estado"));
                horario.setDescripcion(resultSet.getString("descripcion"));
                horario.setHoraInicio(resultSet.getTime("hora_inicio"));
                horario.setHoraFin(resultSet.getTime("hora_fin"));
                horario.setArea(resultSet.getString("area"));                
            }
            preparedStatement.close();
            resultSet.close();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            this.Cerrar();
        }
        return horario;
    }
}
