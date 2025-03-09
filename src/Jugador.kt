package src

class Jugador(
    val id: Int,
    var nombre: String,
    val cartasAlmacenadas: Inventario<Carta>,
    val mazosAlmacenados: Inventario<Mazo>
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

    fun renombrarJugador(nuevoNombre: String){
        nombre = nuevoNombre
    }

    fun atacar(carta: Carta, jugador: Jugador){
        jugador.puntosSalud = jugador.puntosSalud - (carta.especialidad - puntosDefensa)
        println("¡El jugador ${jugador.id} ha sido atacado y su vida ahora es de $puntosSalud puntos de salud!")
    }

    fun defender(carta: Carta){
        puntosDefensa += carta.especialidad
        println("¡La defensa del jugador $id ha subido a $puntosDefensa puntos!")
    }

    fun curar(carta: Carta){
        puntosSalud += carta.especialidad
        println("¡El jugador $id se ha curado y ahora su vida es de $puntosSalud puntos!")
    }

    fun eliminarCarta(carta: Carta, jugador: Jugador){

    }

}