<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
	<script src="resources/js/jquery.js"></script>
	 <link href="resources/css/bootstrap.css" rel="stylesheet">
	 <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
</head>
<body>
<div class="container">

	<div class="visible-phone navbar navbar-fixed-top">
      <div class="navbar-inner">
        <div class="container">
          <a class="brand" href="#">Quick Quote Fast Estimate</a>
        </div>
      </div>
    </div>

	<div class="hero-unit hidden-phone">
		<h1>Quick Quote Fast Estimate</h1>
		<p>Enter an address to receive an estimate for homeowners insurance</p>
	</div>

<form class="well form-inline">
  <input type="text" class="span4" placeholder="Street">
  <input type="text" class="span4" placeholder="City">
  <input type="text" class="span1" placeholder="St">
  <input type="text" class="span2" placeholder="Zip">
  <button type="submit" class="btn">Submit</button>
</form>
</div>
<!-- Placed at the end of the document so the pages load faster -->
    <script src="/resources/js/bootstrap-transition.js"></script>
    <script src="/resources/js/bootstrap-alert.js"></script>
    <script src="/resources/js/bootstrap-modal.js"></script>
    <script src="/resources/js/bootstrap-dropdown.js"></script>
    <script src="/resources/js/bootstrap-scrollspy.js"></script>
    <script src="/resources/js/bootstrap-tab.js"></script>
    <script src="/resources/js/bootstrap-tooltip.js"></script>
    <script src="/resources/js/bootstrap-popover.js"></script>
    <script src="/resources/js/bootstrap-button.js"></script>
    <script src="/resources/js/bootstrap-collapse.js"></script>
    <script src="/resources/js/bootstrap-carousel.js"></script>
    <script src="/resources/js/bootstrap-typeahead.js"></script>
</body>
</html>
