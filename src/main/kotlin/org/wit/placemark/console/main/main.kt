package org.wit.placemark.console.main


import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkMemStore
import org.wit.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}

val placemarks = PlacemarkMemStore()




var title = ""
var description = ""

fun main(args: Array<String>)
{
    dummyData()
    logger.info { "Launching Placemark Console App" }


    println("Placemark Kotlin App Version 1.0")


    var input: Int



    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
            4 -> searchPlacemark()
            -1 -> println("Exiting App")
            else -> println("Invalid Option")
        }
        println()
    } while (input != -1)
    logger.info { "Shutting Down Placemark Console App" }
}




fun menu() : Int {
    var option: Int
    var input: String? = null

    println("Main Menu")
    println("1. Add Placemark")
    println("2. Update Placemark")
    println("3. List all of the Placemarks")
    println("4. Search by Id")
    println("-1. Exit")
    println()
    print("Enter an integer : ")
    input = readLine()!!
    option = if (input.toIntOrNull() != null && !input.isEmpty())
        input.toInt()
    else

        -9

    return option

}

fun addPlacemark(){


    placemark++
    println("Add a placemark")
    println()
    print("Enter a title: ")
    placemark.title = readLine()!!
    println()
    print("Enter a description: : ")
    placemark.description = readLine()!!


    if(placemark.title.isNotEmpty() && placemark.description.isNotEmpty()){
        placemarks.add(placemark.copy())
    }
    else
        logger.info("Placemark NOT Added!!")





    println("You entered "+placemark.title+" for title and "+placemark.description+" for the description")


}


fun updatePlacemark() {
    println("Update Placemark")
    println()
    listAllPlacemarks()
    var searchId = getId()
    val aPlacemark = search(searchId)
    var tempTitle : String?
    var tempDescription : String?

    if(aPlacemark != null) {
        print("Enter a new Title for [ " + aPlacemark.title + " ] : ")
        tempTitle = readLine()!!
        print("Enter a new Description for [ " + aPlacemark.description + " ] : ")
        tempDescription = readLine()!!

        if (!tempTitle.isNullOrEmpty() && !tempDescription.isNullOrEmpty()) {
            aPlacemark.title = tempTitle
            aPlacemark.description = tempDescription
            println(
                "You updated [ " + aPlacemark.title + " ] for title " +
                        "and [ " + aPlacemark.description + " ] for description")
            logger.info("Placemark Updated : [ $aPlacemark ]")
        }
        else
            logger.info("Placemark Not Updated")
    }
    else
        println("Placemark Not Updated...")
}



fun listAllPlacemarks() {
    println("List all Placemarks")
    println()
    placemarks.forEach {
        logger.info("${it}")
    }
    println()
}

fun getId(): Long{
    var strId : String?
    var searchId : Long
    print("Enter id to Search/Update: ")
    strId = readLine()!!
    searchId = if(strId.toLongOrNull() != null && !strId.isEmpty())
        strId.toLong()
    else
        -9
    return searchId

}


fun search(id: Long) : PlacemarkModel? {
    var foundPlacemark: PlacemarkModel? = placemarks.find { p -> p.id == id }
    return foundPlacemark
}

fun searchPlacemark() {

    var searchId = getId()
    val aPlacemark = search(searchId)

    if(aPlacemark != null)
        println("Placemark Details [ $aPlacemark ]")
    else
        println("Placemark Not Found...")
}


fun dummyData() {
    placemarks.add(PlacemarkModel(1, "Dungarvan", "A town in the West of Waterford."))
    placemarks.add(PlacemarkModel(2, "Abbeyside", "A village connected to Dungarvan."))
    placemarks.add(PlacemarkModel(3, "Ballinroad", "A suburb of the Dungarvan area."))
}