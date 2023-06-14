package com.prog2sem.client

import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.GroupLayout.Alignment


var lang = "ru"
var region = "RU"

fun main() {
    SwingUtilities.invokeLater { gui() }
}

fun gui() {
    val loc = Locale.Builder().setLanguage(lang).setRegion(region).build()

    val labels = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

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

    // create table
    val table = JTable(100, 9)
    val tablePane = JScrollPane(table)

    // create graphics
    val graphics = JPanel()

    val stickman = ImageIO.read(File("/home/kit/IdeaProjects/Prog2Sem/jvmClient/src/main/resources/images/stickman.png"))
    val picLabel = JLabel(ImageIcon(stickman))

    graphics.add(picLabel)

    graphics.background = Color.WHITE
    graphics.maximumSize = Dimension(200, 400)

    val graphicsPane = JPanel()
    val graphicsLayout = BorderLayout()
    graphicsPane.layout = graphicsLayout
    graphicsPane.add(graphics, BorderLayout.CENTER)

    // create info panel
    val infoPanel = JLabel(labels.getString("no db info"))
    infoPanel.minimumSize = Dimension(50, 100)

    // create a history panel
    val historyPanel = JLabel(labels.getString("empty history"))
    historyPanel.minimumSize = Dimension(50, 100)

    // create buttons
    val buttonAdd = JButton(labels.getString("add"))
    val buttonRemove = JButton(labels.getString("remove"))
    val buttonClear = JButton(labels.getString("clear"))
//    val buttonUpdate = JButton(labels.getString("update"))
    val buttonAddIfMin = JButton(labels.getString("add if min"))
    val buttonRemoveGreater = JButton(labels.getString("remove greater"))

    val buttonsPanel = JPanel()
    val buttonsLayout = GridLayout(5, 1)
    buttonsPanel.layout = buttonsLayout
    buttonsPanel.add(buttonAdd)
    buttonsPanel.add(buttonRemove)
    buttonsPanel.add(buttonClear)
    buttonsPanel.add(buttonAddIfMin)
    buttonsPanel.add(buttonRemoveGreater)

    // create contentPane
    val horizontalSeparator = JSeparator(SwingConstants.HORIZONTAL)
    val contentPane = GroupLayout(f.contentPane)

    f.contentPane.layout = contentPane

    contentPane.setHorizontalGroup(contentPane.createSequentialGroup()
        .addComponent(tablePane)
        .addGroup(contentPane.createParallelGroup(Alignment.CENTER)
            .addComponent(graphicsPane)
            .addGroup(contentPane.createSequentialGroup()
                .addGroup(contentPane.createParallelGroup(Alignment.CENTER)
                    .addComponent(infoPanel)
                    .addComponent(horizontalSeparator)
                    .addComponent(historyPanel))
                .addGroup(contentPane.createParallelGroup(Alignment.CENTER)
                    .addComponent(buttonsPanel)
                ))))

    contentPane.setVerticalGroup(contentPane.createParallelGroup(Alignment.BASELINE)
        .addComponent(tablePane)
        .addGroup(contentPane.createSequentialGroup()
            .addComponent(graphicsPane)
            .addGroup(contentPane.createParallelGroup(Alignment.BASELINE)
                .addGroup(contentPane.createSequentialGroup()
                    .addComponent(infoPanel)
                    .addComponent(horizontalSeparator)
                    .addComponent(historyPanel))
                .addGroup(contentPane.createSequentialGroup()
                    .addComponent(buttonsPanel)
            ))))

    // authorization

    f.pack() // установка размеров фрейма

    f.isVisible = true
}