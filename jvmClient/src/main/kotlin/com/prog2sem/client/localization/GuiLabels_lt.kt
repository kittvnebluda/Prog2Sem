package com.prog2sem.client.localization

import java.util.*

class GuiLabels_lt : ListResourceBundle() {
    private val contents = arrayOf(
    arrayOf<Any>("add", "PRIDĖTI"),
    arrayOf<Any>("clear", "AIŠKUS"),
    arrayOf<Any>("remove", "PAŠALINTI"),
    arrayOf<Any>("update", "NAUJINIMAS"),
    arrayOf<Any>("add if min", "PRIDĖTI, JEI MIN"),
    arrayOf<Any>("remove greater", "PAŠALINTI DIDESNĮ"),
    arrayOf<Any>("no db info", "nėra db informacijos"),
    arrayOf<Any>("empty history", "tuščia istorija"),
    arrayOf<Any>("username", "vardas"),
    arrayOf<Any>("password", "slaptažodis"),
    arrayOf<Any>("login", "prisijungimas"),
    arrayOf<Any>("signup", "UŽSIREGISTRUOTI"),
    arrayOf<Any>("server address", "serverio adresas"),
    arrayOf<Any>("server port", "serverio prievadas"),
    arrayOf<Any>("auth tab", "leidimo skirtukas"),
    arrayOf<Any>("server settings tab", "serverio nustatymų skirtukas"),
    arrayOf<Any>("server_not_answering", "serveris neatsako."),
    arrayOf<Any>("incorrect_name_or_pass", "neteisingas vardas arba leidimas."),
    arrayOf<Any>("try_again", "bandykite dar kartą."),
    arrayOf<Any>("user_exists", "vartotojas egzistuoja."),
    arrayOf<Any>("sort", "RŪŠIUOTI"),
    arrayOf<Any>("name", "pavadinimas:"),
    arrayOf<Any>("coordinates", "koordinuoti:"),
    arrayOf<Any>("height", "aukštis:"),
    arrayOf<Any>("birthday", "gimtadienis:"),
    arrayOf<Any>("weight", "svoris:"),
    arrayOf<Any>("hair_color", "plaukų spalva:"),
    arrayOf<Any>("location", "vieta:")
    )

    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}