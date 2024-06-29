package com.xiaozhang.code;

import java.io.File;
import java.util.Objects;
import java.util.Stack;

/**
 * 给定一个文件目录的路径，写一个函数统计这个目录下所有的文件数量并返回，隐藏文件也算，但是文件夹不算
 */
public class Code02_FileNums {
    
    public static int getFileNums(String path){
        File file = new File(path);
        if(!file.isDirectory()&&!file.isFile()){
            return 0;
        }
        if(file.isFile()){
            return 1;
        }
        Stack<File> fileStack = new Stack<>();
        fileStack.push(file);
        int files= 0;
        while (!fileStack.isEmpty()){
            File folder = fileStack.pop();
            for (File f : Objects.requireNonNull(folder.listFiles())) {
                if(f.isFile()){
                    files++;
                }
                if(f.isDirectory()){
                    fileStack.push(f);
                }
            }
        }

        return files;
    }

    public static void main(String[] args) {
        String path = "/Users/banma/meCode/ds-algos-practice/src/main/java/com/xiaozhang/code";
        System.out.println(getFileNums(path));
    }
    
}
