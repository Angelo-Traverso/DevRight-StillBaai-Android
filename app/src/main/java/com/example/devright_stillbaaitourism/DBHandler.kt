package com.example.devright_stillbaaitourism

import java.sql.*
import java.util.*

const val BASEURL = ""
class DBHandler {
    internal var conn: Connection? = null
    internal var username = "k5g2h_zsu89" // provide the username
    internal var password = "G857456FD325g6p" // provide the corresponding password

    //Creates the SQL connection, Suspen is for Asynch
    fun getConnection() {
        val connectionProps = Properties()
        connectionProps.put("user", username) //Username for the DB
        connectionProps.put("password", password) //Password for the DB
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            //connection string for the DB
            conn = DriverManager.getConnection(
                "jdbc:mysql://dedi1778.jnb1.host-h.net:3306/stil_app_db",
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
    fun fetchEatData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Eat_Table")
                //goes until no more records are found
                if (resultSet != null) {
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
                        GlobalClass.EatDataList.add(eatData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //pulling Bussinesses data from the db and putting it in a list
    fun fetchBusinessData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Business_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val businessData = BusinessData()
                        businessData.BUSINESS_ID = resultSet.getInt("BUSINESS_ID")
                        businessData.BUSINESS_NAME = resultSet.getString("BUSINESS_NAME")
                        businessData.BUSINESS_TELL_NUM = resultSet.getString("BUSINESS_TELL_NUM")
                        businessData.BUSINESS_MOBILE_NUM = resultSet.getString("BUSINESS_MOBILE_NUM")
                        businessData.BUSINESS_EMAIL = resultSet.getString("BUSINESS_EMAIL")
                        businessData.BUSINESS_WEBSITE = resultSet.getString("BUSINESS_WEBSITE")
                        businessData.BUSINESS_ADDRESS = resultSet.getString("BUSINESS_ADDRESS")
                        businessData.BUSINESS_CONTACT_PERSON = resultSet.getString("BUSINESS_CONTACT_PERSON")
                        businessData.BUSINESS_DESCRIPTION = resultSet.getString("BUSINESS_DESCRIPTION")
                        businessData.BUSINESS_CATEGORY_ID = resultSet.getInt("BUSINESS_CATEGORY_ID")
                        GlobalClass.BusinessDataList.add(businessData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //getting events data from the events table
    fun fetchEventsData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Event_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val EventData = EventData()
                        EventData.EVENT_ID = resultSet.getInt("EVENT_ID")
                        EventData.EVENT_NAME = resultSet.getString("EVENT_NAME")
                        EventData.EVENT_NUM = resultSet.getString("EVENT_NUM")
                        EventData.EVENT_ADDRESS = resultSet.getString("EVENT_ADDRESS")
                        EventData.EVENT_DATE = resultSet.getDate("EVENT_DATE")
                        EventData.EVENT_DESCRIPTION = resultSet.getString("EVENT_DESCRIPTION")
                        EventData.EVENT_DURATION = resultSet.getDouble("EVENT_DURATION")
                        EventData.EVENT_PERSON = resultSet.getString("EVENT_PERSON")
                        EventData.EVENT_WEBSITE = resultSet.getString("EVENT_WEBSITE")
                        EventData.EVENT_EMAIL = resultSet.getNString("EVENT_EMAIL")
                        GlobalClass.EventDataList.add(EventData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //fecth activity data from the activity table
    fun fetchActivityData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Activity_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val ActivityData = ActivityData()
                        ActivityData.ACTIVITY_ID = resultSet.getInt("ACTIVITY_ID")
                        ActivityData.ACTIVITY_NAME = resultSet.getString("ACTIVITY_NAME")
                        ActivityData.ACTIVITY_MOBILE_NUM = resultSet.getString("ACTIVITY_MOBILE_NUM")
                        ActivityData.ACTIVITY_TEL_NUM = resultSet.getString("ACTIVITY_TEL_NUM")
                        ActivityData.ACTIVITY_ADDRESS = resultSet.getNString("ACTIVITY_ADDRESS")
                        ActivityData.ACTIVITY_DESCRIPTION = resultSet.getString("ACTIVITY_DESCRIPTION")
                        ActivityData.ACTIVITY_CONTACT_PERSON = resultSet.getString("ACTIVITY_CONTACT_PERSON")
                        ActivityData.ACTIVITY_EMAIL = resultSet.getString("ACTIVITY_EMAIL")
                        ActivityData.ACTIVITY_WEBSITE = resultSet.getString("ACTIVITY_WEBSITE")
                        ActivityData.ACTIVITY_CATEGORY_ID = resultSet.getInt("ACTIVITY_CATEGORY_ID")
                        GlobalClass.ActivityDataList.add(ActivityData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //fetching stay data from the stay table
    fun fetchStayData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Stay_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val StayData = StayData()
                        StayData.STAY_ID = resultSet.getString("STAY_ID")
                        StayData.STAY_NAME = resultSet.getString("STAY_NAME")
                        StayData.STAY_TEL_NUM = resultSet.getString("STAY_TEL_NUM")
                        StayData.STAY_MOBILE_NUM = resultSet.getString("STAY_MOBILE_NUM")
                        StayData.STAY_EMAIL = resultSet.getNString("STAY_EMAIL")
                        StayData.STAY_WEBSITE = resultSet.getString("STAY_WEBSITE")
                        StayData.STAY_ADDRESS = resultSet.getString("STAY_ADDRESS")
                        StayData.STAY_CONTACT_PERSON = resultSet.getString("STAY_CONTACT_PERSON")
                        StayData.STAY_DESCRIPTION = resultSet.getString("STAY_DESCRIPTION")
                        StayData.STAY_CATEGORY_ID = resultSet.getInt("STAY_CATEGORY_ID")
                        GlobalClass.StayDataList.add(StayData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //fetches all the eel data from the eel table
    fun fetchEelData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Activity_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val EelData = EelData()
                        EelData.EEL_ID = resultSet.getInt("EEL_ID")
                        EelData.EEL_NAME = resultSet.getString("EEL_NAME")
                        EelData.EEL_ADDRESS = resultSet.getString("EEL_ADDRESS")
                        EelData.EEL_DESCRIPTION = resultSet.getString("EEL_DESCRIPTION")
                        EelData.EEL_CONTACT_NUM = resultSet.getNString("EEL_CONTACT_NUM")
                        GlobalClass.EelDataList.add(EelData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
    //fetches contact information from the contact table
    fun fetchContactData(){
        try {
            //closes connection automatically
            conn?.createStatement().use { stmt ->
                val resultSet = stmt?.executeQuery("SELECT * FROM Contact_Table")
                //goes until no more records are found
                if (resultSet != null) {
                    while (resultSet.next()) {
                        val ContactData = ContactData()
                        ContactData.CONTACT_ID = resultSet.getInt("CONTACT_ID")
                        ContactData.CONTACT_NUM = resultSet.getNString("CONTACT_NUM")
                        ContactData.CONTACT_NAME = resultSet.getString("CONTACT_NAME")
                        ContactData.CONTACT_ADDRESS = resultSet.getString("CONTACT_ADDRESS")
                        ContactData.CONTACT_EMAIL = resultSet.getString("CONTACT_EMAIL")
                        ContactData.CONTACT_PERSON = resultSet.getNString("CONTACT_PERSON")
                        GlobalClass.ContactDataList.add(ContactData)
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


}