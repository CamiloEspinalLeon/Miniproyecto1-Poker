package co.edu.univalle.poe.mvc.model;
import java.util.Random;

public class Mazo {

    private static final String[] VALORES = {
            "As", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"
    };
    private static final String[] PALOS = {"P", "C", "D", "T"};

    private Carta[] cartas;
    private int siguienteCarta;

    /**
     * Deja el mazo completo (52 cartas), mezclado y listo para repartir.
     */
    public Mazo() {
        cartas = new Carta[VALORES.length * PALOS.length];
        siguienteCarta = 0;
        crearCartas();
        mezclar();
    }

    /**
     * Crea las 52 combinaciones de valor y palo, sin comodines ni repeticiones.
     */
    private void crearCartas() {
        int posicion = 0;
        for (String palo : PALOS) {
            for (String valor : VALORES) {
                cartas[posicion] = new Carta(valor, palo);
                posicion++;
            }
        }
    }

    /**
     * Cambia aleatoriamente el orden de las cartas (algoritmo Fisher-Yates).
     */
    private void mezclar() {
        Random random = new Random();
        for (int i = cartas.length - 1; i > 0; i--) {
            int j = random.nextInt(i + 1);
            Carta temporal = cartas[i];
            cartas[i] = cartas[j];
            cartas[j] = temporal;


        }
        System.out.println("Se mezclo el mazo exitosamente");
    }

    /**
     * Entrega la siguiente carta y avanza el índice.
     *
     * throws IllegalStateException si el mazo está agotado
     */
    public Carta repartirCarta() {
        // 1. Comprobar que existe una carta disponible.
        if (siguienteCarta >= cartas.length) {
            throw new IllegalStateException("Mazo agotado");
        }

        // 2. Obtener la carta señalada.
        Carta carta = cartas[siguienteCarta];

        // 3. Avanzar para el próximo reparto.
        siguienteCarta++;

        // 4. Entregar la carta obtenida.
        return carta;
    }

    /**
     * @return true si todavía es posible repartir una carta
     */
    public boolean hayCartasDisponibles() {
        return siguienteCarta < cartas.length;
    }

    /**
     * @return cantidad de cartas que aún quedan por repartir
     */
    public int getCartasDisponibles() {
        return cartas.length - siguienteCarta;
    }
}
