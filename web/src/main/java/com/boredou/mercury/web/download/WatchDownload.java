package com.boredou.mercury.web.download;

public class WatchDownload implements AmazonDownload {

	@Override
	public void doDownload(String goodsType) {
		
	}
	
	public static String getAllCol(){
		return getCol1("lwk") + getCol2();
	}
	private static String getCol1(String modelNum){
		return String.format(col1,modelNum);
	}
	private static String getCol2(){
		return col2;
	}
	private static String col1 = "%s";
	private static String col2 = "レビューを書いて送料無料】【正規品】Marc Jacobsのセカンドライン『マークバイマークジェイコブス』カジュアルで遊び心あるポップなデザイン、リーズナブルな価格帯が魅力的 <BR>";
	private static String col3 = "正規品 マークジェイコブス Marc by Marc Jacobs 腕時計";
	private static String col4 = "【お取り寄せ】 マ-ク バイ マ-ク ジェイコブス レディース ウォッチ MBM3077 Marc By Marc Jacobs Ladies Watch MBM3077 ROSEGOLD";
	private static String col5 = "";
	private static String col6 = "";
	private static String col7 = "";
	private static String col8 = "";
	public static void main(String[] args){
		System.out.println(getCol1("M990"));
	}

}
