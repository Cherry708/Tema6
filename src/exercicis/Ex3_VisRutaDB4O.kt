package exercicis

import com.db4o.Db4oEmbedded

fun main(){
    val bd = Db4oEmbedded. openFile("Rutes.db4o")

    val patro =  Ruta(null,null,null)

    val llistaRutes = bd.queryByExample<Ruta>(patro)
    for (ruta in llistaRutes) {
        System.out.println(ruta.nom + ": " + ruta.llistaDePunts.size + " punts")
    }
    bd.close()
}