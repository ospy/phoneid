<%@page import="java.util.*"%>
<%@page import="java.net.*"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script type="text/javascript" src="js/jquery-3.2.0.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<%=new Date() %><br>
<%
	String location="";
	//URL url = new URL("https://www.baidu.com/rec?platform=wise&ms=1&rset=rcmd&word=%E6%88%90%E9%83%BD&qid=10126517336476765368&rq=%E6%88%90%E9%83%BD&from=844b&baiduid=C9B38F2B715E1E07D94D818C895C1189%3AFG%3D1&tn=&clientWidth=400&t=1490430233087&r=2184");
	URL url = new URL("http://wap.10086.cn/phoneid.jsp");
	
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


<script type="text/javascript">
$.ajax({
    type: 'HEAD', // 获取头信息，type=HEAD即可
    dataType:'JSONP',
   //url : window.location.href,
   url : "http://wap.10086.cn/phoneid.jsp",
   // url : 'https://www.baidu.com/rec?platform=wise&ms=1&rset=rcmd&word=%E6%88%90%E9%83%BD&qid=10126517336476765368&rq=%E6%88%90%E9%83%BD&from=844b&baiduid=C9B38F2B715E1E07D94D818C895C1189%3AFG%3D1&tn=&clientWidth=400&t=1490430233087&r=2184',
    complete: function( xhr,data ){
        // 获取相关Http Response header
        var wpoInfo = {
            // 服务器端时间
            "date" : xhr.getResponseHeader('Date'),
            // 如果开启了gzip，会返回这个东西
            "contentEncoding" : xhr.getResponseHeader('Content-Encoding'),
            // keep-alive ？ close？
            "connection" : xhr.getResponseHeader('Connection'),
            // 响应长度
            "contentLength" : xhr.getResponseHeader('Content-Length'),
            // 服务器类型，apache？lighttpd？
            "server" : xhr.getResponseHeader('Server'),
            "vary" : xhr.getResponseHeader('Vary'),
            "transferEncoding" : xhr.getResponseHeader('Transfer-Encoding'),
            // text/html ? text/xml?
            "contentType" : xhr.getResponseHeader('Content-Type'),
            "cacheControl" : xhr.getResponseHeader('Cache-Control'),
            // 生命周期？
            "exprires" : xhr.getResponseHeader('Exprires'),
            "lastModified" : xhr.getResponseHeader('Last-Modified'),
            "Set-Cookie" : xhr.getResponseHeader('Set-Cookie')
        };
        // 在这里，做想做的事。。。
        console.log(wpoInfo);
    }
});
</script>
</body>
</html>