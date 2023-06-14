package com.prog2sem.client.app

import com.prog2sem.client.lang
import com.prog2sem.client.region
import java.awt.Dimension
import java.util.*
import javax.swing.Box
import javax.swing.JButton
import javax.swing.JLabel
import javax.swing.JPanel
import javax.swing.JTextField

class AuthPane() : JPanel() {
    init {
        val loc = Locale.Builder().setLanguage(lang).setRegion(region).build()
        val labels = ResourceBundle.getBundle("com.prog2sem.client.localization.GuiLabels", loc)

        val usernameText = JTextField()
        val passwdText = JTextField()

        val usernameLabel = JLabel(labels.getString("username"))
        val passwdLabel = JLabel(labels.getString("password"))

        val projectLabel = JLabel("PROJECT\nPROG2SEM")

        val loginButton = JButton(labels.getString("login"))
        val signupButton = JButton(labels.getString("signup"))

        val box = Box.createVerticalBox()

        minimumSize = Dimension(200, 400)

        box.add(projectLabel)
        box.add(usernameLabel)
        box.add(usernameText)
        box.add(passwdLabel)
        box.add(passwdText)
        box.add(loginButton)
        box.add(signupButton)
    }

}