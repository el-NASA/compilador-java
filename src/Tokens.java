/**
 *
 * @author Alba
 */
public class Tokens {
    
    String[][] palabrasRes;
    int pR=3;
    String[][] agrupacion;
    int ag=4;
    String [][] operadoresArit;
    int opAr=4;
    String[][] operadoresComparacion;
    int opCom = 6;
    
    String[][] operadoresLogicos;
    int opLog = 2;
    
    String[][] var;
    int va = 3;
    
    String [] variables;

    Tokens() {
        this.palabrasRes = new String [pR][2];
        this.agrupacion = new String [ag][2];
        this.operadoresArit = new String[opAr][2];
        this.operadoresComparacion = new String[opCom][2];
        this.operadoresLogicos = new String[opLog][2];
        this.var = new String[va][3];
        Iniciar();
        
    }

    public void Iniciar(){
        
        this.palabrasRes[0][0]="if";
        this.palabrasRes[0][1]="Palabra Reservada-Condicional";
        
        this.palabrasRes[1][0]="for";
        this.palabrasRes[1][1]="Palabra Reservada-siclo";
        
        this.palabrasRes[2][0]=";";
        this.palabrasRes[2][1]="Palabra Reservada-separador";
        
        
        this.agrupacion[0][0]="(";
        this.agrupacion[0][1]="Caracter Agrupacion- Apertura-1";
        
        this.agrupacion[1][0]=")";
        this.agrupacion[1][1]="Caracter Agrupacion- Cierre-1";
        
        this.agrupacion[2][0]="{";
        this.agrupacion[2][1]="Caracter Agrupacion- Apertura-2";
        
        this.agrupacion[3][0]="}";
        this.agrupacion[3][1]="Caracter Agrupacion- Cierre-2";
        
        this.operadoresArit[0][0] = "+";
        this.operadoresArit[0][1] = "Signo-Arit-suma";
        this.operadoresArit[1][0] = "-";
        this.operadoresArit[1][1] = "Signo-Arit-menos";
        this.operadoresArit[2][0] = "*";
        this.operadoresArit[2][1] = "Signo-Arit-multiplicación";
        this.operadoresArit[3][0] = "/";
        this.operadoresArit[3][1] = "Signo-Arit-divisón";

        
        
        this.operadoresComparacion[0][0] = "<=";
        this.operadoresComparacion[0][1] = "Simbolo-Comp-menor o igual";
        
        this.operadoresComparacion[1][0] = "==";
        this.operadoresComparacion[1][1] = "Simbolo-Comp-igual que";
        
        this.operadoresComparacion[2][0] = "!=";
        this.operadoresComparacion[2][1] = "Simbolo-Comp-diferete que";
        
        this.operadoresComparacion[3][0] = ">=";
        this.operadoresComparacion[3][1] = "Simbolo-Comp-mayor 0 igual";
        
        this.operadoresComparacion[4][0] = ">";
        this.operadoresComparacion[4][1] = "Simbolo-Comp-mayor";
        
        this.operadoresComparacion[5][0] = "<";
        this.operadoresComparacion[5][1] = "Simbolo-Comp-menor";
        
        
        this.operadoresLogicos[0][0] = "||";
        this.operadoresLogicos[0][1] = "Simbolo-Log- O";
        
        this.operadoresLogicos[1][0] = "&&";
        this.operadoresLogicos[1][1] = "Simbolo-Log- y";
        
        this.var[0][0] = "i";
        this.var[0][1] = "variable-entero";
        this.var[0][2] = "12";
        
        this.var[1][0] = "e";
        this.var[1][1] = "variable-entero";
        this.var[1][2] = "20";
        
        this.var[2][0] = "a";
        this.var[2][1] = "var-string";
        this.var[2][2] = "hola";
       
        
    }
    
    
    
}
