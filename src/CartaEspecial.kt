package src

class CartaEspecial(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 6){ "El número debe ser de hasta 5 casillas." }
    }

}