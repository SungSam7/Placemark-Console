package org.wit.placemark.console.controllers

import mu.KotlinLogging
import org.wit.placemark.console.models.PlacemarkJSONStore


import org.wit.placemark.console.models.PlacemarkMemStore
import org.wit.placemark.console.models.PlacemarkModel
import org.wit.placemark.console.views.PlacemarkView


class PlacemarkController {

var choice : String = ""




    fun start()
    {
        println("Launching Placemark Console App")
        println("Placemark Kotlin App Version 1.0")
        println()

        var input: Int

        do {
            input = org.wit.placemark.console.main.placemarkView.menu()
            when(input) {
                1 -> add()
                2 -> update()
                3 -> list()
                4 -> search()
                -99 -> dummyData()
                -1 -> println("Exiting App")
                else -> println("Invalid Option")
            }
            println()
        } while (input != -1)
        org.wit.placemark.console.main.logger.info { "Shutting Down Placemark Console App" }
    }

   // val placemarks = PlacemarkMemStore()
    val placemarks = PlacemarkJSONStore()

    val placemarkView = PlacemarkView()
    val logger = KotlinLogging.logger {}



    fun menu() :Int { return placemarkView.menu() }

    fun add(){
        var aPlacemark = PlacemarkModel()

        if (placemarkView.addPlacemarkData(aPlacemark))
            placemarks.create(aPlacemark)
        else
            println("Placemark Not Added")
    }

    fun list() {
        placemarkView.listPlacemarks(placemarks)
    }

    fun update() {

        placemarkView.listPlacemarks(placemarks)
        var searchId = placemarkView.getId()
        val aPlacemark = search(searchId)

        if(aPlacemark != null) {
            if(placemarkView.updatePlacemarkData(aPlacemark)) {
                placemarks.update(aPlacemark)
                placemarkView.showPlacemark(aPlacemark)
                println("Placemark Updated : [ $aPlacemark ]")
            }
            else
                println("Placemark Not Updated")
        }
        else
            println("Placemark Not Updated...")
    }

    fun search() {
        val aPlacemark = search(placemarkView.getId())!!
        placemarkView.showPlacemark(aPlacemark)
    }


    fun search(id: Long) : PlacemarkModel? {
        var foundPlacemark = placemarks.findOne(id)
        return foundPlacemark
    }

    fun dummyData() {
        placemarks.create(PlacemarkModel(title = "New York New York", description = "So Good They Named It Twice"))
        placemarks.create(PlacemarkModel(title= "Ring of Kerry", description = "Some place in the Kingdom"))
        placemarks.create(PlacemarkModel(title = "Waterford City", description = "You get great Blaas Here!!"))
    }
}