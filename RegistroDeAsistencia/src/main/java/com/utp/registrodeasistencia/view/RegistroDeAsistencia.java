/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.utp.registrodeasistencia.view;

import com.utp.registrodeasistencia.controller.AsistenciaDaoImple;
import com.utp.registrodeasistencia.controller.ConnectionDB;
import com.utp.registrodeasistencia.controller.UsuarioDaoImpl;
import com.utp.registrodeasistencia.model.Asistencia;
import com.utp.registrodeasistencia.model.DetalleAsistencia;
import com.utp.registrodeasistencia.model.Horario;
import com.utp.registrodeasistencia.model.Usuario;
import java.sql.Timestamp;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.Date;
import javax.swing.JButton;


/**
 *
 * @author Miguel
 */
public class RegistroDeAsistencia extends javax.swing.JPanel {
    private Timestamp fechaHoraIngreso2;
    private DetalleAsistencia detalleAsistencia;
    private Date HoraEntrada;
    private Date HoraSalida;
    private AsistenciaDaoImple AsistenciaDaoImple;
    /**
     * Creates new form Principal
     */
    public RegistroDeAsistencia() {
        
        initComponents();
        btnEntrada.setEnabled(true);
        
        
        
        
    }
    
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bg = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnEntrada = new javax.swing.JButton();
        btnSalida = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtMinutosTardanza = new javax.swing.JLabel();
        txtHorasTrabajadas = new javax.swing.JLabel();

        setPreferredSize(new java.awt.Dimension(750, 430));

        bg.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Minutos de tardanza:");

        btnEntrada.setBackground(new java.awt.Color(255, 255, 255));
        btnEntrada.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        btnEntrada.setForeground(new java.awt.Color(13, 71, 161));
        btnEntrada.setText("Marcar Ingreso");
        btnEntrada.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnEntrada.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEntradaActionPerformed(evt);
            }
        });

        btnSalida.setBackground(new java.awt.Color(255, 255, 255));
        btnSalida.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        btnSalida.setForeground(new java.awt.Color(13, 71, 161));
        btnSalida.setText("Marcar Salida");
        btnSalida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        btnSalida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSalidaActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Total de horas trabajadas: ");

        txtMinutosTardanza.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtMinutosTardanza.setText("00:00:00");

        txtHorasTrabajadas.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        txtHorasTrabajadas.setText("00:00:00");

        javax.swing.GroupLayout bgLayout = new javax.swing.GroupLayout(bg);
        bg.setLayout(bgLayout);
        bgLayout.setHorizontalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addGap(63, 63, 63)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(bgLayout.createSequentialGroup()
                        .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(40, 40, 40)
                        .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addGroup(bgLayout.createSequentialGroup()
                            .addComponent(jLabel1)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtMinutosTardanza, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(bgLayout.createSequentialGroup()
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 244, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(txtHorasTrabajadas, javax.swing.GroupLayout.PREFERRED_SIZE, 171, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(47, Short.MAX_VALUE))
        );
        bgLayout.setVerticalGroup(
            bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(bgLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtHorasTrabajadas))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(txtMinutosTardanza))
                .addGap(32, 32, 32)
                .addGroup(bgLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEntrada, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSalida, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(bg, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnEntradaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEntradaActionPerformed
        AsistenciaDaoImple asistenciadao  = new AsistenciaDaoImple();
        Asistencia asistencia = new Asistencia();
        Usuario usuario = new Usuario();
        UsuarioDaoImpl u = new UsuarioDaoImpl();
        Menu menu = new Menu();
        
        fechaHoraIngreso2 = new Timestamp(System.currentTimeMillis());//este es para calcular las horas trabajadas
        
        System.out.println(menu.dni);
        try {
             usuario = u.obtenerUsuarioPorDni(menu.dni);             
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        
        // Insertar la entrada en la tabla de asistencia
        try {
            asistenciadao.registrarEntrada(usuario);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se guardo al insertar 1");
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("No se guardo al insertar 2");
        }
        btnEntrada.setEnabled(false);
        btnSalida.setEnabled(true);

        
        // Obtener los segundos de tardanza
        long segundosTardanza=0;
        try {
            segundosTardanza = asistencia.obtenerSegundosTardanza(menu.dni);
            System.out.println("Segundos Tardanza traidos de Asistencia (formulario) "+segundosTardanza);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("SegundosTardanza (formulario) "+segundosTardanza);
        
        
        long horas = segundosTardanza/3600;
        long minutos = (segundosTardanza % 3600) / 60;
        long segundos = (segundosTardanza % 60);
        System.out.println("horas "+horas);
        System.out.println("minutos "+minutos);
        System.out.println("segundos "+segundos);
        // Convertir los segundos de tardanza al formato "hh:mm:ss"
        String tardanza=String.format("%02d:%02d:%02d", horas, minutos, segundos);
        System.out.println("Se ve el formato (formulario)"+tardanza);
        
        // Mostrar el resultado en el JTextField
        txtMinutosTardanza.setText(tardanza);
        
    }//GEN-LAST:event_btnEntradaActionPerformed
    
    
    //BOTON DE SALIDA
    private void btnSalidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSalidaActionPerformed
        AsistenciaDaoImple asisdao  = new AsistenciaDaoImple();
        Usuario usuario = new Usuario();
        UsuarioDaoImpl u = new UsuarioDaoImpl();
        Asistencia asis=new Asistencia();
        Menu menu = new Menu();
        
        try {
             usuario = u.obtenerUsuarioPorDni(menu.dni);             
        } catch (Exception ex) {
            Logger.getLogger(Menu.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        try {
            asisdao.registrarSalida(usuario);
       
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(RegistroDeAsistencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnEntrada.setEnabled(true);
        btnSalida.setEnabled(false);
        
        Timestamp fechaHoraSalida = new Timestamp(System.currentTimeMillis());
        
        if (fechaHoraIngreso2 != null) {
            System.out.println("Fecha de Ingreso"+fechaHoraIngreso2);
        // Calcular la diferencia entre la hora de salida y entrada
        long diferenciaMillis = fechaHoraSalida.getTime() - fechaHoraIngreso2.getTime();
            System.out.println("Hora de salida "+fechaHoraSalida);
        long segundosTrabajados = diferenciaMillis / 1000;
            System.out.println("Diferencia de las dos anteriores"+diferenciaMillis);
            System.out.println("La diferencia entre 1000"+ segundosTrabajados);
        // Calcular las horas y minutos
        long horasTrabajadas = segundosTrabajados / 3600;
            System.out.println("Horastrabajadas"+horasTrabajadas);
        long minutosRestantes = (segundosTrabajados % 3600) / 60;
        System.out.println("MinutosTrabajados"+minutosRestantes);
        long segundosRestantes = segundosTrabajados % 60;
        System.out.println("segundosRestantes"+segundosRestantes);
        
        // Mostrar el resultado en el formato deseado
        String horasTrabajadasFormato = String.format("%02d:%02d:%02d", horasTrabajadas, minutosRestantes, segundosRestantes);
        
        // Imprimir en el txtHorasTrabajadas
        txtHorasTrabajadas.setText(horasTrabajadasFormato);
         } else {
        // Si fechaHoraIngreso es nulo, manejar la situación según sea necesario
        txtHorasTrabajadas.setText("Error: hora de ingreso no válida");
        }
    }//GEN-LAST:event_btnSalidaActionPerformed

    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel bg;
    private javax.swing.JButton btnEntrada;
    private javax.swing.JButton btnSalida;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel txtHorasTrabajadas;
    public javax.swing.JLabel txtMinutosTardanza;
    // End of variables declaration//GEN-END:variables
}
