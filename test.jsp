<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8" import="org.json.simple.*"%>


<%

  // 데이터를 안드로이드에서 받음
	request.setCharacterEncoding("UTF-8");
	String name = request.getParameter("name");

  // 초기 선언
	JSONObject jsonMain = new JSONObject();
  
	JSONArray jArray = new JSONArray();
	
	JSONObject jObject = new JSONObject();


    // 안드로이드로 보낼 메시지를 만듬
	jObject.put("ID", "hello");
	jObject.put("EMAIL", "gmail.com");
	jObject.put("NAME", name);


    // 위에서 만든 각각의 객체를 하나의 배열 형태로 만듬

	jArray.add(0, jObject); //http://itpangpang.xyz/262
//	jArray.add(1, jObject);
//	jArray.add(2, jObject);	https://www.youtube.com/watch?v=oszgN7MLeEc
    
    // 최종적으로 배열을 하나로 묶음
	jsonMain.put("List", jArray);
    // 안드로이드에 보낼 데이터를 출력
   
	out.println(jsonMain.toJSONString());
	
%>