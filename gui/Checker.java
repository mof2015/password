import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Checker {
	/* password checker
	 * 다음과 같은 조건을 확인하여야 한다
	 * 1. 패스워드 길이
	 * 2. 패스워드의 특수문자 포함 여부
	 * 3. 패스워드에서 사전적 의미가 포함되는 단어가 있는가(ex. apple)
	 * 4. 패스워드에서 연속적인 단어열 혹은 문자열이 포함되는가(ex. 12345, abcde)
	 * 5. 패스워드에서 반복되는 숫자나 문자가 포함되는가(ex. 11111, aaaaa)
	 * 6. ?
	 */
	public int checker(String st){
		int score=0;
		int includeInt=0;
		int includeChar=0;
		int includeSp=0;
		int existWord=0;
		int consecInt[] = new int[3];
		int consecChar[] = new int[3];
		String strNum= new String("0123456789");
		String strChar= new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String strSp= new String("@#$%^&*()-_=+|[]{};:/?.><.");
		
		char ch;
//--------------string의 한 자리 한 자리에 대한 password checking------------
		for(int i=0;i<st.length();i++){
			ch=st.charAt(i);
			Character cr= new Character(ch);
			if(strSp.contains(cr.toString())){
				includeSp++;
			}
			if(strNum.contains(cr.toString())){
				if(consecInt[1]==1)
					consecInt[2]++;
				else if(consecInt[0]==1)
					consecInt[1]=0;
				else
					consecInt[0]=1;
				consecChar[0]=0;
				consecChar[1]=0;
				//연속된 문자 초기화
				includeInt++;
			}
			/* 숫자가 포함되어있을 경우, 연속되는 숫자의 유무를 확인.
			 * 3개 이상의 숫자가 연속되어 있으면 그만큼을 계산해서 나중에 뺌
			 */
			if(strChar.contains(cr.toString())){
				if(consecChar[1]==1)
					consecChar[2]++;
				else if(consecChar[0]==1)
					consecChar[1]=0;
				else
					consecChar[0]=1;
				consecInt[0]=0;
				consecInt[1]=0;
				//연속된 숫자 초기화
				includeChar++;
			}
		}
//-------------------------문자열 전체에 대한 password check----------------------------------
/*		FileReader fr = new FileReader("dictionary.txt");
		BufferedReader br = new BufferedReader(fr);
		String tempStr="";
		while( (tempStr=br.readLine())!=null){
			if(st.contains(tempStr)){
				existWord++;
			}
		}
		fr.close();*/
//-------------------------점수 측정------------------------------------------------------
	
		score+=st.length()*6;
//		System.out.println("Criteria 1(길이): Score + "+st.length()+"*"+"6 " + "= "+score);
		//길이에 따른 점수 가점
		score+=includeSp*8;
//		System.out.println("Criteria 2(특수문자 개수): Score + "+includeSp+"*"+"8 " + "= "+score);
		//특수문자 개수에 따른 점수 가점

		score-=consecInt[2]*4;
//		System.out.println("Criteria 3(연속되는 숫자): Score - "+consecInt[2]+"*"+"4 " + "= "+score);
		//연속되는 숫자에 따른 점수 감점
		score-=consecChar[2]*8;
//		System.out.println("Criteria 4(연속되는 문자): Score - "+consecChar[2]+"*"+"4 " + "= "+score);
		//연속되는 문자에 따른 점수 감점
		if(includeInt*includeChar==0){
			score-=20;
//			System.out.println("Criteria 5(숫자와 문자가 다 포함되는가): Score - 20 " + "= "+score);
		}
		//숫자와 문자 중 안 들어간 게 있으면 감점
		score-=existWord*10;
//		System.out.println("Criteria 6(사전에 정의된 단어): Score - "+existWord+"*"+"10 " + "= "+score);
		return score;
		//최종점수 반환
	}
	/*
	public static void main(String[] args){
		Checker check = new Checker();
		Scanner input= new Scanner(System.in);
		System.out.print("Please enter a Password: ");
		String password = input.next();
		System.out.print("Password score: "+check.checker(password));		
	}
	*/
}
