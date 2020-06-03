package io.file;

import java.io.File;
import lombok.Data;

/**
 * @author Zhangjie
 * @description 封装文件计数信息
 * @date 2020/03/08
 */
@Data
public class FileCount {

    private File file;

    private int fileNum;

}
