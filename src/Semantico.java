import java.util.ArrayList;

public class Semantico {
    ArbolBinario arbolSintactico;
    String[][] var;
    int va = 0;
    int cantTokens;
    NodoArbol nodoAux;
    ArrayList <Integer> rangos;
    Vista vista;

    Semantico(ArbolBinario arbol, int caTok, ArrayList<Integer> rangos, Vista vista2){
        this.arbolSintactico = arbol;
        //this.arbolSintactico.inOrderTraverseTree(arbol.root);
        this.var = new String[10][5];
        this.cantTokens = caTok;
        this.rangos = rangos;
        this.vista = vista2;

    }

    public void addVariable(String nombre, String descr, String valor,String nivel, String llegada){
        this.var[va][0] = nombre;
        this.var[va][1] = descr;
        this.var[va][2] = valor;
        this.var[va][3] = nivel;
        this.var[va][4] = llegada;
        va++;
    }
    public void inOrderTraverseTreeSem(NodoArbol focusNode){
        if (focusNode!=null){
            inOrderTraverseTreeSem(focusNode.izquierdo);
            System.out.println(focusNode);
            if(focusNode.nodo.token.equals("int")||focusNode.nodo.token.equals("String")||
                    focusNode.nodo.token.equals("char")||focusNode.nodo.token.equals("double")){
                NodoArbol nodoAux2 = focusNode.derecho;
                String nombre = nodoAux2.nodo.token;
                nodoAux2=nodoAux2.derecho;
                nodoAux2=nodoAux2.derecho;
                String valor = nodoAux2.nodo.token;
                String descripcion = "Palabra Reservada-"+focusNode.nodo.token;
                addVariable(nombre,descripcion,valor,""+focusNode.key,""+focusNode.llegada);
            }


            inOrderTraverseTreeSem(focusNode.derecho);

        }
    }


    public void verificacionVariables(){
        System.out.println("retorno de nodos");
        nodoAux = arbolSintactico.root;

        inOrderTraverseTreeSem(this.arbolSintactico.root);
        System.out.println("impresion tabla variables");
        for (int i=0;i<va;i++){
            //System.out.println("nombre: "+this.var[i][0]+" descr: "+ this.var[i][1]+ " valor: "+this.var[i][2]+
            //        " nivel:" +this.var[i][3]+ " llegada: "+this.var[i][4]);
            vista.getJTextArea2().setText(vista.getJTextArea2().getText()+"\n->"+ "nombre: "+this.var[i][0]+
                    " descr: "+ this.var[i][1]+ " valor: "+this.var[i][2]+" nivel:" +this.var[i][3]+ " llegada: "+
                        this.var[i][4]);

            for(int j=i-1;j>=0;j--){
                int x = getRango(Integer.parseInt(var[i][3]));
                if(Integer.parseInt(var[j][3])>=x||Integer.parseInt(var[j][3])==1){
                    if (this.var[i][0].equals(this.var[j][0])){
                        int entero1=Integer.parseInt(this.var[i][4]);
                        int entero2 = Integer.parseInt(this.var[j][4]);
                        if (entero2<entero1){
                            //System.out.println("La variable: "+this.var[i][0]+" con valor: "+this.var[i][2]+" ya fue " +
                            //        "declarada, se repite: "+this.var[j][0]+"= "+this.var[j][2]);
                            vista.getJTextArea2().setText(vista.getJTextArea2().getText()+"\n->"+ "La variable: "+
                                            this.var[i][0]+" con valor: "+this.var[i][2]+" ya fue declarada, " +
                                                "se repite: "+this.var[j][0]+"= "+this.var[j][2]);

                            eliminarVariable(i);
                        }
                    }
                }

            }

        }



    }

    public int getRango(int nivel){
        for (int i = 0;i<rangos.size();i++){
            if(rangos.get(i)==nivel){
                return rangos.get(i);
            }else if (rangos.get(i)>nivel){
                return rangos.get(i-1);
            }if(i==rangos.size()-1){
                return rangos.get(i);
            }
        }return 1;
    }

    public void eliminarVariable(int j){
        System.out.println("La variable: "+this.var[j][0]+"="+this.var[j][2]+" ha sido ELIMINADA");
        vista.getJTextArea2().setText(vista.getJTextArea2().getText()+"\n->"
                +"La variable: "+this.var[j][0]+"="+this.var[j][2]+" ha sido ELIMINADA");

        for(int i=j;i<va-1;i++){
            this.var[i] = this.var[i+1];
        }
        this.va -=1;
    }

    public boolean compararacionNivel(){

        return true;
    }



}
