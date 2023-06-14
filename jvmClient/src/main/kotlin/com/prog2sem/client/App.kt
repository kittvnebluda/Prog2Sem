package com.prog2sem.client

import java.awt.BorderLayout
import java.util.*
import javax.swing.*


fun main() {
    SwingUtilities.invokeLater { gui() }
}

fun gui() {
    val loc = Locale.Builder().setLanguage("ru").setRegion("RU").build()

    val labels = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

    val f = JFrame()

    f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    f.jMenuBar = JMenuBar()
    f.jMenuBar.add(JLabel("PROJECT PROG2SEM"))
    f.jMenuBar.add(JLabel(labels.getString("add")))

    f.contentPane = JPanel()
    f.contentPane.setBounds(0, 0, 500, 500)

    f.contentPane.add(JLabel(labels.getString("clear")), BorderLayout.CENTER)

    f.pack() // установка размеров фрейма

    f.isVisible = true
}