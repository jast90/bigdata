package cn.jast.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class LuaFileUtils {

    private LuaFileUtils(){

    }

    public static String getLuaScript(String path){
        StringBuilder sb = new StringBuilder();
        InputStream stream = LuaFileUtils.class.getClassLoader().getResourceAsStream(path);
        try(BufferedReader br = new BufferedReader(new InputStreamReader(stream))){
            String str;
            while ((str = br.readLine())!=null){
                sb.append(str).append(System.lineSeparator());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    public static void main(String[] args) {
        System.out.println(getLuaScript("reduceStock.lua"));
    }
}
