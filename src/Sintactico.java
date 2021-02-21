import java.util.ArrayList;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class Sintactico {

    ArrayList<Nodo> entrada = new ArrayList<Nodo>();

    public Sintactico(ArrayList<Nodo> e) {
        this.entrada=e;

    }


    public int analizar_revursivo(JTable vista, int entero1, Vista vista2){
        DefaultTableModel modelo;
        modelo = (DefaultTableModel) vista.getModel();
        modelo.addRow(new Object[]{"", ""});

        String[] expReg;

        int a=entero1;//aumento en la entrada
        int b=0;//aumento en la exprecionReg
        int error =0;


        switch(entrada.get(a).descripcion){
            case "Palabra Reservada-ciclo":
                expReg=baseFor();
                break;

            case "Palabra Reservada-Condicional":
                expReg=baseIf();
                break;

            case "Palabra Reservada-impresion":
                expReg=basePrint();
                break;

                case "Palabra Reservada-int":
                    expReg=baseDefinicion();
                    break;

            case "Palabra Reservada-String":
                expReg=baseDefinicion();
                break;

            case "Palabra Reservada-char":
                expReg=baseDefinicion();
                break;

            case "Palabra Reservada-double":
                expReg=baseDefinicion();
                break;

            case "variable-entero":
                expReg=baseAritmetica();
                break;

            default:
                expReg=baseMain();
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
                        vista2.getJTextPane().setText(vista2.getJTextPane().getText()+" "+ entrada.get(a).token);
                    }

                }
                if(error>0){
                    modelo.addRow(new Object[]{"error en: "+entrada.get(a).descripcion, ""});
                    vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"!!! "+ entrada.get(a).token+" !!!");
                }

                b=b+x;
            }else{

                if( entrada.get(a).descripcion.equals(expReg[b]) == false){ //verificacion de solo un caso
                    error++;
                    modelo.addRow(new Object[]{"error en: "+entrada.get(a).token, "correccion: "+expReg[b] + "Token #: "+ a});
                    if(entrada.get(a).token.equals("{"))
                        vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"!!!"+ entrada.get(a).token+" !!!\n");
                    else
                        vista2.getJTextPane().setText(vista2.getJTextPane().getText()+"!!! "+ entrada.get(a).token+" !!!");
                }
                else{
                    if(entrada.get(a).token.equals("{"))
                        vista2.getJTextPane().setText(vista2.getJTextPane().getText()+ entrada.get(a).token+"\n");
                    else
                    vista2.getJTextPane().setText(vista2.getJTextPane().getText()+" "+ entrada.get(a).token);
                }
                if(entrada.get(a).token.equals("{") || entrada.get(a).token.equals("}") || entrada.get(a).token.equals(";") ){
                    if(a+1<entrada.size()){
                        if(comparacion(entrada, a+1)){
                            //funcion para determinar operaciones anidadas
                            a=analizar_revursivo(vista, a+1, vista2);
                            a--;
                        }
                    }


                }
            }
            a++;
            b++;
            error=0;
        }
        if(b<expReg.length){
            modelo.addRow(new Object[]{"Falta completar", expReg[b]});
        }
        //ArbolSintactico arbol = new ArbolSintactico(entrada);
        //arbol.constuir_arbol();
        return a;
    }

    //Función que determina que expresión regular usar a continuación
    public boolean comparacion(ArrayList<Nodo> lista, int entero1){
        switch (lista.get(entero1).descripcion){
            case "Palabra Reservada-ciclo":
                return true;

            case "Palabra Reservada-Condicional":
                return true;

            case "Palabra Reservada-impresion":
                return true;

            case "Palabra Reservada-int":
                System.out.println("bandera int");
                return true;

            case "Palabra Reservada-String":
                return true;

            case "Palabra Reservada-char":
                return true;

            case "Palabra Reservada-double":
                return true;

            case "variable-entero":
                if(lista.get(entero1+1).descripcion.equals("Signo-Arit-asignacion")){
                    return true;
                }
                return false;


            default:
                return false;

        }
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

    //Definición expresión regular para ciclo for
    public String[] baseFor(){
        String[] expReg = new String[24];

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

        return expReg;
    }

    //Definición expresión regular para if
    public String[] baseIf(){
        String[] expReg = new String[20];

        expReg[0]="Palabra Reservada-Condicional";
        expReg[1]="Caracter Agrupacion- Apertura-1";
        expReg[2]="3";
        expReg[3]="variable-entero";
        expReg[4]="Constante-Numerica-Entera";
        expReg[5]="Constante-Numerica-Decimal";

        expReg[6]="6"; //si hay un numero significa que son posibles entradas en esa posicion
        expReg[7]="Simbolo-Comp-menor o igual";
        expReg[8]="Simbolo-Comp-igual que";
        expReg[9]="Simbolo-Comp-diferete que";
        expReg[10]="Simbolo-Comp-mayor 0 igual";
        expReg[11]="Simbolo-Comp-mayor";
        expReg[12]="Simbolo-Comp-menor";

        expReg[13]="3";
        expReg[14]="variable-entero";
        expReg[15]="Constante-Numerica-Entera";
        expReg[16]="Constante-Numerica-Decimal";

        expReg[17]="Caracter Agrupacion- Cierre-1";
        expReg[18]="Caracter Agrupacion- Apertura-2";
        expReg[19]="Caracter Agrupacion- Cierre-2";

        return expReg;
    }

    //Definición expresión regular para print
    public String[] basePrint(){
        String[] expReg = new String[5];

        expReg[0]="Palabra Reservada-impresion";
        expReg[1]="Caracter Agrupacion- Apertura-1";
        expReg[2]="Cadena de texto";

        expReg[3]="Caracter Agrupacion- Cierre-1";
        expReg[4]="Palabra Reservada-separador";

        return expReg;
    }

    //Definición expresión regular para main
    public String[] baseMain(){
        String[] expReg = new String[8];

        expReg[0]="Palabra Reservada-public";
        expReg[1]="Palabra Reservada-static";
        expReg[2]="Palabra Reservada-void";
        expReg[3]="Palabra Reservada-main";
        expReg[4]="Caracter Agrupacion- Apertura-1";
        expReg[5]="Caracter Agrupacion- Cierre-1";
        expReg[6]="Caracter Agrupacion- Apertura-2";
        expReg[7]="Caracter Agrupacion- Cierre-2";

        return expReg;
    }

    //Definición expresión regular para operaciones aritmeticas
    public String[] baseAritmetica(){
        String[] expReg = new String[14];

        expReg[0]="variable-entero";
        expReg[1]="Signo-Arit-asignacion";
        expReg[2]="2";
        expReg[3]="variable-entero";
        expReg[4]="Constante-Numerica-Entera";
        expReg[5]="4";
        expReg[6]="Signo-Arit-suma";
        expReg[7]="Signo-Arit-menos";
        expReg[8]="Signo-Arit-multiplicacion";
        expReg[9]="Signo-Arit-divison";
        expReg[10]="2";
        expReg[11]="variable-entero";
        expReg[12]="Constante-Numerica-Entera";
        expReg[13]="Palabra Reservada-separador";

        return expReg;
    }

    public String[] baseDefinicion(){
        String[] expReg = new String[12];

        expReg[0]="4";
        expReg[1]="Palabra Reservada-int";
        expReg[2]="Palabra Reservada-String";
        expReg[3]="Palabra Reservada-char";
        expReg[4]="Palabra Reservada-double";
        expReg[5]="variable";
        expReg[6]="Signo-Arit-asignacion";
        expReg[7]="3";
        expReg[8]="Constante-Numerica-Entera";
        expReg[9]="Constante-Numerica-Decimal";
        expReg[10]="Cadena de texto";
        expReg[11]="Palabra Reservada-separador";

        return expReg;
    }


}
