package com.prog2sem.client.app

import com.prog2sem.client.SwingApp
import com.prog2sem.client.labels
import com.prog2sem.shared.Color
import com.prog2sem.shared.Coordinates
import com.prog2sem.shared.Location
import java.awt.Dimension
import java.awt.FlowLayout
import java.awt.GridBagConstraints
import java.awt.GridBagLayout
import java.time.ZonedDateTime
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField
import javax.swing.SwingConstants

object CreatePersonPane : JPanel(GridBagLayout()) {
    private var name = "Tipical name"
    private var coordinates = Coordinates(0f, 0.0)
    private var height = 172
    private var birthday = ZonedDateTime.now()
    private var weight = 62
    private var hairColor = Color.BROWN
    private var location = Location(0f, 0f, 0, "Омск")

    private val nameLabel = JLabel(labels.getString("name"))
    private val coordsLabel = JLabel(labels.getString("coordinates"))
    private val heightLabel = JLabel(labels.getString("height"))
    private val birthLabel = JLabel(labels.getString("birthday"))
    private val weightLabel = JLabel(labels.getString("weight"))
    private val hairColorLabel = JLabel(labels.getString("hair_color"))
    private val locationLabel = JLabel(labels.getString("location"))


    val nameText = JTextField(name)
    val coordinatesXText = JTextField(coordinates.x.toString())
    val coordinatesYText = JTextField(coordinates.y.toString())
    val heightText = JTextField(height.toString())
    val birthdayText = JTextField(birthday.toString())
    val weightText = JTextField(weight.toString())
    val hairColorText = JTextField(hairColor.toString())
    val locationText = JTextField(location.toTable())

    val errorLabel = JLabel()

    init {
        errorLabel.foreground = java.awt.Color.RED

        val coordinatesPane = JPanel(FlowLayout())
        coordinatesPane.add(coordinatesXText)
        coordinatesPane.add(coordinatesYText)

        nameLabel.labelFor = nameText
        coordsLabel.labelFor = coordinatesPane
        heightLabel.labelFor = heightText
        birthLabel.labelFor = birthdayText
        weightLabel.labelFor = weightText
        hairColorLabel.labelFor = hairColorText
        locationLabel.labelFor = locationText

        val c = GridBagConstraints()
        c.gridx = 0
        c.gridy = 0
        add(nameLabel, c)
        c.gridx = 1
        c.gridy = 0
        add(nameText, c)
        c.gridx = 0
        c.gridy = 1
        add(coordsLabel, c)
        c.gridx = 1
        c.gridy = 1
        add(coordinatesPane, c)
        c.gridx = 0
        c.gridy = 2
        add(heightLabel, c)
        c.gridx = 1
        c.gridy = 2
        add(heightText, c)
        c.gridx = 0
        c.gridy = 3
        add(birthLabel, c)
        c.gridx = 1
        c.gridy = 3
        add(birthdayText, c)
        c.gridx = 0
        c.gridy = 4
        add(weightLabel, c)
        c.gridx = 1
        c.gridy = 4
        add(weightText, c)
        c.gridx = 0
        c.gridy = 5
        add(hairColorLabel, c)
        c.gridx = 1
        c.gridy = 5
        add(hairColorText, c)
        c.gridx = 0
        c.gridy = 6
        add(locationLabel, c)
        c.gridx = 1
        c.gridy = 6
        add(locationText, c)
        c.gridx = 0
        c.gridy = 7
        c.gridwidth = 2
        add(errorLabel, c)
    }

    fun updateLabels() {
        nameLabel.text = labels.getString("name")
        coordsLabel.text = labels.getString("coordinates")
        heightLabel.text = labels.getString("height")
        birthLabel.text = labels.getString("birthday")
        weightLabel.text = labels.getString("weight")
        hairColorLabel.text = labels.getString("hair_color")
        locationLabel.text = labels.getString("location")
    }
}