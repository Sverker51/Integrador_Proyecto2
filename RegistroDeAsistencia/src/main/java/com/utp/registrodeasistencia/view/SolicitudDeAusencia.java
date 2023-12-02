/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package com.utp.registrodeasistencia.view;

import java.awt.Color;
import com.utp.registrodeasistencia.controller.ConnectionDB;
import com.utp.registrodeasistencia.controller.GenerarCorreo;
import com.utp.registrodeasistencia.controller.UsuarioDaoImpl;
import com.utp.registrodeasistencia.model.Usuario;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import javax.swing.JOptionPane;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Miguel
 */
public class SolicitudDeAusencia extends javax.swing.JPanel {
    private ConnectionDB connectionDB;
    /**
     * Creates new form SolicitudDiaLibre
     */
    Usuario usuario = new Usuario();
    public SolicitudDeAusencia() {
        initComponents();
        InitStyles();
        connectionDB = new ConnectionDB();
        InitListeners();
    }
    
    private void InitListeners() {
        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        Main main = new Main();
        try {
            usuario = usuarioDao.obtenerUsuarioPorDni(main.dni);
        } catch (Exception ex) {
            Logger.getLogger(SolicitudDeAusencia.class.getName()).log(Level.SEVERE, null, ex);
        }
        btnSolicitarPermiso.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                solicitarPermiso();
            }
        });
    }
    
    private void solicitarPermiso() {
            Date fechaInicio = FechaInicio.getDate();
            Date fechaFin = FechaFin.getDate();
            String motivo = Motivo.getSelectedItem().toString();
            String detalles = descripcion.getText();

            if (fechaInicio == null || fechaFin == null || motivo.isEmpty() || detalles.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.");
                return;
            }

            // Calcular la cantidad de días entre las fechas
            long diasSolicitados = ChronoUnit.DAYS.between(fechaInicio.toInstant(), fechaFin.toInstant());

            if (diasSolicitados <= 0) {
                JOptionPane.showMessageDialog(this, "La fecha de fin debe ser posterior a la fecha de inicio.");
                return;
            }

            DiasSolicitados.setText(String.valueOf(diasSolicitados));

            try {
                // Intenta registrar la solicitud en la base de datos
                registrarSolicitud(fechaInicio, fechaFin, motivo, detalles);

                // Si no hay excepción, muestra el mensaje de éxito
                JOptionPane.showMessageDialog(this, "Solicitud de permiso enviada con éxito.");
            } catch (Exception ex) {
                // Si hay una excepción, muestra el error y sus detalles
                ex.printStackTrace(); // Esto imprime la traza del error en la consola
                JOptionPane.showMessageDialog(this, "Error al registrar la solicitud en la base de datos.");
            }

            // Luego de procesar la solicitud, puedes realizar alguna acción, como limpiar los campos
            GenerarCorreo generarCorreo = new GenerarCorreo();
            generarCorreo.enviarCorreo(usuario.getCorreo(), fechaInicio + " - " + fechaFin, usuario.getNombre(), motivo);
            
            limpiarCampos();
            }
    
            private void registrarSolicitud(Date fechaInicio, Date fechaFin, String motivo, String descripcion) {
        
            try {
            connectionDB.Conectar();
            Connection conexion = connectionDB.conexion; // Accede a la conexión desde ConnectionDB
            PreparedStatement statement = conexion.prepareStatement("INSERT INTO detalle_permiso_usuario (fecha_inicio, fecha_fin, motivo) VALUES (?, ?, ?)");

            statement.setDate(1, new java.sql.Date(fechaInicio.getTime()));
            statement.setDate(2, new java.sql.Date(fechaFin.getTime()));
            statement.setString(3, motivo);

            statement.executeUpdate();
            connectionDB.Cerrar();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al registrar la solicitud en la base de datos.");
        }
        
    }
            
        private void limpiarCampos() {
            FechaInicio.setDate(null);
            FechaFin.setDate(null);
            Motivo.setSelectedIndex(0);
            descripcion.setText("");
        }
    private void InitStyles() {
        //btnSolicitarPermiso.putClientProperty("JButton.buttonType", "roundRect");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        FechaInicio = new com.toedter.calendar.JDateChooser();
        jLabel2 = new javax.swing.JLabel();
        FechaFin = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        Motivo = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        descripcion = new javax.swing.JEditorPane();
        jLabel5 = new javax.swing.JLabel();
        DiasSolicitados = new javax.swing.JLabel();
        btnSolicitarPermiso = new javax.swing.JButton();
        btnLimpiar = new javax.swing.JButton();

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel1.setText("Fecha de inicio: ");

        FechaInicio.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FechaInicio.setMinSelectableDate(new java.util.Date(-62135747903000L));
        FechaInicio.setPreferredSize(new java.awt.Dimension(95, 27));

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel2.setText("Fecha de fin: ");

        FechaFin.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        FechaFin.setMinSelectableDate(new java.util.Date(-62135747903000L));
        FechaFin.setPreferredSize(new java.awt.Dimension(95, 27));

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel3.setText("Motivo:");

        Motivo.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        Motivo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Vacaciones", "Enfermedad", "Asuntos familiares", "Licencia por maternidad o paternidad", "Días personales", "Citas médicas", "Formación y desarrollo", "Permiso de estudios", "Licencia sin sueldo", "Permiso para cuidado de familiares", "Otros" }));
        Motivo.setPreferredSize(new java.awt.Dimension(56, 27));

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel4.setText("Detalle:");

        descripcion.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        jScrollPane1.setViewportView(descripcion);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        jLabel5.setText("Total de dias solicitados:");

        DiasSolicitados.setFont(new java.awt.Font("Segoe UI", 0, 40)); // NOI18N
        DiasSolicitados.setText("0");

        btnSolicitarPermiso.setBackground(new java.awt.Color(15, 118, 210));
        btnSolicitarPermiso.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnSolicitarPermiso.setForeground(new java.awt.Color(255, 255, 255));
        btnSolicitarPermiso.setText("Solicitar Permiso");
        btnSolicitarPermiso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSolicitarPermisoActionPerformed(evt);
            }
        });

        btnLimpiar.setBackground(new java.awt.Color(15, 118, 210));
        btnLimpiar.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Limpiar");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel2)
                                    .addComponent(jLabel3))
                                .addGap(29, 29, 29)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(Motivo, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(FechaFin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(FechaInicio, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 120, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addGap(65, 65, 65))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(DiasSolicitados)
                                .addGap(172, 172, 172))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jScrollPane1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel4)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addGap(122, 122, 122)
                                        .addComponent(btnSolicitarPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(89, 89, 89)
                                        .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(0, 0, Short.MAX_VALUE)))
                        .addContainerGap())))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(FechaInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel1))
                    .addComponent(jLabel5))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(FechaFin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel3)
                            .addComponent(Motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(DiasSolicitados))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 19, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnLimpiar, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnSolicitarPermiso, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents

    private void btnSolicitarPermisoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSolicitarPermisoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnSolicitarPermisoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DiasSolicitados;
    private com.toedter.calendar.JDateChooser FechaFin;
    private com.toedter.calendar.JDateChooser FechaInicio;
    private javax.swing.JComboBox<String> Motivo;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JButton btnSolicitarPermiso;
    private javax.swing.JEditorPane descripcion;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
