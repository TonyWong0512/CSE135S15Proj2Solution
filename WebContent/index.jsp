<%@ page contentType="text/html; charset=utf-8" language="java"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<jsp:include page="/html/head.html" />
</head>
<body class="page-index" data-spy="scroll" data-offset="60" data-target="#toc-scroll-target">

    <jsp:include page="/jsp/header.jsp" />
    <div class="container">
        <div class="row">
            <div class="span12">
                <div class="body-content">
                    <div class="section">
                        <div class="page-header">
                            <h2 id="CSE135S15">CSE 135 Project</h2>
                        </div>
                        <div class="row">
                            <%
                            	String name = null;
                            	try {
                            		name = request.getParameter("name");
                            	} catch (Exception e) {
                            		name = null;
                            	}
                            	if (name != null)
                            		out.println(helpers.IndexHelper.login(name, session));
                            %>
                            <%
                            	if (session.getAttribute("name") != null) {
                            %>
                            <% if (session.getAttribute("role") == "owner") { %>
                            <jsp:include page="/jsp/owner-menu.jsp"/>
                            <% } else { %>
                            <jsp:include page="/jsp/customer-menu.jsp"/>
                            <% } %>
                            <%
                            	} else {
                                    String data = "You need to be logged into see this page.";
                                    data += "Want to <a href=\"/login\">login</a>?";
                            		out.println(helpers.HelperUtils
                            				.printInfo(data));
                            	}
                            %>
                        </div>
                        <jsp:include page="/html/footer.html" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
