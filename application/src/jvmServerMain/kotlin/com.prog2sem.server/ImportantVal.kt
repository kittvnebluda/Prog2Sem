package com.prog2sem.server

import kotlinx.serialization.Serializable

/**
 * @param filePath
 * @param maxId
 * @param removesIds
 */
@Serializable
data class ImportantVal (
    val filePath: String = "Important",
    var maxId: Int = 1,
    var removesIds: MutableList<Int> = mutableListOf()
)