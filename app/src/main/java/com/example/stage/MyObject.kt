package com.example.stage

//class MyObject(private val name:String, val text: String, private val imageUrl: String)//getters & setters
//{
//
//    fun getImageUrl(): String {
//    return imageUrl
//}
//}

class MyObject {
    var name: String? =null
    var text: String? = null
    var localisation: String? = null
    var price: String? = null
    private var imageUrl: String? = null

    constructor(name:String, text: String, localisation: String, price: String, imageUrl: String) {
        this.name =  name
        this.text = text
        this.localisation= localisation
        this.price = price
        this.imageUrl = imageUrl
    }

//    constructor(name: String,imageUrl: String){
//        this.name
//        this.imageUrl
//    }


    fun getImageUrl(): String {
        return imageUrl.toString()
    }

}