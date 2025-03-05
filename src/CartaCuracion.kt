package src

class CartaCuracion(
    id: Int,
    nombre: String,
    descripcion: String,
    val puntosCuracion: Int
) : Carta(id, nombre, descripcion) {

}