<%@page import="com.gargoylesoftware.htmlunit.*" %>
<%@page import="com.gargoylesoftware.htmlunit.html.*" %>
<%@page import="com.gargoylesoftware.htmlunit.util.*" %>
<%@page import="java.net.URL" %>
<%@page import="java.util.*" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%
String url = "http://wap.10086.cn/getLoginInfo.jsp";
String refer = "http://wap.10086.cn/";
URL link = new URL(url);
WebClient webClient =  new WebClient();//创建WebClient
webClient.getOptions().setUseInsecureSSL(true);
WebRequest req = new WebRequest(link);
req.setAdditionalHeader("Referer", refer);
webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Android 5.0.2; Mobile; rv:52.0) Gecko/52.0 Firefox/52.0");
//webClient.addRequestHeader("User-Agent", "Mozilla/5.0 (Linux; Android 6.0; Nexus 5 Build/MRA58N) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/56.0.2924.87 Mobile Safari/537.36");
webClient.getCookieManager().setCookiesEnabled(true);//开启Cookie管理
//设置cookie
/**
Set<com.gargoylesoftware.htmlunit.util.Cookie> cookies = null;
Iterator<com.gargoylesoftware.htmlunit.util.Cookie> i = cookies.iterator();
while(i.hasNext()){
	webClient.getCookieManager().addCookie(i.next());
}
*/
HtmlPage htmlpage = webClient.getPage(req);    //打开
if(htmlpage != null){
	System.out.println(htmlpage.asXml());
}


%>
<%=htmlpage.asXml() %>
</body>
</html>