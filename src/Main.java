/*
 * Compilador lenguaje Java
 * Hecho por: Jordy Pineda - 20172020119
 * Daniel Rodriguez - 20172020009
 * Universidad Distrital Francisco José de Caldas
 */

public class Main {
    /*
    public static void main(){

	int i=1;
	for(i;i<10;i++){
		int a =3;
            		System.out.println("hola");
		if(i<4){
			double w =6.9;
			char j="j";
		}

        	}

	int w = 3;

        	if(a<i){
		int w = 3;
           		i=i+1;
            		int y = 3;
            		int c = 4;
            		i=3-4;

		if(i<4){

			char j="d";

		}
		char h="d";
		char h="d";

        	}
}
    */
    //se puede cambiar el  "<" o la variable, solo esta i,e

    public static void main(String[] args) {
        Lexico c= new Lexico();
        Vista ventana= new Vista(c);
        ventana.setVisible(true);
       
        
    }

}
