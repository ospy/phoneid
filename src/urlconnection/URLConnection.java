package urlconnection;

import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class URLConnection {

	public static void main(String[] args) throws Exception {
		Proxy proxy = new Proxy(java.net.Proxy.Type.HTTP, new InetSocketAddress("127.0.0.1", 8888));  
		URL url = new URL("http://wap.10086.cn/phoneid.jsp");
		HttpURLConnection conn = (HttpURLConnection) url.openConnection(proxy);//´úÀí
		conn.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
		conn.setInstanceFollowRedirects(false);
		conn.connect();
		String location="";
		//URL url = new URL("https://www.baidu.com/rec?platform=wise&ms=1&rset=rcmd&word=%E6%88%90%E9%83%BD&qid=10126517336476765368&rq=%E6%88%90%E9%83%BD&from=844b&baiduid=C9B38F2B715E1E07D94D818C895C1189%3AFG%3D1&tn=&clientWidth=400&t=1490430233087&r=2184");
		
		Map<String, List<String>> headers = conn.getHeaderFields();
		
		Set<String> keys = headers.keySet();
		for (String key : keys) {
			String val = conn.getHeaderField(key);
			System.out.println(key + "===" + val);
			if(key !=null && key.equals("Location")){
				location = val;
			}
		}

		System.out.println("=================");
		System.out.println(location);
		System.out.println("=================");
		
		HostnameVerifier hv = new HostnameVerifier(){
			@Override
			public boolean verify(String urlHostName, SSLSession session) {
				System.out.println("Warning:URL HOST:"+urlHostName+" vs. "+session.getPeerHost());
				return true;
			}
		};
		
		
		if(location !=""){
			trustAllHttpsCertificates();
			HttpsURLConnection.setDefaultHostnameVerifier(hv);
			url = new URL(location);
			HttpsURLConnection conns = (HttpsURLConnection)url.openConnection(proxy);
			conns.setRequestProperty("User-Agent", "Mozilla/5.0 (Windows NT 6.1; WOW64; rv:52.0) Gecko/20100101 Firefox/52.0");
//			conns.setInstanceFollowRedirects(false);
			conns.connect();
			headers = conns.getHeaderFields();
			keys = headers.keySet();
			for (String key : keys) {
				String val = conns.getHeaderField(key);
				System.out.println(key + "///" + val);			
			}
			
			//print set-cookie
			String key=null;
			for(int i=1;(key = conns.getHeaderFieldKey(i)) != null;i++){
				String value =  conns.getHeaderField(i);
				System.out.println(key + "~~~~~~~~"+value);
			}
			
		}
		System.out.println("conn.getLastModified():"+conn.getLastModified());
	}
	
	
	

	private static void trustAllHttpsCertificates() throws Exception{
		TrustManager[] trustAllCerts = new TrustManager[1];
		TrustManager tm = new miTM();
		trustAllCerts[0]= tm;
		SSLContext sc = SSLContext.getInstance("SSL");
		sc.init(null, trustAllCerts, null);
		HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	}
	
	static class miTM implements TrustManager,X509TrustManager{

		@Override
		public void checkClientTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
			// TODO Auto-generated method stub
			return;
		}

		@Override
		public void checkServerTrusted(X509Certificate[] arg0, String arg1)
				throws CertificateException {
			return;
		}

		@Override
		public X509Certificate[] getAcceptedIssuers() {
			// TODO Auto-generated method stub
			return null;
		}
		
		public boolean isServerTrusted(X509Certificate[] certs){
			return true;
		}
		public boolean isClientTrusted(X509Certificate[] certs){
			return true;
		}
	}

}
