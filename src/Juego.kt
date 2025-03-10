package src

class Juego (val jugador1: Jugador, val jugador2: Jugador){

    private fun mostrarComienzo(){
        println("-----------------------------")
        println("**JUEGO DE CARTAS INICIADO**")
        println("-----------------------------")
    }

    private fun mostrarTurno(contador: Int, jugador: Jugador){
        println("Turno de ${jugador.nombre} (Turno $contador):")
    }

    private fun mostrarGanador(ganador: Jugador){
        println("¡El ganador del juego es ${ganador.nombre}, con un total de ${ganador.puntosSalud} puntos de vida!")
    }

    private fun comprobarQuienGanador(){
        if (jugador1.puntosSalud > 0 && jugador2.puntosSalud <= 0){
            mostrarGanador(jugador1)
        } else if (jugador2.puntosSalud > 0 && jugador1.puntosSalud <= 0){
            mostrarGanador(jugador2)
        }
    }

    private fun mostrarCartas(jugador: Jugador) {
        println("Cartas disponibles de ${jugador.nombre}:")
        jugador.mazoActivo?.cartas?.forEachIndexed { index, carta ->
            println("${index + 1}. ${carta.nombre} (${carta.descripcion})")
        }
    }

    private fun accionarCarta(carta: Carta, jugador: Jugador){
        when (carta) {
            is CartaAtaque -> {
                val oponente = if (jugador == jugador1) jugador2 else jugador1
                carta.atacar(oponente)
            }
            is CartaDefensa -> {
                carta.defender(jugador)
                jugador.mazoActivo?.cartas?.remove(carta)
                println("¡La carta de defensa '${carta.nombre}' ha sido eliminada del mazo de ${jugador.nombre}!")
            }
            is CartaCuracion -> {
                carta.curar(jugador)
            }
            is CartaEspecial -> {
                val oponente = if (jugador == jugador1) jugador2 else jugador1
                carta.eliminarCartaContrincante(oponente)
                jugador.mazoActivo?.cartas?.remove(carta)
                println("¡La carta especial '${carta.nombre}' ha sido eliminada del mazo de ${jugador.nombre}!")
            }
            else -> {
                println("Tipo de carta no reconocido.")
            }
        }
    }

    private fun elegirCarta(jugador: Jugador){
        val entrada = readlnOrNull()?.toIntOrNull()

        if (entrada == null || entrada !in 1..jugador.mazoActivo!!.cartas.size) {
            println("Opción inválida, perdiste el turno ${jugador.nombre}...")
            return
        }
        val cartaSeleccionada = jugador.mazoActivo!!.cartas[entrada - 1]

        accionarCarta(cartaSeleccionada, jugador)
    }

    private fun mostrarDatos(){
        println(jugador1)
        println(jugador2)
    }

    fun iniciar(){
        mostrarComienzo()
        var jugadorActivo = jugador1
        var contador = 1
        while (jugador1.puntosSalud > 0 && jugador2.puntosSalud > 0){
            mostrarTurno(contador, jugadorActivo)
            contador += 1
            mostrarCartas(jugadorActivo)
            elegirCarta(jugadorActivo)
            mostrarDatos()

            if (jugadorActivo == jugador1){
                jugadorActivo = jugador2
            } else {
                jugadorActivo = jugador1
            }
        }
        comprobarQuienGanador()
    }
}