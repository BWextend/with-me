package io.stream.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description Zhangjie
 * @description 批量读入文件内容
 * @date 2020/03/08
 */
public class InputStreamBatchTest {

    public static void main(String[] args) {
        //1、创建源
        File file = new File("src/main/resources/io/input.txt");
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        //2、确定流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            //3、i/o操作
            byte[] flush = new byte[4];
            int flushSize;
            while (-1 != (flushSize = inputStream.read(flush))){
                System.out.println(new String(flush, 0, flushSize));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、释放系统资源
            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
