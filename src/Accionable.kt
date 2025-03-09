package src

interface Accionable {
    fun atacar(jugador: Jugador)
    fun defender(jugador: Jugador)
    fun curar(jugador: Jugador)
    fun eliminarCartaContrincante(jugador: Jugador)
}