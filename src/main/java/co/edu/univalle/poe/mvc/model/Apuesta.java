package co.edu.univalle.poe.mvc.model;

public class Apuesta {
    // Atributos:
    private int dineroDisponible;
    private int dineroArriesgado;

    // Metodo Constructor:
    public Apuesta (){
        dineroDisponible = 1000;
        dineroArriesgado = 0;

    }
    // Metodo para realizar la apuesta
    public void realizarApuesta(int monto) {
        dineroDisponible -= monto;
        dineroArriesgado += monto;
    }

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public int getDineroArriesgado() {
        return dineroArriesgado;
    }

    public void perderApuesta() {
        dineroArriesgado = 0;
    }

    public void ganarApuesta() {
        dineroDisponible += 2 * dineroArriesgado;
        dineroArriesgado = 0;
    }
}
