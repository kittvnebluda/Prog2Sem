package com.prog2sem.client.app

import com.prog2sem.client.*
import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.shared.exceptions.GotErrorMsgException
import java.awt.*
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener


class AuthPane : JPanel() {
    init {
        val usernameText = JTextField()
        val passwdText = JPasswordField()
        val addressText = JTextField(host)
        val portText = JTextField(port.toString())

        val usernameLabel = JLabel(labels.getString("username"))
        val passwdLabel = JLabel(labels.getString("password"))
        val addressLabel = JLabel(labels.getString("server address"))
        val portLabel = JLabel(labels.getString("server port"))

        val projectLabel = JLabel("PROJECT PROG2SEM")
        val projectLabelPane = JPanel()
        projectLabelPane.add(projectLabel)
        projectLabelPane.background = Color.WHITE

        val errorLoginLabel = JLabel()
        errorLoginLabel.font = Font("Serif", Font.PLAIN, 11)
        errorLoginLabel.foreground = Color.RED

        val loginButton = JButton(labels.getString("login"))
        val signupButton = JButton(labels.getString("signup"))

        minimumSize = Dimension(100, 400)
        background = Color.WHITE

        val serverPane = JPanel()
        val serverLayout = GroupLayout(serverPane)
        serverPane.layout = serverLayout
        serverPane.background = Color.CYAN

        addressText.preferredSize.height = addressLabel.minimumSize.height

        serverLayout.setHorizontalGroup(serverLayout.createSequentialGroup()
            .addGroup(serverLayout.createParallelGroup(GroupLayout.Alignment.TRAILING)
                .addComponent(addressLabel)
                .addComponent(portLabel))
            .addGroup(serverLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                .addComponent(addressText)
                .addComponent(portText)))

        serverLayout.setVerticalGroup(serverLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
            .addGroup(serverLayout.createSequentialGroup()
                .addComponent(addressLabel)
                .addComponent(portLabel))
            .addGroup(serverLayout.createSequentialGroup()
                .addComponent(addressText)
                .addComponent(portText)))

        val loginPane = JPanel()
        val loginLayout = GroupLayout(loginPane)
        loginPane.layout = loginLayout
        loginPane.background = Color.WHITE

        val rigidArea = Box.createRigidArea(Dimension(0, 14))

        loginLayout.setHorizontalGroup(loginLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addComponent(usernameLabel)
            .addComponent(usernameText)
            .addComponent(errorLoginLabel)
            .addComponent(rigidArea)
            .addComponent(passwdLabel)
            .addComponent(passwdText))

        loginLayout.setVerticalGroup(loginLayout.createSequentialGroup()
            .addComponent(usernameLabel)
            .addComponent(usernameText)
            .addComponent(errorLoginLabel)
            .addComponent(rigidArea)
            .addComponent(passwdLabel)
            .addComponent(passwdText))

        val loginButtonsPane = JPanel()
        val loginButtonsLayout = GridLayout(2, 1)
        loginButtonsPane.layout = loginButtonsLayout
        loginButtonsPane.background = Color.WHITE

        loginButtonsPane.add(loginButton)
        loginButtonsPane.add(signupButton)

        val box = Box(BoxLayout.Y_AXIS)

        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(projectLabelPane)
        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(loginPane)
        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(loginButtonsPane)

        val boxPane = JPanel()
        boxPane.add(box)
        boxPane.background=  Color.WHITE

        val tabbedPane = JTabbedPane()
        tabbedPane.addTab(labels.getString("auth tab"), boxPane)
        tabbedPane.addTab(labels.getString("server settings tab"), serverPane)

        add(tabbedPane)

        // add action listeners
        addressText.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                host = addressText.text
            }

            override fun removeUpdate(e: DocumentEvent?) {
                host = addressText.text
            }

            override fun changedUpdate(e: DocumentEvent?) {}
        })

        portText.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                try {
                    port = portText.text.toInt()
                } catch (e: NumberFormatException) {
                    println(e.message)
                }
            }

            override fun removeUpdate(e: DocumentEvent?) {
                try {
                    port = portText.text.toInt()
                } catch (e: NumberFormatException) {
                    println(e.message)
                }
            }

            override fun changedUpdate(e: DocumentEvent?) {}
        })

        usernameText.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                login = usernameText.text
            }

            override fun removeUpdate(e: DocumentEvent?) {
                login = usernameText.text
            }

            override fun changedUpdate(e: DocumentEvent?) {}
        })

        passwdText.document.addDocumentListener(object : DocumentListener {
            override fun insertUpdate(e: DocumentEvent?) {
                password = passwdText.text
            }

            override fun removeUpdate(e: DocumentEvent?) {
                password = passwdText.text
            }

            override fun changedUpdate(e: DocumentEvent?) {}
        })

        loginButton.addActionListener {
            try {
                if(dbCommands.login(login, password)) {
                    isLogged = true
                    SwingApp.authDone()
                }

            } catch (e: GotErrorMsgException) {
                rigidArea.isVisible = false
                errorLoginLabel.text = labels.getString("incorrect_name_or_pass")
                println(e.message)
            }
        }

        signupButton.addActionListener {
            try {
                if(dbCommands.signup(login, password)) {
                    isLogged = true
                    SwingApp.authDone()
                }
            } catch (e: ServerNotAnsweringException) {
                rigidArea.isVisible = false
                errorLoginLabel.text = labels.getString("try_again")
                println(e.message)
            } catch (e: GotErrorMsgException) {
                rigidArea.isVisible = false
                errorLoginLabel.text = labels.getString("user_exists")
                println(e.message)
            }
        }
    }
}