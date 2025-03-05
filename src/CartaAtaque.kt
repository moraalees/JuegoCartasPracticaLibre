package src

class CartaAtaque(
    id: Int,
    nombre: String,
    descripcion: String,
    val puntosAtaque: Int
) : Carta(id, nombre, descripcion) {

}