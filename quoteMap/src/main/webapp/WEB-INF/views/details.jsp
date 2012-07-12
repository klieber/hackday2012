<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>SF - Home Insurance Estimate</title>
	<script type="text/javascript" src="resources/js/jquery.js"></script>
	<script type="text/javascript" src="resources/js/quote.js"></script>
	<script type="text/javascript" src="http://ecn.dev.virtualearth.net/mapcontrol/mapcontrol.ashx?v=6.3"></script>
	 <link href="resources/css/bootstrap.css" rel="stylesheet">
	 <link href="resources/css/bootstrap-responsive.css" rel="stylesheet">
	 <link href="resources/css/map.css" rel="stylesheet">
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
	<div id="detailsForm" class="well">
		<div id="quoteError" class="alert alert-error">
			We're sorry... we are experiencing technical difficulties at this time. Please check back later.
		</div>
		<h3>We have found these details for the property you entered.</h3>
		<h3>Please confirm and update any incorrect information and then submit.</h3>
		<br>
		<div class="row">
		<form class="form-horizontal span5">
			<div class="span5">
				<h2 id="street">${property.address.street}</h2>
				<h2 id="cityStateZip">${property.address.city}, ${property.address.state} ${property.address.zipcode}</h2>
				<br>
				<fieldset>
					<div class="control-group">
						<label class="control-label" for="yearBuilt">Year Built</label>
						<div class="controls">
							<input type="text" id="yearBuilt" class="input-mini" value="${property.yearBuilt}" name="yearBuilt">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="squareFeet">Finished Sq Ft</label>
						<div class="controls">
							<input type="text" id="squareFeet" class="input-small" value="${property.finishedSqFt}" name="squareFeet">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="bathrooms">Bathrooms</label>
						<div class="controls">
							<input type="text" id="bathrooms" class="input-mini" value="${property.bathrooms}" name="bathrooms">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="bedrooms">Bedrooms</label>
						<div class="controls">
							<input type="text" id="bedrooms" class="input-mini" value="${property.bedrooms}" name="bedrooms">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="lotSize">Lot Size Sq Ft</label>
						<div class="controls">
							<input type="text" id="lotSize" class="input-small" value="${property.lotSizeSqFt}" name="lotSize">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="propertyValue">Property Value</label>
						<div class="controls">
							<input type="text" id="propertyValue" class="input-small" value="${property.zestimate.valuationRange.high.value}" name="propertyValue">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="insureValue">Insure Value</label>
						<div class="controls">
							<input type="text" id="insureValue" class="input-small" value="${insureValue}" name="insureValue">
						</div>
					</div>
					<div class="control-group">
						<label class="control-label" for="totalRooms">Total Rooms</label>
						<div class="controls">
							<input type="text" id="totalRooms" class="input-mini" value="${property.totalRooms}" name="totalRooms">
						</div>
					</div>
				</fieldset>
			</div>
			<div class="row">
			<div class="offset1">
		  		<button type="button" class="btn btn-large btn-primary" onclick="quote.getEstimate();">Get Estimate!</button>
		  		<button id="buyNow" type="button" class="btn btn-large btn-primary">Buy Now!</button>
		  	</div>
		  	</div>
		</form>
		<div class="span6">
			<br>
			<div id="quoteEstimate" class="alert alert-info"></div>
			<div id='myMap' style="position:relative; width:400px; height:400px;"></div>
		</div>
		</div>
	</div>
	<img alt="Zillow" src="http://www.zillow.com/vstatic/3efe31b610f8cb7313c4ae39a7009393/static/logos/Zillowlogo_150x40_rounded.gif">
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
