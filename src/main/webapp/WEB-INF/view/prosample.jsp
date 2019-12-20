<html>
  <head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Hello Bulma!</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bulma@0.8.0/css/bulma.min.css">
    <script defer src="https://use.fontawesome.com/releases/v5.3.1/js/all.js"></script>
      

  </head>
  <body>
  <section class="section">

  <h1 class="has-text-centered has-text-weight-bold is-size-4">Personal Information</h1><br>
      
<div class="columns">
      <div class="column is-centered ">
    
    
    <form action="updte.jsp" method="get">
          
          
          
<div class="field is-horizontal">
  <div class="field-label is-normal">
    
  </div>
  <div class="field-body ">
    <div class="field ">
         <label class="label">Name</label>
      <p class="control is-expanded has-icons-left">
        <input class="input" style="width:70%" type="text" placeholder="Name" disabled>
        <span class="icon is-small is-left">
          <i class="fas fa-user"></i>
        </span>
      </p>
    </div>
    <div class="field">
         <label class="label">Email</label>
      <p class="control is-expanded has-icons-left has-icons-right">
        <input class="input is-success" style="width:70%" type="email" placeholder="Email" value="alex@smith.com" disabled>
        <span class="icon is-small is-left">
          <i class="fas fa-envelope"></i>
        </span>
        
      </p>
    </div>
  </div>
</div>
          <br>
          <br>
    <div class="field is-horizontal">
  <div class="field-label is-normal">
    
  </div>
  <div class="field-body ">
   <div class=" field">
       
         <label class="label">DOB</label>
       
        
        <input class="input" style="width:65%" value="dat" type="date" id="date1" disabled>
        
        </div>
    
     <div class="field">
    
    <label class="label">Gender</label>

              
        <input class="is-checkradio is-success" id="gen1" type="radio" checked name="gen"value="male" > Male
        <input class="   is-checkradio is-success" id="gen2" type="radio" name="gen" value="female" > Female
   
    </div>
  </div>
</div>
          <br>
          <br>
<div class="field is-horizontal">
  <div class="field-label is-normal">
    
  </div>
    
      <div class="field-body ">
   <div class=" field">
        <label class="label">Phone Number</label>
    <p class="control is-expanded has-icons-left">
         <input class="input" type="number" style="width:70%" name="num" placeholder="9447747744" disabled>
       <span class="icon is-small is-left"> <i class="fas fa-phone"></i>
        </span> 
      </p>
        
       
        
         </div>
    
    <div class="field">
    
      <label class="label">Address</label> 
         <textarea  class="textarea"  disabled style="width:250px;" id="add" rows = "3" cols = "30"  name = "description">
            Enter your name
            
         </textarea>
    
        
    </div>
  </div>
    
    
    </div>
          
          <br>
        
        <div class="  has-text-centered">
        <button onclick="enable()" type="button" class="button is-info set">Edit</button>
          
            <input class="button is-success" type="submit" id="sub" name="submit" value=" submit" disabled>
        
        
        </div>
 
        
        
  </form>
          
          
          
      </div>
      </div>

      </section>
       <script>
        
        
function enable() 
        { 
            document.getElementById("date1").disabled = false;
            document.getElementById("gen1").disabled=false;
            document.getElementById("gen2").disabled=false;
            document.getElementById("add").disabled=false;
            document.getElementById("sub").disabled=false;
        
        } 
    
    </script>
  </body>
</html>