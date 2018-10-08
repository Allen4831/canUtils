// BookInterface.aidl
package com.can.aidl;

// Declare any non-default types here with import statements
import com.can.aidl.Book;

interface BookInterface {
    //所有返回值类型前都不需要加任何东西，不管是任何数据
    List<Book> getBooks();
    //传参除了Java基本类型以及String，CharSequence外，都需要加定向tag(in out inout)
    void addBook(in Book book);
}
