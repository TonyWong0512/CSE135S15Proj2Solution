<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="btn btn-navbar" data-toggle="collapse" data-target="#top-nav-collapse"> <span class="icon-bar"></span> <span
                class="icon-bar"></span> <span class="icon-bar"></span>
            </a>
            <%
            	if (session.getAttribute("name") != null) {
            %>
            <h2>
                Hello
                <%
            	session.getAttribute("name");
            %>
            </h2>
            <%
            	}
            %>
            <!--/.nav-collapse -->
        </div>
    </div>
    <div class="nav-collapse collapse" id="top-nav-collapse">
        <ul class="nav pull-right">
        </ul>
    </div>
</div>