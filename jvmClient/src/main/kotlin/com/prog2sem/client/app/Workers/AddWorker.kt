package com.prog2sem.client.app.Workers

import com.prog2sem.client.*
import com.prog2sem.client.persona.RndPersonBuilder
import com.prog2sem.shared.persona.PersonDirector
import com.prog2sem.shared.utils.Log
import java.net.InetSocketAddress
import java.util.concurrent.ExecutionException
import javax.swing.SwingWorker

class AddWorker : SwingWorker<Boolean, Unit>() {
    override fun doInBackground(): Boolean {
            // TODO add not random persons
            val person = PersonDirector(RndPersonBuilder()).createPerson()
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