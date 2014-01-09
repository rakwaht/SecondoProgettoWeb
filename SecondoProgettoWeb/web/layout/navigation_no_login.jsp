

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
            <a class="navbar-brand" href="/SecondoProgettoWeb/secure/home.jsp">

            </a>
        </div>

        <!-- Collect the nav links, forms, and other content for toggling -->
        <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
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