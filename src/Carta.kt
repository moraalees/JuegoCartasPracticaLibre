package src

abstract class Carta(
    val id: Int,
    var nombre: String,
    val descripcion: String,
    val especialidad: Int
) {
    fun renombrarCarta(nuevoNombre: String){
        nombre = nuevoNombre
    }

    override fun toString(): String {
        return "La carta $id '$nombre' ($descripcion) tiene $especialidad puntos en su especialidad."
    }
}