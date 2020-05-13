/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ppv.forms;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import ppv.dbConection.DBConect;
import ppv.utils.TestCut;
import ppv.utils.TicketCorteCaja;
import ppv.utils.Utils;

/**
 *
 * @author user2
 */
public class ConfirmarCorte extends javax.swing.JFrame {

    String userSession;
    String passSession;
    String dia;
    String caja;
    /**
     * Creates new form ConfirmarCorte
     */
    public ConfirmarCorte() {
        initComponents();
    }

    public ConfirmarCorte(String money,String userSession,String passSession,String dia,String enCaja) {
        initComponents();
        setLocationRelativeTo(null);
        String textoMoney=labelMoney.getText();
        textoMoney=textoMoney.replace("{{money}}",money);
        labelMoney.setText(textoMoney);
        this.userSession=userSession;
        this.passSession=passSession;
        this.dia=dia;
        this.caja=enCaja;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        labelMoney = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        user = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        pass = new javax.swing.JPasswordField();
        corteDeCajaConfirm = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Confirma la recepción de efectivo");

        labelMoney.setText("Declaro haber recibido la cantidad de {{money}} en efectivo.");

        jLabel3.setText("Relice el conteo y confirmo que es correcto.");

        jLabel4.setText("Usuario:");

        jLabel5.setText("Ingresa los datos de quien recibe para finalizar.");

        jLabel6.setText("Contraseña:");

        corteDeCajaConfirm.setText("Confirmar corte de caja");
        corteDeCajaConfirm.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                corteDeCajaConfirmActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(user)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(pass)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelMoney)
                            .addComponent(jLabel6))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(corteDeCajaConfirm, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelMoney, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(1, 1, 1)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(pass, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(corteDeCajaConfirm)
                .addGap(0, 11, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void corteDeCajaConfirmActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_corteDeCajaConfirmActionPerformed
      DBConect conexion=new DBConect();  
      Utils u =new Utils();
      Date fecha=new Date();
      
      try{
        Connection conexionMysql = conexion.GetConnection();
       
        Statement statement = conexionMysql.createStatement();
        
        String userVar=user.getText();
        String passVar=pass.getText();
        
        if(userVar.equals("") || passVar.equals("")){
            JOptionPane.showMessageDialog(null,"Es necesario proporcionar usuario y contraseña"); 
            return;
        }
        
        if(userVar.equals(this.userSession) && passVar.equals(this.passSession)){
            JOptionPane.showMessageDialog(null,"Para confirmar corte de caja se necesitan\n los datos del usuario que recibe el corte de caja"); 
            return;
        }
        
        String sqlString="Select * from usuarios where nombreUsuario='"+userVar+"' and password='"+passVar+"'";
        ResultSet rs = statement.executeQuery(sqlString);
        
        int resultados=0;
        int idUsuario=0;
        String usuario="";
        while(rs.next()){
           resultados++;
           idUsuario=rs.getInt("idusuario");
           usuario=rs.getString("nombreUsuario");
        }
        
        if(resultados>0){
            realizarCorteCaja();
            JOptionPane.showMessageDialog(null, "Se realizo corte de caja exitosamente");
            TicketCorteCaja ticket=new TicketCorteCaja("ENTREGA",u.formateaFechaTicket(fecha),
                    u.obtenerNombreCompletoUsuarioByPassAndUser(this.userSession, this.passSession),
                    u.obtenerNombreCompletoUsuarioByPassAndUser(userVar, passVar),this.dia,this.caja,labelMoney.getText());
            ticket.Imprimir();
            TestCut testCut= new TestCut();
            testCut.clean();
            testCut.cortar();
                        
            TicketCorteCaja ticket2=new TicketCorteCaja("RECEPCION",u.formateaFechaTicket(fecha),
                    u.obtenerNombreCompletoUsuarioByPassAndUser(this.userSession, this.passSession),
                    u.obtenerNombreCompletoUsuarioByPassAndUser(userVar, passVar),this.dia,this.caja,labelMoney.getText());
            ticket2.Imprimir();
            TestCut testCut2= new TestCut();
            testCut2.clean();
            testCut2.cortar();
            this.setVisible(false);
            this.dispose();
            Inicio init=new Inicio();
            init.setVisible(true);
            init.toFront();
            
        }else{
             JOptionPane.showMessageDialog(null, "No se pudo realizar corte, DATOS DE AUTORIZACIÓN CON CORRECTOS");
        }
        
      }catch(Exception e){
          
      }
    }//GEN-LAST:event_corteDeCajaConfirmActionPerformed

    public void realizarCorteCaja(){
        
        DBConect conexion=new DBConect(); 
        Connection conexionMysql = conexion.GetConnection();
        
        /////obtenemos el maximo ingresado en la caja
        String sqlString="Select max(idCaja),inicioDelDia,finalDelDia,fecha from caja ";
        try{
         
         Statement statement=conexionMysql.createStatement();
         ResultSet rs2 = statement.executeQuery(sqlString);
         SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
         int idCaja=0;
            while(rs2.next()){
                idCaja=rs2.getInt(1);
            }
            
            if(idCaja!=0){        
                
                String balance=labelMoney.getText();
                balance=balance.replace("$", "");
                balance=balance.trim();
                
                String sqlString2="UPDATE CAJA set finalDelDia="+balance+ " WHERE idCaja="+idCaja;
                statement.executeUpdate(sqlString2);
                JOptionPane.showMessageDialog(null,"Cantidad en caja al final del día: "+balance);
            }
        
        }catch(Exception e){
            e.printStackTrace();
        }    
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
            java.util.logging.Logger.getLogger(ConfirmarCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConfirmarCorte.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConfirmarCorte().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton corteDeCajaConfirm;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel labelMoney;
    private javax.swing.JPasswordField pass;
    private javax.swing.JTextField user;
    // End of variables declaration//GEN-END:variables
}
