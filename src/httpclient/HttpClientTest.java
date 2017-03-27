package httpclient;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.junit.Test;


public class HttpClientTest {

	public static void main(String[] args) {
		

	}

	@Test
	public void unitTest(){
		get();
	}

	public void get2(){
		 HttpClient client = new HttpClient(); 
	      // 设置代理服务器地址和端口      
	      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
	      // 使用 GET 方法 ，如果服务器需要通过 HTTPS 连接，那只需要将下面 URL 中的 http 换成 https 
	         HttpMethod method=new GetMethod("http://java.sun.com");
	      //使用POST方法
	      //HttpMethod method = new PostMethod("http://java.sun.com");
	      client.executeMethod(method);

	      //打印服务器返回的状态
	      System.out.println(method.getStatusLine());
	      //打印返回的信息
	      System.out.println(method.getResponseBodyAsString());
	      //释放连接
	      method.releaseConnection();
	}
	
	
	/** 
     * 发送 get请求 
     */  
    public void get() {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // 创建httpget.    
            HttpGet httpget = new HttpGet("http://www.baidu.com/");  
            System.out.println("executing request " + httpget.getURI());  
            // 执行get请求.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // 获取响应实体    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // 打印响应状态    
                System.out.println(response.getStatusLine());  
                
                
                if (entity != null) {  
                    // 打印响应内容长度    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // 打印响应内容    
                    System.out.println("Response content: " + EntityUtils.toString(entity));  
                }  
                System.out.println("------------------------------------");  
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
