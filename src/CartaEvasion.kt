package src

class CartaEvasion(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 4){ "Los puntos de evasión deben ser de hasta 3 puntos." }
    }

}