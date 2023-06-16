package com.prog2sem.client.app

import com.prog2sem.client.dbCommands
import java.awt.event.ActionEvent
import java.awt.event.ActionListener


class InfoListener : ActionListener {
        override fun actionPerformed(e: ActionEvent?) {
            HomePane.infoPane.text = dbCommands.info()
        }
}