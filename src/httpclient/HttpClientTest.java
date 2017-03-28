package httpclient;

import java.util.Iterator;
import java.util.List;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.client.CookieStore;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.protocol.HttpClientContext;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;

import com.gargoylesoftware.htmlunit.javascript.host.geo.Coordinates;


public class HttpClientTest {

	public static void main(String[] args) {
		

	}

	@Test
	public void unitTest() throws Exception{
		get();
	}


	
	/** 
     * ���� get���� 
	 * @throws Exception 
     */  
    public void get() throws Exception {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
//        CloseableHttpClient httpclient = new SSLClient();  
//        RequestConfig config = RequestConfig.DEFAULT ;
//        System.out.println("getConnectTimeout():"+config.getConnectTimeout());
//        System.out.println("getConnectionRequestTimeout():"+config.getConnectionRequestTimeout());
//        System.out.println("getCookieSpec():"+config.getCookieSpec());
        HttpClientContext context = HttpClientContext.create();
     
        try {  
            // ����httpget.    
//            HttpGet httpget = new HttpGet("http://wap.10086.cn/getLoginInfo.jsp");  
//            HttpGet httpget = new HttpGet("http://www.baidu.com");  
            HttpGet httpget = new HttpGet("http://wap.10086.cn/phoneid.jsp");  
            System.out.println("executing request " + httpget.getURI()); 
            
         // �����Ǵ����ַ������˿ںţ�Э������  
            HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");  
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build(); 
            httpget.setConfig(config);
            
            // ִ��get����.    
            CloseableHttpResponse response = httpclient.execute(httpget,context);
//            CookieStore cookieStore = context.getCookieStore();
//            List<Cookie> cookies = cookieStore.getCookies();
//            Iterator<Cookie> cookiesItr = cookies.iterator();
//            while(cookiesItr.hasNext()){
//            	Cookie cookieTep = cookiesItr.next();
//            	System.out.println(cookieTep.getName()+"----"+cookieTep.getValue());
//            }
            try {  
                // ��ȡ��Ӧʵ��    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // ��ӡ��Ӧ״̬    
                System.out.println("��ӡ��Ӧ״̬:"+response.getStatusLine());  
                System.out.println("getStatusCode():"+response.getStatusLine().getStatusCode());  
             // ���Ϊ302��Ҳ����˵��ҳ�������ض���
                if(response.getStatusLine().getStatusCode()==302){
                	// �õ��ض�������ҳ
                	Header redirect = response.getFirstHeader("Location");
                    String url = redirect.getValue();
                    System.out.println("location------"+url);
                    httpget = new HttpGet(url); 
                    response = httpclient.execute(httpget);
                }
 
                if (entity != null) {  
                    // ��ӡ��Ӧ���ݳ���    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // ��ӡ��Ӧ����    
                    System.out.println("Response content: " + EntityUtils.toString(entity)); 
//                    String html = EntityUtils.toString(entity, "GBK");
                }  
                System.out.println("------------------------------------");  
                
             // ��������ֻ�Ǽ򵥵Ĵ�ӡ����ǰCookieֵ���жϵ�¼�Ƿ�ɹ���
//                List<Cookie> cookies = ((AbstractHttpClient)httpclient).getCookieStore().getCookies();
//                for(Cookie cookie: cookies){
//                	System.out.println(cookie);
//                }
                
             // JSESSIONID
//                String setCookie = response.getFirstHeader("Set-Cookie").getValue();
//                String JSESSIONID = setCookie.substring("JSESSIONID=".length(),setCookie.indexOf(";"));
//                System.out.println("JSESSIONID:" + JSESSIONID);
                Header [] headers = response.getAllHeaders();
                for(int i=0;i<headers.length;i++){
                	System.out.println(headers[i].getName()+"======="+headers[i].getValue()); 
                }
            } finally {  
                response.close();  
            }  
        } catch (Exception e) {  
            e.printStackTrace();  
        } finally {  
            // �ر�����,�ͷ���Դ    
            try {  
                httpclient.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
