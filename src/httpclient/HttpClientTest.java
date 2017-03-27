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
	      // ���ô����������ַ�Ͷ˿�      
	      //client.getHostConfiguration().setProxy("proxy_host_addr",proxy_port); 
	      // ʹ�� GET ���� �������������Ҫͨ�� HTTPS ���ӣ���ֻ��Ҫ������ URL �е� http ���� https 
	         HttpMethod method=new GetMethod("http://java.sun.com");
	      //ʹ��POST����
	      //HttpMethod method = new PostMethod("http://java.sun.com");
	      client.executeMethod(method);

	      //��ӡ���������ص�״̬
	      System.out.println(method.getStatusLine());
	      //��ӡ���ص���Ϣ
	      System.out.println(method.getResponseBodyAsString());
	      //�ͷ�����
	      method.releaseConnection();
	}
	
	
	/** 
     * ���� get���� 
     */  
    public void get() {  
        CloseableHttpClient httpclient = HttpClients.createDefault();  
        try {  
            // ����httpget.    
            HttpGet httpget = new HttpGet("http://www.baidu.com/");  
            System.out.println("executing request " + httpget.getURI());  
            // ִ��get����.    
            CloseableHttpResponse response = httpclient.execute(httpget);  
            try {  
                // ��ȡ��Ӧʵ��    
                HttpEntity entity = response.getEntity();  
                System.out.println("--------------------------------------");  
                // ��ӡ��Ӧ״̬    
                System.out.println(response.getStatusLine());  
                
                
                if (entity != null) {  
                    // ��ӡ��Ӧ���ݳ���    
                    System.out.println("Response content length: " + entity.getContentLength());  
                    // ��ӡ��Ӧ����    
                    System.out.println("Response content: " + EntityUtils.toString(entity));  
                }  
                System.out.println("------------------------------------");  
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
