package com.cpf.boottest.util.commen;

import org.apache.commons.lang3.ArrayUtils;

import java.util.*;

/**
 * Created by cpf on 2017/9/26.
 */
public class Splits {
    public static String   getwherein(String[] str){
        String  wherein = "";
           for(String st:str){
               wherein+="'"+st +"',";
           }

    return  wherein.substring(0,wherein.length()-1);
    }
     public static String  getnorepeatwherein(String[] array){
         List<String> list = new ArrayList<>();
         list.add(array[0]);
         for(int i=1;i<array.length;i++){
             if(list.toString().indexOf(array[i]) == -1){
                 list.add(array[i]);
             }
         }
         String[] arrayResult = (String[]) list.toArray(new String[list.size()]);
           return getwherein(arrayResult);
     }
    public  static String[]  getsumsz(String[] a ,String[] b){
        Set<String> set = new HashSet<String>();
        for (String  i : a ) {
            set.add(i);
        }
        for (String i :  b) {
            set.add(i);
        }
        List<String> list = new ArrayList<String>(set);

        String[] array = (String[])list.toArray(new String[list.size()]);
        return  array;
    }
    public static void  main(String[] arges){
        String[] arr=new String[]{"1","2","3","4","5"};
        String[] arrb=new String[]{};



    }
 }
