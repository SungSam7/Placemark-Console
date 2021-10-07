package org.wit.placemark.console.main


import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkModel

private val logger = KotlinLogging.logger {}

var placemark = PlacemarkModel()
val placemarks = ArrayList<PlacemarkModel>()



var title = ""
var description = ""

fun main(args: Array<String>)
{
    logger.info { "Launching Placemark Console App" }


    println("Placemark Kotlin App Version 1.0")


    var input: Int



    do {
        input = menu()
        when(input) {
            1 -> addPlacemark()
            2 -> updatePlacemark()
            3 -> listAllPlacemarks()
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
    println()
    println("Update Placemark")
    println()
    print("Enter a new title for "+ placemark.title+": ")
    title = readLine()!!

    println()
    print("Enter a new description for "+placemark.description+": ")
    description = readLine()!!
    println("You entered "+placemark.title+" for title and "+placemark.description+" for the description")

    }




fun listAllPlacemarks() {
    println("List all Placemarks")
    println()
    placemarks.forEach {
        logger.info("${it}")
    }
}