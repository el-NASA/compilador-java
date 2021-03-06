import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


/**
 *
 * @author Alba
 */
public class Lexico {

    ArrayList<Nodo> etradaDiv = new ArrayList<Nodo>();

    
    public void analizar (String entrada, JTable vista, Vista vista2){
        
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

            /*if(entrada.charAt(a)==' '){
                a++;
            }*/

            //encuentro de palabra res
            if(control==0){
                for(int i=0; i<tablaTok.pR;i++){
                    control=comCompleja(entrada, tablaTok.palabrasRes,i,modelo,control,a);
                    if(control==1){
                        a=a+tablaTok.palabrasRes[i][0].length();


                        if(tablaTok.palabrasRes[i][0].equals("int") || tablaTok.palabrasRes[i][0].equals("String") ||
                                tablaTok.palabrasRes[i][0].equals("char") || tablaTok.palabrasRes[i][0].equals("double")){
                            vista2.getJTextPane().setText(vista2.getJTextPane().getText()+" "+ this.etradaDiv.get(this.etradaDiv.size()-1).token+" ");
                            String nombre_aux = "";

                            System.out.println(entrada.charAt(a));
                            control=ingresoVariables(a, entrada, this.etradaDiv.get(this.etradaDiv.size()-1).token, tablaTok);
                            while (entrada.charAt(a)!='='){
                                nombre_aux = nombre_aux+entrada.charAt(a);
                                a++;
                            }
                            Nodo nodo_aux = new Nodo();
                            nodo_aux.setToken(nombre_aux);
                            nodo_aux.setDescripcion("variable");
                            this.etradaDiv.add(nodo_aux);
                            modelo.addRow(new Object[]{nodo_aux.token, nodo_aux.descripcion});

                        }
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
            if(control==-1){
                errorLex++;
                vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"!!!error def variable");
                control=0;
            }
            if(control==0){
                modelo.addRow(new Object[]{entrada.charAt(a), "Caracter sin sentido- Error"});
                errorLex++;
                vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"!!! "+ entrada.charAt(a)+" !!!");
                a++;
            }
            else{
                vista2.getJTextPane().setText(vista2.getJTextPane().getText()+" "+ this.etradaDiv.get(this.etradaDiv.size()-1).token+" ");
            }
                
        } // fin del ciclo - fin separacion por caracteres
            
        modelo.addRow(new Object[]{"", ""});

        if(errorLex==0){
            vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"\n\n\n#####iniciando sintactico####\n");
            Sintactico sem= new Sintactico(etradaDiv);
            sem.analizar_revursivo(vista,0, vista2);
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
                //si no concuerdan se sale del ciclo para evitar errores
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

    public int ingresoVariables(int i, String entrada, String s2, Tokens tablaTok){
        String descrip="";
        String nombreVar = "";

        if(entrada.charAt(i)=='='){
            return -1;
        }
        while (entrada.charAt(i)!='='){
            nombreVar+=entrada.charAt(i);
            i++;
        }
        i++;
        if(entrada.charAt(i)==';'){
            return -1;
        }

        String valor ="";
            //string entrante
            String aux="";
            if(entrada.charAt(i)=='"'){
                i++;
                while(entrada.charAt(i)!='"'){
                    aux=aux+entrada.charAt(i);
                    i++;
                }descrip="variable-String";

            }else{
                if(digitos(entrada.charAt(i))){
                    descrip="variable-entero";

                    while(digitos(entrada.charAt(i))== true || entrada.charAt(i)=='.' ){
                        if(entrada.charAt(i)=='.')
                            descrip="variable-double";

                        aux= aux+entrada.charAt(i);
                        i++;
                    }


                }

            }
            valor=aux;
            //tablaTok.setVariables(nombreVar, descrip, valor);
            if(s2.equals("int") && descrip.equals("variable-entero")){
                tablaTok.setVariables(nombreVar, descrip, valor);
                return 0;
            }else if (s2.equals("double") && (descrip.equals("variable-double")||descrip.equals("variable-entero"))){
                tablaTok.setVariables(nombreVar, descrip, valor);
                return 0;
            }else if (s2.equals("String") && descrip.equals("variable-String")){
                tablaTok.setVariables(nombreVar, descrip, valor);
                return 0;
            }else if (s2.equals("char") && descrip.equals("variable-String") && valor.length()==1){
                tablaTok.setVariables(nombreVar, descrip, valor);
                return 0;
            }

        return -1;
        }


}   

