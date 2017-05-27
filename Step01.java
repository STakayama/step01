import java.util.*;
import java.io.*;



public class Step01{

    static void quicksort(char[] line,int left,int right){
	int i=left;
	int j=right;
	char pivot=line[(int)((i+(j-1))/2.0)];
	if(left>=right){
	    return;
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
	String[]  dict_line=new String[100000];
	String st;
	
	File file=new File("ex.txt");

	try{
	    PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));




	    try{
		FileReader dict=new FileReader("short.txt");
		BufferedReader d_buf=new BufferedReader(dict);
		int i=0;
		//		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか   一行の文字列をを一個一個格納
		//	dict_line[i];
		st=d_buf.readLine();
		do{
		    char change[]=st.toCharArray();

		    System.out.println(change);			    
		    quicksort(change,0,change.length-1);
		    dict_line[i]=String.valueOf(change);
		    System.out.println(dict_line[i]);
		    pw.write(dict_line[i]+"\n");
		    //	    change=d_buf.readLine().toCharArray();
		    i++;
		}while((st=d_buf.readLine())!=null);
		pw.close();
	    }
	    catch(IOException e){
		System.out.println(e);
	    }
	
	}catch(IOException e){

	}




    }





}
