package com.prog2sem.client.app

import com.prog2sem.client.SwingApp
import com.prog2sem.client.loc
import java.util.*
import javax.swing.JComboBox


class LangPane : JComboBox<String>() {
    private var languages = arrayOf("Русский", "íslenskur", "lietuvių", "Español (Ecuador)")

    init {
        languages.forEach { addItem(it) }

        addActionListener {
            when (selectedItem) {
                languages[0] -> loc = Locale.Builder().setLanguage("ru").setRegion("RU").build()
                languages[1] -> loc = Locale.Builder().setLanguage("io").build()
                languages[2] -> loc = Locale.Builder().setLanguage("lt").build()
                languages[3] -> loc = Locale.Builder().setLanguage("es").setRegion("EC").build()
            }
            SwingApp.updateLang()
        }
    }
}