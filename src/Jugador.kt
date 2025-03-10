package src

class Jugador(
    val id: Int,
    var nombre: String,
    val cartasAlmacenadas: Inventario<Carta> = Inventario(),
    val mazosAlmacenados: Inventario<Mazo> = Inventario()
) {
    var puntosSalud: Int = 20
        set(value){
            require(puntosSalud <= 20){ "Los puntos de salud no pueden superar los 20." }
            field = value
        }

    var puntosDefensa: Int = 0
        set(value){
            require(puntosDefensa <= 5){ "Los puntos de defensa no pueden superar los 5." }
            field = value
        }

    var mazoActivo: Mazo? = null

    fun seleccionarMazoBatalla(mazo: Mazo) {
        if (mazosAlmacenados.contiene(mazo)) {
            mazoActivo = mazo
            println("Mazo ${mazo.id} '${mazo.nombre}' seleccionado con éxito.")
        } else {
            println("No puedes seleccionar un mazo antes de añadirlo al inventario del jugador.")
        }
    }

    fun agregarMazo(mazo: Mazo){
        mazosAlmacenados.agregarElemento(mazo)
        println("Mazo agregado con éxito.")
    }

    fun agregarCarta(carta: Carta){
        cartasAlmacenadas.agregarElemento(carta)
        println("Carta agregada con éxito.")
    }

    fun renombrarJugador(nuevoNombre: String){
        nombre = nuevoNombre
    }

    override fun toString(): String {
        return "Jugador $id '$nombre' tiene $puntosSalud puntos de vida."
    }
}