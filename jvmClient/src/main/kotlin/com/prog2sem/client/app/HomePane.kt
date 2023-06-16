package com.prog2sem.client.app

import com.prog2sem.client.*
import com.prog2sem.client.persona.RndPersonBuilder
import com.prog2sem.shared.persona.PersonDirector
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.awt.event.ActionEvent
import java.awt.event.ActionListener
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.Timer

object HomePane : JPanel() {
    val infoPane = JTextArea(labels.getString("no db info"))
    private val historyPane = JTextArea(labels.getString("empty history"))

    init {
        // create table
        val table = JTable(100, 9)
        val tablePane = JScrollPane(table)

        // create graphics
        val graphics = JPanel()

        val stickman = ImageIO.read(File("C:\\Users\\sizgr\\IdeaProjects\\Prog2Sem\\jvmClient\\src\\main\\resources\\images\\stickman.png"))
        val picLabel = JLabel(ImageIcon(stickman))

        graphics.add(picLabel)

        graphics.background = Color.WHITE
        graphics.maximumSize = Dimension(200, 400)

        val graphicsPane = JPanel()
        val graphicsLayout = BorderLayout()
        graphicsPane.layout = graphicsLayout
        graphicsPane.add(graphics, BorderLayout.CENTER)

        // change the info panel
        infoPane.minimumSize = Dimension(50, 100)

        // change the history panel
        historyPane.isEditable = false
        historyPane.minimumSize = Dimension(50, 100)
        val historyScroll = JScrollPane(historyPane)
        historyScroll.minimumSize = Dimension(50, 100)

        // create buttons
        val buttonAdd = JButton(labels.getString("add"))
        val buttonRemove = JButton(labels.getString("remove"))
        buttonRemove.isEnabled = false
        val buttonClear = JButton(labels.getString("clear"))
        buttonClear.isEnabled = false
        val buttonAddIfMin = JButton(labels.getString("add if min"))
        buttonAddIfMin.isEnabled = false
        val buttonRemoveGreater = JButton(labels.getString("remove greater"))
        buttonRemoveGreater.isEnabled = false

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
        val contentPane = GroupLayout(this)

        layout = contentPane

        contentPane.setHorizontalGroup(contentPane.createSequentialGroup()
            .addComponent(tablePane)
            .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(graphicsPane)
                .addGroup(contentPane.createSequentialGroup()
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(infoPane)
                        .addComponent(horizontalSeparator)
                        .addComponent(historyScroll))
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(buttonsPanel)))))

        contentPane.setVerticalGroup(contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(tablePane)
            .addGroup(contentPane.createSequentialGroup()
                .addComponent(graphicsPane)
                .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addGroup(contentPane.createSequentialGroup()
                        .addComponent(infoPane)
                        .addComponent(horizontalSeparator)
                        .addComponent(historyScroll))
                    .addGroup(contentPane.createSequentialGroup()
                        .addComponent(buttonsPanel)))))

        // create listeners
        buttonAdd.addActionListener {
            AddWorker().execute()
        }

    }

    fun updateHistory() {
        historyPane.text = commandsHistoryList.reversed().joinToString("\n")
    }
}