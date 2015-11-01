/*
 * Copyright (C) Korea University Information Security MOF Team
 */
 
import java.util.*;

public class Generator {
	/*조건
	 * 1: 비밀번호 길이를 미리 입력해야 한다
	 * 2: 특수문자 포함 여부를 반영할 수 있어야 한다
	 * 3: 연속되는 세 개 이상의 숫자/문자가 없어야 한다
	 */
	public static void main(String arg[]){
		Scanner scan = new Scanner(System.in);
		String str=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		String strsp=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789!@#$%^&*(),.?/[]{};:|-=_+");
		
        StringBuffer sb=new StringBuffer();
        String ar=null;
        Random r = new Random();
        int length;
        int enablesp=-1;
        System.out.println("Set password length: ");
        length = scan.nextInt();
        while(enablesp!=0 && enablesp!=1){
        System.out.println("Set special characters(1-enable, 0-disable): ");
        enablesp = scan.nextInt();
        }
               if(length<8)
        	length=8;
        sb=new StringBuffer(length);
        int te=0;
        for(int i=0;i<length;i++){
        	if(enablesp==1){
        		te=r.nextInt(strsp.length());
//				ar=ar+strsp.charAt(te);
        		sb.append(strsp.charAt(te));
        	}
        	else{
        		te=r.nextInt(str.length());
//				ar=ar+str.charAt(te);
        		sb.append(str.charAt(te));
        	}
        }
        System.out.println(sb.toString());
    }	
}
