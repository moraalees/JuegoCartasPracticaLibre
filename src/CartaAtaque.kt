package src

class CartaAtaque(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init{
        require(especialidad in 1..10){ "Los puntos de ataque deben ser de hasta 10 puntos." }
    }

    override fun atacar(jugador: Jugador){
        if (jugador.puntosSalud - (especialidad - jugador.puntosDefensa) > 20){
            println("¡El jugador no ha recibido daño!")
        } else {
            jugador.puntosSalud = jugador.puntosSalud - (especialidad - jugador.puntosDefensa)
            println("¡El jugador ${jugador.id} ha sido atacado y su vida ahora es de ${jugador.puntosSalud} puntos de salud!")
        }
    }

    override fun defender(jugador: Jugador) {
        println("Esta carta no puede defender.")
    }

    override fun curar(jugador: Jugador) {
        println("Esta carta no puede curar.")
    }

    override fun eliminarCartaContrincante(jugador: Jugador) {
        println("Esta carta no puede hacer cosas especiales.")
    }

}