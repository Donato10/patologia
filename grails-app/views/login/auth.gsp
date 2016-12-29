<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">

    <title>Ingreso Sistema de información patológica</title>

 
    <!-- Bootstrap -->
    <asset:stylesheet src="bootstrap/dist/css/bootstrap.css" />
    <!-- FontAwesome -->
    <asset:stylesheet src='font-awesome/css/font-awesome.css'/>

    <!-- Animate.css -->
    <asset:stylesheet src="animate.css/animate.min.css"/>

    <!-- Custom Theme Style -->
    <asset:stylesheet src="gentelella/master.css"/>
    <!-- jQuery -->
    <asset:javascript src="jquery/dist/jquery.js"/>
  
  </head>

  <body class="login">
    <div>
      <a class="hiddenanchor" id="signup"></a>
      <a class="hiddenanchor" id="signin"></a>

      <div class="login_wrapper">
        
        <div class="animate form login_form">
          <section class="login_content">
            <form action="${createLink(controller:'login', action:'authenticate')}" method="POST" id="loginForm" >
              <h1>Ingreso</h1>
              <div>
                <input type="text" class="form-control" name="username" id="username"  placeholder="Email" autofocus required>
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" name="password" id="password" required />
              </div>
              <div>
                <input class="btn btn-default" type="submit"  href="index.html" value="Log in">
                <a class="reset_pass" href="#" style="display:none">Lost your password?</a>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <!--<p class="change_link">New to site?
                  <a id="createAccountLink" href="#signup" class="to_register"> Create Account </a>
                </p>-->

                <div class="clearfix">
                  <g:if test="${flash.message}">
                    <br>
                    <g:if test="${flash.message == 'Account created'}">
                      <div id="messageResponse" style="margin-bottom:20px">
                      <div class="col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 alert alert-success">
                    </g:if>
                    <g:else>
                      <div id="messageResponse" style="margin-bottom:20px">
                      <div class="col-xs-8 col-xs-offset-2 col-sm-6 col-sm-offset-3 col-md-6 col-md-offset-3 alert alert-danger">
                    </g:else>
                          ${flash.message}
                      </div>
                      <br> <br>
                    </div>
                  </g:if>
                </div>
                <br />
                <div>
                  <h1>Syspath</h1>
                  <h1><asset:image src="logos/logo.png" style="height:130px"/></h1>
                  <p>©2016 All Rights Reserved.</p>
                </div>
              </div>
            </form>
          </section>
        </div>

        <div id="register" class="animate form registration_form">
          <section class="login_content">
            <form  action="${createLink(controller:'register', action:'register')}" method="POST" id="registerForm">
              <h1>Crear cuenta</h1>
              
              <div>
                <input type="email" class="form-control" placeholder="Email" required="" name="username" />
              </div>
              <div>
                <input type="password" class="form-control" placeholder="Password" required="" name="password" />
              </div>
              <div>
                <input class="btn btn-default " type="submit" value="Register"/>
              </div>

              <div class="clearfix"></div>

              <div class="separator">
                <p class="change_link">Already a member ?
                  <a href="#signin" class="to_register"> Log in </a>
                </p>

                <div class="clearfix"></div>
                <br />

                <div>
                  <h1><i class="fa fa-paw"></i> Gentelella Alela!</h1>
                  <p>©2016 All Rights Reserved. Gentelella Alela! is a Bootstrap 3 template. Privacy and Terms</p>
                </div>
              </div>
            </form>
          </section>
        </div>
      </div>
    </div>
  </body>
</html>
<script>



$(window).load(function() {
  if( window.location.href.indexOf('showRegister')>=0 && window.location.href.indexOf('#')=="-1"){
    window.location.href+='#signup'
  }
});




   


 
</script>


