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
                        <div class="row">
                            <div class="col-md-3">
                                <%
                                	if (session.getAttribute("name") != null) {
                                %>
                                <%
                                	if (((String) session.getAttribute("role"))
                                				.equalsIgnoreCase("owner")) {
                                %>
                                <jsp:include page="/jsp/owner-menu.jsp" />
                                <%
                                	} else {
                                %>
                                <jsp:include page="/jsp/customer-menu.jsp" />
                                <%
                                	}
                                %>
                                <%
                                	} else {
                                %>
                                <div class="alert alert-info">
                                    You need to be logged into see this page. Want to <a href="login">login</a>?
                                </div>
                                <%
                                	}
                                %>
                            </div>
                            <div class="col-md-9">
                                <div class="page-header">
                                    <h3>Categories</h3>
                                </div>
                                <jsp:include page="/jsp/list-categories.jsp"/>
                            </div>
                        </div>
                        <jsp:include page="/html/footer.html" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</body>
</html>
