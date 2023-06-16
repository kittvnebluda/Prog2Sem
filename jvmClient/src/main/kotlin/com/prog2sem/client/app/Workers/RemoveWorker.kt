package com.prog2sem.client.app.Workers

import com.prog2sem.client.SwingApp
import com.prog2sem.client.app.HomePane
import com.prog2sem.client.dbCommands
import com.prog2sem.client.login
import com.prog2sem.client.password
import com.prog2sem.shared.utils.Log
import java.util.concurrent.ExecutionException
import javax.swing.SwingWorker
import javax.swing.table.DefaultTableModel

class RemoveWorker: SwingWorker<Boolean, Unit>() {

    companion object{
        var id = 0
        var canNext = true;
    }

    override fun doInBackground(): Boolean {
        // TODO add not random persons
        canNext = false
        println(id)
        return dbCommands.removeId(HomePane.table.getValueAt(id, 0) as Int, login, password)
    }

    override fun done() {
        // this method is called when the background
        // thread finishes execution
        try {
            val success = get()
            if (success) {
                canNext = true
                val model = HomePane.table.model as DefaultTableModel
                model.removeRow(id)
                Log.i("Удалено")
            }
            else {
                canNext = true
                SwingApp.errorLabel.text = "Это не ваша персона"
                Log.i("Неудача")
            }
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }


}