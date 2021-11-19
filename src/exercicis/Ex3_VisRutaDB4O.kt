package exercicis

import com.db4o.Db4oEmbedded

fun main(){
    val bd = Db4oEmbedded. openFile("Rutes.db4o")

    val patro =  Ruta(null,null,null)

    val llistaRutes = bd.queryByExample<Ruta>(patro)
    for (ruta in llistaRutes) {
        System.out.println("Nom: " + ruta.nom + ".\n desnivell: " + ruta.desnivell
                + ".\n desnivell acumulat: " + ruta.desnivellAcumulat
                + ".\n llista de punts:\n " + ruta.llistaDePunts
        )
    }
    bd.close()
}