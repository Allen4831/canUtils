package com.can.aidl

import android.os.Parcel
import android.os.Parcelable

/**
 * Created by CAN on 18-10-8.
 */
class Book() : Parcelable {
      var name : String = ""
      var price: Double = 0.0

    constructor(parcel: Parcel) : this() {
        name = parcel.readString()
        price = parcel.readDouble()
    }

    override fun writeToParcel(parcel: Parcel, flags: Int) {
        parcel.writeString(name)
        parcel.writeDouble(price)
    }

    override fun describeContents(): Int {
        return 0
    }

    companion object CREATOR : Parcelable.Creator<Book> {
        override fun createFromParcel(parcel: Parcel): Book {
            return Book(parcel)
        }

        override fun newArray(size: Int): Array<Book?> {
            return arrayOfNulls(size)
        }
    }

    /**
     * 参数是一个Parcel,用它来存储与传输数据
     */
    fun readFromParcel(dest:Parcel){
        name = dest.readString()
        price = dest.readDouble()
    }

    override fun toString(): String {
        return "name : $name , price : $price"
    }
}