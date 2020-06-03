package io.file;

import java.io.File;
import java.util.stream.Stream;

/**
 * @author Zhangjie
 * @description 文件遍历
 * @date 2020/03/08
 */
public class FileTraversalTest {

    public static void fileTraversal(File file) {
        if (null == file) {
            return;
        }
        if (!file.exists()) {
            return;
        }
        System.out.println(file.getAbsoluteFile());
        if (file.isFile()) {
            return;
        }
        File[] files = file.listFiles();
        if (null == files || 1 > files.length) {
            return;
        }
        Stream.of(files).forEach(FileTraversalTest::fileTraversal);
    }

    public static void main(String[] args) {
        String path = System.getProperty("user.dir");
        File file = new File(path);
        if (!file.exists()) {
            return;
        }
        FileTraversalTest.fileTraversal(file);
    }

}
