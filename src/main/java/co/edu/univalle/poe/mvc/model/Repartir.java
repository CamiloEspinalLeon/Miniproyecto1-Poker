package co.edu.univalle.poe.mvc.model;

import java.util.LinkedList;

/**
 * conserva un mazo y la mano del jugador.
 */
public class Repartir {

    private static final int CARTAS_INICIALES = 2;

    private Mazo mazo;
    private LinkedList<Carta> mano;

    /**
     * Prepara el mazo (de 52 cartas, ya mezclado) y reparte la mano inicial.
     */
    public Repartir() {
        mazo = new Mazo();
        mano = new LinkedList<>();

        // Mano inicial: dos cartas al jugador
        for (int i = 0; i < CARTAS_INICIALES; i++) {
            mano.addLast(mazo.repartirCarta());
        }
    }

    /**
     * Atiende la petición de una carta: consulta si hay disponibilidad,
     * solicita la carta al mazo y la agrega al final de la mano.
     * @return true si se repartió una carta; false si el mazo ya estaba agotado
     */
    public boolean pedirCarta() {
        if (!mazo.hayCartasDisponibles()) {
            return false;
        }
        Carta carta = mazo.repartirCarta();
        mano.addLast(carta);
        return true;
    }

    /**
     * @return true si todavía quedan cartas por repartir en el mazo
     */
    public boolean hayCartasDisponibles() {
        return mazo.hayCartasDisponibles();
    }

    /**
     * return cantidad de cartas que tiene el jugador en la mano
     */
    public int getCantidadCartas() {
        return mano.size();
    }

    /**
     * return cantidad de cartas que aún quedan en el mazo
     */
    public int getCartasDisponibles() {
        return mazo.getCartasDisponibles();
    }

    /**
     * Devuelve una COPIA de la lista para que desde afuera no se pueda
     * modificar la mano original (encapsulamiento).
     *
     * return copia de la mano, en el orden en que fueron recibidas las cartas
     */
    public LinkedList<Carta> getMano() {
        return new LinkedList<>(mano);
    }
}
}
