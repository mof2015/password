import java.util.*;
import java.io.*;
import java.lang.*;
import java.math.*;

public class Checker {
	/* password checker
	 * ������ ���� ������ Ȯ���Ͽ��� �Ѵ�
	 * 1. �н����� ����
	 * 2. �н������� Ư������ ���� ����
	 * 3. �н����忡�� �������� �ܾ Ȥ�� ���ڿ��� ���ԵǴ°�(ex. 12345, abcde)
	 * 4. �н������� �Ϻ� ���ڿ��� �ݺ��Ǵ� ���(ex. abc12abc90abc)
	 * 5. �н����忡�� 3ȸ�̻� ���� �ݺ��Ǵ� ���ڳ� ���ڰ� ���ԵǴ°�(ex. 11111, aaaaa)
	 * 6. �н����忡 ����,���� ���� ���� (���� Ȥ�� ���ڸ����� �̷���� �н������ �������� ����)
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
//--------------string�� �� �ڸ� �� �ڸ��� ���� password checking------------
		/* ���ڰ� ���ԵǾ����� ���, ���ӵǴ� ������ ������ Ȯ��.
		 * 3�� �̻��� ���ڰ� ���ӵǾ� ������ �׸�ŭ�� ����ؼ� ���߿� ��
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
				//���ӵ� ���� �ʱ�ȭ
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
				//���ӵ� ���� �ʱ�ȭ
				existWord = 1;
			}
		}
	*/
//-------------------------���� �н����� ��� ���� üũ----------------------------------
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
//-------------------------���� ����------------------------------------------------------
	
		score+=st.length()*4;
		//���̿� ���� ���� ����
		//score+=includeSp*5;
		//Ư������ ������ ���� ���� ����
		//score-=consecInt[2]*6;
		//���ӵǴ� ���ڿ� ���� ���� ����
		//score-=consecChar[2]*6;
		//���ӵǴ� ���ڿ� ���� ���� ����
		
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
		//�κ� ���ڿ��� �ߺ��Ǵ� ��� ����
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
		//���������� ����/�빮��/�ҹ���/Ư�����ڸ� ������ ���
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
		//3ȸ�̻� ���� �ݺ��Ǵ� ���ڳ� ���ڰ� ���ԵǴ� ��� ����
		for(int i = 0; i < st.length() - 3; i++)
		{
			//if(st.charAt(i) == st.charAt(i + 1) && st.charAt(i) == st.charAt(i + 2))
			if(st.charAt(i) == st.charAt(i + 1))
			{
				score -= 10;
			}
		}
		*/		
		//����,���� �� �� �� �� ������ ���� - �� �� ���Ե� ��� existWord, existNum���� 1�� ������
		//score *= existChar;
		//score *= existNum;

		//System.out.println(score);
		
		return score;
		//�������� ��ȯ
	}
}
