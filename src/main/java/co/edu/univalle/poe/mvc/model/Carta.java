package co.edu.univalle.poe.mvc.model;

//Creación de la clase carta (El molde de las cartas)
public class Carta {
    //Atributos:
    private String valor;
    private String palo;

    //Metodo constructor, (para asignar en esas dos variables, los dos valores que se asignen):
    public Carta(String valor, String palo){
        this.valor = valor;
        this.palo = palo;
    }

    //Getters (para consultar los datos desde otras clases)
    public String getValor() {
        return valor;
    }

    public String getPalo() {
        return palo;
    }
}
