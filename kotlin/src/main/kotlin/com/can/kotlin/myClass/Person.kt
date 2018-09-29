package com.can.kotlin.myClass

/**
 * Created by CAN on 18-9-27.
 */
open class Person {

    var name : String = ""

    var no = 100
        get() = field+1
        set(value) {
            field = if(value<10)
                value
            else
                -1
        }

    var age : Int =  1


}