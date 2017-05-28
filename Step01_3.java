import java.util.*;
import java.io.*;
//入力したやつ一致なければ一つずつ抜く
//得点高くしたいから、頻度の情報も入れる？

public class Step01_3{

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


    static int search(char[] ch,String sorted_input){
	String ans=null;
	String st;
	int i=0;
	String[]  dic_line=new String[72412];
	try{
	    FileReader dict=new FileReader("sorted_dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);
	    //		char change[]=d_buf.readLine().toCharArray();//辞書の文字をどう入れ替えるか   一行の文字列をを一個一個格納
	    //	dict_line[i];
	    while((st=d_buf.readLine())!=null){
		dic_line[i]=st;
		if(Arrays.asList(dic_line).contains(sorted_input)){//dic_line配列全体にafter_inputは含まれているか
		    ans=dic_line[i];
		    System.out.println("一致:"+ans);
		    return i;
		    //	break;
		}
		i++;//辞書txtを配列に読み込み
	    } 
	    //input_char短くしたい
	    //ここでd_buf開き直す？ 
	}
	catch(IOException e){
	    System.out.println(e);
	}
	return -1;

    }

    public static  boolean func3(int n, int maxn, boolean[] boollist, ArrayList all){//maxn...文字数
	if(n==maxn){
	    //boollistのコピー
	    all.add(boollist.clone());//これを保存すれば全通りが得られる
	    return true;
	}
	boollist[n]=true;
	func3(n+1,maxn,boollist,all);
	boollist[n]=false;
	func3(n+1,maxn,boollist,all);
	return true;
    }

    public static void main(String[] args){
	//BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	//	String input=args[0];　//入力受け取り

	String answer=null;
	
	int ans_num;

	try{
	    InputStreamReader in = new InputStreamReader(System.in);       //（１）
	    BufferedReader buf_in= new BufferedReader(in);
	    System.out.println("何か入力してください.");
	    String input=buf_in.readLine();//入力受け付け
	    
	    boolean[] boollist = new boolean[input.length()];
	    ArrayList list = new ArrayList();
	    int tempn=0;
	    func3(tempn,input.length(),boollist, list);
	    boollist = null;//ガーベジコレクション対象

	    System.out.println(list.size());
	    for(int li=0;li<list.size();li++){
		boolean[] templist = (boolean[])list.get(li);
		String strtemp = "";
		for(int j=0;j<templist.length;j++){
		    if(templist[j]){
			strtemp += input.charAt(j);
		System.out.println("s");
		    }
		    else{
		System.out.println("b");
		    }
		}
		System.out.println("check1");
		if(strtemp==null) continue;
		System.out.println("check2");
		System.out.println(strtemp.toCharArray());

		char[] input_char=strtemp.toCharArray();
		quicksort(input_char,0,input_char.length-1);
		String    after_input=String.valueOf(input_char);
		System.out.println("並び替え:"+after_input);
		
		ans_num=search(input_char,after_input);
		try{
		    FileReader dic=new FileReader("dic.txt");
		    BufferedReader dic_buf=new BufferedReader(dic);
		    
		    if(ans_num!=-1){	
			for(int i=0;i<ans_num+1;i++){
			    answer=dic_buf.readLine();
			    //	    System.out.println(answer);
			    
			}
			System.out.println(answer);
		    }
		    
		}catch(IOException e){
		    
		}
	    }//for
	}catch(IOException e){

	    }
	
	//mainおわり


    }
}







