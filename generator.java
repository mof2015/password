/*
 * Copyright (C) Korea University Information Security MOF Team
 */
 
import java.util.*;

class Generator{
    public static void main(String arg[]){
        String str=new String("abcdefghjkmnpqrstuvwxyzABCDEFGHJKLMNOPQRSTUVWXYZ23456789");
        StringBuffer sb=new StringBuffer();
        String ar=null;
        Random r = new Random();
        int te=0;
        for(int i=1;i<=8;i++){
            te=r.nextInt(62);
            ar=ar+str.charAt(te);
            sb.append(str.charAt(te));
        }
        String abc="";
        char arr[]=str.toCharArray();
        System.out.println(sb.toString());
    }
}  
