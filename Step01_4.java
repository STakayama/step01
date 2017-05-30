 import java.util.*;
 import java.io.*;
 //二文字以下の単語はなし
 //文字によって得点は異なる
 //http://www.openreference.org/articles/view/623#anchor3
//16文字だとかなり長い->4文字以下は検索させない？,母音なしも無視する？

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

     
     static String[] search(String sorted_input,String num[]){//ここでめちゃくちゃかかってる
	 String ans="";
	 String st,st2;
	 int i=0;
	 String[][]  dic_line=new String[72412][3];//三つ目：名前
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
	     int lef=0;
	     int righ=dic_line.length-1;
	     do{
		 int center=(lef+righ)/2;
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
		 }else{
		     righ=center-1;
		 }
	     }while(lef<=righ);

	     //input_char短くしたい
	 }
	 catch(IOException e){
	     System.out.println(e);
	 }
	 System.out.println("失敗");
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
	 //BufferedReader buf=new BufferedReader(new InputStreamReader(System.in));
	 //	String input=args[0];　//入力受け取り

	 String answer=null;

	 String ans_num[]=new String[3];//場所、得点、名前
	 String save_num[]=new String[3];
	 save_num[1]="0";
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
			 //		 System.out.println("s");
		     }
		     else{
			 //		 System.out.println("b");
		     }
		 }
		 //	 System.out.println("check1");
		 if(strtemp==null||strtemp.length()<3||(strtemp.indexOf("a")==-1&&strtemp.indexOf("i")==-1&&strtemp.indexOf("u")==-1&&strtemp.indexOf("e")==-1&&strtemp.indexOf("o")==-1)) continue;//三文字未満は検索しない、母音なしも
		 //	 System.out.println("check2");
		 System.out.println(strtemp.toCharArray());

		 char[] input_char=strtemp.toCharArray();
		 quicksort(input_char,0,input_char.length-1);//入力文字の整列
		 String    after_input=String.valueOf(input_char);
		 System.out.println("並び替え:"+after_input);

		 ans_num=search(after_input,ans_num);

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

	 /*	 try{
	     FileReader dic=new FileReader("dic.txt");
	     BufferedReader dic_buf=new BufferedReader(dic);

	     if(Integer.parseInt(ans_num[0])!=-1){	
		 for(int i=0;i<Integer.parseInt(save_num[0])+1;i++){
		     answer=dic_buf.readLine();
		     //	    System.out.println(answer);

		 }
		 System.out.println(answer+","+save_num[1]);
	     }

	 }catch(IOException e){

	 }*/
     }
 }






 //辞書に得点情報追加、ansを更新していく
