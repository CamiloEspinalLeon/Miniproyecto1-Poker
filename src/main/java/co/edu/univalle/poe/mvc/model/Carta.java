package co.edu.univalle.poe.mvc.model;

//Creación de la clase carta (El molde de las cartas)
public class Carta {
    //Atributos:
    private String valor;
    private String palo;


    //Metodos para distinguir si es figura (Para veinte y medio)
    public boolean esFigura() {
        return valor.equals("J") || valor.equals("Q") || valor.equals("K");
    }

    public boolean esCuatro() {
        return valor.equals("4");
    }

    //Metodo constructor, (para asignar en esas dos variables, los dos valores que se asignen):
    public Carta(String valor, String palo){
        this.valor = valor;
        this.palo = palo;
    }

    //Getters (para consultar los datos desde otras clases)
    public int getValor() {
        if (esAs()) return 11;
        if (valor.equals("J") || valor.equals("Q") || valor.equals("K")) return 10;
        return Integer.parseInt(valor);
    }
    public boolean esAs() {
        return valor.equals("As");
    }

    public String getNombreImagen() {
        return valor + palo + ".png";
    }

    public String getPalo() {
        return palo;
    }
}
