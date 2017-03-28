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
     * 发送 get请求 
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
            // 创建httpget.    
//            HttpGet httpget = new HttpGet("http://wap.10086.cn/getLoginInfo.jsp");  
//            HttpGet httpget = new HttpGet("http://www.baidu.com");  
            HttpGet httpget = new HttpGet("http://wap.10086.cn/phoneid.jsp");  
            System.out.println("executing request " + httpget.getURI()); 
            
         // 依次是代理地址，代理端口号，协议类型  
            HttpHost proxy = new HttpHost("127.0.0.1", 8888, "http");  
            RequestConfig config = RequestConfig.custom().setProxy(proxy).build(); 
            httpget.setConfig(config);
            
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget,context);
//            CookieStore cookieStore = context.getCookieStore();
//            List<Cookie> cookies = cookieStore.getCookies();
//            Iterator<Cookie> cookiesItr = cookies.iterator();
//            while(cookiesItr.hasNext()){
//            	Cookie cookieTep = cookiesItr.next();
//            	System.out.println(cookieTep.getName()+"----"+cookieTep.getValue());
//            }
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println("打印响应状态:"+response.getStatusLine());  
                System.out.println("getStatusCode():"+response.getStatusLine().getStatusCode());  
             // 输出为302，也就是说网页发生了重定向
                if(response.getStatusLine().getStatusCode()==302){
                	// 得到重定向后的网页
                	Header redirect = response.getFirstHeader("Location");
                    String url = redirect.getValue();
                    System.out.println("location------"+url);
                    httpget = new HttpGet(url); 
                    response = httpclient.execute(httpget);
                }
 
                if (entity != null) {  
                    // 打印响应内容长度    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
                    System.out.println("Response content: " + EntityUtils.toString(entity)); 
//                    String html = EntityUtils.toString(entity, "GBK");
                }  
                System.out.println("------------------------------------");  
                
             // 我们这里只是简单的打印出当前Cookie值以判断登录是否成功。
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
            // 关闭连接,释放资源    
            try {  
                httpclient.close();  
            } catch (Exception e) {  
                e.printStackTrace();  
            }  
        }  
    }  
}
