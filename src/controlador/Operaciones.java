/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package controlador;

import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author ricardoxd
 */
abstract class Operaciones {
    
 TableModel modelo_temporal;
    int contador;
   double Matriz[][] = null;
    int restricciones = 0, variables = 0, iter = 0, iteracion = 0;
    Object array[] = null;
    Object EtiquetaX[] = null, EtiquetaY[] = null;
    DefaultTableModel modelo_operaciones;
    
  
     public int ColumnaPivote() {//buscar la comliman pivote
        int pos = 0;
        double aux = Matriz[restricciones][0];
        for (int i = 1; i < restricciones + variables; i++) {//se recorre las restricciones y variables
            if (aux > Matriz[restricciones][i]) {//busca el valor mayor en la fila z de las variables
                aux = Matriz[restricciones][i];
                pos = i;
            }
        }
        return pos;
    }

    public int FilaPivote() {//buscar fila pivote
        int columna = ColumnaPivote();//toma el valor de la columna pivote

        double temp = 0;
        double razon = Matriz[0][variables + restricciones] / Matriz[0][columna];
        int pos = 0;

        for (int i = 1; i < restricciones; i++) {//se recorre las restricciones

                if (Matriz[i][columna] != 0) {//se recorre cada valor de la columa pivote

                    temp = Matriz[i][variables + restricciones] / Matriz[i][columna];//se divide el cada valor de la columna pivte por su solucion
                            if (razon > temp && temp >= 0) {//si la nueva varible es mayor y es mayor que sero sera la pivote

                                    razon = temp;
                                    pos = i;//numero defila pivote

                            }

                }

        }
        return pos;
    }

    public void NuevaTabla(int Fila, int Columna) {
        double pivote = Matriz[Fila][Columna], temp = 0;//--//se toma el vaor del pivote
        System.out.println("______________________________fila pivote______________________________");
        for (int i = 0; i < restricciones + variables + 1; i++) {
            modelo_operaciones.setValueAt("Fila pivote="+" esta fila/"+ pivote, Fila, 0);
        double anterior=    Matriz[Fila][i];
            Matriz[Fila][i] = anterior / pivote;//se recorre la columna del pivote y se le divide x el pivote
           
            System.out.println(Matriz[Fila][i] +"|=|"+ anterior +"|/|"+ pivote);
        }
 System.out.println("______________________________fila pivote fin______________________________");
        for (int i = 0; i < restricciones + 1; i++) {
            if(i!=restricciones){
    System.out.println("fila q no es pivote:"+i+"______________________________________________________________");            
            }else{
     System.out.println("fila resultado:"+i+"______________________________________________________________");             
            }
                
           temp = Matriz[i][Columna];//se recorre la columna del pivote

            for (int j = 0; j < variables + restricciones + 1; j++) {

                if (i != Fila) {//si no es la fila del pivote
                 
                    contador++;
                     System.out.println("contador:"+contador);
                    int n=i+1;
                   modelo_operaciones.setValueAt("Fila "+n+"= esta fila -"+ temp+ "*"+ "resultado fila pivote ", i, 0);
                 
double anterior=Matriz[i][j];
                    Matriz[i][j] =anterior  - temp * Matriz[Fila][j];//el valor q tiene en cada fila de la columna en el arreglo
                    
                    System.out.println(Matriz[i][j] +"|=|"+ anterior +"|-|"+ temp +"|*|"+ Matriz[Fila][j]);
                    
                    //le sera restado
                } else {
                    break;
                }

            }
        }
    }

    public boolean ComprobarResultado() {
        boolean result = true;
        for (int i = 0; i < restricciones + variables; i++) {
            if (Matriz[restricciones][i] < 0) {//verifica que la el resultado de Xi a sido puesto menor q 0
                result = false;
                break;
            }
        }
        return result;
    }
    String Franccionar(double d){
         String fraccion = null;
try{


String aString = Double.toString(d);
String[] fraction = aString.split("\\.");

int denominador = (int)Math.pow(10, fraction[1].length());
int numerador = Integer.parseInt(fraction[0] + "" + fraction[1]);

System.out.println(numerador + "/" + denominador);
int num1,num2,mcd=0,min;

                num1 = numerador;
                num2 = denominador;

                if(num1 < num2){
                min = num1;
                }else{
                min = num2;
                }

                for(int i=1;i<=min;i++){
                if(num1 % i == 0 && num2 % i == 0){
                mcd = i;
                }
                }

       fraccion=numerador/mcd + "/" + denominador/mcd;
   System.out.println(fraccion);
   }catch(Exception e){
       System.out.println("no francciono:"+e);
       fraccion=String.valueOf(d);

   }
return fraccion;
}
    double Desfraccionar(String x){
       double f = 0;
        try{
         String[] fraction = x.split("/" );
         f=Double.valueOf(fraction[0])/Double.valueOf(fraction[1]);
        System.out.println(f+":como decimal");
        }catch(Exception e){
            System.out.println("de e:"+e);
            f=Double.valueOf(x);
        }

        return f;
    }


}
