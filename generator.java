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
		int self_password=1;
		//기억하고자 하는 비밀번호라면 1, 임의로 생성될 비밀번호라면 0
		int type=3;
		//저장되는 비밀번호의 제약조건에 따라 결정됨
		/* type 1: 특수문자 가능, 특수문자 없어도 됨, 혼합필수, 연속문자 가능
		 * type 2: 특수문자 가능, 특수문자 없어도 됨, 혼합 없어도 됨, 연속문자 가능
		 * type 3: 특수문자 가능, 특수문자 없어도 됨, 혼합필수, 연속문자 금지
		 * */
		/*
		 * type 5 = 모든 조건을 다 충족
		 * */
		String stra=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String strn=new String("0123456789");
		String strsp=new String("!@#$%^&*");//(),.?/[]{};:|-=_+은 생략
		String includeChar=new String();
		String includeNum=new String();
		String digits = "[0-9]+";
		String chars = "[A-Za-z]+";
		String spChars = "[!@#$%^&*]+";
        StringBuffer sb=new StringBuffer();
        
        Random r = new Random();
        int length;
        length=randInt(min,max);
        sb=new StringBuffer(length);      
 
        if(self_password==1){
        	do{
        		do{
        			System.out.println("Select a word you want to include: ");
        			includeChar = scan.next();
        			if(includeChar.length()>min)
        				System.out.println("Invalid input : Too long!");
        			if(!includeChar.matches(chars))
        				System.out.println("Invalid input : Only characters!");
        		}while(includeChar.length()>min||!includeChar.matches(chars));

        		do{
        			System.out.println("Select a number you want to include: ");
        			includeNum = scan.next();
        			if(includeNum.length()>min)
        				System.out.println("Invalid input : Too long!");
        			if(!includeNum.matches(digits))
        				System.out.println("Invalid input : Only digits!");
        		}while(includeNum.length()>min||!includeNum.matches(digits));
        	if((includeChar.length()+includeNum.length())>length)
        		System.out.println("The inserted character and number are too long!");
        	else{
        		sb=new StringBuffer(length);
        	}
        	System.out.println("The set length is "+length);	
        	}while((includeChar.length()+includeNum.length())>length);
        }
        /*비밀번호를 만들 때 필요할 문자열 및 숫자열을 생성한다*/
        	int tea;
        	int ten;
        	int tes;
        	int rand1=0;
        	int rand2=0;
 
        if(self_password==1){//비밀번호에 필요한 문자열과 숫자열을 입력했을 경우
        	if(type==1||type==2){
        		//type 1: 특수문자 가능, 제약 없음
        		//type 2: 특수문자 가능, 문자or숫자or특수문자 중 2개 이상 혼합 필수
        		int numCount=0;
        		int charCount=0;
        		while(sb.length()<includeNum.length()+includeChar.length()){
        			if(numCount+charCount==length){
        				//end
        			}
        			else if(numCount==includeNum.length()){
        				sb.append(includeChar.charAt(charCount));
        				charCount++;
        			}
        			else if(charCount==includeChar.length()){
        				sb.append(includeNum.charAt(numCount));
        				numCount++;
        			}
        			else{
        				rand1=randInt(1,2);
        				if(rand1==1){
        					sb.append(includeNum.charAt(numCount));
        					numCount++;
        				}
        				else{
        					sb.append(includeChar.charAt(charCount));
        					charCount++;
        				}
        				System.out.println(sb.toString());
        			}
        		}
        		while(sb.length()<length){
        			rand1=randInt(0,sb.length()-1);
        			tes=r.nextInt(strsp.length());
        			sb.insert(rand1,strsp.charAt(tes));
        		}
        	}
        	else if(type==3){
        		/*type 3: 특수문자 가능, 문자or숫자or특수문자 중 2개 이상 혼합 필수, 
        		 * 숫자 or 문자 등을 연속해서 3개 이상 사용 금지
        		 */
        		int numCount=0;
        		int charCount=0;
        		int consecNum=0;
        		int consecChar=0;
        		int consecSp=0;
    			do{
        			while(sb.length()<length){        		
    					tes=r.nextInt(strsp.length());
    					
    					if(numCount+charCount==length){
            				//end
            			}
            			else if(numCount==includeNum.length()){
            				rand1=randInt(1,2);
            				if(rand1==1){
            					sb.append(includeChar.charAt(charCount));
            					charCount++;
            					consecChar++;
            					consecNum=0;
            					consecSp=0;
            				}
            				else{
            					sb.append(strsp.charAt(tes));
    							consecChar=0;
    							consecNum=0;
    							consecSp++;
            				}
            			}
            			else if(charCount==includeChar.length()){
            				rand1=randInt(1,2);
            				if(rand1==1){
            					sb.append(includeNum.charAt(numCount));
            					numCount++;
            					consecNum++;
            					consecNum=0;
            					consecSp=0;
            				}
            				else{
            					sb.append(strsp.charAt(tes));
    							consecChar=0;
    							consecNum=0;
    							consecSp++;
            				}
            			}
            			else{
            				rand1=randInt(1,3);
    						if(rand1==1){
    							sb.append(includeNum.charAt(numCount));
    							numCount++;
    							consecChar++;
    							consecNum=0;
    							consecSp=0;
    						}
    						else if(rand1==2){
    							sb.append(includeChar.charAt(charCount));
    							charCount++;
    							consecChar=0;
    							consecNum++;
    							consecSp=0;
    						}
    						else if(rand1==3){
    							sb.append(strsp.charAt(tes));
    							consecChar=0;
    							consecNum=0;
    							consecSp++;
    						}
    						else{
    							//
    						}
            			}
    						if(consecNum>2||consecChar>2||consecSp>2){
    							sb = new StringBuffer(length);
    							consecChar=0;
    							consecNum=0;
    							consecSp=0;
    							numCount=0;
    							charCount=0;
    						}
    					if(numCount!=includeNum.length()||charCount!=includeChar.length()){
    						if(length<max)
    							sb=new StringBuffer(length+1);
    						else
    							sb=new StringBuffer(length);
    						consecChar=0;
    						consecNum=0;
    						consecSp=0;
    						numCount=0;
    						charCount=0;
    					}
        			}
        		System.out.println(sb.toString());
    			}while(numCount!=includeNum.length()||charCount!=includeChar.length()
    					||consecNum>2||consecChar>2||consecSp>2);
        	}
        	else{
        		
        	}
        }
        else{				//임의로 비밀번호를 생성하는 경우           	
        	if(type==1){
        		//type 1: 특수문자 가능, 제약 없음
        		while(sb.length()<length){
               		tea=r.nextInt(stra.length());
               		ten=r.nextInt(strn.length());
               		tes=r.nextInt(strsp.length());
               		rand1=randInt(1,3);
        			if(rand1==1){
        				sb.append(stra.charAt(tea));
        			}
        			else if(rand1==2){
        				sb.append(strn.charAt(ten));
        			}
        			else if(rand1==3){
        				sb.append(strsp.charAt(tes));
        			}
        		}
        	}
        	else if(type==2){
        		//type 2: 특수문자 가능, 문자or숫자or특수문자 중 2개 이상 혼합 필수
        		do{
        			sb=new StringBuffer(length);    
        			while(sb.length()<length){        		
        				tea=r.nextInt(stra.length());
        				ten=r.nextInt(strn.length());
        				tes=r.nextInt(strsp.length());
        				rand1=randInt(1,3);
        				if(rand1==1){
        					sb.append(stra.charAt(tea));
        				}
        				else if(rand1==2){
        					sb.append(strn.charAt(ten));
        				}
        				else if(rand1==3){
        					sb.append(strsp.charAt(tes));
        				}
        			}
        		}while(sb.toString().matches(digits)||sb.toString().matches(chars)
        				||sb.toString().matches(spChars));
        	}
        	else if(type==3){
        		/*type 3: 특수문자 가능, 문자or숫자or특수문자 중 2개 이상 혼합 필수, 
        		 * 숫자 or 문자 등을 연속해서 3개 이상 사용 금지
        		 */
        		int numCounter=0;
        		int charCounter=0;
        		int spCounter=0;
    			while(sb.length()<length){        		
    				tea=r.nextInt(stra.length());
    				ten=r.nextInt(strn.length());
    				tes=r.nextInt(strsp.length());
    				rand1=randInt(1,3);
    				if(rand1==1){
    					sb.append(stra.charAt(tea));
    					charCounter++;
    					numCounter=0;
    					spCounter=0;
    				}
    				else if(rand1==2){
    					sb.append(strn.charAt(ten));
    					charCounter=0;
    					numCounter++;
    					spCounter=0;
    				}
    				else if(rand1==3){
    					sb.append(strsp.charAt(tes));
    					charCounter=0;
    					numCounter=0;
    					spCounter++;
    				}
    				if(numCounter>2||charCounter>2||spCounter>2){
    					sb = new StringBuffer(length);
    					charCounter=0;
    					numCounter=0;
    					spCounter=0;
    				}
    			}
    		}
         	else{
        		System.out.println("Invalid password configuration");        		
        	}
		}
   	System.out.println(sb.toString());
	}
}    	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
        	
