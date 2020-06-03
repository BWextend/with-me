package io.file;

import java.io.File;
import java.util.stream.Stream;
import lombok.Data;

/**
 * @author Zhangjie
 * @description 计算文件夹下文件数量
 * @date 2020/03/08
 */
@Data
public class FileCountTest {

    public static void countFileQuantity(FileCount fileCount){
        if (null == fileCount){
            return;
        }
        File file = fileCount.getFile();
        int fileNum = fileCount.getFileNum();
        if (!file.exists()){
            return;
        }
        if (file.isFile()){
            System.out.println(file.getAbsoluteFile());
            fileCount.setFileNum(++fileNum);
        }
        File[] files = file.listFiles();
        if (null == files || 1 > files.length){
            return;
        }
        Stream.of(files).forEach(fileQuantity -> {
            fileCount.setFile(fileQuantity);
            countFileQuantity(fileCount);
        });
    }

    public static void main(String[] args) {
        FileCount fileCount = new FileCount();
        String path = System.getProperty("user.dir");
        File file = new File(path);
        fileCount.setFile(file);
        fileCount.setFileNum(0);
        FileCountTest.countFileQuantity(fileCount);
        System.out.println(fileCount.getFileNum());
    }

}
