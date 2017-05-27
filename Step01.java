import java.util.*;
import java.io.*;



public class Step01{

    void quicksort(char line,int left,int right){
	if(left<right){
	    char pivot;
	    int i=left;
	    int j=right;
	    int mid=(int)((i+(j-1))/2.0);
	    
	    if(line[i]<line[j]){
		if(line[j]<line[mid]){
		    pivot=line[j]; 
		}
		else if(line[mid]<line[i]){
		    pivot=line[i];
		}
		else{
		    pivot-line[mid];
		}

		if(line){
		}
		else if(){
		}
		else(){
		    }



	    }





	}
    }

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));

	String input=args[0];
	String[]  dict_line=new String[100000];
	try{
	    FileReader dict=new FileReader("../dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);
	    int i=0;
	    while(true){
		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか
		//	dict_line[i]
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
