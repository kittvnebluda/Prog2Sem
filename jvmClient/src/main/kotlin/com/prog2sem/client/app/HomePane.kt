package com.prog2sem.client.app
import com.prog2sem.client.app.CreatePersonPane.birthdayText
import com.prog2sem.client.app.CreatePersonPane.coordinatesXText
import com.prog2sem.client.app.CreatePersonPane.coordinatesYText
import com.prog2sem.client.app.CreatePersonPane.hairColorText
import com.prog2sem.client.app.CreatePersonPane.heightText
import com.prog2sem.client.app.CreatePersonPane.locationText
import com.prog2sem.client.app.CreatePersonPane.nameText
import com.prog2sem.client.app.CreatePersonPane.weightText
import com.prog2sem.client.app.TableInfo.keysWithNoLogin
import com.prog2sem.client.app.TableInfo.previousKey
import com.prog2sem.client.app.TableInfo.tableNow
import com.prog2sem.client.app.workers.*
import com.prog2sem.client.commandsHistoryList
import com.prog2sem.client.labels
import com.prog2sem.server.DataBaseCommands.DataBaseConnector
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.persona.Person
import java.awt.Dimension
import java.awt.GridLayout
import java.time.ZonedDateTime
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

    val graphicsPane = CreatePersonPane // DisplayPerson(Coordinates(100f, 100.0), Color.CYAN)

    init {
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

        val buttonsPane = JPanel()
        val buttonsLayout = GridLayout(6, 1)
        buttonsPane.layout = buttonsLayout
        buttonsPane.add(addButton)
        buttonsPane.add(removeButton)
        buttonsPane.add(clearButton)
        buttonsPane.add(addIfMinButton)
        buttonsPane.add(removeGreaterButton)
        buttonsPane.add(sortButton)

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
                        .addComponent(buttonsPane)))))

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
                        .addComponent(buttonsPane)))))

        // create listeners

        addButton.addActionListener {
            val person = Person(
                nameText.text,
                Coordinates(coordinatesXText.text.toFloat(), coordinatesYText.text.toDouble()),
                heightText.text.toLong(),
                ZonedDateTime.parse(birthdayText.text),
                weightText.text.toInt(),
                com.prog2sem.shared.Color.valueOf(hairColorText.text),
                DataBaseConnector.getLocationFromTable(locationText.text)
            )
            AddWorker.person = person
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