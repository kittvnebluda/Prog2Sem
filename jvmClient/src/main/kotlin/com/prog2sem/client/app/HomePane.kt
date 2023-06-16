package com.prog2sem.client.app

import com.prog2sem.client.*
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.Timer

object HomePane : JPanel() {
    val infoPane = JTextArea(labels.getString("no db info"))
    private val historyPane = JTextArea(labels.getString("empty history"))

    private val addButton = JButton(labels.getString("add"))
    private val removeButton = JButton(labels.getString("remove"))
    private val clearButton = JButton(labels.getString("clear"))
    private val addIfMinButton = JButton(labels.getString("add if min"))
    private val removeGreaterButton = JButton(labels.getString("remove greater"))

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

        // edit buttons
        removeButton.isEnabled = false
        clearButton.isEnabled = false
        addIfMinButton.isEnabled = false
        removeGreaterButton.isEnabled = false

        val buttonsPanel = JPanel()
        val buttonsLayout = GridLayout(5, 1)
        buttonsPanel.layout = buttonsLayout
        buttonsPanel.add(addButton)
        buttonsPanel.add(removeButton)
        buttonsPanel.add(clearButton)
        buttonsPanel.add(addIfMinButton)
        buttonsPanel.add(removeGreaterButton)

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
        addButton.addActionListener {
            AddWorker().execute()
        }

    }

    fun updateHistory() {
        historyPane.text = commandsHistoryList.reversed().joinToString("\n")
    }

    fun updateLabels() {
        addButton.text = labels.getString("add")
        removeButton.text = labels.getString("remove")
        clearButton.text = labels.getString("clear")
        addIfMinButton.text = labels.getString("add if min")
        removeGreaterButton.text = labels.getString("remove greater")
    }
}