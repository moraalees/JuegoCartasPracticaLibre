package src

class CartaEspecial(
    id: Int,
    nombre: String,
    descripcion: String,
    val numeroABorrar: Int
) : Carta(id, nombre, descripcion){

}