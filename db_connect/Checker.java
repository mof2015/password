package db_connec_test;
import java.io.*;

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
		}
		if(containsLower > 0)
		{
			score += (st.length() - containsLower) * 2;
		}
		if(containsNum > 0)
		{
			score += containsNum * 4;
		}
		if(containsSp > 0)
			score += containsSp * 6;
		
		if(containsNum == 0 && containsSp == 0)
		{
			score -= (containsUpper + containsLower);
		}

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
		
		//3회이상 연속 반복되는 숫자나 문자가 포함되는 경우 감점
		for(int i = 0; i < st.length() - 2; i++)
		{
			//if(st.charAt(i) == st.charAt(i + 1) && st.charAt(i) == st.charAt(i + 2))
			if(st.charAt(i) == st.charAt(i + 1))
			{
				score -= 10;
			}
		}
				
		//문자나 숫자 둘 중 하나라도 없는 경우 빵점
		if((containsUpper == 0 && containsLower == 0) || containsNum == 0)
			score = 0;
		
		return score;
		//최종점수 반환
	}
}
