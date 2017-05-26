import java.util.*;
import java.io.*;



public class Step01{

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));

	String input=args[0];
	String[]  dict_line=new String[100000];
	try{
	    FileReader dict=new FileReader("../dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);
	    int i=0;
	    while(true){
		dict_line[i]=d_buf.readLine();//辞書の文字をどう入れ替えるか
		if(dict_line==null){
		    break;
		}	
		i++;
	    }
	}
	catch(IOException e){
	    System.out.println(e);
	}
	





    }





}
