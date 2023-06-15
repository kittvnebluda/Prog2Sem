package com.prog2sem.client

import com.prog2sem.client.app.AuthPane
import java.awt.BorderLayout
import java.net.InetSocketAddress
import javax.swing.*

var lang = "ru"
var region = "RU"

fun main() {
    client.sendToAddress = InetSocketAddress(host, port)

    SwingUtilities.invokeLater { gui() }
}

fun gui() {
    val f = JFrame()

//    f.preferredSize = Dimension(500, 500)
    f.title = "Eights laboratory of java's second semester programming course"
    f.defaultCloseOperation = JFrame.EXIT_ON_CLOSE

    // create menu
    val menu = BorderLayout()

    val rightMenu = JPanel()

    rightMenu.add(JLabel(login))
    rightMenu.add(JSeparator(SwingConstants.VERTICAL))
    rightMenu.add(JLabel("${lang}_$region"))

    f.jMenuBar = JMenuBar()
    f.jMenuBar.layout = menu

    f.jMenuBar.add(JLabel("PROJECT PROG2SEM"), BorderLayout.LINE_START)
    f.jMenuBar.add(rightMenu, BorderLayout.LINE_END)

    f.contentPane = AuthPane()

    f.jMenuBar.isVisible = false
    f.jMenuBar.isEnabled = false

    f.isResizable = false

    f.pack() // установка размеров фрейма

    f.isVisible = true
}