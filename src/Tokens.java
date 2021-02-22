/**
 *
 * @author Alba
 */
public class Tokens {
    
    String[][] palabrasRes;
    int pR=12;
    String[][] agrupacion;
    int ag=4;
    String [][] operadoresArit;
    int opAr=5;
    String[][] operadoresComparacion;
    int opCom = 6;
    
    String[][] operadoresLogicos;
    int opLog = 2;
    
    String[][] var;
    int va = 0;
    
    String [] variables;

    Tokens() {
        this.palabrasRes = new String [pR][2];
        this.agrupacion = new String [ag][2];
        this.operadoresArit = new String[opAr][2];
        this.operadoresComparacion = new String[opCom][2];
        this.operadoresLogicos = new String[opLog][2];
        this.var = new String[100][3];
        Iniciar();
        
    }

    public void Iniciar(){

        this.palabrasRes[0][0]="if";
        this.palabrasRes[0][1]="Palabra Reservada-Condicional";
        
        this.palabrasRes[1][0]="for";
        this.palabrasRes[1][1]="Palabra Reservada-ciclo";
        
        this.palabrasRes[2][0]=";";
        this.palabrasRes[2][1]="Palabra Reservada-separador";

        this.palabrasRes[3][0]="System.out.println";
        this.palabrasRes[3][1]="Palabra Reservada-impresion";

        this.palabrasRes[4][0]="main";
        this.palabrasRes[4][1]= "Palabra Reservada-main";

        this.palabrasRes[5][0]="public";
        this.palabrasRes[5][1]= "Palabra Reservada-public";

        this.palabrasRes[6][0]="static";
        this.palabrasRes[6][1]= "Palabra Reservada-static";

        this.palabrasRes[7][0]="void";
        this.palabrasRes[7][1]= "Palabra Reservada-void";

        this.palabrasRes[8][0]="int";
        this.palabrasRes[8][1]= "Palabra Reservada-int";

        this.palabrasRes[9][0]="String";
        this.palabrasRes[9][1]= "Palabra Reservada-String";

        this.palabrasRes[10][0]="char";
        this.palabrasRes[10][1]= "Palabra Reservada-char";

        this.palabrasRes[11][0]="double";
        this.palabrasRes[11][1]= "Palabra Reservada-double";

        
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
        this.operadoresArit[2][1] = "Signo-Arit-multiplicacion";
        this.operadoresArit[3][0] = "/";
        this.operadoresArit[3][1] = "Signo-Arit-divison";
        this.operadoresArit[4][0] = "=";
        this.operadoresArit[4][1] = "Signo-Arit-asignacion";

        
        
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
        


       
        
    }

    public void setVariables(String nomb, String tipo, String valor){
        this.var[va][0] = nomb;
        this.var[va][1] = tipo;
        this.var[va][2] = valor;
        this.va++;
    }
    public void set_va(int a){
        this.va = a;
    }
    public  int get_va(){
        return this.va;
    }
    
    
}
