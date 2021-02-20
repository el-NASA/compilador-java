import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Alba
 */
public class Lexico {

    ArrayList<Nodo> etradaDiv = new ArrayList<Nodo>();

    
    public void analizar (String entrada, JTable vista){
        
        int errorLex=0; // Cuenta los errores léxicos presentes, ej: caracter sin sentido
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getModel();   
        modelo.addRow(new Object[]{"", ""});     
        Tokens tablaTok= new Tokens();
        
        int a= 0;
        entrada = entrada.replaceAll("\\s","");

        while(a<entrada.length()){
            
            //para no verificar mas en la posicion cuando encuentre algo
            int control=0;

            //encuentro de palabra res
            if(control==0){
                for(int i=0; i<tablaTok.pR;i++){
                    control=comCompleja(entrada, tablaTok.palabrasRes,i,modelo,control,a);
                    if(control==1){
                        a=a+tablaTok.palabrasRes[i][0].length();
                        i=tablaTok.pR;
                    }
                }
            }

            //variables
            if(control==0){

                for(int i=0; i< tablaTok.va;i++){
                    //si son iguales lo imprime
                   control=comSimple(entrada, tablaTok.var,i,modelo,control,a);
                   if(control==1){
                       a++;
                       i=tablaTok.va;
                   }
                }
            }
            

            
            
            //caracteres de agrupacion
            if(control==0){
                for(int i=0; i< tablaTok.ag;i++){
                    //si son iguales lo imprime
                   control=comSimple(entrada, tablaTok.agrupacion,i,modelo,control,a);
                   if(control==1){
                       a++;
                       i=tablaTok.ag;
                   }
                }
            }

            
            //encuantro de operadores aritmeticos
            if(control==0){
                for(int i=0; i< tablaTok.opAr;i++){
                    //si son iguales lo imprime
                    control=comSimple(entrada, tablaTok.operadoresArit,i,modelo,control,a);
                    if(control==1){
                        a++;
                        i=tablaTok.opAr;
                    }
                }
            }

            //encuentro de operadores de comparacion
            if(control==0){
                for(int i=0; i< tablaTok.opCom;i++){
                    control=comCompleja(entrada, tablaTok.operadoresComparacion,i,modelo,control,a);
                    if(control==1){
                        a=a+tablaTok.operadoresComparacion[i][0].length();
                        i=tablaTok.opCom;
                    }
                }
            }

            //encuentro de operadores logicos
            if(control==0){
                for(int i=0; i< tablaTok.opLog;i++){
                    control=comCompleja(entrada, tablaTok.operadoresLogicos,i,modelo,control,a);
                    if(control==1){
                        a=a+tablaTok.operadoresLogicos[i][0].length();
                        i=tablaTok.opLog;
                    }
                }
            }

            
            
            // una cadena de texto, comienza y termina en " " 
            if(control==0){
                //string entrante
                String aux="";
                if(entrada.charAt(a)=='"'){
                    a++;
                    while(entrada.charAt(a)!='"'){
                        aux=aux+entrada.charAt(a);
                        a++;
                    }
                    modelo.addRow(new Object[]{""+aux, "Cadena de texto"});
                    Nodo b= new Nodo();
                    b.setToken(aux);
                    b.setDescripcion("Cadena de texto");
                    this.etradaDiv.add(b);
                    a++;
                    control++;
                }else{
                    if(digitos(entrada.charAt(a))){
                        String descrip="Constante-Numerica-Entera";
                        String numero="";
                       while(digitos(entrada.charAt(a))== true || entrada.charAt(a)=='.' ){
                           if(entrada.charAt(a)=='.')
                               descrip="Constante-Numerica-Decimal";
                          
                           numero= numero+entrada.charAt(a);
                           a++;
                       } 
                       
                       modelo.addRow(new Object[]{""+numero, descrip});
                        Nodo b= new Nodo();
                        b.setToken(numero);
                        b.setDescripcion(descrip);
                        this.etradaDiv.add(b);
                       
                       control++; 
                    }
                    
                }
            }

            if(control==0){
                modelo.addRow(new Object[]{entrada.charAt(a), "Caracter sin sentido- Error"});
                errorLex++;
                a++;
            }
                
        } // fin del ciclo - fin separacion por caracteres
            
        modelo.addRow(new Object[]{"", ""});
        
        if(errorLex==0){
            Sintactico sem= new Sintactico(etradaDiv);
            sem.analizar_revursivo(vista,0);
        }else{
            
            modelo.addRow(new Object[]{"no se puede pasar al semantico", ""});
        }
        
        etradaDiv.clear();
        
    }

    int comSimple(String entrada,String[][] operador, int i, DefaultTableModel modelo, int control, int a){
        if(entrada.charAt(a)==operador[i][0].charAt(0)){
            modelo.addRow(new Object[]{""+operador[i][0], ""+operador[i][1]});
            Nodo b= new Nodo();
            b.setToken(operador[i][0]);
            b.setDescripcion(operador[i][1]);
            this.etradaDiv.add(b);
            
            control++;
        }
        return control;
    }
    
    int comCompleja (String entrada,String[][] operador, int i, DefaultTableModel modelo, int control, int a){
        
        //mira si coinside el primer caracter
        if(entrada.charAt(a)==operador[i][0].charAt(0)){
            //si coinside mira el resto de caracteres
            int verificacion=0;
            for(int j=0;j<operador[i][0].length();j++){
                if(entrada.charAt(a+j)!=operador[i][0].charAt(j)){
                    verificacion++;
                }
                //si no concuerdan se sale del siclo para evitar errores 
                if(verificacion>0){
                    j=operador[i][0].length();
                }
            }
            //si conisidio totalmente, lo imprime
            if(verificacion==0){
                modelo.addRow(new Object[]{""+operador[i][0], ""+operador[i][1]});  
                Nodo b= new Nodo();
                b.setToken(operador[i][0]);
                b.setDescripcion(operador[i][1]);
                this.etradaDiv.add(b);
                control++;
            }
                        
        }
        return control;
    }

    boolean digitos(char a){
        boolean res=false;
        
        if(a=='1' || a=='2' || a=='3' || a=='4' || a=='5' || a=='6' || a=='7' || a=='8' ||
           a=='9' || a=='0'){
            res=true;
        }
        return res;
    }
}   

