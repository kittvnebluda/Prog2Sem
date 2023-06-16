package com.prog2sem.client.app.workers

import com.prog2sem.client.SwingApp
import com.prog2sem.client.app.HomePane
import com.prog2sem.client.dbCommands
import com.prog2sem.shared.utils.Log
import java.util.concurrent.ExecutionException
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel

class ClearWorker : SwingWorker<Boolean, Unit>() {


    override fun doInBackground(): Boolean {
        // TODO add not random persons
        return dbCommands.clear()
    }

    override fun done() {
        // this method is called when the background
        // thread finishes execution
        try {
            val success = get()
            if (success) {
                val model = HomePane.table.model as DefaultTableModel
                while (HomePane.table.getValueAt(0, 0) != null){
                    model.removeRow(0)
                    model.addRow(arrayOf())
                }
                Log.i("Удалено")
            }
            else {
                SwingApp.errorLabel.text = "Неудача"
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }


}