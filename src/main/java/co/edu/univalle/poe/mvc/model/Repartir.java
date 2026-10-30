package co.edu.univalle.poe.mvc.model;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * conserva un mazo y la mano del jugador.
 */
public class Repartir {

    private static final int CARTAS_INICIALES = 2;

    private Mazo mazo;
    private LinkedList<Carta> mano;
    private LinkedList<Carta> manoCompu;
    int compuPuntaje ;
    int compuAs ;
    int jugadorPuntaje;
    int jugadorAs;

    /**
     * Prepara el mazo (de 52 cartas, ya mezclado) y reparte la mano inicial.
     */
    public Repartir() {
        mazo = new Mazo();
        mano = new LinkedList<>();
        manoCompu = new LinkedList<>();   // el computador espera sin cartas


        // Mano inicial: dos cartas al jugador
        for (int i = 0; i < CARTAS_INICIALES; i++) {
            mano.addLast(mazo.repartirCarta());
            jugadorPuntaje += mano.getLast().getValor();
            jugadorAs += mano.getLast().esAs() ? 1 : 0;
        }
        // Mano: dos cartas al computador

    }

    public void Plantar(){
        if (!manoCompu.isEmpty()) {
            return; // ya se plantó, no repartir de nuevo
        }
        System.out.println("El jugador se planto");

        for (int i = 0; i < CARTAS_INICIALES; i++) {
            recibirCompu(mazo.repartirCarta());
        }
        turnoCompu();                       // después de sus 2 cartas, decide si pide más
    }


    /** Agrega una carta a la mano del jugador y actualiza su puntaje. */
    private void recibirJugador(Carta carta) {
        mano.addLast(carta);
        jugadorPuntaje += carta.getValor();
        jugadorAs += carta.esAs() ? 1 : 0;
    }

    private void recibirCompu(Carta carta) {
        manoCompu.addLast(carta);
        compuPuntaje += carta.getValor();
        compuAs += carta.esAs() ? 1 : 0;
    }
    private void turnoCompu() {
        if (esBlackjackJugador()) {
            return;                               // la ronda se resuelve con sus 2 cartas iniciales
        }
        while (getPuntajeCompu() < 17 && mazo.hayCartasDisponibles()) {
            recibirCompu(mazo.repartirCarta());
        }
    }

    /**
     * Atiende la petición de una carta: consulta si hay disponibilidad,
     * solicita la carta al mazo y la agrega al final de la mano.
     * return true si se repartió una carta; false si el mazo ya estaba agotado
     */
    public boolean pedirCarta() {
        if (!mazo.hayCartasDisponibles()) {
            return false;
        }
        recibirJugador(mazo.repartirCarta());
        return true;
    }
    /** Los ases valen 11, pero si la mano se pasa de 21 se cuentan como 1. */
    private int ajustarAs(int puntaje, int ases) {
        while (puntaje > 21 && ases > 0) {
            puntaje -= 10;
            ases--;
        }
        return puntaje;
    }
    public String resolverRonda(Apuesta apuesta) {
        int jugador = getPuntajeJugador();
        int compu = getPuntajeCompu();
        boolean bjJugador = esBlackjackJugador();
        boolean bjCompu = esBlackjackCompu();
        boolean vymJugador = esVeinteYMedioJugador();
        boolean vymCompu = esVeinteYMedioCompu();
        String resultado;

        if (bjJugador && bjCompu) {                        // ambos Blackjack: empate
            resultado = "EMPATE (ambos tienen Blackjack)";
            apuesta.empatarApuesta();
        } else if (bjJugador) {                            // solo el jugador: victoria con Blackjack
            resultado = "¡BLACKJACK! ¡GANASTE!";
            apuesta.ganarConBlackjack();
        } else if (bjCompu) {                              // solo el computador
            resultado = "La computadora tiene Blackjack. PERDISTE";
            apuesta.perderApuesta();
        } else if (compu > 21) {                           // el computador se pasó
            resultado = "La computadora se pasó. ¡GANASTE!";
            apuesta.ganarApuesta();
        } else if (vymJugador && vymCompu) {               // ambos Veinte y medio: empate
            resultado = "EMPATE (ambos tienen Veinte y medio)";
            apuesta.empatarApuesta();
        } else if (vymJugador) {                           // Veinte y medio del jugador
            if (compu == 21) {                             // solo pierde contra 21
                resultado = "Veinte y medio pierde contra 21. PERDISTE";
                apuesta.perderApuesta();
            } else {                                       // gana a cualquier mano menor de 21
                resultado = "¡VEINTE Y MEDIO! Supera cualquier mano menor de 21. ¡GANASTE!";
                apuesta.ganarApuesta();
            }
        } else if (vymCompu) {                             // Veinte y medio del computador
            if (jugador == 21) {
                resultado = "¡GANASTE! Tu 21 supera el Veinte y medio de la computadora.";
                apuesta.ganarApuesta();
            } else {
                resultado = "La computadora tiene Veinte y medio. PERDISTE";
                apuesta.perderApuesta();
            }
        } else if (jugador > compu) {                      // victoria ordinaria
            resultado = "¡GANASTE!";
            apuesta.ganarApuesta();
        } else if (jugador < compu) {                      // derrota
            resultado = "PERDISTE";
            apuesta.perderApuesta();
        } else {                                           // mismo puntaje
            resultado = "EMPATE";
            apuesta.empatarApuesta();
        }

        return resultado;
    }


    // ---------- Jugadas especiales (solo miran las DOS primeras cartas) ----------

    /** Blackjack: un as y una carta de valor 10 (10, J, Q o K) en exactamente dos cartas. */
    public static boolean esBlackjack(LinkedList<Carta> m) {
        if (m.size() != 2) {
            return false;
        }
        Carta primera = m.get(0);
        Carta segunda = m.get(1);
        return (primera.esAs() && segunda.getValor() == 10) || (segunda.esAs() && primera.getValor() == 10);
    }

    /** Veinte y medio: una figura (J, Q o K) y un 4, en cualquier orden*/
    public static boolean esVeinteYMedio(LinkedList<Carta> m) {
        if (m.size() != 2) {
            return false;
        }
        Carta a = m.get(0);
        Carta b = m.get(1);
        return (a.esFigura() && b.esCuatro()) || (b.esFigura() && a.esCuatro());
    }

    public boolean esBlackjackJugador() {
        return esBlackjack(mano);
    }
    public boolean esBlackjackCompu() {
        return esBlackjack(manoCompu);
    }
    public boolean esVeinteYMedioJugador() {
        return esVeinteYMedio(mano);
    }
    public boolean esVeinteYMedioCompu() {
        return esVeinteYMedio(manoCompu);
    }

    public int getPuntajeJugador() {
        return ajustarAs(jugadorPuntaje, jugadorAs);
    }

    public int getPuntajeCompu() {
        return ajustarAs(compuPuntaje, compuAs);
    }


    /**
     * return true si todavía quedan cartas por repartir en el mazo
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
     * return copia de la mano, en el orden en que fueron recibidas las cartas
     */
    public LinkedList<Carta> getMano() {
        return new LinkedList<>(mano);
    }

    /** Copia de la mano del computador (vacía hasta que el jugador se plante). */
    public LinkedList<Carta> getManoCompu() {
        return new LinkedList<>(manoCompu);
    }
}



