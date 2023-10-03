package com.example.devright_stillbaaitourism

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.sql.*
import java.util.*

const val BASEURL = ""
class DBHandler {
    internal var conn: Connection? = null
    internal var username = "k5g2h_zsu89" // provide the username
    internal var password = "G857456FD325g6p" // provide the corresponding password

    //Creates the SQL connection, Suspen is for Asynch
    suspend fun getConnection(): Connection? {
        val connectionProps = Properties()
        connectionProps["user"] = username
        connectionProps["password"] = password
        try {
            val driverInstance = Class.forName("com.mysql.jdbc.Driver").newInstance()
            println(driverInstance)
            conn = DriverManager.getConnection(
                "jdbc:mysql://dedi1778.jnb1.host-h.net:3306/stil_app_db",
                connectionProps
            )
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
        return conn
    }

        //Queries the eats table and puts the data in a list, Suspen is for Asynch
        suspend fun fetchEatData(conn: Connection): List<EatData> {
            //Holds the data from the eats table.
            val eatDataList = mutableListOf<EatData>()
            try {
                //closes connection automatically
                conn.createStatement().use { stmt ->
                    val resultSet = stmt.executeQuery("SELECT * FROM Eat_Table")
                    //goes until no more records are found
                    while (resultSet.next()) {
                        val eatData = EatData()
                        eatData.EAT_ID = resultSet.getInt("EAT_ID")
                        eatData.EAT_NAME = resultSet.getString("EAT_NAME")
                        eatData.EAT_TEL_NUM = resultSet.getString("EAT_TEL_NUM")
                        eatData.EAT_MOBILE_NUM = resultSet.getString("EAT_MOBILE_NUM")
                        eatData.EAT_EMAIL = resultSet.getString("EAT_EMAIL")
                        eatData.EAT_WEBSITE = resultSet.getString("EAT_WEBSITE")
                        eatData.EAT_ADDRESS = resultSet.getString("EAT_ADDRESS")
                        eatData.EAT_CONTACT_PERSON = resultSet.getString("EAT_CONTACT_PERSON")
                        eatData.EAT_DESCRIPTION = resultSet.getString("EAT_DESCRIPTION")
                        eatData.EAT_CATEGORY_ID = resultSet.getInt("EAT_CATEGORY_ID")
                        eatDataList.add(eatData)
                    }
                }
            } catch (ex: SQLException) {
                ex.printStackTrace()
            }
            return eatDataList
        }
    }
