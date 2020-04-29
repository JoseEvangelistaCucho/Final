/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import java.util.ArrayList;
import java.util.Date;
import java.util.Observable;
import javax.swing.JTable;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;
import model.Multa;

/**
 *
 * @author diego
 */
public class Borrados extends javax.swing.JFrame {

    final MessageObservable observable = new MessageObservable();
    /**
     * Creates new form Borrados
     */
    public Borrados(ArrayList<Multa> lstBorradas, Registro regiInstance) {
        initComponents();
        System.err.println("cantidad: "+lstBorradas.size());
        llenarTabla(lstBorradas);
        observable.addObserver(regiInstance); // AGREGANDO AL SUSCRIPTOR
    }
    
    class MessageObservable extends Observable {
        MessageObservable() {	
            super();
        }
        void changeData(Object data) {
            setChanged(); // the two methods of Observable class
            notifyObservers(data);
        }
    }
    
    private void llenarTabla(ArrayList<Multa> lista) {
        DefaultTableModel model = new DefaultTableModel(new String[]{"DNI", "Multa", "Monto", "Correo", "Punto", "Id"}, 0);
        int i = 1;
        for(Multa m : lista) {
            model.addRow(new Object[]{i, m.getDni(), m.getMulta(), m.getMonto(), m.getCorreo(), m.getPunto(), m.getIdMulta()});
            i++;
        }
        tbBorrados = new JTable(model);
        tbBorrados.removeColumn(tbBorrados.getColumnModel().getColumn(5));
        ListSelectionListener lel = new ListSelectionListener(){
            public void valueChanged(ListSelectionEvent event) {
                if(!event.getValueIsAdjusting()) {
                    setearDatos();
                }
            }
        };
        tbBorrados.getSelectionModel().addListSelectionListener(lel);
        jScrollPane1.setViewportView(tbBorrados);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbBorrados = new javax.swing.JTable();
        btnRestaurar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        tbBorrados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "DNI", "Multa", "Monto", "Correo", "Puntos", "ID"
            }
        ));
        jScrollPane1.setViewportView(tbBorrados);

        btnRestaurar.setText("Restaurar");
        btnRestaurar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRestaurarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(15, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(143, 143, 143)
                .addComponent(btnRestaurar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(btnRestaurar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 275, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRestaurarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRestaurarActionPerformed
        // TODO add your handling code here:
        //multaRestaurar
        observable.changeData(multaRestaurar); // NOTIFICIANDO A MIS SUBSCRIPTORES
        // limpiar la lista
    }//GEN-LAST:event_btnRestaurarActionPerformed

    Multa multaRestaurar = new Multa();
    
    private void setearDatos() {
        String dni    = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 1  )+"";
        String multa  = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 2  )+"";
        String monto  = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 3 )+"";
        String correo = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 4  )+"";
        String punto  = tbBorrados.getModel().getValueAt(tbBorrados.getSelectedRow(), 5  )+"";
        System.err.println(dni+" - "+multa+" - "+monto+" - "+correo+" - "+punto);
        
        multaRestaurar.setCorreo(correo);
        multaRestaurar.setDni(dni);
        multaRestaurar.setMonto(Double.parseDouble(monto));
        multaRestaurar.setMulta(multa);
        multaRestaurar.setPunto(Integer.parseInt(punto));
        multaRestaurar.setFecha(new Date());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Borrados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Borrados(null, null).setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRestaurar;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tbBorrados;
    // End of variables declaration//GEN-END:variables
}