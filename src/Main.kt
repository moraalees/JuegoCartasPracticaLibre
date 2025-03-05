package src

fun main(){
    val carta1 = CartaAtaque(4, "Dragón", "Dragón escupefuego", 3)
    val mazo = Mazo(1, "Cartas Tochas", mutableListOf<Carta>(carta1))

    mazo.mostrarCartas()
}