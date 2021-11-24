package exemples

import com.db4o.Db4oEmbedded
import com.db4o.ObjectContainer
import com.db4o.ObjectSet
import com.db4o.query.Predicate

import classesEmpleat.Empleat

class EmpleatsPerPoblacio(pobles: Array<String>) : Predicate<Empleat>() {
    val poblacions = pobles

    override
    fun match(emp: Empleat): Boolean {
        return (emp.adreca?.poblacio in poblacions)
    }
}
/*
class EmpleatsCPNull() : Predicate<Empleat>() {

    override
    fun match(emp: Empleat): Boolean {
        return (emp.adreca?.codipostal == null)
    }
}

fun main() {
    val bd = Db4oEmbedded.openFile("Empleats.db4o")

    val llista = bd.query(EmpleatsCPNUll())

    for (e in llista) {
        println(e.nom + " (" + e.adreca?.poblacio + ") --> " + "e.adreca?.codipostal")
    }
    bd.close()
}
 */


fun main() {
    val bd = Db4oEmbedded.openFile("Empleats.db4o")
    val pobl = arrayOf("Castelló", "Borriana")

    val llista = bd.query(EmpleatsPerPoblacio(pobl))

    for (e in llista) {
        println(e.nom + " (" + e.adreca?.poblacio + ")")
    }
    bd.close()
}

