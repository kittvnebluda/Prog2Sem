package com.prog2sem.client.app

import com.prog2sem.client.lang
import com.prog2sem.client.region
import java.awt.BorderLayout
import java.awt.Color
import java.awt.Dimension
import java.awt.GridLayout
import java.io.File
import java.util.*
import javax.imageio.ImageIO
import javax.swing.*

class HomePane : JPanel() {
    init {
        val loc = Locale.Builder().setLanguage(lang).setRegion(region).build()
        val labels = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

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
        val contentPane = GroupLayout(this)

        layout = contentPane

        contentPane.setHorizontalGroup(contentPane.createSequentialGroup()
            .addComponent(tablePane)
            .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                .addComponent(graphicsPane)
                .addGroup(contentPane.createSequentialGroup()
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(infoPanel)
                        .addComponent(horizontalSeparator)
                        .addComponent(historyPanel))
                    .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.CENTER)
                        .addComponent(buttonsPanel)
                    ))))

        contentPane.setVerticalGroup(contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addComponent(tablePane)
            .addGroup(contentPane.createSequentialGroup()
                .addComponent(graphicsPane)
                .addGroup(contentPane.createParallelGroup(GroupLayout.Alignment.BASELINE)
                    .addGroup(contentPane.createSequentialGroup()
                        .addComponent(infoPanel)
                        .addComponent(horizontalSeparator)
                        .addComponent(historyPanel))
                    .addGroup(contentPane.createSequentialGroup()
                        .addComponent(buttonsPanel)
                ))))
    }
}