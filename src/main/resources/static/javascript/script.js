  $(function() {
	  var x=2;
  $("#addmore").click(function(e) {
    e.preventDefault();
    	
    $("#col1").append("<br> ");
   $("#col1").append(" <div class='field '> <input class='input' type='text' style='width: 80%' name='name"+x+"' /></div>");
    
   $("#col2").append("<br> <div class='field '> <input class='input' type='time' style='width: 80%' name='arri"+x+"' /></div>");
      
    
  $("#col3").append("<br><div class='field '> <input class='input' type='time' style='width: 80%' name='desi"+x+"' /></div>");
 
  $("#col3").append("<br><div class='field '> <input  type='hidden' style='width: 80%' name='length' value='"+x+"'/></div>");
  
  x=x+1;
  });
});