import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sintactico {
    
    ArrayList<Nodo> entrada = new ArrayList<Nodo>();

    public Sintactico(ArrayList<Nodo> e) {
        this.entrada=e;
        
    }

    public void analizar( JTable vista){

        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getModel();
        modelo.addRow(new Object[]{"", ""});

        String[] expReg;
        expReg = basePrint();
        //expReg = baseIf();
        //expReg = baseFor();



        int a=0;//aumento en la entrada
        int b=0;//aumento en la exprecionReg
        int error =0;


        while(a<entrada.size() && error==0 && b<expReg.length){ // inicio de comparacion


            if( coNum(expReg[b]) ){ //verificacion de mas de un paso, si la expresion regular da un numero es porque son mas opciones


                int x= Integer.parseInt(expReg[b]);

                for(int i=b+1 ;i <= b+x ; i++){

                    if( entrada.get(a).descripcion.equals(expReg[i]) == false){
                        error++;
                    }else{
                        error=0;
                        i=b+x+1;
                    }

                }

                b=b+x;
            }else{

                if( entrada.get(a).descripcion.equals(expReg[b]) == false){ //verificacion de solo un caso
                    error++;
                }
            }


            a++;
            b++;
        }

        if(b<expReg.length){
            if(error>0){
                modelo.addRow(new Object[]{"error en: "+entrada.get(a-1).token, ""});
            }else{
                modelo.addRow(new Object[]{"Falta", expReg[b]});
            }

        }else{
            if(error== 0){
                modelo.addRow(new Object[]{"todo correcto", ""});
                ArbolSintactico arbol = new ArbolSintactico(entrada);
                arbol.constuir_arbol();
            }else{

                modelo.addRow(new Object[]{"error en: "+entrada.get(a-1).token, ""});

            }
        }

    }

    public int analizar_revursivo(JTable vista, int entero1){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getModel();
        modelo.addRow(new Object[]{"", ""});

        String[] expReg;

        int a=entero1;//aumento en la entrada
        int b=0;//aumento en la exprecionReg
        int error =0;

        System.out.println(entrada.get(a).token);
        if(entrada.get(a).token.equals("for")){

            expReg=baseFor();
        }else{
            expReg=basePrint();
        }
        while(a<entrada.size() && b<expReg.length){ // inicio de comparacion


            if( coNum(expReg[b]) ){ //verificacion de mas de un paso, si la expresion regular da un numero es porque son mas opciones


                int x= Integer.parseInt(expReg[b]);

                for(int i=b+1 ;i <= b+x ; i++){

                    if( entrada.get(a).descripcion.equals(expReg[i]) == false){
                        error++;
                    }else{
                        error=0;
                        i=b+x+1;
                    }

                }
                if(error>0){
                    modelo.addRow(new Object[]{"error en: "+entrada.get(a).descripcion, ""});
                }

                b=b+x;
            }else{

                if( entrada.get(a).descripcion.equals(expReg[b]) == false){ //verificacion de solo un caso
                    error++;
                    modelo.addRow(new Object[]{"error en: "+entrada.get(a).token, "correccion: "+expReg[b]});
                }
                if(entrada.get(a).token.equals("{")){
                    //funcion para determinar operaciones anidadas
                    a=analizar_revursivo(vista, a+1);
                    a--;
                }
            }


            a++;
            b++;
            error=0;
        }
        if(b<expReg.length){
            modelo.addRow(new Object[]{"Falta completar", expReg[b]});
        }

        return a;


    }


    //definicion de la "exprecion regular"
    public String[] baseFor(){
        String[] expReg = new String[25];

        expReg[0]="Palabra Reservada-ciclo";
        expReg[1]="Caracter Agrupacion- Apertura-1";
        expReg[2]="variable-entero";
        expReg[3]="Palabra Reservada-separador";
        expReg[4]="variable-entero";


        expReg[5]="6"; //si hay un numero significa que son posibles entradas en esa posicion
        expReg[6]="Simbolo-Comp-menor o igual";
        expReg[7]="Simbolo-Comp-igual que";
        expReg[8]="Simbolo-Comp-diferete que";
        expReg[9]="Simbolo-Comp-mayor 0 igual";
        expReg[10]="Simbolo-Comp-mayor";
        expReg[11]="Simbolo-Comp-menor";


        expReg[12]="Constante-Numerica-Entera";
        expReg[13]="Palabra Reservada-separador";
        expReg[14]="variable-entero";

        expReg[15]="2";
        expReg[16]="Signo-Arit-suma";
        expReg[17]="Signo-Arit-menos";


        expReg[18]="2";
        expReg[19]="Signo-Arit-suma";
        expReg[20]="Signo-Arit-menos";


        expReg[21]="Caracter Agrupacion- Cierre-1";
        expReg[22]="Caracter Agrupacion- Apertura-2";
        expReg[23]="Caracter Agrupacion- Cierre-2";
        expReg[24]="Palabra Reservada-separador";

        return expReg;
    }

    //comprobacion a paso de entero
    public boolean coNum (String a){
        boolean result= true;

        try
        {
            int x= Integer.parseInt(a);
        }catch (NumberFormatException nfe){
            result= false;
        }

        return result;
    }

    public String[] baseIf(){
        String[] expReg = new String[14];

        expReg[0]="Palabra Reservada-Condicional";
        expReg[1]="Caracter Agrupacion- Apertura-1";
        expReg[2]="variable-entero";

        expReg[3]="6"; //si hay un numero significa que son posibles entradas en esa posicion
        expReg[4]="Simbolo-Comp-menor o igual";
        expReg[5]="Simbolo-Comp-igual que";
        expReg[6]="Simbolo-Comp-diferete que";
        expReg[7]="Simbolo-Comp-mayor 0 igual";
        expReg[8]="Simbolo-Comp-mayor";
        expReg[9]="Simbolo-Comp-menor";

        expReg[10]="variable-entero";

        expReg[11]="Caracter Agrupacion- Cierre-1";
        expReg[12]="Caracter Agrupacion- Apertura-2";
        expReg[13]="Caracter Agrupacion- Cierre-2";

        return expReg;
    }
    public String[] basePrint(){
        String[] expReg = new String[5];

        expReg[0]="Palabra Reservada-impresion";
        expReg[1]="Caracter Agrupacion- Apertura-1";
        expReg[2]="Cadena de texto";

        expReg[3]="Caracter Agrupacion- Cierre-1";
        expReg[4]="Palabra Reservada-separador";

        return expReg;
    }


}
