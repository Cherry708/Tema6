package exercicis

import com.db4o.Db4oEmbedded
import util.bd.GestionarRutesBD

fun main(){
    /*
    Instanciamos objeto de la clase GestionarRutesBD,
    esta clase cuenta con el metodo listado que
    devuelve una lista de rutas tras hacer una consulta en la
    base de datos Rutes.sqlite
     */
    val rutesSql = GestionarRutesBD()
    val llistaRutes = rutesSql.llistat()

    val rutesDb4o = Db4oEmbedded.openFile("Rutes.db4o")

    for (ruta in llistaRutes){
        rutesDb4o.store(ruta)
    }
    rutesDb4o.close()
    rutesSql.close()
}