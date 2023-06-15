package com.prog2sem.client

import com.prog2sem.client.app.AuthPane
import com.prog2sem.client.app.HomePane
import java.awt.BorderLayout
import java.net.InetSocketAddress
import javax.swing.*

var lang = "ru"
var region = "RU"

fun main() {
    client.sendToAddress = InetSocketAddress(host, port)

    SwingUtilities.invokeLater { SwingApp }
}

object SwingApp : JFrame() {
    init {
//      preferredSize = Dimension(500, 500)
        title = "Eights laboratory of java's second semester programming course"
        defaultCloseOperation = EXIT_ON_CLOSE

        contentPane = AuthPane()

        isResizable = false

        pack() // установка размеров фрейма

        isVisible = true
    }

    fun authDone() {
        isResizable = true

        // create menu
        val menu = BorderLayout()

        val rightMenu = JPanel()

        rightMenu.add(JLabel(login))
        rightMenu.add(JSeparator(SwingConstants.VERTICAL))
        rightMenu.add(JLabel("${lang}_$region"))

        jMenuBar = JMenuBar()
        jMenuBar.layout = menu

        jMenuBar.add(JLabel("PROJECT PROG2SEM"), BorderLayout.LINE_START)
        jMenuBar.add(rightMenu, BorderLayout.LINE_END)

        contentPane = HomePane()

        pack() // установка размеров фрейма
    }
}