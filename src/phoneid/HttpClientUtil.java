package phoneid;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;

public class HttpClientUtil {
	public static final String urlString = "http://....?passport=...&password=...";  //�ȵ�¼����cookie
	public static final String urlString2 = "http://......";
	public String sessionId = "";
	
	public void doGet(String urlStr) throws IOException{
		String key = "";
		String cookieVal = "";
		URL url = new URL(urlStr);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.connect(); //���˲�ֻ�ǽ������������tcp���ӣ���δ����http����
		/** 
		 * ����cookie
		 */
		if(!sessionId.equals("")){
			connection.setRequestProperty("Cookie", sessionId);
		}
		for(int i=1;(key=connection.getHeaderField(i))!=null;i++){
			cookieVal = connection.getHeaderField(i);
			cookieVal = cookieVal.substring(0,cookieVal.indexOf(";")>-1?cookieVal.indexOf(";"):cookieVal.length()-1);
			sessionId = sessionId + cookieVal + ";";
		}
		System.out.println("sessionId========"+sessionId);
		//ֱ��getInputStream()��������������������ͳ�ȥ
		BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		StringBuilder sb = new StringBuilder();
		String line;
		while((line=br.readLine()) != null){
			sb.append(line);
			sb.append("\n");
		}
		System.out.println("~~~~~~~~~~"+sb.toString());
		br.close();
		connection.disconnect();
	}
	
	public void doPost() throws IOException{
		URL url = new URL(urlString);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setDoOutput(true); //�������������Ϊtrue,Ĭ��false (post �����������ķ�ʽ��ʽ�Ĵ��ݲ���)
		connection.setDoInput(true); // ��������������Ϊtrue
		connection.setRequestMethod("POST"); // ��������ʽΪpost
		connection.setUseCaches(false); // post���󻺴���Ϊfalse
		connection.setInstanceFollowRedirects(true); //// ���ø�HttpURLConnectionʵ���Ƿ��Զ�ִ���ض���
		// ��������ͷ����ĸ������� (����Ϊ�������ݵ�����,����Ϊ����urlEncoded�������from����)
        // application/x-javascript text/xml->xml���� application/x-javascript->json���� application/x-www-form-urlencoded->������
		connection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
		connection.connect();
		
		// �������������,�����������������Я���Ĳ���,(�������Ϊ?���������)
		DataOutputStream dataout = new DataOutputStream(connection.getOutputStream());
        String parm = "storeId=" + URLEncoder.encode("32", "utf-8"); //URLEncoder.encode()����  Ϊ�ַ������б���           
        dataout.writeBytes(parm); 
        dataout.flush();
        dataout.close(); // ��Ҫ���׺��Բ��� (�ر���,�м�!)           
        // ���ӷ�������,�����������Ӧ  (�����ӻ�ȡ������������װΪbufferedReader)
        BufferedReader bf = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        String line;
        StringBuilder sb = new StringBuilder(); // �����洢��Ӧ����           
        // ѭ����ȡ��,��������β��
        while ((line = bf.readLine()) != null) {
            sb.append(bf.readLine());
        }
        bf.close();    // ��Ҫ���׺��Բ��� (�ر���,�м�!)
        connection.disconnect(); // ��������
        System.out.println(sb.toString()); 
	}
	
	public static void main(String[] args) throws IOException {
		HttpClientUtil hcu = new HttpClientUtil();
		hcu.doGet(urlString);
		hcu.doGet(urlString2);
	}
}