package com.prog2sem.client.app.workers

import com.prog2sem.client.*
import com.prog2sem.client.persona.RndPersonBuilder
import com.prog2sem.shared.persona.PersonDirector
import com.prog2sem.shared.utils.Log
import java.util.concurrent.ExecutionException
import javax.swing.SwingWorker

class AddWorker : SwingWorker<Boolean, Unit>() {

    companion object {
        var person = PersonDirector(RndPersonBuilder()).createPerson()
    }
    override fun doInBackground(): Boolean {
            // TODO add not random persons
            return dbCommands.add(person, login, password)
    }

    override fun done() {
        // this method is called when the background
        // thread finishes execution
        try {
            val success = get()
            if (success)
                Log.i("Добавлено")
            else
                SwingApp.errorLabel.text = "Неудача"
        } catch (e: InterruptedException) {
            e.printStackTrace()
        } catch (e: ExecutionException) {
            e.printStackTrace()
        }
    }

}