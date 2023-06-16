package com.prog2sem.client.app

import com.prog2sem.client.commandsHistoryList
import com.prog2sem.client.labels
import com.prog2sem.shared.Coordinates
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import javax.swing.*

object HomePane : JPanel() {
    val infoPane = JTextArea(labels.getString("no db info"))
    private val historyPane = JTextArea(labels.getString("empty history"))

    private val addButton = JButton(labels.getString("add"))
    private val removeButton = JButton(labels.getString("remove"))
    private val clearButton = JButton(labels.getString("clear"))
    private val addIfMinButton = JButton(labels.getString("add if min"))
    private val removeGreaterButton = JButton(labels.getString("remove greater"))

    val graphicsPane = DisplayPerson(Coordinates(100f, 100.0), Color.CYAN)

    init {
        // create table
        val table = JTable(100, 9)
        val tablePane = JScrollPane(table)

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
        val anotherHorizontalSeparator = JSeparator(SwingConstants.HORIZONTAL)
        val contentPane = GroupLayout(this)

        layout = contentPane

        contentPane.setHorizontalGroup(contentPane.createSequentialGroup()
            .addComponent(tablePane)
            .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(graphicsPane)
                .addComponent(anotherHorizontalSeparator)
                .addGroup(contentPane.createSequentialGroup()
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(infoPane)
                        .addComponent(horizontalSeparator)
                        .addComponent(historyScroll))
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(buttonsPanel)))))

        contentPane.setVerticalGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
            .addComponent(tablePane)
            .addGroup(contentPane.createSequentialGroup()
                .addComponent(graphicsPane)
                .addComponent(anotherHorizontalSeparator)
                .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
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