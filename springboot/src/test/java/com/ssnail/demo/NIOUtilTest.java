package com.ssnail.demo;

import org.junit.Test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * @ClassName NIOUtilTest
 * @Description TODO
 * @Author shnstt
 * @Date 2019/6/20 15:38
 * @Version 1.0
 **/
public class NIOUtilTest {

    @Test
    public void copyTest(){
        try {
            FileInputStream inputStream = new FileInputStream("NIOUtilTest.txt");
            FileOutputStream outputStream = new FileOutputStream("C:\\temp\\test.txt");
            FileChannel inputStreamChannel = inputStream.getChannel();
            FileChannel outputStreamChannel = outputStream.getChannel();
            outputStreamChannel.transferFrom(inputStreamChannel,0,inputStreamChannel.size());
            inputStream.close();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void writeTest(){
        try {
            FileOutputStream outputStream = new FileOutputStream("NIOUtilTest.txt");
            FileChannel channel = outputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
            String context = "hello word!哈哈";
            byteBuffer.put(context.getBytes());
            byteBuffer.flip();
            channel.write(byteBuffer);
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void readTest(){
        try {
            File file = new File("NIOUtilTest.txt");
            FileInputStream inputStream = new FileInputStream(file);
            FileChannel channel = inputStream.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) file.length());
            channel.read(byteBuffer);
            String context = new String(byteBuffer.array());
            System.out.println(context);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
