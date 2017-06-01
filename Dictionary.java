import java.util.*;
import java.io.*;
//だいたいこれでいいけどtootとか
//http://java-reference.com/java_array_sort.html


public class Dictionary{

     static void quicksort(char[] line,int left,int right){
	int i=left;
	int j=right;
	char pivot=line[(i+j)/2];
	if(left>=right){
	    return ;
	}


        do{
	    while((line[i]<pivot)&&(i<right)){
		i++;
	    }
	    while((line[j]>pivot)&&(j>left)){
		j--;
	    }
	    if(i<=j){
	    char temp=line[i];
	    line[i]=line[j];
	    line[j]=temp;
	    i++;
	    j--;
	    }
	}while(i<=j);

	    
	if(left<i){
	    quicksort(line,left,j);
	}
	if(right>j){
	    quicksort(line,i,right);
	}


    

    }

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	//	String input=args[0];　//入力受け取り
	String[][] dic_line=new String[72412][3];
	String st;
	
	File file=new File("mid_dic.txt");

	try{
	    PrintWriter pw=new PrintWriter(new BufferedWriter(new FileWriter(file)));




	    try{
		FileReader dict=new FileReader("dic.txt");
		BufferedReader d_buf=new BufferedReader(dict);
		int i=0;
		int point=0;
		//		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか   一行の文字列をを一個一個格納
		//	dict_line[i];
		st=d_buf.readLine();
		do{
		   

		    if(Character.isUpperCase(st.charAt(0))){
		        st=st.toLowerCase();
		    } 
		    char change[]=st.toCharArray();
		    for(int a=0;a<change.length;a++){
			if("jkqxz".indexOf(change[a])!=-1){
			    point+=3;
			}
			else if("cfhlmpvwy".indexOf(change[a])!=-1){
			    point+=2;
			}
			else{
			    point+=1;
			}
		    }
		    
		    quicksort(change,0,change.length-1);
		    dic_line[i][0]=String.valueOf(change);
		    if(Character.isUpperCase(dic_line[i][0].charAt(0))){
		        dic_line[i][0]=dic_line[i][0].toLowerCase();
		    } 
		    dic_line[i][1]=String.valueOf(point);
		    dic_line[i][2]=st;
		    pw.write(dic_line[i][0]+","+dic_line[i][1]+","+dic_line[i][2]+"\n");
		    //	    change=d_buf.readLine().toCharArray();
		    i++;
		    point=0;
		}while((st=d_buf.readLine())!=null);
		//		System.out.println(i-1+dic_line[i-1][0]);
		pw.close();
	    }
	    catch(IOException e){
		System.out.println(e);
	    }
	
	}catch(IOException e){

	}


	try{
	    String st2=null;
	    String[] dic_line2=new String[72412];
	    File file2=new File("sorted_dic.txt");//辞書順に並び替えたやつ、得点、大元の
	    PrintWriter pw2=new PrintWriter(new BufferedWriter(new FileWriter(file2)));
	    FileReader dict2=new FileReader("mid_dic.txt");
	    BufferedReader d_buf2=new BufferedReader(dict2);
	    int j=0;
	    while((st2=d_buf2.readLine())!=null){//辞書配列作成
		dic_line2[j]=st2;//名前
		j++;//辞書txtを配列に読み込み
	     } 
	    Arrays.sort(dic_line2);
	    for(int k=0;k<72412;k++){
	    pw2.write(dic_line2[k]+"\n");
	    //	    System.out.println(dic_line2[k]);
	    }
	    	pw2.close();


	}catch(IOException e){

	}

	




    }





}
