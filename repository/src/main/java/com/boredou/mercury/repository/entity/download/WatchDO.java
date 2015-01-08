package com.boredou.mercury.repository.entity.download;

import com.alibaba.fastjson.JSONObject;

import lombok.Data;
import lombok.Getter;

@Data
public class WatchDO {
	private String e_colA ;
	private String e_colB ;
	private String e_colC ;
	private String e_colD ;
	private String e_colE ;
	private String e_colF ;
	private String e_colG ;
	private String e_colH ;
	public WatchDO(String modelNum,String asin,String bandColor){
		System.out.println("--- "+modelNum+asin+bandColor);
		this.e_colA = String.format(WatchHelp.getColA(), asin,bandColor.substring(0, 3));
		System.out.println("e_colA...complete");
		this.e_colB = String.format(WatchHelp.getColB(), asin,bandColor.substring(0, 3));
		System.out.println("e_colB...complete");
		this.e_colC = WatchHelp.getColC();
		System.out.println("e_colC...complete");
		this.e_colD = WatchHelp.getColD(); 
		System.out.println("e_colD...complete");
		this.e_colE = String.format(WatchHelp.getColE(), modelNum,modelNum,bandColor);
		System.out.println("e_colE...complete");
		this.e_colF = String.format(WatchHelp.getColF(), modelNum,bandColor);
		System.out.println("e_colF...complete");
		this.e_colG = String.format(WatchHelp.getColG(), modelNum,bandColor);
		System.out.println("e_colG...complete");
		this.e_colH = String.format(WatchHelp.getColH(), modelNum,bandColor);
		System.out.println("e_colH...complete");
		
	}
//	private
	public static void main(String[] args){
		System.out.println(String.format("%s100%%本", "wo","sss",null));
//		String json = "{\"ModelNum\":\"MBM1246\",\"imgUrl\":[\"http://ecx.images-amazon.com/images/I/414qZH0icCL.jpg\",\"http://ecx.images-amazon.com/images/I/51GqEffR1oL.jpg\",\"http://ecx.images-amazon.com/images/I/41CCh-SyhGL.jpg\"],\"material\":{\"Band Material\":\"leather\",\"Case Material\":\"Gold Ion-plated Stainless Steel\",\"Dial window material type\":\"Scratch Resistant Mineral\"},\"price\":\"$128.50\",\"stock\":\"In Stock.\"}";
//		JSONObject jo = JSONObject.parseObject(json);
//		if(jo.containsKey("ModelNum"))
//			System.out.println(jo.get("ModelNum"));
	}

}

@Data
class WatchHelp{
//	asin=B004JVVYSI , Band color=ROS(前三个字母)
	@Getter
	private static String colA="%s-%s";
//	asin=B004JVVYSI , Band color=ROS(前三个字母)
	@Getter
	private static String colB="%s-%s";
//	照写
	@Getter
	private static String colC="【レビューを書いて送料無料】【正規品】Marc Jacobsのセカンドライン『マークバイマークジェイコブス』カジュアルで遊び心あるポップなデザイン、リーズナブルな価格帯が魅力的 <BR>";
//	照写
	@Getter
	private static String colD="正規品 マークジェイコブス Marc by Marc Jacobs 腕時計";
	
//	ModelNum=MBM3077 ,ModelNum=MBM3077 ,Band color=ROSEGOLD
	@Getter
	private static String colE="【お取り寄せ】 マ-ク バイ マ-ク ジェイコブス レディース ウォッチ %s Marc By Marc Jacobs Ladies Watch %s %s";
	
//	ModelNum=MBM3077,Band color=ROSEGOLD
	@Getter
	private static String colF="<TABLE cellspacing=\"2\" cellpadding=\"0\" border=\"0\">" 
  +"\n"+"<TBODY>"
  +"\n"+"<TR>"
  +"\n"+"<TD>"
  +"\n"+"<TABLE width=\"500\" border=\"0\">"
  +"\n"+"<TBODY>"
  +"\n"+"<TR>"
  +"\n"+"<TD height=\"25\" valign=\"middle\" bgcolor=\"#8B4513\"><FONT color=\"#FFFFFF\" size=\"2\">■商品詳細</FONT></TD>"
  +"\n"+"</TR>"
  +"\n"+"<TR>"
  +"\n"+"<TD height=\"10\"></TD>"
  +"\n"+"</TR>"
  +"\n"+"<TR>"
  +"\n"+"<TD align=\"center\">"
  +"\n"+"<TABLE width=\"480\" border=\"1\" bgcolor=\"#FFFFFF\">"
		  +"\n"+"<TBODY>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD width=\"100\"><FONT size=\"2\">■ブランド</FONT></TD>"
		  +"\n"+"<TD><FONT size=\"-1\">Marc by Marc Jacobs マークバイマークジェイコブス</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD><FONT size=\"2\">■商品名</FONT></TD>"
		  +"\n"+"<TD><FONT size=\"-1\">%s</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD><FONT size=\"2\">■商品状態</FONT></TD>"
		  +"\n"+"<TD><FONT size=\"2\">新品未使用・並行輸入品<BR>"
		  +"\n"+"輸入品につき、箱に傷み等がある場合がございます。<BR>"
		  +"\n"+"予めご了承下さい。<BR>"
		  +"\n"+"</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD><FONT size=\"2\">■色</FONT></TD>"
		  +"\n"+"<TD><FONT size=\"2\">%s</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD><FONT size=\"2\">■発送</FONT></TD>"
		  +"\n"+"<TD><FONT size=\"2\">当店通常発送【送料500円】沖縄、離島を除く</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"</TBODY>"
		  +"\n"+"</TABLE>"
		  +"\n"+"</TD>"
		  +"\n"+"</TR>"
		  +"\n"+"</TBODY>"
		  +"\n"+"</TABLE>"
		  +"\n"+"</TD>"
		  +"\n"+"</TR>"
		  +"\n"+"</TBODY>"
		  +"\n"+"</TABLE>"
		  +"\n"+"<!-- size start -->"
		  +"\n"+"<TABLE width=\"500\" border=\"0\">"
		  +"\n"+"<TBODY>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD height=\"25\" valign=\"middle\" bgcolor=\"#8B4513\"><FONT color=\"#FFFFFF\" size=\"2\">■商品説明</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD height=\"10\"></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD align=\"center\">"
		  +"\n"+"<TABLE width=\"480\" border=\"1\" bgcolor=\"#FFFFFF\">"
		  +"\n"+"<TBODY>"
		  +"\n"+"<TR>"
		  +"\n"+"<TD><FONT size=\"2\">■100%%本物のマークバイマークジェイコブス、正規品のみを販売しております。 Marc by Marc Jacobs（マークbyマークジェイコブス）は、Marc"
		  +"\n"+"Jacobsよりカジュアルで遊び心あるポップなデザインが特徴的。普段のコーディネートをワンランクオシャレに魅せてくれるアイテムが沢山。またリーズナブルな価格帯も魅力のひとつです。"
		  +"\n"+"ご質問等ございましたらお気軽にどうぞ。 <BR>"
		  +"\n"+"<BR>"
		  +"\n"+"</FONT></TD>"
		  +"\n"+"</TR>"
		  +"\n"+"</TBODY>"
		  +"\n"+"</TABLE>"
		  +"\n"+"</TD>"
		  +"\n"+"</TR>"
		  +"\n"+"</TBODY>"
		  +"\n"+"</TABLE>"
		  +"\n"+"<pt><spf><sa>";
		
//	ModelNum=MBM3077,Band color=ROSEGOLD
	@Getter
	private static String colG="<CENTER>【この商品はお取り寄せ商品です】<BR>"
			+"\n"+"<FONT color=\"red\">↓重要↓</FONT><BR>"
			+"\n"+"<A href=\"http://m.rakuten.co.jp/auc-jetrag/p/82816\">◆購入前に必ずご確認下さい◆</A><BR>"
			+"\n"+"<FONT color=\"#ff0000\">※スマートフォンの方は<BR>"
			+"\n"+"PC用ページを必ずご確認下さい。</FONT></CENTER>"
			+"\n"+"<HR>"
			+"\n"+"■ブランド Marc by Marc Jacobs マークバイマークジェイコブス<BR>"
			+"\n"+"■商品名 %s<BR>"
			+"\n"+"■商品状態 新品未使用・並行輸入品<BR>"
			+"\n"+"輸入品につき、箱に傷み等がある場合がございます。<BR>"
			+"\n"+"予めご了承下さい。<BR>"
			+"\n"+"■色 %s<BR>"
			+"\n"+"■発送 当店通常発送【送料500円】沖縄、離島を除く <BR>";
	
//	ModelNum=MBM3077,Band color=ROSEGOLD
	@Getter
	private static String colH="<CENTER>【この商品はお取り寄せ商品です】<BR>"
			+"\n"+"<FONT color=\"red\">↓重要↓</FONT><BR>"
			+"\n"+"<A href=\"http://www.rakuten.ne.jp/gold/auc-jetrag/buckorder-sp.htm\">◆購入前に必ずご確認下さい◆</A><BR>"
			+"\n"+"<FONT color=\"#ff0000\">※スマートフォンの方は<BR>"
			+"\n"+"PC用ページを必ずご確認下さい。</FONT></CENTER>"
			+"\n"+"<BR>"
			+"\n"+"■ブランド Marc by Marc Jacobs マークバイマークジェイコブス<BR>"
			+"\n"+"■商品名 %s<BR>"
			+"\n"+"■商品状態 新品未使用・並行輸入品<BR>"
			+"\n"+"輸入品につき、箱に傷み等がある場合がございます。<BR>"
			+"\n"+"予めご了承下さい。<BR>"
			+"\n"+"■色 %s<BR>"
			+"\n"+"■発送 当店通常発送【送料500円】沖縄、離島を除く <BR>";
	
	
//	private String colI="scrolling="no" width="760" height="350"></iframe><BR><P><A href="http://www.rakuten.ne.jp/gold/auc-jetrag/review.html"><IMG src="http://image.rakuten.co.jp/auc-jetrag/cabinet/02942174/img60194372.jpg" border="0"><BR><FONT size="+3">詳しくはこちらでご確認頂けます。</FONT></A></P><BR><TABLE border="0" cellspacing="0" bgcolor="#865b30">  <TBODY>    <TR>      <TH bgcolor="#865b30"><FONT color="#ffffff">and more...</FONT></TH>    </TR>    <TR>      <TD><A href="http://item.rakuten.co.jp/auc-jetrag/camper-8267616-123118/"><IMG src="http://image.rakuten.co.jp/auc-jetrag/cabinet/gaityu/gaityu5/8267616-123118-1.jpg" width="150" border="0"></A></TD>    </TR>  </TBODY></TABLE>";
	@Getter
	private byte[] colIMG = null;
}
