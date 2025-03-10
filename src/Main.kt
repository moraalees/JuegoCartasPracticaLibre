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
    println("4. Crear inventario de cartas.")
    println("5. Crear inventario de mazos.")
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
            println("Opción inválida.")
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
        println("*Error* No se pudo determinar la especialidad de la carta.")
        return null
    }

    val carta = when (entrada) {
        1 -> CartaAtaque(id, nombre, descripcion, especialidad)
        2 -> CartaDefensa(id, nombre, descripcion, especialidad)
        3 -> CartaCuracion(id, nombre, descripcion, especialidad)
        4 -> CartaEspecial(id, nombre, descripcion, especialidad)
        else -> {
            println("Tipo de carta no reconocido.")
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

fun comprobarMazosCartas(): Boolean{
    if (listaCartas.isEmpty()) {
        println("No hay cartas disponibles. Crea alguna carta primero.")
        return false
    } else if (listaMazos.isEmpty()) {
        println("No hay mazos disponibles. Crea algún mazo primero.")
        return false
    } else {
        return true
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
                println("Hubo un error, no se creo la carta adecuadamente.")
            } else {
                println(carta)
                listaCartas.add(carta)
            }
        } else if (entrada == 2) {
            val mazo = crearMazo()
            listaMazos.add(mazo)
        } else if (entrada == 3) {
            if (!comprobarMazosCartas()){
                println("Redirigiendo...")
            } else {
                println("Seleccione una carta:")
                mostrarCartas()
                val seleccionCarta = readlnOrNull()?.toIntOrNull()
                if (seleccionCarta == null || seleccionCarta !in 1..listaCartas.size) {
                    println("*ERROR* Escogiste un número o entrada inválida.")
                } else {
                    val cartaSeleccionada = listaCartas[seleccionCarta - 1]

                    println("Seleccione un mazo para añadir la carta:")
                    mostrarMazos()

                    val seleccionMazo = readlnOrNull()?.toIntOrNull()
                    if (seleccionMazo == null || seleccionMazo !in 1..listaMazos.size) {
                        println("*ERROR* Escogiste un número o entrada inválida.")
                    } else {
                        val mazoSeleccionado = listaMazos[seleccionMazo - 1]

                        mazoSeleccionado.agregarCarta(cartaSeleccionada)
                        println("Carta ${cartaSeleccionada.id} '${cartaSeleccionada.nombre}' añadida al mazo '${mazoSeleccionado.nombre}'.")
                    }
                }
            }
        } else if (entrada == 4) {
            if (listaJugadores.isEmpty()) {
                println("No hay jugadores creados. Crea un jugador primero.")
            } else {
                println("Seleccione un jugador:")
                mostrarJugadores()
                val seleccionJugador = readlnOrNull()?.toIntOrNull()
                if (seleccionJugador == null || seleccionJugador !in 1..listaJugadores.size) {
                    println("*ERROR* Selección de jugador inválida.")
                } else {
                    val jugadorSeleccionado = listaJugadores[seleccionJugador - 1]

                    println("Elige la carta que quieres añadir al inventario de cartas de ${jugadorSeleccionado.nombre}:")
                    mostrarCartas()
                    val seleccionCarta = readlnOrNull()?.toIntOrNull()
                    if (seleccionCarta == null || seleccionCarta !in 1..listaCartas.size) {
                        println("*ERROR* Escogiste un número o entrada inválida.")
                    } else {
                        val cartaSeleccionada = listaCartas[seleccionCarta - 1]
                        jugadorSeleccionado.cartasAlmacenadas.agregarElemento(cartaSeleccionada)
                        println("Carta '${cartaSeleccionada.nombre}' añadida al inventario de cartas de ${jugadorSeleccionado.nombre}.")
                    }
                }
            }

        } else if (entrada == 5) {
            if (listaJugadores.isEmpty()) {
                println("No hay jugadores creados. Crea un jugador primero.")
            } else {
                println("Seleccione un jugador:")
                listaJugadores.forEachIndexed { index, jugador ->
                    println("${index + 1}. ${jugador.nombre}")
                }
                val seleccionJugador = readlnOrNull()?.toIntOrNull()
                if (seleccionJugador == null || seleccionJugador !in 1..listaJugadores.size) {
                    println("*ERROR* Selección de jugador inválida.")
                } else {
                    val jugadorSeleccionado = listaJugadores[seleccionJugador - 1]

                    println("Elige el mazo que quieres añadir al inventario de mazos de ${jugadorSeleccionado.nombre}:")
                    listaMazos.forEachIndexed { index, mazo ->
                        println("${index + 1}. ${mazo.nombre} (${mazo.cartas.size} cartas)")
                    }
                    val seleccionMazo = readlnOrNull()?.toIntOrNull()
                    if (seleccionMazo == null || seleccionMazo !in 1..listaMazos.size) {
                        println("*ERROR* Escogiste un número o entrada inválida.")
                    } else {
                        val mazoSeleccionado = listaMazos[seleccionMazo - 1]
                        jugadorSeleccionado.mazosAlmacenados.agregarElemento(mazoSeleccionado)
                        println("Mazo '${mazoSeleccionado.nombre}' añadido al inventario de mazos de ${jugadorSeleccionado.nombre}.")
                    }
                }
            }
        } else if (entrada == 6) {
            if (listaJugadores.isEmpty()){
                println("No hay jugadores creados...")
            } else {
                println("Escoge el jugador:")
                mostrarJugadores()
                val seleccionJugador = readlnOrNull()?.toIntOrNull()
                if (seleccionJugador == null || seleccionJugador !in 1..listaJugadores.size) {
                    println("*ERROR* Escogiste un número o entrada inválida.")
                } else {
                    val jugadorSeleccionado = listaJugadores[seleccionJugador - 1]

                    println("Ahora escoge un mazo para ponerlo activo:")
                    mostrarMazos()
                    val seleccionMazo = readlnOrNull()?.toIntOrNull()
                    if (seleccionMazo == null || seleccionMazo !in 1..listaMazos.size) {
                        println("*ERROR* Escogiste un número o entrada inválida.")
                    } else {
                        val mazoSeleccionado = listaMazos[seleccionMazo - 1]
                        jugadorSeleccionado.mazoActivo = mazoSeleccionado
                    }
                }
            }
        } else if (entrada == 7) {
                mostrarReglas()
        } else if (entrada == 8) {

        } else if (entrada == 9) {

        } else if (entrada == 10) {
            mostrarMazos()
        } else if (entrada == 11) {
            mostrarCartas()
        } else if (entrada == 12){
            mostrarJugadores()
        } else if (entrada == 13) {
            //submenú
        } else if (entrada == 14) {
            salir = true
            println("Fin del programa.")
        } else {
            println("Entrada inválida, ingrese del 1-9.")
        }
    }
}