package src

class CartaCuracion(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 6){ "Los puntos de curación deben ser de hasta 10 puntos." }
    }

}