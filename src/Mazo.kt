package src

class Mazo(
    val id: Int,
    var nombre: String,
    val cartas: MutableList<Carta>
) {

    companion object{
        private const val MAXIMO_CARTAS_MAZO = 5
    }

    private fun tipoCarta(carta: Carta): String{
        val tipo = when {
            carta is CartaAtaque -> "Carta Ataque"
            carta is CartaDefensa -> "Carta Defensa"
            carta is CartaCuracion -> "Carta Curación"
            carta is CartaEspecial -> "Carta Especial"
            carta is CartaEvasion -> "Carta Evasión"
            else -> "Desconocido"
        }
        return tipo
    }

    fun renombrarMazo(nuevoNombre : String){
        if (nombre == nuevoNombre){
            println("Deberías ponerle un nuevo nombre...")
        } else {
            println("Mazo '$nombre' se llama ahora $nuevoNombre")
            nombre = nuevoNombre
        }
    }

    fun agregarCarta(carta: Carta){
        if (cartas.size < MAXIMO_CARTAS_MAZO){
            cartas.add(carta)
            println("Carta ${carta.nombre} añadida a $nombre con éxito.")
        } else {
            println("No puedes superar el máximo de cartas ($MAXIMO_CARTAS_MAZO).")
        }
    }

    fun eliminarCarta(carta: Carta){
        cartas.removeIf { it.id == carta.id }
        println("Carta ${carta.nombre} eliminada de $nombre con éxito.")
    }

    fun mostrarCartas(){
        println("Cartas dentro del mazo '$nombre':")
        for (carta in cartas){
            println("La carta ${carta.id} '${carta.nombre}' (${carta.descripcion}) es de tipo ${tipoCarta(carta)}")
        }
    }

    override fun toString(): String {
        return "Mazo $id '$nombre' tiene ${cartas.size} cartas en él."
    }


}