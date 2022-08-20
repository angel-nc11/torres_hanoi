/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author ann
 */
public class Pila {

    private int i =0;
    private Nodo cabeza;
    
    
     public int getI() {
        return i;
    }

    
    public Nodo getCabeza() {
        return cabeza;
    }

    public void Push(Nodo n) {
        i++;
        if (cabeza == null) {
            cabeza = n;
        } else {
            n.setAbajo(cabeza);
            cabeza.setArriba(n);

            cabeza = n;
        }
    }
    public void Pop(){
        if(i >0){
            i--;
            
            cabeza = cabeza.getAbajo();
        }
    }
    
    public String Peek(){
        return cabeza.getDato();
    }


}
