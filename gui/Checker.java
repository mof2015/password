import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Checker {
	/* password checker
	 * 다음과 같은 조건을 확인하여야 한다
	 * 1. 패스워드 길이
	 * 2. 패스워드의 특수문자 포함 여부
	 * 3. 패스워드에서 연속적인 단어열 혹은 문자열이 포함되는가(ex. 12345, abcde)
	 * 4. 패스워드의 일부 문자열이 반복되는 경우(ex. abc12abc90abc)
	 * 5. 패스워드에서 3회이상 연속 반복되는 숫자나 문자가 포함되는가(ex. 11111, aaaaa)
	 * 6. 패스워드에 숫자,문자 조합 여부 (숫자 혹은 문자만으로 이루어진 패스워드는 인정하지 않음)
	 */
	public int checker(String st) {
		int score=0;
		int includeSp=0;
		//int existNum=0;
		//int existChar=0;
		int containsNum=0;
		int containsUpper=0;
		int containsLower=0;
		int containsSp=0;
		int consecNumCnt=0;
		int consecCharCnt=0;
		int consecSpCnt=0;
		//int consecInt[] = new int[3];
		//int consecChar[] = new int[3];
		String strNum= new String("0123456789");
		String strChar= new String("abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ");
		String strSp= new String("@#$%^&*()-_=+|[]{};:/?.><.");
		
		char ch;
//--------------string의 한 자리 한 자리에 대한 password checking------------
		/* 숫자가 포함되어있을 경우, 연속되는 숫자의 유무를 확인.
		 * 3개 이상의 숫자가 연속되어 있으면 그만큼을 계산해서 나중에 뺌
		 */
		/**
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
				existNum = 1;
			}
			
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
				existWord = 1;
			}
		}
	*/
//-------------------------흔한 패스워드 사용 여부 체크----------------------------------
		FileReader fr = null;
		try {
			fr = new FileReader("common.txt");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		BufferedReader br = new BufferedReader(fr);
		String tempStr="";
		try {
			while( (tempStr=br.readLine())!=null){
				if(st.contains(tempStr)){
					score -= 10;
				}
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			fr.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
//-------------------------점수 측정------------------------------------------------------
	
		score+=st.length()*4;
		//길이에 따른 점수 가점
		//score+=includeSp*5;
		//특수문자 개수에 따른 점수 가점
		//score-=consecInt[2]*6;
		//연속되는 숫자에 따른 점수 감점
		//score-=consecChar[2]*6;
		//연속되는 문자에 따른 점수 감점
		
		for(int i = 0; i < st.length(); i++)
		{
			ch=st.charAt(i);
			Character cr= new Character(ch);
			if(strChar.substring(26).contains(cr.toString()))
			{
				containsUpper++;
			}
			else if(strChar.substring(0, 26).contains(cr.toString()))
			{
				containsLower++;
			}
			else if(strNum.contains(cr.toString()))
			{
				containsNum++;
			}
			else if(strSp.contains(cr.toString()))
			{
				containsSp++;
			}
		}
		
		if(containsUpper > 0)
		{
			score += (st.length() - containsUpper) * 2;
			//existChar = 1;
		}
		if(containsLower > 0)
		{
			score += (st.length() - containsLower) * 2;
			//existChar = 1;
		}
		if(containsNum > 0)
		{
			score += containsNum * 4;
			//existNum = 1;
		}
		if(containsSp > 0)
			score += containsSp * 6;
		
		if(containsUpper == 0 && containsLower == 0 && containsSp == 0)
		{
			score -= containsNum;
		}
		
		if(containsNum == 0 && containsSp == 0)
		{
			score -= (containsUpper + containsLower);
		}
		/**
		//부분 문자열이 중복되는 경우 감점
		for(int i = 1; i < st.length() / 2; i++)
		{
			for(int j = 0; j < i; j++)
			{
				String sub = st.substring(j, j + i);
				if(st.substring(j + i + 1, st.length() - 1).contains(sub))
				{
					score -= 10;
				}
			}
		}
		*/
		//연속적으로 숫자/대문자/소문자/특수문자만 나오는 경우
		for(int i = 0; i < st.length(); i++)
		{
			ch=st.charAt(i);
			Character cr= new Character(ch);
			if(strChar.contains(cr.toString()))
			{
				for(int j = i + 1; j < st.length(); j++)
				{
					if(strChar.contains(st.substring(j - 1, j)))
					{
						consecCharCnt++;
					}
					else
					{
						i = j;
						break;
					}
				}
			}
			else if(strNum.contains(cr.toString()))
			{
				for(int j = i + 1; j < st.length(); j++)
				{
					if(strNum.contains(st.substring(j - 1, j)))
					{
						consecNumCnt++;
					}
					else
					{
						i = j;
						break;
					}
				}
			}
			else if(strSp.contains(cr.toString()))
			{
				for(int j = i + 1; j < st.length(); j++)
				{
					if(strSp.contains(st.substring(j - 1, j)))
					{
						consecSpCnt++;
					}
					else
					{
						i = j;
						break;
					}
				}
			}
		}
		
		score -= consecCharCnt * 2;
		score -= consecNumCnt * 2;
		score -= consecSpCnt * 2;
		
		/**
		//3회이상 연속 반복되는 숫자나 문자가 포함되는 경우 감점
		for(int i = 0; i < st.length() - 3; i++)
		{
			//if(st.charAt(i) == st.charAt(i + 1) && st.charAt(i) == st.charAt(i + 2))
			if(st.charAt(i) == st.charAt(i + 1))
			{
				score -= 10;
			}
		}
		*/		
		//숫자,문자 중 안 들어간 게 있으면 감점 - 둘 다 포함된 경우 existWord, existNum값은 1로 설정됨
		//score *= existChar;
		//score *= existNum;

		System.out.println(score);
		
		return score;
		//최종점수 반환
	}
}
