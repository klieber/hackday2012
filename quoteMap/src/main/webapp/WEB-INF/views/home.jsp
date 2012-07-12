<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>SF - Home Insurance Estimate</title>
	<script src="resources/js/jquery.js"></script>
	 <link href="resources/css/bootstrap.css" rel="stylesheet">
	 <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container">
<div class="row">
	<div class="visible-phone navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#">Home Insurance Estimate</a>
        </div>
      </div>
    </div>

	<div class="hero-unit hidden-phone">
		<h1>Home Insurance Estimate</h1>
	</div>
	<form class="well form-inline" action="details" method="post">
	  <input type="text" class="input-xlarge" placeholder="Street" name="street">
	  <input type="text" class="input-xlarge" placeholder="City" name="city">
	  <input type="text" class="input-mini" placeholder="St" name="state">
	  <input type="text" class="input-small" placeholder="Zip" name="zip">
	  <button type="submit" class="btn btn-large btn-primary">Get Estimate</button>
	</form>
</div>
</div>
<!-- Placed at the end of the document so the pages load faster -->
    <script src="resources/js/bootstrap-transition.js"></script>
    <script src="resources/js/bootstrap-alert.js"></script>
    <script src="resources/js/bootstrap-modal.js"></script>
    <script src="resources/js/bootstrap-dropdown.js"></script>
    <script src="resources/js/bootstrap-scrollspy.js"></script>
    <script src="resources/js/bootstrap-tab.js"></script>
    <script src="resources/js/bootstrap-tooltip.js"></script>
    <script src="resources/js/bootstrap-popover.js"></script>
    <script src="resources/js/bootstrap-button.js"></script>
    <script src="resources/js/bootstrap-collapse.js"></script>
    <script src="resources/js/bootstrap-carousel.js"></script>
    <script src="resources/js/bootstrap-typeahead.js"></script>
</body>
</html>
