package util.bd

import util.bd.Coordenades
import util.bd.PuntGeo
import util.bd.Ruta

fun main(args: Array<String>) {
    // Creació del gestionador
    val gRutes = GestionarRutesBD()

    // Inserció d'una nova Ruta
    val noms = arrayOf( "Les Useres", "Les Torrocelles", "Lloma Bernat", "Xodos (Molí)", "El Marinet", "Sant Joan")
    val latituds = arrayOf(40.158126, 40.196046, 40.219210, 40.248003, 40.250977, 40.251221)
    val longituds = arrayOf(-0.166962, -0.227611, -0.263560, -0.296690, -0.316947, -0.354052)

    val punts = arrayListOf<PuntGeo>()
    for (i in 0 until noms.size){
        punts.add(PuntGeo(noms[i], Coordenades(latituds[i], longituds[i])))
    }

    //println(punts.size)
    //Añadimos una tercera ruta
    //gRutes.inserir(Ruta("Pelegrins de Les Useres",896,1738, punts))

    /*
    IMPORTANTE:
    Siempre eliminamos la ruta cuyo num_r = 4
     */
    //gRutes.esborrar(4)

    // Llistat de totes les rutes
    println("--- Llistat ---")
    for (r in gRutes.llistat())
        println(r?.mostrarRuta())

    // Buscar una ruta determinada
    println("--- Busqueda ruta ---")
    val r2 = gRutes.buscar(2)
    if (r2 != null)
        println(r2.mostrarRuta())

    gRutes.close()
}