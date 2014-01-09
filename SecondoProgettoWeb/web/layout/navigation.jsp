

<nav class="navbar navbar-custom purple-bg navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a href="/SecondoProgettoWeb/secure/home.jsp">
                <!-- AVATAR -->        
                <c:choose>
                    <c:when test="${empty user.avatar_name}">
                        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar-small" />
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" class="avatar-small" />
                    </c:otherwise>
                </c:choose>
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi</a></li>
                <li><a href="/SecondoProgettoWeb/secure/InviteServlet">Inviti</a></li>
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=edit">Profilo</a></li>
                <c:if test="${user.moderator}">
                    <li><a href="/SecondoProgettoWeb/secure/ModeratorServlet">Pagina moderatore</a></li>
                </c:if>
                <li><a href="/SecondoProgettoWeb/LogoutServlet">Logout</a></li>

        </div><!-- /.navbar-collapse -->
    </div>
</nav>