/*
 * Compilador lenguaje Java
 * Hecho por: Jordy Pineda - 20172020119
 * Daniel Rodriguez - 20172020009
 * Universidad Distrital Francisco José de Caldas
 */

public class Main {
    /*
    public static void main(){
        for(i;i<10;i++){
            print("hola");
        }
        if(i<i){
            i=i+1;
            int y = 3;
            int c = 4;
            i=3-4;
        }
    }*/
    //se puede cambair el  "<" o la variable, solo esta i,e

    public static void main(String[] args) {
        Lexico c= new Lexico();
        Vista ventana= new Vista(c);
        ventana.setVisible(true);
       
        
    }

}
