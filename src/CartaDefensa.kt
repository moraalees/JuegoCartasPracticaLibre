package src

class CartaDefensa(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 6){ "Los puntos de defensa deben ser de hasta 5 puntos." }
    }

}