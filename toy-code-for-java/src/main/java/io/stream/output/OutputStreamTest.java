package io.stream.output;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * @author Zhangjie
 * @description 写入内容至文件
 * @date 2020/03/08
 */
public class OutputStreamTest {

    public static String OUTPUT_TEXT = "hello, output!\n";

    public static void main(String[] args) {
        //1、确认数据源
        File file = new File("src/main/resources/io/output.txt");
        //2、确认输出流
        OutputStream outputStream = null;
        try {
            outputStream = new FileOutputStream(file, true);
            //3、output
            outputStream.write(OUTPUT_TEXT.getBytes());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、释放系统资源
            if (null != outputStream){
                try {
                    outputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
