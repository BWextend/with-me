package io.stream.input;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * @description Zhangjie
 * @description 输入流使用
 * @date 2020/03/08
 */
public class InputStreamSingleTest {

    public static void main(String[] args) {
        //1、创建源
        File file = new File("src/main/resources/io/input.txt");
        if (!file.exists()) {
            System.out.println("文件不存在");
            return;
        }
        //2、选择流
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream(file);
            int readResult;
            //3、i/o操作
            while (-1 != (readResult = inputStream.read())) {
                System.out.print((char) readResult);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //4、释放系统资源
            if (null != inputStream) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
