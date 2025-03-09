package src

class CartaDefensa(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 6){ "Los puntos de defensa deben ser de hasta 5 puntos." }
    }

    override fun atacar(jugador: Jugador) {
        println("Esta carta no puede atacar.")
    }

    override fun defender(jugador: Jugador){
        jugador.puntosDefensa += especialidad
        println("¡La defensa del jugador $id ha subido a ${jugador.puntosDefensa} puntos!")
    }

    override fun curar(jugador: Jugador) {
        println("Esta carta no puede curar.")
    }

    override fun eliminarCartaContrincante(jugador: Jugador) {
        println("Esta carta no puede hacer cosas especiales.")
    }



}