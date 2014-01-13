

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

            <a class="navbar-brand nav-app-name" href="/SecondoProgettoWeb/HomeServlet">
                <i class="fa fa-coffee m-r" style='font-size: 15px'></i>CoffeClassRoom 2
            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
               
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi&nbsp;&nbsp;<i class="fa fa-users" ></i></a></li>
                <li><a href="/SecondoProgettoWeb/secure/InviteServlet">Inviti&nbsp;&nbsp;<i class="fa fa-envelope"></i></a></li>
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=edit">Profilo&nbsp;&nbsp;<i class="fa fa-smile-o" ></i></a></li>
                <c:if test="${user.moderator}">
                    <li><a href="/SecondoProgettoWeb/secure/ModeratorServlet">Pagina moderatore&nbsp;&nbsp;<i class="fa fa-search" ></i></a></li>
                </c:if>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="#"> <c:out value="${user.username}"></c:out> </a></li>
                <li><a href="/SecondoProgettoWeb/LogoutServlet"><i class="fa fa-sign-out"></i>&nbsp;&nbsp;Logout</a></li>
            </ul>

        </div><!-- /.navbar-collapse -->
    </div>
</nav>