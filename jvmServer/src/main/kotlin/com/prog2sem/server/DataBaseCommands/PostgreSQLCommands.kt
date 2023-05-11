package com.prog2sem.server.DataBaseCommands

import com.prog2sem.shared.utils.Log
import java.sql.Connection
import java.sql.DriverManager
import java.sql.PreparedStatement
import java.sql.ResultSet


/*

TABLESPACE pg_default;

ALTER TABLE IF EXISTS public."TestJDBC"
    OWNER to postgres;

*/

/*
--CREATE SEQUENCE id START 1;
--insert into public."TestJDBC" (id, name, login, password) values(nextval('id'), 'Alex', 'me', '1234')
--select * from public."TestJDBC"
--delete from public."TestJDBC"
--drop SEQUENCE id;
*/

object PostgreSQLCommands {

    private lateinit var SQLConnection: Connection

    const val createTableOfPersons = "CREATE TABLE IF NOT EXISTS public.\"TestJDBC\" \n" +
            "            (\n" +
            "                id integer NOT NULL,\n" +
            "                creationTime character varying,\n" +
            "                name character varying,\n" +
            "                weight integer,\n" +
            "                height integer,\n" +
            "                birthday character varying,\n" +
            "                hairColor character varying,\n" +
            "                coordinates character varying,\n" +
            "                location character varying,\n" +
            "                login character varying,\n" +
            "                pass character varying,\n" +
            "                CONSTRAINT \"TestJDBC_pkey\" PRIMARY KEY (id)\n" +
            "            )"
    const val createTableOfLogins = "CREATE TABLE IF NOT EXISTS public.\"TestLogin\" \n" +
            "            (\n" +
            "                id integer NOT NULL,\n" +
            "                login character varying,\n" +
            "                pass character varying,\n" +
            "                CONSTRAINT \"TestLogin_pkey\" PRIMARY KEY (id)\n" +
            "            )"

    const val getAllPersons = "select * from public.\"TestJDBC\""

    val createSequancePersons = "CREATE SEQUENCE public.id START 1"
    val createSequanceLogins = "CREATE SEQUENCE public.id_log START 1"

   // val getId = "select public.id.nextval "

    val personKeys = listOf("id", "createTime", "name", "weight", "height", "birthday", "hairColor", "coordinates", "location", "login", "pass")

    val loginKeys = listOf("id", "login", "password")

    const val addPerson = "insert into public.\"TestJDBC\" values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)"

    /*(${personKeys[1]}, ${personKeys[2]}, ${personKeys[3]}, ${personKeys[4]}, ${personKeys[5]}, " +
            "${personKeys[6]}, ${personKeys[7]}, ${personKeys[8]}, ${personKeys[9]}, ${personKeys[10]})*/

    fun startConnection(){
        SQLConnection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/", "postgres", "prog2sem")

        Log.i(if (!SQLConnection.isClosed) "Успешно подключились к базе дынных" else "Соединение не установлено")
    }

    fun useUpdateQueryStat(script: String): Boolean{
        val stat = SQLConnection.createStatement()

        return stat.executeUpdate(script) > 0
    }

    fun useUpdateStat(script: String): Boolean{
        val stat = SQLConnection.createStatement()

        return stat.execute(script)
    }

    fun useUpdateQueryPrepare(script: String, keys: List<String>, args: HashMap<String, Any>): Boolean{

        val preparedStatement = SQLConnection.prepareStatement(script)

        for (i in keys.indices){
            preparedStatement.setObject(i + 1, args[keys[i]])
        }

        return preparedStatement.executeUpdate() > 0
    }

    fun getId(): Long{
        val sql = "select nextval(\'id\')"
        val ps: PreparedStatement = SQLConnection.prepareStatement(sql)
        val rs = ps.executeQuery()
        if (rs.next())
            return rs.getLong(1)
        return 0
    }

    fun getLoginId(): Long{
        val sql = "select nextval(\'id_log\')"
        val ps: PreparedStatement = SQLConnection.prepareStatement(sql)
        val rs = ps.executeQuery()
        if (rs.next())
            return rs.getLong(1)
        return 0
    }

    fun useStatement(script: String):Boolean {
        val statement = SQLConnection.createStatement()

        return statement.execute(script)
    }

    fun getAllFromTable(): ResultSet? {
        val all = "select * from public.\"TestJDBC\""

        val statement = SQLConnection.createStatement()

        return if (statement.execute(all)) statement.executeQuery(all) else null
    }
}