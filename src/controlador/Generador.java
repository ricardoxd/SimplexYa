package controlador;

import vista.Ventana;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author @iRicardoXD
 */
public class Generador extends Operaciones{
private vista.Ventana vista;

    public Generador() {
        vista=new Ventana();
        vista.getBotonGenerar().addActionListener(generar);
        vista.getBoton_solucion().addActionListener(solucionar);
        System.out.println("inicia");
           vista.getMenu_salir().addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
         System.exit(0);
            }
        });
    }
 private ActionListener solucionar =new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
        
           try {
 modelo_operaciones = new DefaultTableModel();//***
            DefaultTableModel modeloSolucion = new DefaultTableModel();
            Matriz = new double[restricciones + 1][restricciones + variables + 1];



            for (int i = 0; i < (restricciones + 1); i++) {//se recorre las filas de la tabla

                for (int j = 0; j < (restricciones + variables + 1); j++) {//se recorre las columans de la tabla
                    //se guardan cada una en la matriz
                        Matriz[i][j] = Double.parseDouble(vista.getTabla_simplex().getValueAt(i, j + 1).toString());
                    //  Matriz[i][j] = Desfraccionar(jTable1.getValueAt(i, j + 1).toString());
                }

            }


            while (ComprobarResultado() != true) {//verifica que la el resultado se ha invertido a negativo

                EtiquetaY[FilaPivote()] = EtiquetaX[ColumnaPivote()];
  modelo_operaciones.setRowCount(restricciones+1);//***
                modelo_operaciones.setColumnCount(1);//***
                NuevaTabla(FilaPivote(), ColumnaPivote());

                modeloSolucion.setColumnCount(restricciones + variables + 2);
                modeloSolucion.setRowCount(restricciones + 1);

              
                //--------------------------
                modeloSolucion.setColumnIdentifiers(array);
                //---------------------------
                for (int i = 0; i < (restricciones + 1); i++) {
                    modeloSolucion.setValueAt(EtiquetaY[i], i, 0);

                    for (int j = 0; j < (restricciones + variables + 1); j++) {
                         modeloSolucion.setValueAt(Matriz[i][j], i, j + 1);
                       // modeloSolucion.setValueAt(Franccionar(Matriz[i][j]), i, j + 1);

                    }
                }
                vista.getTabla_resultado().setModel(modeloSolucion);
                vista.getTabla_operaciones().setModel(modelo_operaciones);
                iteracion++;

            }


            vista.getBoton_solucion().setEnabled(false);
             vista.getBoton_iteraccion().setEnabled(true);
             
                         vista.getPestanas().setSelectedIndex(2);
            System.out.println("listo");
        } catch (Exception es) {
            System.out.println(es.getMessage());
            StackTraceElement[] stackTrace = es.getStackTrace();
            System.out.println(stackTrace);
            System.out.println(e.getClass());
        }
       
            
           
            }
        }; 
    
 private ActionListener generar= new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("click");
                      //click en boton aceptar para hacer tabla
modelo_temporal= vista.getTabla_simplex().getModel();
        try {

            restricciones = Integer.parseInt(vista.getRestricciones().getText());
            variables = Integer.parseInt(vista.getVariables().getText());

            DefaultTableModel modelo = new DefaultTableModel();


                 System.out.println("Dimencionar tabla");
            modelo.setRowCount(restricciones + 1);
            modelo.setColumnCount(variables + restricciones + 2);
            

            array = new Object[variables + restricciones + 2];

            EtiquetaX = new Object[variables + restricciones];

            for (int i = 1; i < array.length - 1; i++) {//recorre cada columna

                    if (i < variables + 1) {//identifica las variables
                                array[i] = "X" + i;
                                EtiquetaX[i - 1] = "X" + i;
                    } else {//identifica las variables de holgura
                                array[i] = "S" + (i - variables);
                                EtiquetaX[i - 1] = "S" + (i - variables);
                    }

            }



            //---------------------
            EtiquetaY = new Object[restricciones + 1];
            for (int i = 0; i < restricciones; i++) {//recorre la restricciones

                modelo.setValueAt("S" + (i + 1), i, 0);//se identifica en el modelo las soluciones

                EtiquetaY[i] = "S" + (i + 1);
            }


            modelo.setValueAt("Z", restricciones, 0);//identifica la fila de Z
            EtiquetaY[restricciones] = "Z";

            //----------------------
            array[array.length - 1] = "Solución";
            modelo.setColumnIdentifiers(array);//se identifica la etiqueta de solución

            vista.getTabla_simplex().setModel(modelo);//se da el modelo a la tabla
//vista.getTabla_simplex().setModel(modelo_temporal);
            vista.getPestanas().setSelectedIndex(1);
            System.out.println("listo");
        } catch (Exception ed) {
            System.out.println(ed.getMessage());
            System.out.println(ed.getClass());
        }  
                
            }
        };
    

    public Ventana getVista() {
        return vista;
    }

    public void setVista(Ventana vista) {
        this.vista = vista;
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
                if ("Metal".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ventana.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
             Generador g=  new Generador();
             
             g.vista.setLocationRelativeTo(null);
             g.getVista().setVisible(true);
            }
        });
    }
}
