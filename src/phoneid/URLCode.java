package phoneid;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class URLCode {

	public static void main(String[] args) throws UnsupportedEncodingException {
		String url = "cmccssotoken=2addfe2e76b94d59a66724038f54c03c@.10086.cn;domain=.10086.cn;secure;HTTPOnly;userinfokey=%7b%22loginType%22%3a%2201%22%2c%22provinceName%22%3a%22280%22%2c%22pwdType%22%3a%2202%22%2c%22userName%22%3a%2215802811012%22%7d; domain=.10086.cn; path=/is_login=true; domain=.10086.cn; path=/loginName=15802811012; domain=.10086.cn; path=/c=2addfe2e76b94d59a66724038f54c03c; domain=.10086.cn; path=/CITY_INFO=100|10;domain=.10086.cn;path=/;expires=Mon, 29-Jun-2015 06:11:17 GMT";
//		String url = "userinfokey=%7b%22loginType%22%3a%2201%22%2c%22provinceName%22%3a%22280%22%2c%22pwdType%22%3a%2202%22%2c%22userName%22%3a%2215802811012%22%7d; is_login=true; loginName=15802811012; c=4931b52456ac4e35bc4651ba311fc441";
		String decodeURL = URLDecoder.decode(url, "utf-8");
		System.out.println(decodeURL);

	}

}
//userinfokey={"loginType":"01","provinceName":"280","pwdType":"02","userName":"15802811012"}; is_login=true; loginName=15802811012; c=4931b52456ac4e35bc4651ba311fc441
//{"loginType":"01","provinceName":"280","pwdType":"02","userName":"15802811012"}