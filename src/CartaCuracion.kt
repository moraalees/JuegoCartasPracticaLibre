package src

class CartaCuracion(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 6){ "Los puntos de curación deben ser de hasta 10 puntos." }
    }

    override fun atacar(jugador: Jugador) {
        println("Esta carta no puede atacar.")
    }

    override fun defender(jugador: Jugador) {
        println("Esta carta no puede defender.")
    }

    override fun curar(jugador: Jugador){
        jugador.puntosSalud += especialidad
        println("¡El jugador $id se ha curado y ahora su vida es de ${jugador.puntosSalud} puntos!")
    }

    override fun eliminarCartaContrincante(jugador: Jugador) {
        println("Esta carta no puede hacer cosas especiales.")
    }

}