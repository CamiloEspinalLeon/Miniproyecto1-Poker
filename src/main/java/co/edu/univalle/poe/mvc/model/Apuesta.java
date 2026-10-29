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
        if (hayApuesta()) {
            return;                       // ya hay una apuesta activa: no se apuesta dos veces
        }
        dineroDisponible -= monto;
        dineroArriesgado += monto;
    }

    public int getDineroDisponible() {
        return dineroDisponible;
    }

    public int getDineroArriesgado() {
        return dineroArriesgado;
    }



    public boolean hayApuesta() {
        return dineroArriesgado > 0;
    }


    /** true si el saldo alcanza para la apuesta mínima (10). */
    public boolean puedeApostar() {
        return dineroDisponible >= 10;
    }

    public void perderApuesta() {
        dineroArriesgado = 0;
    }

    /** Empate: se devuelve la apuesta. */
    public void empatarApuesta() {
        dineroDisponible += dineroArriesgado;
        dineroArriesgado = 0;
    }

    public void ganarApuesta() {
        dineroDisponible += 2 * dineroArriesgado;
        dineroArriesgado = 0;
    }

    /** Victoria con Blackjack: se devuelve la apuesta y se gana 1,5 veces su valor (total 2,5x). */
    public void ganarConBlackjack() {
        dineroDisponible += dineroArriesgado * 5 / 2;   // la apuesta es múltiplo de 10: división exacta
        dineroArriesgado = 0;
    }

}
