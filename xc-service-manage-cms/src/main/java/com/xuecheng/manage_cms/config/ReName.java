package com.xuecheng.manage_cms.config;

import java.io.File;

public class ReName {
    public static void main(String[] args) {
        File file = new File("G:\\尚硅谷Java学科全套教程\\4.尚硅谷全套JAVA教程--JavaEE阶段\\尚硅谷Git&GitHUP视频教程\\视频");
        Cylce(file);

    }
    public static void Cylce(File file){
        File[] files = file.listFiles();
        for(File file1:files){
            if(!file1.isDirectory()){
                if(file1.getName().endsWith(".avi")){
                /*String namesuf = file1.getName().substring(3);
                String namePref = file1.getParentFile().getParentFile().getName().substring(0,2)
                        +"."+file1.getName().substring(0,2)+".";
                String newName = namePref+namesuf;*/
                String newName=22+"."+file1.getName();
                file1.renameTo(new File(file1.getParentFile(),newName));
                System.out.println(newName);}
            }
            else{
                Cylce(file1);
            }
        }
    }
}
