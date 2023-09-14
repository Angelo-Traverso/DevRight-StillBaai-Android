package com.example.devright_stillbaaitourism

import java.sql.*
import java.util.*

const val BASEURL = ""
class DBHandler {
    internal var conn: Connection? = null
    internal var username = "k5g2h_zsu89" // provide the username
    internal var password = "G857456FD325g6p" // provide the corresponding password

    //Creates the SQL connection, Suspen is for Asynch
    suspend fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username) //Username for the DB
        connectionProps.put("password", password) //Password for the DB
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            //connection string for the DB
            conn = DriverManager.getConnection(
                "jdbc:mysql://dedi1778.jnb1.host-h.net:306/stil_app_db",
                connectionProps)
        } catch (ex: SQLException) {
            // handle any errors
            ex.printStackTrace()
        } catch (ex: Exception) {
            // handle any errors
            ex.printStackTrace()
        }
    }
    //Queries the eats table and puts the data in a list, Suspen is for Asynch
    suspend fun fetchEatData(conn: Connection): List<EatData> {
        //Holds the data from the eats table.
        val eatDataList = mutableListOf<EatData>()
        try {
            //closes connection automatically
            conn.createStatement().use { stmt ->
                val resultSet = stmt.executeQuery("SELECT * FROM eat")
                //goes until no more records are found
                while (resultSet.next()) {
                    val eatData = EatData()
                    eatData.EAT_ID = resultSet.getInt("")
                    eatData.EAT_NAME = resultSet.getString("")
                    eatData.EAT_TEL_NUM = resultSet.getString("")
                    eatData.EAT_MOBILE_NUM = resultSet.getString("")
                    eatData.EAT_EMAIL = resultSet.getString("")
                    eatData.EAT_WEBSITE = resultSet.getString("")
                    eatData.EAT_ADDRESS = resultSet.getString("")
                    eatData.EAT_CONTACT_PERSON = resultSet.getString("")
                    eatData.EAT_DESCRIPTION = resultSet.getString("")
                    eatData.EAT_CATEGORY_ID = resultSet.getInt("")
                    eatDataList.add(eatData)
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
        return eatDataList
    }
}
