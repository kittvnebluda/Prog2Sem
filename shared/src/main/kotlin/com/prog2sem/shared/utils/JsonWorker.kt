package com.prog2sem.shared.utils

import kotlinx.serialization.json.Json

object JsonWorker {
    val json = Json {
        prettyPrint = true
        encodeDefaults = true
    }
}

