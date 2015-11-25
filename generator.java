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
	public static int randInt(int min, int max) {

	    // NOTE: This will (intentionally) not run as written so that folks
	    // copy-pasting have to think about how to initialize their
	    // Random instance.  Initialization of the Random instance is outside
	    // the main scope of the question, but some decent options are to have
	    // a field that is initialized once and then re-used as needed or to
	    // use ThreadLocalRandom (if using at least Java 1.7).
	    Random rand = new Random();

	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	public static void main(String arg[]){
		Scanner scan = new Scanner(System.in);
		int min=8;		//문자열 최저길이 지정
		int max=15;		//문자열 최대길이 지정, 없으면 0
		int specialChar=1;//특수문자가능하면 1, 불가능하면 0
		int specialCharMust=1;//특수문자필수이면 1, 아니면 0
		int mixed=1;		//문자,숫자,특수문자 등 2개 이상 혼합이 필수이면 1, 아니면 0
		int noConsecutive=1;//연속 3개 이상의 숫자 혹은 문자가 불가능하면 1, 아니면 0
		int type=5;
		/*
		 * type 5 = 모든 조건을 다 충족
		 * */
		String str=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789");
		
		String stra=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String strn=new String("0123456789");
		String strsp=new String("!@#$%^&*");//(),.?/[]{};:|-=_+은 생략
		String include=new String();
		
        StringBuffer sb=new StringBuffer();
        String ar=null;
        Random r = new Random();
        int length;
        int enablesp=-1;
        length=randInt(min,max);
        do{
        System.out.println("Select a word you want to include: ");
        include = scan.next();
        if(include.length()>min)
        	System.out.println("Invalid input : Too long!");
        }while(include.length()>length);
/*        while(enablesp!=0 && enablesp!=1){
        System.out.println("Set special characters(1-enable, 0-disable): ");
        enablesp = scan.nextInt();
        }

        
        
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
        }*/
        

        sb=new StringBuffer(length);
        int tea=0;
        int ten=0;
        int tes=0;
        int rand1=0;
        int rand2=0;
        sb.append(include);
        while(sb.length()<length){
        	tea=r.nextInt(stra.length());
        	ten=r.nextInt(strn.length());
        	tes=r.nextInt(strsp.length());
        	if(type==5){
        		rand1=randInt(1,3);
        		if(rand1==1){
        			//ar=ar+stra.charAt(tea);
        			rand2=randInt(0,sb.length()-1);
            		sb.insert(rand2, stra.charAt(tea));
        			//sb.append(stra.charAt(tea));
        		}
        		else if(rand1==2){
        			//ar=ar+strn.charAt(ten);
        			rand2=randInt(0,sb.length()-1);
            		sb.insert(rand2, strn.charAt(ten));
        			//sb.append(strn.charAt(ten));
        		}
        		else if(rand1==3){
        			//ar=ar+strsp.charAt(tes);
        			rand2=randInt(0,sb.length()-1);
            		sb.insert(rand2, strsp.charAt(tes));
        			//sb.append(strsp.charAt(tes));
        		}
        	}
        	else if(type==2){
        		
        	}
        	else if(type==3){
        		
        	}
        	else if(type==4){
        		
        	}
        	else{
                System.out.println("Invalid password configuration");        		
        	}
        }
        System.out.println(sb.toString());
    }	
}
