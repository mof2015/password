import java.util.Random;


public class Generator {
	int self_password=1;
	int type=3;
	int length=10;
	String password;
	String includeChar;
	String includeNum;
	int max=15;
	int min=10;
	
	private StringBuffer sb = new StringBuffer();
	private String stra=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
	private String strn=new String("0123456789");
	private String strsp=new String("!@#$%^&*");//(),.?/[]{};:|-=_+은 생략
	private String digits = "[0-9]+";
	private String chars = "[A-Za-z]+";
	private String spChars = "[!@#$%^&*]+";
	
	
	void setSelfPassword(int n){
		this.self_password=n;
	}
	int getSelfPassword(){
		return this.self_password;
	}
	void setLength(int length){
		this.length=length;
	}
	int getLength(){
		return this.length;
	}
	void setType(int type){
		if(type==2||type==3)
		this.type=type;
		else
			this.type=1;
	}
	void setPassword(){
		
	}
	String getPassword(){
		if(self_password==1)
			return createCustomPassword();
		else
			return createRandomPassword();
	}
	void setIncludeNum(int n){
		this.includeNum=String.valueOf(n);
	}
	String getIncludeNum(){
		return this.includeNum;
	}
	void setIncludeChar(String includeChar){
		this.includeChar=includeChar;
	}
	String getIncludeChar(){
		return this.includeChar;
	}
	
	public static int randInt(int min, int max) {//두 개의 숫자를 받아 랜덤값을 반환하는 함수
	    Random rand = new Random();
	    // nextInt is normally exclusive of the top value,
	    // so add 1 to make it inclusive
	    int randomNum = rand.nextInt((max - min) + 1) + min;
	    return randomNum;
	}


	String createRandomPassword(){
		int tea=0;
		int ten=0;
		int tes=0;
		int rand1;
		Random r = new Random();

		if(type==1){
    		//type 1: 특수문자 가능, 제약 없음
    		while(sb.length()<length){
           		tea=r.nextInt(stra.length());
           		ten=r.nextInt(strn.length());
           		tes=r.nextInt(strsp.length());
           		rand1=randInt(1,2);
    			if(rand1==1){
    				sb.append(stra.charAt(tea));
    			}
    			else if(rand1==2){
    				sb.append(strn.charAt(ten));
    			}
//    			else if(rand1==3){
//      				sb.append(strsp.charAt(tes));
//      			}
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
		return sb.toString();
	}
	
	String createCustomPassword(){
		StringBuffer sb = new StringBuffer();		
		int numCount=0;
		int charCount=0;
		int rand1=0;
		int tea=0;
		int ten=0;
		int tes=0;
		int outputLength;
		String stra=new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String strn=new String("0123456789");
		String strsp=new String("!@#$%^&*");//(),.?/[]{};:|-=_+은 생략
		String digits = "[0-9]+";
		String chars = "[A-Za-z]+";
		String spChars = "[!@#$%^&*]+";
		Random r = new Random();
		outputLength=length;//출력용 길이를 따로 저장
//------------------------type 1(제약 없음, 문자열과 숫자열을 넣자--------------------------------
		if(type==1||type==2){
			while(sb.length()<length){
			if(numCount>=includeNum.length()&&charCount>=includeChar.length()){
				tea=r.nextInt(stra.length());
				ten=r.nextInt(strn.length());
				tes=r.nextInt(strsp.length());
				rand1=randInt(1,2);
				if(rand1==1){
					sb.append(stra.charAt(tea));
				}
				else{
					sb.append(strn.charAt(ten));
				}
			}
			else if(numCount>=includeNum.length()){
				sb.append(includeChar.charAt(charCount));
				charCount++;
			}
			else if(charCount>=includeChar.length()){
				sb.append(includeNum.charAt(numCount));
				numCount++;
			}
			else{
				rand1=randInt(1,2);
				if(rand1==1){
					sb.append(includeChar.charAt(charCount));
					charCount++;
				}
				else{
					sb.append(includeNum.charAt(numCount));
					numCount++;
				}
			}
		}
	}
		else if(type==3){
			//연속문자 or 숫자 방지
			String tempString=new String();
			String tempString2=new String();
			int temp=2;
			while(sb.length()<length){
				if(numCount>=includeNum.length()&&charCount>=includeChar.length()){
					tea=r.nextInt(stra.length());
					ten=r.nextInt(strn.length());
					tes=r.nextInt(strsp.length());
					rand1=randInt(1,2);
					if(rand1==1){
						sb.append(stra.charAt(tea));
					}
					else{
						sb.append(strn.charAt(ten));
					}
				}
				else if(numCount>=includeNum.length()){
					sb.append(includeChar.charAt(charCount));
					charCount++;
				}
				else if(charCount>=includeChar.length()){
					sb.append(includeNum.charAt(numCount));
					numCount++;
				}
				else{
					rand1=randInt(1,2);
					if(rand1==1){
						sb.append(includeChar.charAt(charCount));
						charCount++;
					}
					else{
						sb.append(includeNum.charAt(numCount));
						numCount++;
					}
				}
			}
		while(temp<=sb.length()-1){
			tempString=(sb.toString()).substring(temp-2, temp+1);
//			System.out.println("tempString은?: "+tempString);
			if(tempString.matches(chars)||tempString.matches(digits)){
				tempString2=(sb.toString()).substring(temp, sb.length());
//				System.out.println("연속하는 숫자 or 문자: "+tempString);
				sb.delete(temp, sb.length());
//				System.out.println("제거된 문자열: "+sb.toString());        				
        		tes=r.nextInt(strsp.length());
        		sb.append(strsp.charAt(tes));
//				System.out.println("랜덤값 추가열: "+sb.toString());        				
        		sb.append(tempString2);
//				System.out.println("뒷부분 추가열: "+sb.toString());        				
//				System.out.println(sb.toString());
				temp=temp+3;
			}
			temp=temp+1;
		}
		}
   		return sb.substring(0,outputLength);
	}

}

