package com.prog2sem.client

import com.prog2sem.client.app.AuthPane
import com.prog2sem.client.app.HomePane
import com.prog2sem.client.app.LangPane
import java.awt.BorderLayout
import java.awt.Color
import java.net.InetSocketAddress
import java.util.*
import javax.swing.*


var loc: Locale = Locale.Builder().setLanguage("ru").setRegion("RU").build()
var labels: ResourceBundle = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

fun main() {
    client.sendToAddress = InetSocketAddress(host, port)

    SwingUtilities.invokeLater { SwingApp }

}

object SwingApp : JFrame() {

    val errorLabel = JLabel()

    init {
        title = "Eights laboratory of java's second semester programming course"
        defaultCloseOperation = EXIT_ON_CLOSE

        contentPane = AuthPane

        isResizable = false

        pack() // установка размеров фрейма

        isVisible = true
    }

    fun authDone() {
        isResizable = true

        // create menu
        errorLabel.foreground = Color.RED

        val rightMenu = JPanel()

        rightMenu.add(JLabel(login))
        rightMenu.add(JSeparator(SwingConstants.VERTICAL))
        rightMenu.add(LangPane())

        val menu = BorderLayout()
        menu.hgap = 30

        jMenuBar = JMenuBar()
        jMenuBar.layout = menu

        jMenuBar.add(JLabel("PROJECT PROG2SEM"), BorderLayout.LINE_START)
        jMenuBar.add(errorLabel, BorderLayout.CENTER)
        jMenuBar.add(rightMenu, BorderLayout.LINE_END)

        contentPane = HomePane

        pack() // установка размеров фрейма
    }

    fun updateLang() {
        labels = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

        AuthPane.updateLabels()
        HomePane.updateLabels()
    }
}