package src

class CartaEspecial(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 4){ "El número debe ser de hasta 3 casillas." }
    }

    override fun atacar(jugador: Jugador) {
        println("Esta carta no puede atacar.")
    }

    override fun defender(jugador: Jugador) {
        println("Esta carta no puede defender.")
    }

    override fun curar(jugador: Jugador) {
        println("Esta carta no puede curar.")
    }

    override fun eliminarCartaContrincante(jugador: Jugador){
        val posicionCartaABorrar = especialidad - 1
        val cartaEliminada = jugador.mazoActivo!!.cartas.removeAt(posicionCartaABorrar)
        println("Carta '${cartaEliminada.nombre}' eliminada del mazo activo '${jugador.mazoActivo!!.nombre}' en la posición $posicionCartaABorrar.")
    }
}