import java.util.*;
import java.io.*;



public class Step01{

    static void quicksort(char[] line,int left,int right){
	    int i=left;
	    int j=right;
	    char pivot=line[(int)((i+(j-1))/2.0)];
	    System.out.println(pivot);

	    while(i!=right-1||j!=0){System.out.println(i);System.out.println(j);
		while(line[i]<pivot){
		    i++;
		}
		while(line[j]>pivot){
		    j--;
		}
		if(i>=j){
		    break;
		}

		char temp=line[i];
		line[i]=line[j];
		line[j]=temp;
	    }
	    
	    if(left<i-1){
		quicksort(line,left,i-1);
	    }
	    if(right>j+1){
		quicksort(line,j+1,right);
	    }


    

    }

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	//	String input=args[0];　//入力受け取り
	String[]  dict_line=new String[100000];
	
	File file=new File("../ex.txt");

	try{
	PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));




	try{
	    FileReader dict=new FileReader("short.txt");
	    BufferedReader d_buf=new BufferedReader(dict);
	    int i=0;
	    while(true){
		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか   一行の文字列をを一個一個格納
		//	dict_line[i]
		quicksort(change,0,change.length-1);

		pw.println(change);
		
		System.out.println("f");

		if(dict_line==null){
		    break;
		}	
		i++;
	    }
	}
	catch(IOException e){
	    System.out.println(e);
	}
	
	}catch(IOException e){

	}




    }





}
