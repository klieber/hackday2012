var map = null;
var index = 0;
var results = null;

$(document).ready(function(){
	
	$("#quoteError").hide();
	$("#quoteEstimate").hide();
	$("#buyNow").hide();
	quote.loadMap();
	
});

var quote = {
	
	getEstimate : function(){
		yearBuilt = $('#yearBuilt').val();
		squareFeet = $('#squareFeet').val();
		bathrooms = $('#bathrooms').val();
		bedrooms = $('#bedrooms').val();
		lotSize = $('#lotSize').val();
		propertyValue = $('#propertyValue').val();
		insureValue = $('#insureValue').val();
		totalRooms = $('#totalRooms').val();
		
		quoteData = {'yearBuilt':yearBuilt, 'squareFeet':squareFeet, 'bathrooms':bathrooms, 'bedrooms':bedrooms, 'lotSize':lotSize,
				'propertyValue':propertyValue, 'insureValue':insureValue, 'totalRooms':totalRooms};
		$('#quoteEstimate').fadeOut();
		 $.ajax({
		        type: "POST",
		        url: "quote",
		        data: quoteData,
		        success: function(json){
		        	$('#quoteError').fadeOut();
		        	$('#quoteEstimate').html('<h3>Your Price Estimate is</h3><h2>$' + json + '</h2>');
		        	$('#quoteEstimate').fadeIn();
		        	$('#buyNow').fadeIn();
		        },
		        error: function(e){
		        	$('#quoteError').fadeIn();
		        }
		  });
	},
	loadMap : function(){
		
		map = new VEMap('myMap');
        map.LoadMap();
        map.AttachEvent("onobliqueenter", OnObliqueEnterHandler);
       try
       {
    	   
    	   address = $('#street').text();
    	   if($('#street').text().toUpperCase() == '9 WORTHINGTON CT'){
    		   address = '7 WORTHINGTON CT';
    	   }
          results = map.Find("Your Location",
                             address +', ' + $("#cityStateZip").text(),
                             null,
                             null,
                             index,
                             1,
                             true,
                             true,
                             true,
                             true,
                             MapCallback);
           index = parseInt(index)+9;

           
       }
       catch(e)
       {

       }
	}
	
};

function MapCallback(layer, resultsArray, places, hasMore, veErrorMessage) {
  var pin = new VEShape(VEShapeType.Pushpin, places[0].LatLong);
  pin.SetCustomIcon("<div class='pinStyle1'><div class='text'>1</div></div>"); 
  pin.SetTitle('Your Location');
  pin.SetDescription(places[0].Name);
  map.AddShape(pin);
  map.SetBirdseyeOrientation(VEOrientation.East);
}

function OnObliqueEnterHandler() {
   if (map.IsBirdseyeAvailable())
   {
      map.SetMapStyle(VEMapStyle.BirdseyeHybrid);

      // So we don't repeatedly reset map style
   }else{
	   map.SetMapStyle(VEMapStyle.Aerial);
   }
   map.DetachEvent("onobliqueenter", OnObliqueEnterHandler);
}