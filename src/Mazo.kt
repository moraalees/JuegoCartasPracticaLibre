package src

class Mazo(
    val id: Int,
    val nombre: String,
    val cartas: MutableList<Carta>
) {

    fun agregarCarta(carta: Carta){
        cartas.add(carta)
    }

}