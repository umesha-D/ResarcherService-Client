<!doctype html>
<html lang="en">
   <head>
      <!-- Required meta tags -->
      <meta charset="utf-8">
      <meta name="viewport" content="width=device-width, initial-scale=1">
      <!-- Bootstrap CSS -->
      <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-wEmeIV1mKuiNpC+IOBjI7aAzPcEZeedi5yW5f2yOq55WWLwNGmvvx4Um1vskeMj0" crossorigin="anonymous">
      <link rel="stylesheet" href="styles.css">
      
      <title>Researcher Service</title>
      <style>
         .show {
         display: "block";
         }
         .hide {
         display: "none";
         }
      </style>
   </head>
   <body>
      <!--Nevabr -->
      <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
         <div class="container-fluid">
            <a class="navbar-brand" href="#">Researcher Service</a>
            <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
            </button>
            
               <form class="d-flex">
                  <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                  <button class="btn btn-outline-success" type="submit">Search</button>
               </form>
            </div>
         </div>
      </nav>
      <!--end Nevbar -->
      <br>
      <div class="container">
      <div style="display: flex; flex-direction: row; justify-content: space-between">
      	<h1>Researcher Service Management</h1>
      	<button id="logoutButton" class="btn btn-danger">Logout</button>
      </div>
      <br>
      <form>
         <div class="mb-3">
            <label for="name" class="form-label">Name</label>
            <input class="form-control" id="name" aria-describedby="namelHelp">
         </div>
         <div id="nameerror" class="alert alert-danger hide" role="alert" style="display: none">
      Invalid name 
      </div>
         <div class="mb-3" style="display: none">
            <label for="id" class="form-label">Name</label>
            <input class="form-control" id="id" aria-describedby="idHelp">
         </div>
         <div class="mb-3">
            <label for="email" class="form-label">Email</label>
            <input class="form-control" id="email" aria-describedby="emailHelp">
         </div>
          <div id="emailerror" class="alert alert-danger hide" role="alert" style="display: none">
      Invalid email 
      </div>
         <div>
            <div class="row">
               <div class="col">
                  <div class="mb-3">
                     <label for="password" class="form-label">Password</label>
                     <input class="form-control" id="password" aria-describedby=passwordHelp" type="password">
                  </div>
               </div>
               <div class="col">
                  <div class="col">
                     <div class="mb-3">
                        <label for="cpassword" class="form-label">Confirm Password</label>
                        <input class="form-control" id="cpassword" aria-describedby=cpasswordHelp" type="password">
                     </div>
                  </div>
               </div>
            </div>
            <div id="passworderror" class="alert alert-danger hide" role="alert" style="display: none">
      Invalid password 
      </div>
            <div class="mb-3">
               <label for="CID" class="form-label">CID</label>
               <input class="form-control" id="CID" aria-describedby="CIDHelp">
            </div>
            <div id="CIDerror" class="alert alert-danger hide" role="alert" style="display: none">
      Invalid categoryID 
      </div>
            <br>
            <button class="btn btn-primary submit">Submit</button>
            
          						 
            <button class="btn btn-primary update">Update</button>
      </form>
      <br/>
      <br/>
      <div id="deleteLabel" class="alert alert-success hide" role="alert" style="display: none">
      Succesfully delected the Researcher. 
      </div>
      <div id="insertLabel" class="alert alert-success hide" role="alert" style="display: none">
      Succesfully inserted the Researcher. 
      </div>
      <div id="updateLabel" class="alert alert-success hide" role="alert" style="display: none">
      Succesfully updated the Researcher. 
      </div>
      <div id="deleteLabelerror" class="alert alert-danger hide" role="alert" style="display: none">
      Error while deleting the Researcher. 
      </div>
      <div id="insertLabelerror" class="alert alert-danger hide" role="alert" style="display: none">
      Error while inserting the Researcher. 
      </div>
      <div id="updateLabelerror" class="alert alert-danger hide" role="alert" style="display: none">
      Error while updating the Researcher. 
      </div>
    
	
	
      <table class="table">
      <thead>
      <tr>
      <th scope="col">Id</th>
      <th scope="col">Name</th>
      <th scope="col">Email</th>
      <th scope="col">CID</th>
      <th scope="col">Action</th>
      </tr>
      </thead>
      <tbody id="researcher_table">
      </tbody>
      </table>
      </div>
      <!--grid -->
      <!--end grid -->
      <!-- Option 1: Bootstrap Bundle with Popper -->
      <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0/dist/js/bootstrap.bundle.min.js" integrity="sha384-p34f1UUtsS3wqzfto5wAAmdvj+osOnFyQFpp4Ua3gs/ZVWx6oOypYoCJhGGScy+8" crossorigin="anonymous"></script>
      <script
         src="https://code.jquery.com/jquery-3.6.0.js"
         integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk="
         crossorigin="anonymous"></script>
      <script src="script.js"></script>
   </body>
</html>