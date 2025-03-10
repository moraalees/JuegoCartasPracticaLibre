package src

fun mostrarReglas(){
    println("1. EL JUGADOR ANTES DE JUGAR DEBE CREARSE UN MAZO.")
    println("2. EL JUGADOR ANTES DE JUGAR DEBE CREARSE UN INVENTARIO.")
    println("3. EL JUGADOR ANTES DE JUGAR DEBE ESTABLECER UN MAZO ACTIVO.")
}

fun main(){
    mostrarReglas()
    val carta1 = CartaAtaque(4, "Dragón", "Dragón escupefuego", 3)
    val carta2 = CartaDefensa(4, "mariposa", "Dragón escupefuego", 4)
    val carta3 = CartaCuracion(4, "Hada", "Dragón escupefuego", 1)
    val carta4 = CartaAtaque(4, "Bomba", "Dragón escupefuego", 2)
    val carta5 = CartaAtaque(4, "Pokemon", "Dragón escupefuego", 3)
    val carta6 = CartaEspecial(4, "Mago", "Dragón escupefuego", 2)
    val mazo1 = Mazo(1, "Cartas Tochas", mutableListOf<Carta>(carta1, carta2, carta3))
    val mazo2 = Mazo(2, "Cartas Tochas", mutableListOf<Carta>(carta4, carta5, carta6))

    val inventario1 = Inventario<Carta>(mutableListOf<Carta>(carta1, carta2, carta3))
    val inventario2 = Inventario<Carta>(mutableListOf<Carta>(carta4, carta5, carta6))

    val inventario3 = Inventario<Mazo>(mutableListOf<Mazo>(mazo1))
    val inventario4 = Inventario<Mazo>(mutableListOf<Mazo>(mazo2))

    val jugador1 = Jugador(1, "uno", inventario1, inventario3)
    val jugador2 = Jugador(2, "dos", inventario2, inventario4)
    jugador1.mazoActivo = mazo1
    jugador2.mazoActivo = mazo2

    val juego = Juego(jugador1, jugador2)
    juego.iniciar()

}