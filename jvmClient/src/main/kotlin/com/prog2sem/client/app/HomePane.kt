package com.prog2sem.client.app

import com.prog2sem.client.*
import com.prog2sem.client.app.TableInfo.keysWithNoLogin
import com.prog2sem.client.app.TableInfo.previousKey
import com.prog2sem.client.app.TableInfo.tableNow
import com.prog2sem.client.app.Workers.*
import com.prog2sem.shared.Coordinates
import java.awt.*
import java.awt.event.MouseAdapter
import java.awt.event.MouseEvent
import java.awt.event.MouseListener
import java.io.File
import javax.imageio.ImageIO
import javax.swing.*
import javax.swing.table.DefaultTableModel

object HomePane : JPanel() {
    val infoPane = JTextArea(labels.getString("no db info"))
    private val historyPane = JTextArea(labels.getString("empty history"))

    private val addButton = JButton(labels.getString("add"))
    private val removeButton = JButton(labels.getString("remove"))
    private val clearButton = JButton(labels.getString("clear"))
    private val addIfMinButton = JButton(labels.getString("add if min"))
    private val removeGreaterButton = JButton(labels.getString("remove greater"))
    private val sortButton = JButton(labels.getString("sort"))
    var table = JTable(DefaultTableModel(arrayOf(), keysWithNoLogin))
    var tablePane = JScrollPane(table)

    val sortNow = mutableListOf<Int>()

    val graphicsPane = DisplayPerson(Coordinates(100f, 100.0), Color.CYAN)

    init {

        // create graphics
        val graphics = JPanel()

        val stickman = ImageIO.read(File("C:\\ItmoProject2023\\Prog2Sem\\jvmClient\\src\\main\\resources\\images\\stickman.png"))
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
        //removeButton.isEnabled = false
        //clearButton.isEnabled = false
        //addIfMinButton.isEnabled = false
        //removeGreaterButton.isEnabled = false

        val buttonsPanel = JPanel()
        val buttonsLayout = GridLayout(6, 1)
        buttonsPanel.layout = buttonsLayout
        buttonsPanel.add(addButton)
        buttonsPanel.add(removeButton)
        buttonsPanel.add(clearButton)
        buttonsPanel.add(addIfMinButton)
        buttonsPanel.add(removeGreaterButton)
        buttonsPanel.add(sortButton)

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

        removeButton.addActionListener {
            val id = table.selectedRow
            RemoveWorker.id = id
            RemoveWorker().execute()
        }

        clearButton.addActionListener {
            ClearWorker().execute()
        }

        addIfMinButton.addActionListener {
            AddIfMInWorker().execute()
        }

        removeGreaterButton.addActionListener {
            RemoveGreaterWorker().execute()
        }

        sortButton.addActionListener {
            val index = table.selectedColumn
            previousKey = keysWithNoLogin[index]
        }


    }

    fun updateHistory() {
        historyPane.text = commandsHistoryList.reversed().joinToString("\n")
    }

    fun updateTable(){
        val now = tableNow
        val model = table.model as DefaultTableModel

        while (table.rowCount < now.size)
            model.addRow(arrayOf())

        while (table.getValueAt(0, 0) != null){
            model.removeRow(0)
            model.addRow(arrayOf())
        }


        for (i in 0 until now.size) {
            table.setValueAt(now[i].id, i, 0)
            table.setValueAt(now[i].creteTime, i, 1)
            table.setValueAt(now[i].person.name, i, 2)
            table.setValueAt(now[i].person.weight, i, 3)
            table.setValueAt(now[i].person.height, i, 4)
            table.setValueAt(now[i].person.birthday, i, 5)
            table.setValueAt(now[i].person.hairColor, i, 6)
            table.setValueAt(now[i].person.coordinates.toString(), i, 7)
            table.setValueAt(now[i].person.location.toString(), i, 8)
        }

    }

    fun updateLabels() {
        addButton.text = labels.getString("add")
        removeButton.text = labels.getString("remove")
        clearButton.text = labels.getString("clear")
        addIfMinButton.text = labels.getString("add if min")
        removeGreaterButton.text = labels.getString("remove greater")
    }
}