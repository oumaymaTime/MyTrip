package com.example.stage

class Reservation {

    var firstName: String? = null
    var lastName: String? = null
    var phone: String? = null
    var nbPer: String? = null
    var email: String? = null

    constructor(firstName: String, lastName: String, email: String, phone: String, nbPer: String) {
        this.firstName = firstName
        this.lastName = lastName
        this.email = email
        this.nbPer = nbPer
        this.phone = phone
    }
}