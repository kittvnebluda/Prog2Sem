package com.prog2sem.client.localization

import java.util.*

class GuiLabels_io : ListResourceBundle() {
    private val contents = arrayOf(
        arrayOf<Any>("add", "BÆTA VIÐ"),
        arrayOf<Any>("clear", "HREINSA"),
        arrayOf<Any>("remove", "FJARLÆGJA"),
        arrayOf<Any>("update", "UPPFÆRA"),
        arrayOf<Any>("add if min", "BÆTA VIÐ EF MÍN"),
        arrayOf<Any>("remove greater", "FJARLÆGJA GREATER"),
        arrayOf<Any>("no db info", "engar db upplýsingar"),
        arrayOf<Any>("empty history", "tómur ferill"),
        arrayOf<Any>("username", "notendanafn"),
        arrayOf<Any>("password", "lykilorð"),
        arrayOf<Any>("login", "innskráning"),
        arrayOf<Any>("signup", "SKRÁÐU ÞIG INN"),
        arrayOf<Any>("server address", "vistfang þjóns"),
        arrayOf<Any>("server port", "þjónstengi"),
        arrayOf<Any>("auth tab", "auðkenndur flipi"),
        arrayOf<Any>("server settings tab", "flipi þjónsstillinga"),
        arrayOf<Any>("server_not_answering", "þjónn svarar ekki."),
        arrayOf<Any>("incorrect_name_or_pass", "rangt nafn eða pass."),
        arrayOf<Any>("try_again", "reyna aftur."),
        arrayOf<Any>("user_exists", "notandi er þegar til."),
        arrayOf<Any>("sort", "RAÐA"),
        arrayOf<Any>("name", "nafn:"),
        arrayOf<Any>("coordinates", "innihald:"),
        arrayOf<Any>("height", "hæð:"),
        arrayOf<Any>("birthday", "afmæli:"),
        arrayOf<Any>("weight", "þyngd:"),
        arrayOf<Any>("hair_color", "hárlitur:"),
        arrayOf<Any>("location", "staðsetning:")

    )
    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}