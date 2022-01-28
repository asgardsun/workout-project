<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<script type="text/javascript">
	var msg = "${msg}";
	if(msg == ""){
		console.log("빈경고창");
	}else{
		alert(msg);	
	}
	location.href = "${url}";
</script>