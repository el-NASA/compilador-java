public class ArbolBinario {
    NodoArbol root;
    int size =0;
    public void addNode(int key, Nodo nodo, int llegada){
        NodoArbol newNode = new NodoArbol(key,nodo, llegada);
        size++;
        if (root==null){
            root = newNode;
        }else {
            NodoArbol focusNode = root;
            NodoArbol parent;

            while (true) {
                parent = focusNode;
                if(key<focusNode.key){
                    focusNode = focusNode.izquierdo;
                    if (focusNode==null){
                        parent.izquierdo = newNode;
                        return;
                    }
                } else {
                    focusNode=focusNode.derecho;
                    if(focusNode==null){
                        parent.derecho = newNode;
                        return;
                    }
                }

            }

        }
    }
    public int getSize(){return  size;}

    public void inOrderTraverseTree(NodoArbol focusNode){
        if (focusNode!=null){
            inOrderTraverseTree(focusNode.izquierdo);
            System.out.println(focusNode);

            inOrderTraverseTree(focusNode.derecho);

        }
    }

    boolean search(int key)  {
        root = search_Recursive(root, key);
        if (root!= null) {
            System.out.println(root.nodo.token);
            return true;
        }
        else
            return false;
    }
    //recursive insert function
    NodoArbol search_Recursive(NodoArbol root, int key)  {
        // Base Cases: root is null or key is present at root
        if (root==null || root.key==key)
            return root;
        // val is greater than root's key
        if (root.key > key)
            return search_Recursive(root.izquierdo, key);
        // val is less than root's key
        return search_Recursive(root.derecho, key);
    }


    /*public static void main(String[] args){
        ArbolBinario arbol = new ArbolBinario();
        Nodo nodo1 = new Nodo();
        nodo1.setToken("token 1");
        Nodo nodo2 = new Nodo();
        nodo2.setToken("token 2");
        Nodo nodo3 = new Nodo();
        nodo3.setToken("token 3");
        arbol.addNode(1, nodo1);
        arbol.addNode(1, nodo2);
        arbol.addNode(3, nodo3);

        arbol.inOrderTraverseTree(arbol.root);
    }*/

}

class NodoArbol{
    int key; // nivel
    Nodo nodo;
    int llegada = 0;


    NodoArbol izquierdo;
    NodoArbol derecho;

    NodoArbol(int key, Nodo nodo, int llegada){
        this.key = key;
        this.nodo = nodo;
        this.llegada = llegada;
    }

    public String toString(){
        return "nivel: "+key+" llegada: "+llegada+" token: "+nodo.token;
    }
}

