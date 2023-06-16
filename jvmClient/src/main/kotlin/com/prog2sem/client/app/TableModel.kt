package com.prog2sem.client.app

import com.prog2sem.shared.FromServer
import javax.swing.table.DefaultTableModel




class TableModel(private val now: List<FromServer>) : DefaultTableModel(TableInfo.keysWithNoLogin, 0) {
        init {
            for (i in 0..now.size - 2) {
                HomePane.table.setValueAt(now[i].id, i, 0)
                HomePane.table.setValueAt(now[i].creteTime, i, 1)
                HomePane.table.setValueAt(now[i].person.name, i, 2)
                HomePane.table.setValueAt(now[i].person.weight, i, 3)
                HomePane.table.setValueAt(now[i].person.height, i, 4)
                HomePane.table.setValueAt(now[i].person.birthday, i, 5)
                HomePane.table.setValueAt(now[i].person.hairColor, i, 6)
                HomePane.table.setValueAt(now[i].person.coordinates.toString(), i, 7)
                HomePane.table.setValueAt(now[i].person.location.toString(), i, 8)
            }
        }
}

