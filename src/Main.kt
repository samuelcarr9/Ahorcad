fun Quitartildes(palabra: String): String {
    return palabra
        .replace("á", "a")
        .replace("é", "e")
        .replace("í", "i")
        .replace("ó", "o")
        .replace("ú", "u")
        .replace("Á", "a")
        .replace("É", "e")
        .replace("Í", "i")
        .replace("Ó", "o")
        .replace("Ú", "u")
}

fun main() {
    val rm = ReproductorMidi("Reto.mid")
    val mensajeinicial = println("Bienvenido al ahorcado, espera un momento...")
    Thread.sleep(5000)
    val mensajereto = println("Hoy tendrás que adivinar el nombre de un país europeo")
    Thread.sleep(2500)
    val lista: List<String> = listOf(
        "Albania", "Alemania", "Andorra", "Armenia", "Austria",
        "Azerbaiyán", "Bélgica", "Bielorrusia", "Bosnia", "Bulgaria",
        "Chipre", "Ciudad del Vaticano", "Croacia", "Dinamarca", "Eslovaquia",
        "Eslovenia", "España", "Estonia", "Finlandia", "Francia",
        "Georgia", "Grecia", "Hungría", "Irlanda", "Islandia",
        "Italia", "Kazajistán", "Letonia", "Liechtenstein", "Lituania",
        "Luxemburgo", "Malta", "Moldavia", "Mónaco", "Montenegro",
        "Noruega", "Países Bajos", "Polonia", "Portugal", "Reino Unido",
        "República Checa", "República de Macedonia del Norte", "Rumanía", "Rusia", "San Marino",
        "Serbia", "Suecia", "Suiza", "Turquía", "Ucrania"
    )
    var palabrareal = lista.random()
    var sintildes = Quitartildes(palabrareal).lowercase()
    var palabrasecreta = palabrareal.map { char -> if (char == ' ') ' ' else '*' }.joinToString("")
    println("La palabra secreta es: $palabrasecreta")
    var fallos = 0
    var letrasusadas = mutableSetOf<Char>()
    while (fallos < 7 && palabrasecreta.contains('*')) {
        var intento = readln()
        var letra = Quitartildes(intento).lowercase()[0]
        if (letrasusadas.contains(letra)) {
            println("Esa letra ya la has utilizado, utiliza otra")
            continue
        }
        letrasusadas.add(letra)
        if (sintildes.contains(letra)) {
            var result = ""
            for (i in palabrareal.indices) {
                if (sintildes[i] == letra) {
                    result += palabrareal[i]
                } else {
                    result += palabrasecreta[i]
                }
            }
            palabrasecreta = result
            Thread.sleep(500)
            println("La palabra secreta es: $palabrasecreta")
        } else {
            fallos++
            if (fallos < 7) {
                var mensajefallos = "Llevas $fallos fallos, puedes fallar hasta 6 veces, a la séptima pierdes"
                Thread.sleep(250)
                println(mensajefallos)
                Thread.sleep(1000)
                DibujoAhorcado.dibujar(fallos)
            }
        }
    }
    Thread.sleep(500)
    if (!palabrasecreta.contains('*')) { //Si no contiene asteriscos, significa que hemos descubierto todas las letras, hemos ganado
        Thread.sleep(500)
        println("¡Enhorabuena, has ganado!")
    } else {
        Thread.sleep(500)
        println("Lo siento, has perdido...")
        Thread.sleep(1000)
        println("La palabra era $palabrareal")
        Thread.sleep(1000)
        DibujoAhorcado.dibujar(7)
    }
    rm.cerrar()
}