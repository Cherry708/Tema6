package exercicis

import java.awt.EventQueue
import java.awt.GridLayout
import java.awt.FlowLayout
import javax.swing.JFrame
import javax.swing.JPanel
import javax.swing.BoxLayout
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JTextField
import javax.swing.JTable
import javax.swing.JScrollPane

import util.bd.Ruta
import util.bd.PuntGeo
import com.db4o.Db4oEmbedded

class FinestraComplet : JFrame() {
    var llistaRutesGlobal = ArrayList<Ruta>()
    var indexGlobal = 0
    val bd = Db4oEmbedded.openFile("Rutes.db4o")

    // Declaració de la Base de Dades

    val rNom = JTextField(15)
    val rDesn = JTextField(5)
    val rDesnAcum = JTextField(5)
    val punts = JTable(1, 3)
    val primer = JButton(" << ")
    val anterior = JButton(" < ")
    val seguent = JButton(" > ")
    val ultim = JButton(" >> ")
    val tancar = JButton("Tancar")

    init {
        defaultCloseOperation = JFrame.EXIT_ON_CLOSE
        setTitle("JDBC: Visualitzar Rutes Complet")
        setLayout(GridLayout(0, 1))

        val p_prin = JPanel()
        p_prin.setLayout(BoxLayout(p_prin, BoxLayout.Y_AXIS))
        val panell1 = JPanel(GridLayout(0, 2))
        panell1.add(JLabel("Ruta:"))
        rNom.setEditable(false)
        panell1.add(rNom)
        panell1.add(JLabel("Desnivell:"))
        rDesn.setEditable(false)
        panell1.add(rDesn)
        panell1.add(JLabel("Desnivell acumulat:"))
        rDesnAcum.setEditable(false)
        panell1.add(rDesnAcum)
        panell1.add(JLabel("Punts:"))

        val panell2 = JPanel(GridLayout(0, 1))
        punts.setEnabled(false)
        val scroll = JScrollPane(punts)
        panell2.add(scroll, null)

        val panell5 = JPanel(FlowLayout())
        panell5.add(primer)
        panell5.add(anterior)
        panell5.add(seguent)
        panell5.add(ultim)

        val panell6 = JPanel(FlowLayout())
        panell6.add(tancar)

        add(p_prin)
        p_prin.add(panell1)
        p_prin.add(panell2)
        p_prin.add(panell5)
        p_prin.add(panell6)
        pack()

        primer.addActionListener {
            // instruccions per a situar-se en la primera ruta, i visualitzar-la

        }
        anterior.addActionListener {
            // instruccions per a situar-se en la ruta anterior, i visualitzar-la

        }
        seguent.addActionListener {
            // instruccions per a situar-se en la ruta següent, i visualitzar-la

        }
        ultim.addActionListener {
            // instruccions per a situar-se en l'últim ruta, i visualitzar-la

        }
        tancar.addActionListener {
            // instruccions per a tancar la BD i el programa
            bd.close()
        }

        inicialitzar()
        VisRuta()
    }

    fun plenarTaula(ll_punts: MutableList<PuntGeo>) {
        var ll = Array(ll_punts.size) { arrayOfNulls<String>(3) }
        for (i in 0 until ll_punts.size) {
            ll[i][0] = ll_punts.get(i).nom
            ll[i][1] = ll_punts.get(i).coord.latitud.toString()
            ll[i][2] = ll_punts.get(i).coord.longitud.toString()
        }
        val caps = arrayOf("Nom punt", "Latitud", "Longitud")
        punts.setModel(javax.swing.table.DefaultTableModel(ll, caps))
    }

    fun inicialitzar() {
        // instruccions per a inicialitzar llista i numActual
        val patro = Ruta(null,null,null)
        val llistaQuery = bd.queryByExample<Ruta>(patro)

        for (e in llistaQuery){
            val ruta = llistaQuery.next() as Ruta
            llistaRutesGlobal.add(ruta)

        }
    }

    fun VisRuta() {
        // instruccions per a visualitzar la ruta actual (l'índex el tenim en numActual
        val ruta = llistaRutesGlobal.get(indexGlobal)
        rNom.text = ruta.nom
        rDesn.text = ruta.desnivell.toString()
        rDesnAcum.text = ruta.desnivellAcumulat.toString()
        ActivarBotons()
    }

    fun ActivarBotons() {
        // instruccions per a activar o desactivar els botons de moviment ( setEnabled(Boolean) )
        primer.isEnabled = true
        anterior.isEnabled = true
        seguent.isEnabled = true
        ultim.isEnabled = true
    }

}

fun main(args: Array<String>) {
    EventQueue.invokeLater {
        FinestraComplet().isVisible = true
    }
}

