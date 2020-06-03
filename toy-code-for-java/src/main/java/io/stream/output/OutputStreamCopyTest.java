package io.stream.output;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import org.apache.commons.lang3.StringUtils;

/**
 * @author Zhangjie
 * @description 使用i/o流实现文件拷贝
 * @date 2020/03/08
 */
public class OutputStreamCopyTest {

    /**
     * 从源文件拷贝文件内容至拷贝生成文件
     *
     * @param fromPath 源文件
     * @param toPath 拷贝生成文件
     */
    public static void copy(String fromPath, String toPath){
        if (StringUtils.isEmpty(fromPath) || StringUtils.isEmpty(toPath)){
            System.out.println("文件路径错误");
            return;
        }
        //1、确认数据源
        File file = new File(fromPath);
        InputStream inputStream = null;
        OutputStream outputStream = null;
        try {
            //2、确认i/o流
            inputStream = new FileInputStream(file);
            byte[] flush = new byte[4];
            int flushSize;

            outputStream = new FileOutputStream(toPath);
            //3、i/o操作
            while (-1 != (flushSize = inputStream.read(flush))){
                outputStream.write(flush, 0, flushSize);
            }
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

            if (null != inputStream){
                try {
                    inputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        String fromPath = "src/main/resources/io/input.txt";
        String toPath = "src/main/resources/io/inputCopy.txt";
        OutputStreamCopyTest.copy(fromPath, toPath);
    }

}
