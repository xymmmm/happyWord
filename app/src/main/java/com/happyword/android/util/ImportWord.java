package com.happyword.android.util;

import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.happyword.android.db.Word;

public class ImportWord {    //按行读取TXT格式的词库
    public static void ReadFile(String strFilePath){
        String path = strFilePath;
        //打开文件
        File file = new File(path);
        //如果path是传递过来的参数，可以做一个非目录的判断
        if (file.isDirectory())
        {
            Log.d("TestFile", "The File doesn't not exist.");
        }
        else
        {
            try {
                InputStream instream = new FileInputStream(file);
                if (instream != null)
                {
                    InputStreamReader inputreader = new InputStreamReader(instream);
                    BufferedReader buffreader = new BufferedReader(inputreader);
                    String line;
                    //分行读取
                    while (( line = buffreader.readLine()) != null) {
                        String strword="";
                        String interpret="";
                        Pattern patternWord=Pattern.compile("[a-zA-Z]+[ ]+");   //匹配单词
                        Matcher matcherWord=patternWord.matcher(line);
                        String lineInterpret="";
                        if(matcherWord.find()) {
                            strword = matcherWord.group();
                            lineInterpret=matcherWord.replaceAll("1111");  //把单词替换成数字
                        }
                        Pattern patternInterpret=Pattern.compile("[^0~9]");  //匹配翻译
                        Matcher matcherInterpret=patternInterpret.matcher(lineInterpret);
                        if(matcherInterpret.find()){
                            String str=matcherInterpret.group();
                            interpret=new String(str.toCharArray(),3,str.length()-6);
                        }
                        //添加word.trim（）和interpret到数据库
                        Word word=new Word();
                        word.setWord(strword.trim());
                        word.setInterpret(interpret);
                        if(file.getName()=="大学英语四级词汇.txt")
                            word.setLevel("四级");
                        else if (file.getName()=="大学英语六级词汇.txt")
                            word.setLevel("六级");
                        else word.setLevel("本地词库");
                        word.setLearned(0);
                    }
                    instream.close();
                }
            }
            catch (java.io.FileNotFoundException e)
            {
                Log.d("TestFile", "The File doesn't not exist.");
            }
            catch (IOException e)
            {
                Log.d("TestFile", e.getMessage());
            }
        }
    }
}
