const PORT = 8080;

function getData() {
	  //load initial data to the view
	  $.ajax({
	    url: `http://localhost:${PORT}/ResearcherService/api/v2/researcher/getresearchers`,
	    type: "GET",
	    success: function (result) {
	      const researcherData = result;
	      const tableId = $("#researcher_table");
	      let output = ``;
	      researcherData.forEach((data) => {
	        output =
	          output +
	          `<tr>
	          <td>${data.id}</td>
	          <td>${data.name}</td>
	          <td>${data.email}</td>
	          <td>${data.researchCategory}</td>
	          <td style="display: flex; flex-direction: row; align-itmes: center">
	            <button
	              id="${data.id}"
	              type="button"
	              class="btn btn-success btnUpdate"
	              style="margin-right: 10px"
	            >
	              Update
	            </button>
	            <button
	              id="${data.id}"
	              class="btn btn-danger btnRemove"
	              type="button"
	              style="margin-left: 10px"
	            >
	              Delete
	            </button>
	          </td>
	        </tr>
	        `;
	      });
	
	      tableId.html(output);
	    },
	  });
}


getData();

//delete
$(document).on("click", ".btnRemove", (event) => {
  const deleteId = event.target.id;
  const target = event.target;
  target.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Wait`;

  $.ajax({
    url: `http://localhost:${PORT}/ResearcherService/api/v2/researcher/deletebyid/${deleteId}`,
    type: "DELETE",
    complete: function (result) {
      if (result.status == 200) {
        target.parentNode.parentNode.style.display = "none";
        $("#deleteLabel").css("display", "block");

        setTimeout(() => {
          $("#deleteLabel").css("display", "none");
        }, 2000);
      } else {
        $("#deleteLabelerror").css("display", "block");
        target.innerHTML = `Delete`;
        setTimeout(() => {
          $("#deleteLabelerror").css("display", "none");
        }, 2000);
      }
    },
  });
});

//update
$(document).on("click", ".update", (event) => {
  event.preventDefault();
  const target = event.target;
  const id = $("#id").val();
  const name = $("#name").val();
  const email = $("#email").val();
  const password = $("#password").val();
  const cpassword = $("#cpassword").val();
  const CID = Number.parseInt($("#CID").val());
  
  const isValid = validateFucntion(name, email, CID, password, cpassword);
  
  if(!isValid){
  	return;
  }

  target.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Wait`;

  $.ajax({
    url: `http://localhost:${PORT}/ResearcherService/api/v2/researcher/update/${id}`,
    type: "PUT",
    data: JSON.stringify({
      name: name,
      email: email,
      password: password,
      researchCategory: CID,
    }),
    contentType: "application/json",
    dataType: "json",
    complete: function (response, status) {
      target.innerHTML = `Update`;
      if (response.status) {
        getData();
        $("#updateLabel").css("display", "block");

        setTimeout(() => {
          $("#updateLabel").css("display", "none");
        }, 2000);
      } else {
        $("#updateLabelerror").css("display", "block");

        setTimeout(() => {
          $("#updateLabelerror").css("display", "none");
        }, 2000);
      }
    },
  });
});

//update initialize
$(document).on("click", ".btnUpdate", (event) => {
  const targetParent = event.target.parentNode.parentNode.children;
  const id = targetParent[0].innerHTML;
  const name = targetParent[1].innerHTML;
  const email = targetParent[2].innerHTML;
  const CID = targetParent[3].innerHTML;

  $("#id").val(id);
  $("#name").val(name);
  $("#email").val(email);
  $("#CID").val(CID);
  
  window.scroll(0,0);
});

//insert
$(document).on("click", ".submit", (event) => {
  event.preventDefault();
  const target = event.target;
  const name = $("#name").val().trim();
  const email = $("#email").val().trim();
  const password = $("#password").val().trim();
  const cpassword = $("#cpassword").val().trim();
  const CID = Number.parseInt($("#CID").val());
  
 
  
  const isValid = validateFucntion(name, email, CID, password, cpassword);
  
  if(!isValid){
  	return;
  }

  target.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Wait`;

  $.ajax({
    url: `http://localhost:${PORT}/ResearcherService/api/v2/researcher/register`,
    type: "POST",
    data: JSON.stringify({
      name: name,
      email: email,
      password: password,
      researchCategory: CID,
    }),
    contentType: "application/json",
    dataType: "json",
    complete: function (response, status) {
      const newlyAddedItems = {
        name: name,
        email: email,
        password: password,
        researchCategory: CID,
      };
      onItemSaveComplete(response, status, newlyAddedItems, target);
    },
  });
});

function onItemSaveComplete(response, status, newlyAddedItems, target) {
  if (response.status == 201) {
    $("#insertLabel").css("display", "block");
    target.innerHTML = `Submit`;
    let tableId = $("#researcher_table");
    let output = `${tableId.html()}`;
    output =
      output +
      `<tr>
      <td>${Number.parseInt(Math.random() * 100)}</td>
      <td>${newlyAddedItems.name}</td>
      <td>${newlyAddedItems.email}</td>
      <td>${newlyAddedItems.researchCategory}</td>
      <td style="display: flex; flex-direction: row; align-itmes: center">
        <button
          type="button"
          class="btn btn-success btnUpdate"
          style="margin-right: 10px"
        >
          Update
        </button>
        <button
          class="btn btn-danger btnRemove"
          type="button"
          style="margin-left: 10px"
        >
          Delete
        </button>
      </td>
    </tr>
    `;
    tableId.html(output);
    setTimeout(() => {
      $("#insertLabel").css("display", "none");
    }, 2000);
  } else {
    $("#insertLabelerror").css("display", "block");
    target.innerHTML = `Submit`;
    setTimeout(() => {
      $("#insertLabelerror").css("display", "none");
    }, 2000);
  }
}

function validateFucntion(name, email, cateforyId, password, cpasssword) {
    const No_pattern = /^\d+$/;
    const email_pattern = /^(([^<>()[\]\\.,;:\s@"]+(\.[^<>()[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
	
	if(name === undefined || name === null || name.trim() === "" ){
		$("#nameerror").css("display", "block");
		setTimeout( () => {
			$("#nameerror").css("display", "none");
		}, 2000)
		return false;
	}
	if(email === undefined || email === null || email.trim() === "" || !email_pattern.test(email.toLowerCase()) ){
		$("#emailerror").css("display", "block");
		setTimeout( () => {
			$("#emailerror").css("display", "none");
		}, 2000)
		return false;
	}
	if(cateforyId === undefined 
		|| cateforyId === null 
		|| cateforyId  === ""
		|| !No_pattern.test(cateforyId)){
		$("#CIDerror").css("display", "block");
		setTimeout( () => {
			$("#CIDerror").css("display", "none");
		}, 2000)
		return false;
	}
	if(password === undefined || password === null || password.trim() === "" ){
		$("#passworderror").css("display", "block");
		setTimeout( () => {
			$("#passworderror").css("display", "none");
		}, 2000)
		return false;
	}
	
	if(cpassword === undefined || cpassword === null || cpassword === "" ){
		alert("cpassword is invalid");
		return false;
	}

	
	return true;
}

//check login 
(function(){
  const token = localStorage.getItem("token");
  if(token === undefined || token === null){
  	window.location.replace(`http://localhost:${PORT}/ResearcherService/login.jsp`);
  }
})();


//logout 
$(document).on("click", "#logoutButton", (event) => {
	localStorage.removeItem("token");
	window.location.replace(`http://localhost:${PORT}/ResearcherService/login.jsp`);
});


