@file:Suppress("unused")

package com.prog2sem.client.localization

import java.util.*

class GuiLabels_es_EC : ListResourceBundle() {
    private val contents = arrayOf(
        arrayOf<Any>("add", "AÑADIR NUEVO ELEMENTO"),
        arrayOf<Any>("clear", "BORRAR TABLA"),
        arrayOf<Any>("remove", "ELIMINAR ELEMENTO"),
        arrayOf<Any>("update", "ACTUALIZAR TABLA"),
        arrayOf<Any>("add if min", "AGREGAR ELEMENTO SI ES EL MÁS BAJO"),
        arrayOf<Any>("remove greater", "ELIMINAR ARTÍCULOS GRANDES"),
        arrayOf<Any>("no db info", "Sin información de base de datos"),
        arrayOf<Any>("empty history", "la historia esta vacia"),
        arrayOf<Any>("username", "Nombre de usuario"),
        arrayOf<Any>("password", "Contraseña"),
        arrayOf<Any>("login", "ENTRAR"),
        arrayOf<Any>("signup", "REGISTRO"),
        arrayOf<Any>("server address", "Dirección del servidor"),
        arrayOf<Any>("server port", "Puerto"),
        arrayOf<Any>("auth tab", "Autenticación"),
        arrayOf<Any>("server settings tab", "Configuración del servidor"),
        arrayOf<Any>("server_not_answering", "El servidor está en silencio."),
        arrayOf<Any>("incorrect_name_or_pass", "Nombre de usuario o contraseña incorrecta."),
        arrayOf<Any>("try_again", "Intentar otra vez."),
        arrayOf<Any>("user_exists", "Ya existe un usuario con el mismo nombre.")
    )
    override fun getContents(): Array<Array<Any>> {
        return contents
    }
}