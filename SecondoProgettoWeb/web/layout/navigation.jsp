

<nav class="navbar navbar-custom purple-bg navbar-fixed-top" role="navigation">
    <div class="container">
        <!-- Brand and toggle get grouped for better mobile display -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle green-bg" data-toggle="collapse" data-target="#bs-example-navbar-collapse-1">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>

            <a class="navbar-brand nav-app-name" href="/SecondoProgettoWeb/secure/home.jsp">
                <i class="fa fa-coffee m-r" style='font-size: 15px'></i>CoffeClassRoom 2
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
                <li>
                    <!-- AVATAR -->        
                <c:choose>
                    <c:when test="${empty user.avatar_name}">
                        <img src="${pageContext.request.contextPath}/res/images/default_avatar.png" class="avatar-small" />
                    </c:when>
                    <c:otherwise>
                        <img src="${pageContext.request.contextPath}/resource/avatar/${user.id}_${user.avatar_name}" class="avatar-small" />
                    </c:otherwise>
                </c:choose>
                </li>
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi</a></li>
                <li><a href="/SecondoProgettoWeb/secure/InviteServlet">Inviti</a></li>
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=edit">Profilo</a></li>
                <c:if test="${user.moderator}">
                    <li><a href="/SecondoProgettoWeb/secure/ModeratorServlet">Pagina moderatore</a></li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="/SecondoProgettoWeb/LogoutServlet">Logout</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div>
</nav>