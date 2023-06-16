package com.prog2sem.client.app

import com.prog2sem.client.*
import com.prog2sem.client.exceptions.ServerNotAnsweringException
import com.prog2sem.shared.exceptions.GotErrorMsgException
import java.awt.*
import javax.swing.*
import javax.swing.event.DocumentEvent
import javax.swing.event.DocumentListener


object AuthPane : JPanel() {
    private val usernameLabel = JLabel(labels.getString("username"))
    private val passwdLabel = JLabel(labels.getString("password"))
    private val addressLabel = JLabel(labels.getString("server address"))
    private val portLabel = JLabel(labels.getString("server port"))

    private val errorLoginLabel = JLabel()

    private val loginButton = JButton(labels.getString("login"))
    private val signupButton = JButton(labels.getString("signup"))

    private val tabbedPane = JTabbedPane()

    init {
        val projectLabel = JLabel("PROJECT PROG2SEM")
        val projectLabelPane = JPanel()

        val usernameText = JTextField()
        val passwdText = JPasswordField()
        val addressText = JTextField(host)
        val portText = JTextField(port.toString())

        projectLabelPane.add(projectLabel)
        projectLabelPane.background = Color.WHITE

        errorLoginLabel.font = Font("Serif", Font.PLAIN, 11)
        errorLoginLabel.foreground = Color.RED

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

        val langPane = JPanel()
        val langLayout = BorderLayout()
        langPane.layout = langLayout
        langPane.background = Color.WHITE
        langPane.add(LangPane(), BorderLayout.LINE_END)


        val box = Box(BoxLayout.Y_AXIS)

        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(projectLabelPane)
        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(loginPane)
        box.add(Box.createRigidArea(Dimension(0, 15)))
        box.add(loginButtonsPane)
        box.add(Box.createRigidArea(Dimension(0, 7)))
        box.add(langPane)

        val boxPane = JPanel()
        boxPane.add(box)
        boxPane.background = Color.WHITE

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

    fun updateLabels() {
        usernameLabel.text = labels.getString("username")
        passwdLabel.text = labels.getString("password")
        addressLabel.text = labels.getString("server address")
        portLabel.text = labels.getString("server port")

        loginButton.text = labels.getString("login")
        signupButton.text = labels.getString("signup")

        tabbedPane.setTitleAt(0, labels.getString("auth tab"))
        tabbedPane.setTitleAt(1, labels.getString("server settings tab"))
    }
}