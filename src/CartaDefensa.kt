package src

class CartaDefensa(
    id: Int,
    nombre: String,
    descripcion: String,
    val puntosDefensa: Int
) : Carta(id, nombre, descripcion) {

}