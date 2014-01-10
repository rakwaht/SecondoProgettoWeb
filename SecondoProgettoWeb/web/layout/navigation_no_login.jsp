

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
                <li><a href="/SecondoProgettoWeb/HomeServlet?next=groups">Gruppi</a></li>
            </ul>
            
            <form class="navbar-form navbar-right" method="post" action="LoginServlet" role="form">
                <div class="form-group">
                    <input type="text" name="username" placeholder="username" class="form-control"/>
                </div>
                <div class="form-group">
                    <input type="password" name="password" placeholder="password" class="form-control"/>
                </div>
                <button type="submit" class="btn btn-green">Entra</button>
            </form>

        </div><!-- /.navbar-collapse -->
    </div>
</nav>