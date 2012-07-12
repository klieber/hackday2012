 		 var map = null;
         var index = 0;
         var results = null;
       
      
         function OnObliqueEnterHandler()
         {
            if (map.IsBirdseyeAvailable())
            {
               map.SetMapStyle(VEMapStyle.BirdseyeHybrid);

               // So we don't repeatedly reset map style
               map.DetachEvent("onobliqueenter", OnObliqueEnterHandler);
            }
         }
          
         function LoadMap()
         {
        	 map = new VEMap('myMap');
             map.LoadMap();
             map.AttachEvent("onobliqueenter", OnObliqueEnterHandler);
            try
            {
               results = map.Find("Your Location",
                                  document.getElementById('txtWhere').value,
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
               alert(e.message);
            }
         }
         
         function MapCallback(layer, resultsArray, places, hasMore, veErrorMessage) {
        	  var pin = new VEShape(VEShapeType.Pushpin, places[0].LatLong);
        	  pin.SetCustomIcon("<div class='pinStyle1'><div class='text'>1</div></div>"); 
        	  pin.SetTitle('Your Location');
        	  pin.SetDescription(places[0].Name);
        	  map.AddShape(pin);
        	  map.SetBirdseyeOrientation(VEOrientation.West);
        	  
        	  
        }
        
