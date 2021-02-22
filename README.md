# compilador-java
Compilador para lenguaje de programación Java escrito en Java (un poco redundante).
El objetivo de este proyecto es servir como ejercicio acádemico para
comprender las diferentes fases que posee un compilador para funcionar.

El trabajo fue hecho por los estudiantes:
* Jordy Esteban Pineda: 20172020119
* Daniel Alejandro Rodriguez: 20172020009

El compilador es capz de detectar errores de tipo:
* Léxico: errores de tipado.
* Sintactico: Orden en el que deben ingresarse las declaraciones.
* Semantico: Declaraciones de variables repetidas o usadas en segmentos donde no
han sido declaradas; verificación de declaraciones con valores asignados.
  
Las funciones del lenguaje soportadas son:
* for
* if
* System.out.println()
* Declaraciones de variables int, char, String y double


Se puede utilizar el siguiente código de ejemplo para probrar el funcionamiento
del software.

```
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
```

Y su salida es:

![vista](./img/vista.png "modelo funcional")