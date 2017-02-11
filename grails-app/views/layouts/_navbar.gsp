<!DOCTYPE html>
<html lang="en">
  <head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <!-- Meta, title, CSS, favicons, etc. -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <asset:link rel="shortcut icon" href="logos/logo.ico" type="image/x-icon"/>
    <title>Sys path </title>

    <!-- Bootstrap -->
    <asset:stylesheet src="bootstrap/dist/css/bootstrap.min.css" />
    <!-- Font Awesome -->
    <asset:stylesheet src="font-awesome/css/font-awesome.min.css" />
    <!-- NProgress -->
    <asset:stylesheet src="nprogress/nprogress.css" />
    <!-- iCheck -->
    <asset:stylesheet src="iCheck/skins/flat/green.css" />
    <!-- Gentelella Theme Style -->
    <asset:stylesheet src='gentelella/master.css'/>
    <!-- jQuery, se carga aquí por conveniencia de procesos -->
    <asset:javascript src="jquery/dist/jquery.min.js"/>
    <!-- bootstrap-daterangepicker -->
    <asset:stylesheet src="bootstrap-daterangepicker/daterangepicker.css" />
    <!-- Custom Theme Style -->
    <asset:stylesheet src='custom.css'/>


  </head>

  <body class="nav-md">
    <div class="container body">
      <div class="main_container">
        <div class="col-md-3 left_col">
          <div class="left_col scroll-view">
            <div class="navbar nav_title" style="border: 0;">
              <g:link controller="user" action="index" class="site_title" style="margin-top:5px">
                <asset:image src="logos/logo.png" alt="avatar" style="height:43px; width:auto; margin: 5px 10px 0px 0px; padding: 0px" class="img-circle profile_img"/><span>Syspath</span></g:link>
            </div>

            <div class="clearfix"></div>

            <!-- menu profile quick info -->
            <div class="profile clearfix">
              <div class="profile_pic">
                <asset:image src="avatars/avatar.png" alt="avatar" class="img-circle profile_img"/>
              </div>
              <div class="profile_info">
                <span>Bienvenido,</span>
                <h2><sec:username/></h2>
              </div>
            </div>
            <!-- /menu profile quick info -->

            <br />

            <!-- sidebar menu -->
            <div id="sidebar-menu" class="main_menu_side hidden-print main_menu">
              <div class="menu_section">
                <h3>General</h3>
                <ul class="nav side-menu">
                  <li><a><i class="fa fa-home"></i> Ingresos <span class="fa fa-chevron-down"></span></a>
                    <ul class="nav child_menu">
                      <li><g:link controller='caso' action="nuevoPaciente">Nuevo paciente</g:link></li>
                      <li><g:link controller='caso' action="nuevoCaso">Nuevo caso</g:link></li>
                    </ul>
                  </li>
                  <sec:ifAnyGranted roles='ROLE_PATOLOGO,ROLE_SECRETARIA'>
                    <li>
                      <g:link controller="caso" action="buscador"><i class="fa fa-search"></i> Buscador <span class="fa fa-chevron-right"></span></g:link>
                    </li>
                  </sec:ifAnyGranted>

                  <sec:ifAllGranted roles="ROLE_PATOLOGO">
                    <li>
                      <g:link controller="patologoProfesional" action="work"><i class="fa fa-list"></i> Hoja de trabajo <span class="fa fa-chevron-right"></span></g:link>
                    </li>
                  </sec:ifAllGranted>
              </div>

            </div>
            <!-- /sidebar menu -->

            <!-- /menu footer buttons -->
            <div class="sidebar-footer hidden-small">
              <a data-toggle="tooltip" data-placement="top" title="Settings">
                <span class="glyphicon glyphicon-cog" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="FullScreen">
                <span class="glyphicon glyphicon-fullscreen" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Lock">
                <span class="glyphicon glyphicon-eye-close" aria-hidden="true"></span>
              </a>
              <a data-toggle="tooltip" data-placement="top" title="Logout">
                <span class="glyphicon glyphicon-off" aria-hidden="true"></span>
              </a>
            </div>
            <!-- /menu footer buttons -->
          </div>
        </div>

        <!-- top navigation -->
        <div class="top_nav">
          <div class="nav_menu">
            <nav>
              <div class="nav toggle">
                <a id="menu_toggle"><i class="fa fa-bars"></i></a>
              </div>

              <ul class="nav navbar-nav navbar-right">
                <li class="">
                  <a href="javascript:;" class="user-profile dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                    <sec:ifAllGranted roles='ROLE_PATOLOGO'>
                      <asset:image src="avatars/patologo.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <sec:ifAllGranted roles='ROLE_CITOLOGO'>
                      <asset:image src="avatars/citologo.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <sec:ifAllGranted roles='ROLE_SECRETARIA'>
                      <asset:image src="avatars/secretaria.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <sec:ifAllGranted roles='ROLE_HISTOTECNOLOGO'>
                      <asset:image src="avatars/histotecnologo.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <sec:ifAllGranted roles='ROLE_RESIDENTE'>
                      <asset:image src="avatars/residente.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <sec:ifAllGranted roles='ROLE_ADMIN'>
                      <asset:image src="avatars/residente.png" alt="avatar"/><sec:username/>
                    </sec:ifAllGranted>
                    <span class=" fa fa-angle-down"></span>
                  </a>
                  <ul class="dropdown-menu dropdown-usermenu pull-right">
                    <li><a href="javascript:;"> Profile</a></li>
                    <li>
                      <a href="javascript:;">
                        <span class="badge bg-red pull-right">50%</span>
                        <span>Settings</span>
                      </a>
                    </li>
                    <li><a href="javascript:;">Help</a></li>
                    <li><a href="${createLink(controller:'logout', action:'index')}"><i class="fa fa-sign-out pull-right"></i> Cerrar sesion</a></li>
                  </ul>
                </li>

                <li role="presentation" class="dropdown">
                  <a href="javascript:;" class="dropdown-toggle info-number" data-toggle="dropdown" aria-expanded="false">
                    <i class="fa fa-envelope-o"></i>
                    <span class="badge bg-green">6</span>
                  </a>
                  <ul id="menu1" class="dropdown-menu list-unstyled msg_list" role="menu">
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <a>
                        <span class="image"><img src="images/img.jpg" alt="Profile Image" /></span>
                        <span>
                          <span>John Smith</span>
                          <span class="time">3 mins ago</span>
                        </span>
                        <span class="message">
                          Film festivals used to be do-or-die moments for movie makers. They were where...
                        </span>
                      </a>
                    </li>
                    <li>
                      <div class="text-center">
                        <a>
                          <strong>See All Alerts</strong>
                          <i class="fa fa-angle-right"></i>
                        </a>
                      </div>
                    </li>
                  </ul>
                </li>
              </ul>
            </nav>
          </div>
        </div>
        <!-- /top navigation -->



        