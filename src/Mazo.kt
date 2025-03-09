package src

class Mazo(
    val id: Int,
    var nombre: String,
    val cartas: MutableList<Carta> = mutableListOf()
) {

    companion object {
        private const val MAXIMO_CARTAS_MAZO = 3
        private const val MAXIMO_PUNTOS_ESPECIALIDAD = 12
    }

    private fun tipoCarta(carta: Carta): String {
        val tipo = when {
            carta is CartaAtaque -> "Ataque"
            carta is CartaDefensa -> "Defensa"
            carta is CartaCuracion -> "Curación"
            carta is CartaEspecial -> "Especial"
            else -> "Desconocido"
        }
        return tipo
    }

    private fun calcularTotalEspecialidad(): Int {
        return cartas.sumOf { it.especialidad }
    }

    private fun puedeAgregarCarta(nuevaEspecialidad: Int): Boolean {
        val totalEpecialidad = calcularTotalEspecialidad()
        val comprobacion =  (totalEpecialidad + nuevaEspecialidad)
        if (comprobacion <= MAXIMO_PUNTOS_ESPECIALIDAD){
            return true
        } else {
            return false
        }
    }

    fun renombrarMazo(nuevoNombre: String) {
        nombre = nuevoNombre
    }

    fun agregarCarta(carta: Carta) {
        if (cartas.size >= MAXIMO_CARTAS_MAZO) {
            println("No puedes superar el máximo de cartas ($MAXIMO_CARTAS_MAZO).")
            return
        }
        if (!puedeAgregarCarta(carta.especialidad)) {
            println("No puedes agregar la carta '${carta.nombre}'. Superarías el límite de $MAXIMO_PUNTOS_ESPECIALIDAD puntos de especialidad.")
            return
        }
        cartas.add(carta)
        println("Carta '${carta.nombre}' añadida a '$nombre' con éxito.")
    }

    fun eliminarCarta(carta: Carta) {
        cartas.removeIf { it.id == carta.id }
        println("Carta '${carta.nombre}' eliminada de '$nombre' con éxito.")
    }

    fun mostrarCartas() {
        println("Cartas dentro del mazo $id '$nombre':")
        for (carta in cartas) {
            println("La carta ${carta.id} '${carta.nombre}' (${carta.descripcion}) es de tipo ${tipoCarta(carta)} (Especialidad: ${carta.especialidad})")
        }
        println("Total de puntos de especialidad: ${calcularTotalEspecialidad()}")
    }

    fun modificarMazo(posicion: Int, carta: Carta) {
        if (posicion < 0) {
            println("La posición no puede ser negativa.")
            return
        }
        val especialidadActual = if (posicion < cartas.size)
            cartas[posicion].especialidad
        else
            0
        if (!puedeAgregarCarta(carta.especialidad - especialidadActual)) {
            println("No puedes sustituir la carta en la posición $posicion. Superarías el límite de $MAXIMO_PUNTOS_ESPECIALIDAD puntos de especialidad.")
            return
        }
        if (posicion < cartas.size) {
            cartas[posicion] = carta
            println("Carta en la posición $posicion reemplazada con éxito.")
        } else {
            if (cartas.size < MAXIMO_CARTAS_MAZO) {
                agregarCarta(carta)
            } else {
                println("No puedes superar el máximo de cartas ($MAXIMO_CARTAS_MAZO).")
            }
        }
    }

    override fun toString(): String {
        return "Mazo $id '$nombre' tiene ${cartas.size} cartas en él (Total de especialidad: ${calcularTotalEspecialidad()})."
    }

}