<%@ page session="true" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>

<html>
  <head>
    <title>Welcome, John Doe</title>
    <link href="resources/bootstrap/css/bootstrap.css" rel="stylesheet">
    <style type="text/css">
      body {
        padding-top: 100px;
        padding-bottom: 40px;
      }
      .sidebar-nav {
        padding: 9px 0;
      }
    </style>
    <link href="resources/bootstrap/css/bootstrap-responsive.css" rel="stylesheet">
  </head>
  <body>
    <div class="navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container-fluid">
          <a class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
          </a>
          <a class="brand" href="#"><img src="resources/images/logo.png" /></a>
          <div class="btn-group pull-right">
            <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
              <i class="icon-user"></i> Username
              <span class="caret"></span>
            </a>
            <ul class="dropdown-menu">
              <li><a href="#">Profile</a></li>
              <li class="divider"></li>
              <li><a href="#">Sign Out</a></li>
            </ul>
          </div>
          <div class="nav-collapse">
            <ul class="nav" style="font-size: 16px; font-weight: bold">
              <li class="active"><a href="#">Navigation</a></li>
              <li><a href="#about">Navigation</a></li>
              <li><a href="#contact">Navigation</a></li>
              <li><a href="#contact">Navigation</a></li>
            </ul>
          </div><!--/.nav-collapse -->
        </div>
      </div>
    </div>
    <div class="container-fluid">
      <div class="row-fluid">
        <div class="span3">
          <div class="well sidebar-nav">
            <ul class="nav nav-list">
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
              <li class="nav-header">Sidebar</li>
            </ul>
          </div><!--/.well -->
        </div><!--/span-->
        <div class="span9">
           <div id="primaryContent">
		      <tiles:insertAttribute name="content" />
		    </div>
        </div><!--/span-->
      </div><!--/row-->

      <hr>

      <footer>
        <p>&copy; Company 2012</p>
      </footer>

    </div><!--/.fluid-container-->
   
    
    <script src="resources/bootstrap/js/bootstrap.min.js"></script>
  </body>
</html>