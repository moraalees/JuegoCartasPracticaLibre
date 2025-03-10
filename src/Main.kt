package src

val listaCartas = mutableListOf<Carta>()
val listaMazos = mutableListOf<Mazo>()
val listaJugadores = mutableListOf<Jugador>()

fun mostrarReglas(){
    println("""
        **REGLAS DEL JUEGO**
        1. Cada jugador tiene un mazo de cartas.
        2. Las cartas pueden ser de ataque, defensa, curación o especiales.
        3. El objetivo es reducir los puntos de salud del oponente a 0.
        4. Las cartas especiales tienen efectos únicos y se eliminan después de usarse.
    """.trimIndent())
    println("ANTES DE JUGAR:")
    println("1. EL JUGADOR ANTES DE JUGAR DEBE CREARSE UN MAZO.")
    println("2. EL JUGADOR ANTES DE JUGAR DEBE CREARSE DOS INVENTARIOS.")
    println("3. EL JUGADOR ANTES DE JUGAR DEBE ESTABLECER UN MAZO ACTIVO.")
    println("4. ENTRE LAS 3 CARTAS DEL MAZO NO SE PUEDEN SUPERAR LOS 15 PUNTOS DE ESPECIALIDAD.")
}

fun mostrarMenu(){
    println("1. Crear carta.")
    println("2. Crear mazo.")
    println("3. Añadir cartas a un mazo.")
    println("4. Añadir cartas al inventario.")
    println("5. Añadir mazos al inventario.")
    println("6. Establecer mazo activo")
    println("7. Mostrar reglas.")
    println("8. Crear jugador.")
    println("9. Iniciar juego")
    println("10. Mostrar mazos creados.")
    println("11. Mostrar cartas creadas.")
    println("12. Mostrar jugadores.")
    println("13. Modificar mazos.")
    println("14. Salir del programa.")
}

fun mostrarTiposCartas(){
    println("1. Ataque")
    println("2. Defensa")
    println("3. Curación")
    println("4. Especial")
}

fun determinarCarta(entrada : Int?): Int?{
    return when (entrada) {
        1 -> {
            println("Elija la especialidad de su carta de ataque (1-10):")
            readlnOrNull()?.toIntOrNull()
        }
        2 -> {
            println("Elija la especialidad de su carta de defensa (1-5):")
            readlnOrNull()?.toIntOrNull()
        }
        3 -> {
            println("Elija la especialidad de su carta de curación (1-4):")
            readlnOrNull()?.toIntOrNull()
        }
        4 -> {
            println("Elija la especialidad de su carta especial (1-3):")
            readlnOrNull()?.toIntOrNull()
        }
        else -> {
            mostrarError("Opción inválida")
            null
        }
    }
}

fun crearCarta(): Carta? {
    println("Escriba el ID de su carta:")
    val id = readlnOrNull()?.toIntOrNull()?: 0
    println("Escriba el nombre de su carta (alias):")
    val nombre = readlnOrNull()?: "Desconocido"
    println("Describa su carta brevemente:")
    val descripcion = readlnOrNull() ?: "Desconocido"
    println("Ingrese la especialidad de la carta, donde:")
    mostrarTiposCartas()
    val entrada = readlnOrNull()?.toIntOrNull()

    val especialidad = determinarCarta(entrada)

    if (especialidad == null) {
        mostrarError("No se pudo determinar la especialidad de la carta")
        return null
    }

    val carta = when (entrada) {
        1 -> CartaAtaque(id, nombre, descripcion, especialidad)
        2 -> CartaDefensa(id, nombre, descripcion, especialidad)
        3 -> CartaCuracion(id, nombre, descripcion, especialidad)
        4 -> CartaEspecial(id, nombre, descripcion, especialidad)
        else -> {
            mostrarError("Tipo de carta no reconocido")
            return null
        }
    }
    return carta
}

fun crearMazo(): Mazo{
    println("Escriba el ID de su carta:")
    val id = readlnOrNull()?.toIntOrNull()?: 0
    println("Escriba el nombre de su carta (alias):")
    val nombre = readlnOrNull()?: "Desconocido"
    val cartas = mutableListOf<Carta>()

    val mazo = Mazo(id, nombre, cartas)
    return mazo
}

fun crearJugador(): Jugador {
    println("Escriba el ID de su carta:")
    val id = readlnOrNull()?.toIntOrNull() ?: 0
    println("Escriba el nombre de su carta (alias):")
    val nombre = readlnOrNull() ?: "Desconocido"

    val jugador = Jugador(id, nombre)
    return jugador
}

fun mostrarCartas() {
    listaCartas.forEachIndexed { indice, carta ->
        println("${indice + 1}. ${carta.nombre} (${carta.descripcion}).")
    }
}

fun mostrarMazos(){
    listaMazos.forEachIndexed { indice, mazo ->
        println("${indice + 1}. ${mazo.nombre} (${mazo.cartas.size} cartas)")
    }
}

fun mostrarJugadores(){
    listaJugadores.forEachIndexed { indice, jugador ->
        println("${indice + 1}. ${jugador.nombre} con el ID de ${jugador.id}")
    }
}

fun comprobarCartas(): Boolean{
    if (listaCartas.isEmpty()) {
        mostrarError("No hay cartas disponibles, crea alguna carta primero")
        return false
    } else {
        return true
    }
}

fun comprobarMazos(): Boolean {
    if (listaMazos.isEmpty()) {
        println("Advertencia: No hay mazos disponibles. Puedes crear uno nuevo.")
        return false
    }
    return true
}

fun comprobarJugadores(): Boolean{
    if (listaJugadores.isEmpty()) {
        mostrarError("No hay jugadores disponibles, crea alguno primero")
        return false
    } else {
        return true
    }
}

fun mostrarError(msj: String){
    println("*ERROR* $msj.")
}

fun escogerMazo(): Mazo? {
    if (listaMazos.isEmpty()) {
        println("Advertencia: No hay mazos disponibles. Puedes crear uno nuevo.")
        return null
    } else {
        println("Escoge un mazo:")
        mostrarMazos()
        val seleccionMazo = readlnOrNull()?.toIntOrNull()
        if (seleccionMazo == null || seleccionMazo !in 1..listaMazos.size) {
            mostrarError("Escogiste un número o entrada inválida")
            return null
        } else {
            val mazoSeleccionado = listaMazos[seleccionMazo - 1]
            return mazoSeleccionado
        }
    }
}

fun escogerCarta(): Carta? {
    if (comprobarCartas() == true) {
        println("Seleccione una carta:")
        mostrarCartas()
        val seleccionCarta = readlnOrNull()?.toIntOrNull()
        if (seleccionCarta == null || seleccionCarta !in 1..listaCartas.size) {
            mostrarError("Escogiste un número o entrada inválida")
            return null
        } else {
            val cartaSeleccionada = listaCartas[seleccionCarta - 1]
            return cartaSeleccionada
        }
    } else {
        mostrarError("No hay cartas creadas")
        return null
    }
}

fun escogerJugador(): Jugador? {
    if (comprobarJugadores() == true) {
        println("Seleccione un jugador:")
        mostrarJugadores()
        val seleccionJugador = readlnOrNull()?.toIntOrNull()
        if (seleccionJugador == null || seleccionJugador !in 1..listaJugadores.size) {
            mostrarError("Selección de jugador inválida")
            return null
        } else {
            val jugadorSeleccionado = listaJugadores[seleccionJugador - 1]
            return jugadorSeleccionado
        }
    } else {
        mostrarError("No hay jugadores creados")
        return null
    }
}


fun menuModificarMazo() {
    println("**MENÚ PARA MODIFICAR MAZO**")
    println("1. Agregar carta al mazo.")
    println("2. Eliminar carta del mazo.")
    println("3. Renombrar mazo.")
    println("4. Mostrar cartas del mazo.")
    println("5. Modificar una carta en el mazo.")
    println("6. Volver al menú principal.")
}

fun modificarMazo() {
    val mazoSeleccionado = escogerMazo()

    if (mazoSeleccionado != null){
        var salirSubmenu = false
        while (!salirSubmenu) {
            menuModificarMazo()
            val entradaSubmenu = readlnOrNull()?.toIntOrNull()
            when (entradaSubmenu) {
                1 -> {
                    val cartaSeleccionada = escogerCarta()
                    if (cartaSeleccionada != null){
                        mazoSeleccionado.agregarCarta(cartaSeleccionada)
                    } else {
                        mostrarError("Hubo un error al elegir la carta")
                    }
                }
                2 -> {
                    if (mazoSeleccionado.cartas.isEmpty()) {
                        println("El mazo '${mazoSeleccionado.nombre}' no tiene cartas.")
                    } else {
                        println("Seleccione una carta para eliminar del mazo '${mazoSeleccionado.nombre}':")
                        mazoSeleccionado.mostrarCartas()
                        val seleccionCarta = readlnOrNull()?.toIntOrNull()
                        if (seleccionCarta == null || seleccionCarta !in 1..mazoSeleccionado.cartas.size) {
                            println("*ERROR* Selección de carta inválida.")
                        } else {
                            val cartaSeleccionada = mazoSeleccionado.cartas[seleccionCarta - 1]
                            mazoSeleccionado.eliminarCarta(cartaSeleccionada)
                        }
                    }
                }
                3 -> {
                    println("Ingrese el nuevo nombre para el mazo '${mazoSeleccionado.nombre}':")
                    val nuevoNombre = readln()
                    mazoSeleccionado.renombrarMazo(nuevoNombre)
                    println("Mazo renombrado a '$nuevoNombre'.")
                }
                4 -> {
                    mazoSeleccionado.mostrarCartas()
                }
                5 -> {
                    if (mazoSeleccionado.cartas.isEmpty()) {
                        println("El mazo '${mazoSeleccionado.nombre}' no tiene cartas.")
                    } else {
                        println("Seleccione una carta para modificar en el mazo '${mazoSeleccionado.nombre}':")
                        mazoSeleccionado.mostrarCartas()
                        val seleccionCarta = readlnOrNull()?.toIntOrNull()
                        if (seleccionCarta == null || seleccionCarta !in 1..mazoSeleccionado.cartas.size) {
                            mostrarError("Selección de carta inválida")
                        } else {
                            val posicion = seleccionCarta - 1
                            val nuevaCarta = escogerCarta()
                            if (nuevaCarta != null){
                            mazoSeleccionado.modificarMazo(posicion, nuevaCarta)
                            } else {
                                mostrarError("Hubo un error al elegir la carta")
                            }
                        }
                    }
                }
                6 -> {
                    salirSubmenu = true
                    println("Volviendo al menú principal...")
                } else -> {
                mostrarError("Entrada inválida, ingrese del 1-6")
                }
            }
        }
    } else {
         mostrarError("Hubo un error al elegir el mazo")
    }
}


fun main() {
    var salir = false

    while (!salir) {
        mostrarMenu()
        val entrada = readlnOrNull()?.toIntOrNull()
        if (entrada == 1) {
            val carta = crearCarta()
            if (carta == null) {
                mostrarError("Hubo un error, no se creó la carta adecuadamente")
            } else {
                println(carta)
                listaCartas.add(carta)
            }
        } else if (entrada == 2) {
            val mazo = crearMazo()
            println(mazo)
            listaMazos.add(mazo)
        }else if (entrada == 3) {
            if (!comprobarCartas()) {
                mostrarError("No hay cartas disponibles, crea alguna carta primero")
            } else {
                val cartaSeleccionada = escogerCarta()
                if (cartaSeleccionada != null) {
                    val mazoSeleccionado = escogerMazo()
                    if (mazoSeleccionado != null) {
                        mazoSeleccionado.agregarCarta(cartaSeleccionada)
                    } else {
                        mostrarError("Hubo un error al elegir el mazo")
                    }
                } else {
                    mostrarError("Hubo un error al elegir la carta")
                }
            }
        } else if (entrada == 4) {
            val jugadorSeleccionado = escogerJugador()
                if (jugadorSeleccionado != null){
                    val cartaSeleccionada = escogerCarta()
                    if (cartaSeleccionada != null){
                        jugadorSeleccionado.cartasAlmacenadas.agregarElemento(cartaSeleccionada)
                    } else {
                        mostrarError("Hubo un error al elegir la carta")
                    }
                } else {
                    mostrarError("Hubo un error al elegir el jugador")
                }
        } else if (entrada == 5) {
            val jugadorSeleccionado = escogerJugador()
            if (jugadorSeleccionado != null){
                val mazoSeleccionado = escogerMazo()
                if (mazoSeleccionado != null){
                    jugadorSeleccionado.mazosAlmacenados.agregarElemento(mazoSeleccionado)

                } else {
                    mostrarError("Hubo un error al elegir el mazo")
                }
            } else {
                mostrarError("Hubo un error al elegir el jugador")
            }
        } else if (entrada == 6) {
            val jugadorSeleccionado = escogerJugador()
            if (jugadorSeleccionado != null){
                val mazoSeleccionado = escogerMazo()
                if (mazoSeleccionado != null){
                    jugadorSeleccionado.mazoActivo = mazoSeleccionado
                    println("Ahora el mazo activo de ${jugadorSeleccionado.nombre} activo ${mazoSeleccionado.id} '${mazoSeleccionado.nombre}' ")
                } else {
                    mostrarError("No se ha podido seleccionar el mazo activo")
                }
            } else {
                mostrarError("Hubo un error al elegir al jugador")
            }
        } else if (entrada == 7) {
            mostrarReglas()
        } else if (entrada == 8) {
            val jugador = crearJugador()
            println(jugador)
            listaJugadores.add(jugador)
        } else if (entrada == 9) {
            //TODO(LIBRERIAS EXTERNAS)
        } else if (entrada == 10) {
            mostrarMazos()
        } else if (entrada == 11) {
            mostrarCartas()
        } else if (entrada == 12){
            mostrarJugadores()
        } else if (entrada == 13) {
            modificarMazo()
        } else if (entrada == 14) {
            salir = true
            println("Fin del programa.")
        } else {
            mostrarError("Entrada inválida, ingrese del 1-14")
        }
    }
}