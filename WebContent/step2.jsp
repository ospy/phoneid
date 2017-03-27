<%@page import="java.util.*"%>
<%@page import="java.net.*"%>
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
	String location="";
	URL url = new URL("http://wap.10086.cn/getLoginInfo.jsp");
	
	URLConnection conn = url.openConnection();
	Map headers = conn.getHeaderFields();
	
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
	
	if(location !=""){
		url = new URL(location);
		conn = url.openConnection();
		headers = conn.getHeaderFields();
		keys = headers.keySet();
		for (String key : keys) {
			String val = conn.getHeaderField(key);
			System.out.println(key + "///" + val);			
		}
	}
	System.out.println(conn.getLastModified());
	/***/
%>
</body>
</html>