<%@page import="java.util.Date"%>

<div class="container">
	<h1>Hello</h1>
	
	<h2>Inclure du java</h2>
	<p>
	<%
	Date d = new Date();
	out.print("Aujourd'hui : ");
	out.print(d);
	request.setAttribute("today", d);
	%>
	</p>
	
	<h2>Explorer la requête avec les EL</h2>
	<p>params = ${param}</p>
	<p>today = ${today}</p>
	<p>Message en session = ${sessionScope.hello}</p>
	<p>1+2 = ${1 + 2}</p>
	<%
	session.setAttribute("hello", "Message en session");
	%>
	
</div>
