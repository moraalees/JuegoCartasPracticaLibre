package src

class CartaEspecial(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init {
        require(especialidad < 4){ "El número debe ser de hasta 3 casillas." }
    }

}