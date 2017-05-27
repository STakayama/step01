import java.util.*;
import java.io.*;
//だいたいこれでいいけどtootとか


public class Step01_2{

    static void quicksort(char[] line,int left,int right){
	int i=left;
	int j=right;
	char pivot=line[(int)((i+(j-1))/2.0)];
	if(left>=right){
	    return ;
	}


	while(i<right||j>left){
	    if(line[i]==line[j]){
		break;
	    }
	    //	for(int g=0;g<line.length;g++){
	    //    System.out.print(line[g]);
	    //	}
	    //System.out.println("");
	    //System.out.println("i:"+i);//ここ
	    //System.out.println(right);


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
	    
	if(left<i){
	    quicksort(line,left,i-1);
	}
	if(right>j+1){
	    quicksort(line,j+1,right);
	}


    

    }

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	//	String input=args[0];　//入力受け取り
	String[]  dic_line=new String[72412];
	String st;
	try{
	    InputStreamReader in = new InputStreamReader(System.in);       //（１）
	    BufferedReader buf_in= new BufferedReader(in);
	    System.out.println("何か入力してください.");
	    String input=buf_in.readLine();//入力受け付け
	    char[] input_char=input.toCharArray();System.out.println("並び替え:"+input);
	    quicksort(input_char,0,input_char.length-1);
	    //    input=String.valueOf(input_char);
	    System.out.println("並び替え:"+String.valueOf(input_char));

	    /*

	try{
	    FileReader dict=new FileReader("sorted_dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);
	    int i=0;

	    //		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか   一行の文字列をを一個一個格納
	    //	dict_line[i];
	    while((st=d_buf.readLine())!=null){
		dic_line[i]=st;
		if(Arrays.asList(dic_line).contains(input)){
		    System.out.println(dic_line[i]);
		    break;
		}
		i++;//辞書txtを配列に読み込み
	    }


	  
	}
	catch(IOException e){
	    System.out.println(e);
	    }*/
	}catch(IOException e){

	}
	
    }//mainおわり




}






