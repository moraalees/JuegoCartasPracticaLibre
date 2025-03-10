package src

class Inventario<T>(
    private val elementos: MutableList<T> = mutableListOf()
) {

    private fun estaVacio(): Boolean {
        return elementos.isEmpty()
    }

    fun contiene(elemento: T): Boolean {
        return elementos.contains(elemento)
    }

    fun agregarElemento(elemento: T) {
        if (elemento in elementos){
            println("El elemento ya está en el inventario.")
        } else {
            elementos.add(elemento)
            println("Elemento agregado al inventario correctamente.")
        }
    }

    fun eliminarElemento(elemento: T) {
        if (elementos.remove(elemento)) {
            println("Elemento eliminado del inventario correctamente")
        } else {
            println("El elemento no está en el inventario")
        }
    }

    fun mostrarElementos() {
        if (estaVacio()) {
            println("El inventario está vacío.")
        } else {
            println("Elementos en el inventario:")
            elementos.forEach { elemento ->
                var contador = 1
                println("$contador. $elemento")
                contador++
            }
        }
    }

}