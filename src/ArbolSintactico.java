/* Desarrollado por Daniel Rodriguez
 * fuentes: https://www.seas.upenn.edu/~cit596/notes/dave/bnf2.html
 * https://www.javatpoint.com/parse-tree-and-syntax-tree
 * https://youtu.be/4m7ubrdbWQU
 * https://youtu.be/bxpc9Pp5pZM
 */

import java.util.ArrayList;
import java.util.Vector;

public class ArbolSintactico {
    private Vector <Vector> arbol = new Vector<Vector>();
    private ArrayList<Nodo> entrada;

    public ArbolSintactico(ArrayList<Nodo> e){
        this.entrada = e;
    }
    //<for loop> ::= for ( <expression> ; <expression>; <expression> ) <statement>
    public void constuir_arbol(){
        verificar_for(); // Construye rama izquierda del arbol
        verificar_declaracion(); // Construye rama derecha del arbol
        imprimir_arbol();
    }

    public void verificar_for(){ // Añade palabra reservada for al arbol
        Vector<String> token = new Vector<>(); // Declara nuevo vector que guarda valor de Token
        token.add(this.entrada.get(0).getToken()); // Añade el valor del primer token
        arbol.add(token); // Añade el primer subarbol izquierdo
    }

    public void verificar_declaracion(){ //Revisa la declaración del for
        Vector <Vector> subarbol = new Vector<>();  //Subarbol derecho
        Vector <String> token = new Vector<>();  // Token de la declaración
        for(int i=1; i<entrada.size(); i++) //Junta de nuevo las partes de la delcaración
            token.add(entrada.get(i).getToken());

        subarbol.add(token); // Añade como primer elemento la delcaración
        //System.out.println("tam subarbol: "+subarbol.get(0).get(1).getClass());
        arbol.add(subarbol); //añade subarbol izquierdo -> 2
        verificar_expresiones(subarbol);
        verificar_cuerpo(subarbol);
    }
    // Revisa la parte de ( <expression> ; <expression>; <expression> )
    public void verificar_expresiones(Vector<Vector> subarbol){
        Vector<Vector> subarbol_izq = new Vector<>();
        Vector<String> token_izq = new Vector<>();
        for(int i=1;i<10;i++)
            token_izq.add((String) subarbol.get(0).get(i));

        subarbol_izq.add(token_izq); // añade las expresiones
        subarbol.add(subarbol_izq); // añade subarbol_izq al subarbol -> 3
        verificar_condicionales(subarbol_izq);
    }
    // la parte de <statement>
    public void verificar_cuerpo(Vector<Vector> subarbol){
        Vector<String> token = new Vector<>();
        Vector<Vector> subarbol_der = new Vector<>();
        token.add((String) subarbol.get(0).get(11));
        token.add((String) subarbol.get(0).get(12));
        subarbol_der.add(token);
        subarbol.add(subarbol_der); //añade cuerpo al subarbol der -> 5
    }
    // Revisa la parte de <expression> ; <expression>; <expression>
    public void verificar_condicionales(Vector<Vector> subarbol){
        String condicional="";
        Vector <String> token = new Vector<>();
        System.out.println(subarbol.firstElement().size());
        for(int i=0; i<subarbol.firstElement().size();i++)
            condicional+= subarbol.firstElement().get(i);
        for(String retval: condicional.split(";")) {
            token.add(retval);
        }
        subarbol.add(token); // añade subarbol izq al sub arbol -> 4
    }
    public void imprimir_arbol(){
        System.out.println("imprimiendo arbol ...");
        System.out.println("raiz");
        System.out.println("|\t|");
        System.out.println("|\t|- "+arbol.get(1).get(1));
        Vector<Vector>subarbol = (Vector)arbol.get(1).get(2);
        System.out.println("|\t|\t|- "+subarbol.get(0));
        System.out.println("|\t|\t|\t|- "+subarbol.get(0).get(1));
        System.out.println("|\t|\t|\t|- "+subarbol.get(0).get(0));
        subarbol = (Vector)arbol.get(1).get(1);
        System.out.println("|\t|-"+subarbol.get(1));
        System.out.println("|\t|\t|- "+subarbol.get(1).get(0));
        System.out.println("|\t|\t|- "+subarbol.get(1).get(1));
        System.out.println("|\t|\t|- "+subarbol.get(1).get(2));
        System.out.println("|- "+arbol.get(0).get(0));
    }

}
