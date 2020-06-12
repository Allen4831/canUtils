package com.can.http.utils

import com.can.http.bean.LoadInfoBean
import java.io.RandomAccessFile
import java.net.HttpURLConnection
import java.net.URL

/**
 * Created by CAN on 2020/6/9.
 */
/*
开启一个下载文件的线程
 */
class DownLoadTask(private val downLoadInfoBean: LoadInfoBean) : Runnable {

    override fun run() {
        val url = URL(downLoadInfoBean.downLoadUrl)
        val httpUrlConnection = url.openConnection() as HttpURLConnection
        //设置请求数据的区间
        httpUrlConnection.setRequestProperty("range", "bytes=${downLoadInfoBean.downLoadStartLocation}-${downLoadInfoBean.downLoadEndLocation}")
        httpUrlConnection.requestMethod = "GET"
        httpUrlConnection.setRequestProperty("Charset", "UTF-8")
        httpUrlConnection.connectTimeout = 10000
        httpUrlConnection.readTimeout = 10000

        //读取流
        val inputSteam = httpUrlConnection.inputStream
        //保存输入流的文件
        val randomAccessFile = RandomAccessFile(downLoadInfoBean.downLoadFilePath, "rwd")
        //设置文件的长度
        randomAccessFile.setLength(httpUrlConnection.contentLength.toLong())
        //当前下载的位置
        val currentLocation = downLoadInfoBean.downLoadStartLocation

        //定位写入文件的位置
        randomAccessFile.seek(currentLocation.toLong())
        val buffer = byteArrayOf()
        //读取流
        var length = inputSteam.read(buffer)
        while (length != -1) {
            length = inputSteam.read(buffer)
            randomAccessFile.write(buffer, 0, length)
        }
        randomAccessFile.close()
        inputSteam.close()
    }
}