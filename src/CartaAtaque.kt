package src

class CartaAtaque(
    id: Int,
    nombre: String,
    descripcion: String,
    especialidad: Int
) : Carta(id, nombre, descripcion, especialidad) {

    init{
        require(especialidad < 11){ "Los puntos de ataque deben ser de hasta 10 puntos." }
    }

}