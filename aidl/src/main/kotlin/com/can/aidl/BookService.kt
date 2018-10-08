package com.can.aidl

import android.app.Service
import android.content.Intent
import android.os.IBinder
import android.util.Log

/**
 * 服务端Service
 * Created by CAN on 18-10-8.
 */
class BookService : Service() {

    private val s = this.javaClass.toString()

    private var bookList = mutableListOf<Book>()

    override fun onCreate() {
        super.onCreate()
        val book = Book()
        book.name = "android"
        book.price = 2.222
        bookList.add(book)
        Log.e(s, "create BookService")
        bookManager.addBook(book)
    }

    private val bookManager = object : BookInterface.Stub(){
        override fun getBooks(): MutableList<Book> {
            synchronized(this){
                return bookList
            }
        }

        override fun addBook(book: Book?) {
            synchronized(this){
                if(book!=null){
                    book.price = 2.34
                    book.name = "我改了名字"
                    if(!bookList.contains(book)){
                        bookList.add(book)
                        Log.e(s, "addBooks() method , now the list is : " + bookList.size)
                    }
                }
            }
        }
    }

    override fun onBind(p0: Intent?): IBinder {
        return bookManager
    }
}