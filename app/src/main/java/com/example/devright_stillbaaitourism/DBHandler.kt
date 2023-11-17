package com.example.devright_stillbaaitourism

import java.sql.*
import java.util.*

class DBHandler {

    // ------------------- Declarations ------------------- //
    private var conn: Connection? = null
    private val username = BuildConfig.STIL_USERNAME
    private val password = BuildConfig.STIL_PASSWORD
    // ------------------- END Declarations ------------------- //

    /* ----------------------------------------------------------------- //
    * Creating the sql connection
    */
    fun getConnection() {
        val connectionProps = Properties()
        //Username for the DB
        connectionProps.put("user", username)
        //Password for the DB
        connectionProps.put("password", password)
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance()
            //connection string for the DB
            conn = DriverManager.getConnection(
                "jdbc:mysql://dedi1778.jnb1.host-h.net:3306/stil_app_db",
                connectionProps)
        } catch (ex: SQLException) {
            ex.printStackTrace()
        } catch (ex: Exception) {
            ex.printStackTrace()
        }
    }

    /* ----------------------------------------------------------------- //
    * Querying the eats table and puts the data in a list
    */
    fun fetchEatData() {
        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.EatDataList.isEmpty()) {

                // Close connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Eat_Table")

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

                            // Add EatData object to the list
                            GlobalClass.EatDataList.add(eatData)
                        }
                    }
                }

                // Fetching image URLs and associating them with the EatData objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Eat_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val eatId = imageResultSet.getInt("EAT_ID")
                            val imageUrl = imageResultSet.getString("EAT_IMAGE_URL")

                            // Find the corresponding EatData object by EAT_ID
                            val eatData = GlobalClass.EatDataList.find { it.EAT_ID == eatId }

                            // If an EatData object is found, add the image URL to its list
                            eatData?.EAT_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


    /* ----------------------------------------------------------------- //
    * Pulling Business data from the db and putting it in a list
    */
    fun fetchBusinessData(){
        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.BusinessDataList.isEmpty()) {

                // Closes connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Business_Table")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val businessData = BusinessData()
                            businessData.BUSINESS_ID = resultSet.getInt("BUSINESS_ID")
                            businessData.BUSINESS_NAME = resultSet.getString("BUSINESS_NAME")
                            businessData.BUSINESS_TELL_NUM =
                                resultSet.getString("BUSINESS_TELL_NUM")
                            businessData.BUSINESS_MOBILE_NUM =
                                resultSet.getString("BUSINESS_MOBILE_NUM")
                            businessData.BUSINESS_EMAIL = resultSet.getString("BUSINESS_EMAIL")
                            businessData.BUSINESS_WEBSITE = resultSet.getString("BUSINESS_WEBSITE")
                            businessData.BUSINESS_ADDRESS = resultSet.getString("BUSINESS_ADDRESS")
                            businessData.BUSINESS_CONTACT_PERSON =
                                resultSet.getString("BUSINESS_CONTACT_PERSON")
                            businessData.BUSINESS_DESCRIPTION =
                                resultSet.getString("BUSINESS_DESCRIPTION")
                            businessData.BUSINESS_CATEGORY_ID =
                                resultSet.getInt("BUSINESS_CATEGORY_ID")
                            GlobalClass.BusinessDataList.add(businessData)
                        }
                    }
                }

                // Now, fetch image URLs and associate them with the ActivityData objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Business_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val businessId = imageResultSet.getInt("BUSINESS_ID")
                            val imageUrl = imageResultSet.getString("BUSINESS_IMAGE_URL")

                            // Find the corresponding EatData object by EAT_ID
                            val businessData =
                                GlobalClass.BusinessDataList.find { it.BUSINESS_ID == businessId }

                            // If an ActivityData object is found, add the image URL to its list
                            businessData?.BUSINESS_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }

                // Create a map to store category types by category ID
                val categoryTypeMap = mutableMapOf<Int, String>()

                // Fetch Business Category Types and store them in the map
                conn?.createStatement().use { stmt ->
                    val categoryResultSet =
                        stmt?.executeQuery("SELECT * FROM Business_Category_Table")

                    if (categoryResultSet != null) {
                        while (categoryResultSet.next()) {
                            val categoryId = categoryResultSet.getInt("BUSINESS_CATEGORY_ID")
                            val categoryType = categoryResultSet.getString("BUSINESS_CATEGORY_TYPE")

                            // Store category types in the map
                            categoryTypeMap[categoryId] = categoryType
                        }
                    }
                }

                // Associate category types with ActivityData objects
                for (businessData in GlobalClass.BusinessDataList) {
                    val categoryId = businessData.BUSINESS_CATEGORY_ID
                    businessData.BUSINESS_CATEGORY_TYPE = categoryTypeMap[categoryId]
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


    /* ----------------------------------------------------------------- //
    * Fetching events data from the events table
    */
    fun fetchEventsData(){
        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.EventDataList.isEmpty()) {

                // Closes connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Event_Table")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val EventData = EventData()
                            EventData.EVENT_ID = resultSet.getInt("EVENT_ID")
                            EventData.EVENT_NAME = resultSet.getString("EVENT_NAME")
                            EventData.EVENT_NUM = resultSet.getString("EVENT_NUM")
                            EventData.EVENT_ADDRESS = resultSet.getString("EVENT_ADDRESS")
                            EventData.EVENT_DATE = resultSet.getDate("EVENT_DATE")
                            EventData.EVENT_STARTTIME = resultSet.getString("EVENT_STARTTIME")
                            EventData.EVENT_DESCRIPTION = resultSet.getString("EVENT_DESCRIPTION")
                            EventData.EVENT_DURATION = resultSet.getDouble("EVENT_DURATION")
                            EventData.EVENT_PERSON = resultSet.getString("EVENT_PERSON")
                            EventData.EVENT_WEBSITE = resultSet.getString("EVENT_WEBSITE")
                            EventData.EVENT_EMAIL = resultSet.getString("EVENT_EMAIL")
                            GlobalClass.EventDataList.add(EventData)
                        }
                    }
                }

                // Now, fetch image URLs and associate them with the eventData objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Event_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val eventId = imageResultSet.getInt("EVENT_ID")
                            val imageUrl = imageResultSet.getString("EVENT_IMAGE_URL")

                            // Find the corresponding EatData object by EVENT_ID
                            val eventData =
                                GlobalClass.EventDataList.find { it.EVENT_ID == eventId }

                            // If an ActivityData object is found, add the image URL to its list
                            eventData?.EVENT_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


    /* ----------------------------------------------------------------- //
    * Fetch activity data from the activity table
    */
    fun fetchActivityData(){

        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.ActivityDataList.isEmpty()) {

                // Closes connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Activity_Table")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val ActivityData = ActivityData()
                            ActivityData.ACTIVITY_ID = resultSet.getInt("ACTIVITY_ID")
                            ActivityData.ACTIVITY_NAME = resultSet.getString("ACTIVITY_NAME")
                            ActivityData.ACTIVITY_MOBILE_NUM =
                                resultSet.getString("ACTIVITY_MOBILE_NUM")
                            ActivityData.ACTIVITY_TEL_NUM = resultSet.getString("ACTIVITY_TEL_NUM")
                            ActivityData.ACTIVITY_ADDRESS = resultSet.getString("ACTIVITY_ADDRESS")
                            ActivityData.ACTIVITY_DESCRIPTION =
                                resultSet.getString("ACTIVITY_DESCRIPTION")
                            ActivityData.ACTIVITY_CONTACT_PERSON =
                                resultSet.getString("ACTIVITY_CONTACT_PERSON")
                            ActivityData.ACTIVITY_EMAIL = resultSet.getString("ACTIVITY_EMAIL")
                            ActivityData.ACTIVITY_WEBSITE = resultSet.getString("ACTIVITY_WEBSITE")
                            ActivityData.ACTIVITY_CATEGORY_ID =
                                resultSet.getInt("ACTIVITY_CATEGORY_ID")
                            GlobalClass.ActivityDataList.add(ActivityData)
                        }
                    }
                }

                // Now, fetch image URLs and associate them with the ActivityData objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Activity_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val activityId = imageResultSet.getInt("ACTIVITY_ID")
                            val imageUrl = imageResultSet.getString("ACTIVITY_IMAGE_URL")

                            // Find the corresponding EatData object by EAT_ID
                            val activityData =
                                GlobalClass.ActivityDataList.find { it.ACTIVITY_ID == activityId }

                            // If an ActivityData object is found, add the image URL to its list
                            activityData?.ACTIVITY_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }

                // Create a map to store category types by category ID
                val categoryTypeMap = mutableMapOf<Int, String>()

                // Fetch Activity Category Types and store them in the map
                conn?.createStatement().use { stmt ->
                    val categoryResultSet =
                        stmt?.executeQuery("SELECT * FROM Activity_Category_Table")

                    if (categoryResultSet != null) {
                        while (categoryResultSet.next()) {
                            val categoryId = categoryResultSet.getInt("ACTIVITY_CATEGORY_ID")
                            val categoryType = categoryResultSet.getString("ACTIVITY_CATEGORY_TYPE")

                            // Store category types in the map
                            categoryTypeMap[categoryId] = categoryType
                        }
                    }
                }

                // Associate category types with ActivityData objects
                for (activityData in GlobalClass.ActivityDataList) {
                    val categoryId = activityData.ACTIVITY_CATEGORY_ID
                    activityData.ACTIVITY_CATEGORY_TYPE = categoryTypeMap[categoryId]
                }
            }

        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }


    /* ----------------------------------------------------------------- //
    * Fetching stay data from the stay table
    */
    fun fetchStayData(){

        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.StayDataList.isEmpty()) {

                // Closes connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Stay_Table")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val stayData = StayData()
                            stayData.STAY_ID = resultSet.getInt("STAY_ID")
                            stayData.STAY_NAME = resultSet.getString("STAY_NAME")
                            stayData.STAY_TEL_NUM = resultSet.getString("STAY_TEL_NUM")
                            stayData.STAY_MOBILE_NUM = resultSet.getString("STAY_MOBILE_NUM")
                            stayData.STAY_EMAIL = resultSet.getString("STAY_EMAIL")
                            stayData.STAY_WEBSITE = resultSet.getString("STAY_WEBSITE")
                            stayData.STAY_ADDRESS = resultSet.getString("STAY_ADDRESS")
                            stayData.STAY_CONTACT_PERSON = resultSet.getString("STAY_CONTACT_PERSON")
                            stayData.STAY_DESCRIPTION = resultSet.getString("STAY_DESCRIPTION")
                            stayData.STAY_CATEGORY_ID = resultSet.getInt("STAY_CATEGORY_ID")
                            GlobalClass.StayDataList.add(stayData)
                        }
                    }
                }
                // Fetch Stay Category Types and associate them with StayData objects
                // Create a map to store category types by category ID
                val categoryTypeMap = mutableMapOf<Int, String>()

                // Fetch Stay Category Types and store them in the map
                conn?.createStatement().use { stmt ->
                    val categoryResultSet =
                        stmt?.executeQuery("SELECT * FROM Stay_Category_Table")

                    if (categoryResultSet != null) {
                        while (categoryResultSet.next()) {
                            val categoryId = categoryResultSet.getInt("STAY_CATEGORY_ID")
                            val categoryType = categoryResultSet.getString("STAY_CATEGORY_TYPE")

                            // Store category types in the map
                            categoryTypeMap[categoryId] = categoryType
                        }
                    }
                }

                // Associate category types with ActivityData objects
                for (stayData in GlobalClass.StayDataList) {
                    val categoryId = stayData.STAY_CATEGORY_ID
                    stayData.STAY_CATEGORY_TYPE = categoryTypeMap[categoryId]
                }
                // Fetch stay image URLs and associate them with EEL objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Stay_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val stayId = imageResultSet.getInt("STAY_ID")
                            val imageUrl = imageResultSet.getString("STAY_IMAGE_URL")

                            // Find the corresponding StayData object by Stay_ID
                            val stayData = GlobalClass.StayDataList.find { it.STAY_ID == stayId }

                            // If an StayData object is found, add the image URL to its list
                            stayData?.STAY_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }

    /* ----------------------------------------------------------------- //
    * Fetching listing data from the listing table
    */
    fun fetchListingData(){

        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.ListingDataList.isEmpty()) {

                // Closes connection automatically
                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Listing")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val listingImagesData = ListingImagesData()
                            listingImagesData.ID = resultSet.getInt("ID")
                            listingImagesData.LISTING_ID = resultSet.getInt("Listing_ID")
                            listingImagesData.link = resultSet.getString("link")
                            listingImagesData.title = resultSet.getString("title")
                            listingImagesData.content = resultSet.getString("content")
                            GlobalClass.ListingDataList.add(listingImagesData)
                        }
                    }
                }

                // Fetch eel image URLs and associate them with EEL objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Images")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val imageId = imageResultSet.getInt("Image_ID")
                            val imageUrl = imageResultSet.getString("sourceURL")

                            // Find the corresponding EatData object by EAT_ID
                            val listingImagesData =
                                GlobalClass.ListingDataList.find { it.LISTING_ID == imageId }

                            // If an Images object is found, add the image URL to its list
                            listingImagesData?.imageUrlList?.add(imageUrl)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }

    /* ----------------------------------------------------------------- //
    * Fetches all the eel data from the eel table
    */
    fun fetchEelData(){
        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.EelDataList.isEmpty()) {

                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Eel_Table")

                    // Executes until no more records are found
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
                // Fetch eel image URLs and associate them with EEL objects
                conn?.createStatement().use { stmt ->
                    val imageResultSet = stmt?.executeQuery("SELECT * FROM Eel_Image_Table")

                    if (imageResultSet != null) {
                        while (imageResultSet.next()) {
                            val eelId = imageResultSet.getInt("EEL_ID")
                            val imageUrl = imageResultSet.getString("EEL_IMAGE_URL")

                            // Find the corresponding EatData object by EAT_ID
                            val eelData = GlobalClass.EelDataList.find { it.EEL_ID == eelId }

                            // If an ActivityData object is found, add the image URL to its list
                            eelData?.EEL_IMAGE_URLS?.add(imageUrl)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }

    /* ----------------------------------------------------------------- //
    * Fetches contact information from the contact table
    */
    fun fetchContactData(){

        try {

            // Ensure the list is empty before making DB Query
            if(GlobalClass.ContactDataList.isEmpty()) {

                conn?.createStatement().use { stmt ->
                    val resultSet = stmt?.executeQuery("SELECT * FROM Contact_Table")

                    // Executes until no more records are found
                    if (resultSet != null) {
                        while (resultSet.next()) {
                            val ContactData = ContactData()
                            ContactData.CONTACT_ID = resultSet.getInt("CONTACT_ID")
                            ContactData.CONTACT_NUM = resultSet.getString("CONTACT_NUM")
                            ContactData.CONTACT_NAME = resultSet.getString("CONTACT_NAME")
                            ContactData.CONTACT_ADDRESS = resultSet.getString("CONTACT_ADDRESS")
                            ContactData.CONTACT_EMAIL = resultSet.getString("CONTACT_EMAIL")
                            ContactData.CONTACT_PERSON = resultSet.getString("CONTACT_PERSON")
                            GlobalClass.ContactDataList.add(ContactData)
                        }
                    }
                }
            }
        } catch (ex: SQLException) {
            ex.printStackTrace()
        }
    }
}
// ..........oooooooooo0000000000 END OF FILE 0000000000oooooooooo.......... //