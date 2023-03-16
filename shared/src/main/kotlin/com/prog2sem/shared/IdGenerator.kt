package com.prog2sem.shared

import kotlinx.serialization.Serializable
import java.util.*

@Serializable
class IdGenerator {
    private var maxId = 0
    private var remIds: List<Int> = listOf()

    init {
        removedIds = LinkedList(remIds)
        previous_id = maxId
    }

    companion object {
        private var previous_id: Int = 0
        private var removedIds: Queue<Int> = LinkedList()
    }

    fun getId(): Int{
        return if (removedIds.size != 0)
            removedIds.remove()
        else {
            ++previous_id
        }
    }

    fun clear() {
        previous_id = 0
        removedIds.clear()
    }

    fun save(){
        maxId = previous_id
        remIds = removedIds.toList()
    }


    fun newRemovedId(id: Int){
        removedIds.add(id)
    }

}