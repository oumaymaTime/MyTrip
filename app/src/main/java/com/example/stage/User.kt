package com.example.stage
//
////class UserModel(val userid: String, val name: String, val age: String)
//data class User(val id: Int = -1, val name: String, val email: String, val password: String)


class User {
//    var id: Int = -1
    var name: String? = null
    var email: String? = null
    var password: String? = null

    constructor( name:String,email: String, password: String) {
       this.name = name
        this.email = email
        this.password = password
    }
}