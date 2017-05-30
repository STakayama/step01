import java.util.*;
import java.io.*;
//二文字以下の単語はなし
//文字によって得点は異なる
//http://www.openreference.org/articles/view/623#anchor3

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


    static int[] search(String sorted_input,int num[]){//ここでめちゃくちゃかかってる
	String ans=null;
	String st;
	int i=0;
	String[]  dic_line=new String[72412];
	try{
	    FileReader dict=new FileReader("sorted_dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);

	    while((st=d_buf.readLine())!=null){
		dic_line[i]=st.substring(0,st.indexOf(","));//番号
		if(Arrays.asList(dic_line).contains(sorted_input)){//dic_line配列全体にafter_inputは含まれているか
		    ans=dic_line[i];
		    System.out.println("一致:"+ans);
		    num[0]=i;
		    num[1]=Integer.parseInt(st.substring(st.indexOf(",")+1));//得点
		    System.out.println(num[1]);
		    return num;//あった場所
		    //	break;
		}
		i++;//辞書txtを配列に読み込み
	    } 
	    //input_char短くしたい
	}
	catch(IOException e){
	    System.out.println(e);
	}
	num[0]=0;
	num[1]=0;
	return num;

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
	
	int ans_num[]=new int[2];//後ろは、番号とポイント
	int save_num[]=new int[2];
	save_num[1]=0;
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
		if(strtemp==null||strtemp.length()<3) continue;
		System.out.println("check2");
		System.out.println(strtemp.toCharArray());

		char[] input_char=strtemp.toCharArray();
		quicksort(input_char,0,input_char.length-1);//入力文字の整列
		String    after_input=String.valueOf(input_char);
		System.out.println("並び替え:"+after_input);
		
		ans_num=search(after_input,ans_num);

		//判定
		if(save_num[1]<ans_num[1]){
		    //  save_num[0]=ans_num[0];
		    //   save_num[1]=ans_num[1];
		    save_num=ans_num.clone();
		    ans_num[0]=0;
		    ans_num[1]=1;
		}

		
	    }//for

	}catch(IOException e){

	}
	
	//mainおわり

	try{
	    FileReader dic=new FileReader("dic.txt");
	    BufferedReader dic_buf=new BufferedReader(dic);
		    
	    if(ans_num[0]!=-1){	
		for(int i=0;i<save_num[0]+1;i++){
		    answer=dic_buf.readLine();
		    //	    System.out.println(answer);
			    
		}
		System.out.println(answer+","+save_num[1]);
	    }
		    
	}catch(IOException e){
		    
	}
    }
}







//辞書に得点情報追加、ansを更新していく
