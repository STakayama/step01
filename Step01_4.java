import java.util.*;
import java.io.*;
import java.lang.*;
//二文字以下の単語はなし
 //文字によって得点は異なる
 //http://www.openreference.org/articles/view/623#anchor3
 //16文字だとかなり長い->4文字以下は検索させない？,母音なしも無視する？
//3と4は5文字まで対応なかったらやる？同じ文字数なら成功した時点で止める？
//一定の得点達成で止める？
//7文字により高得点な6文字はなかった


//母音なし、3字以下は無視
//a:0-37272,b:37273-42004,c:42005-50359,d:50360-56953,e:56954-67365,f:67366-68131,g:68132-69768,h:69769-70356,i:70357-71439,j:71440-71479,k:71480-71596,l:71597-71818,m:71819-72004,n:72005-72139,o:72140-72345,p:72346-72383,r:72384-72401,s:72402-72407,t:72408-72412


public class Step01_4{
    //辞書作成用
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

     
    static String[] search(String sorted_input,String num[],int dic_index[]){//ここでめちゃくちゃかかってる
	String ans="";
	String st,st2;
	int i=0;
	String[][]  dic_line=new String[72412][3];//名前,得点,答え
	try{
	    FileReader dict=new FileReader("2_sorted_dic.txt");
	    BufferedReader d_buf=new BufferedReader(dict);

	    while((st=d_buf.readLine())!=null){//辞書配列作成
		dic_line[i][0]=st.substring(0,st.indexOf(","));//名前
		//	 System.out.println("*********"+dic_line[i][0]);
		st2=st.substring(st.indexOf(",")+1);
		dic_line[i][1]=st2.substring(0,st2.indexOf(","));//得点
		//  System.out.println("*********"+dic_line[i][1]);
		dic_line[i][2]=st2.substring(st2.indexOf(",")+1);//答え
		i++;//辞書txtを配列に読み込み
	    } 
	     

	    //binary
	    int lef=dic_index[0];
	    int righ=dic_index[1];//dic_line.length-1;
	    do{
		int center=(lef+righ)/2;
		System.out.println(lef);
		System.out.println(righ);
		System.out.println("c:"+center);
		if(dic_line[center][0].length()==sorted_input.length()){
		    if(dic_line[center][0].compareTo(sorted_input)==0){
			ans=dic_line[center][2];
			System.out.println("一致:"+ans+","+dic_line[center][1]);
			num[0]=String.valueOf(center);//どこにあるか
			num[1]=dic_line[center][1];//得点
			num[2]=dic_line[center][2];
			return num;
		    }
		    else if(dic_line[center][0].compareTo(sorted_input)<0){//dic_lineのが小さい
			lef=center+1;
			//  System.out.println(lef);
		    }else {
			righ=center-1;
		    }
		}
		else{
		    if(dic_line[center][0].compareTo(sorted_input)<0){//dic_lineのが小さい
			lef=center+1;
			//  System.out.println(lef);
		    }else {
			righ=center-1;
		    }
		}
	    }while(lef<=righ);

	    //input_char短くしたい
	}
	catch(IOException e){
	    System.out.println(e);
	}
	//	 System.out.println("失敗");
	num[0]="0";
	num[1]="0";
	num[1]="0";
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
	//	String input=args[0];　//入力受け取り
 
	int dic_index[]=new int[2];

	String answer=null;

	String ans_num[]=new String[3];//場所、得点、名前
	String save_num[]=new String[3];
	save_num[1]="0";
	try{
	    InputStreamReader in = new InputStreamReader(System.in);       //（１）
	    BufferedReader buf_in= new BufferedReader(in);
	    System.out.println("何か入力してください.");
	    String input=buf_in.readLine();//入力受け付け


	     int index[][]=new int[26][2];
	    try{//索引作成
	   //アルファベット、開始と終わり	
	    FileReader ind=new FileReader("2_sorted_dic.txt");
	    BufferedReader in_buf=new BufferedReader(ind);

	    String com=in_buf.readLine();
	    index[0][0]=0;
	    int al=0;
	    int inde=1;
	    char check='a';
	    while((com=in_buf.readLine())!=null){
		//		System.out.println(check);
		//System.out.println(al);	
		//System.out.println(inde);
		 
		if(!com.startsWith(String.valueOf(check))){
		    index[al][1]=inde-1;//ok
		    al++;
		    check++;
		    index[al][0]=inde;
		    while(!com.startsWith(String.valueOf(check))||check=='z'){
			//	System.out.println(check);
			//	System.out.println(al);			
			al++;
			check++;//次のアルファベットへ ないやつもある
		    }
		}
		inde++;
	    }
	    
	    do{
				al++;
		check++;
		//	System.out.println(al);
		//	System.out.println(check);
		index[al][0]=-1;
		index[al][1]=-1;

	    }while(check!='z');
	    //	    System.out.println(index[al][0]);
	    //	    System.out.println(index[al][1]);
		al++;
		check++;


	    in_buf.close();

	}catch(IOException e){

	}



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
			//		 System.out.println("s");
		    }
		    else{
			//		 System.out.println("b");
		    }
		}
		//	 System.out.println("check1");
		if(strtemp==null||strtemp.length()<3||(strtemp.indexOf("a")==-1&&strtemp.indexOf("i")==-1&&strtemp.indexOf("u")==-1&&strtemp.indexOf("e")==-1&&strtemp.indexOf("o")==-1)) continue;//三文字未満は検索しない、母音なしも
		//	 System.out.println("check2");


		//	System.out.println(strtemp.toCharArray());

		char[] input_char=strtemp.toCharArray();
		quicksort(input_char,0,input_char.length-1);//入力文字の整列 最初に一回でもよさげ？
		String    after_input=String.valueOf(input_char);
			 System.out.println("並び替え:"+after_input);



		//System.out.println(after_input);
		if(index[after_input.charAt(0)-'a'][0]>-1){//先頭にないやつは除外
		    ans_num=search(after_input,ans_num,index[after_input.charAt(0)-'a']);


				}

		if(Integer.parseInt(ans_num[1])>10){//得点が10以上ならば
		    save_num=ans_num.clone();
		    break;

		}


		//判定
		if(Integer.parseInt(save_num[1])<Integer.parseInt(ans_num[1])){
		    //  save_num[0]=ans_num[0];
		    //   save_num[1]=ans_num[1];
		    save_num=ans_num.clone();
		    ans_num[0]="0";
		    ans_num[1]="1";
		    ans_num[2]=null;
		}


	    }//for

	    System.out.println("結果："+save_num[2]+"得点:"+save_num[1]);

	}catch(IOException e){

	}

	//mainおわり

    }
}






//辞書に得点情報追加、ansを更新していく
