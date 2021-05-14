const PORT = 8080;

//login 
$(document).on("click", "#loginBtn", (event) => {
	event.preventDefault();
	const target = event.target;
  	const email = $("#emaillogin").val().trim();
  	const password = $("#exampleInputPassword1").val().trim();
  	
  	if(email.trim() === "" || password.trim() === ""){
  		alert("Please fill all the fields.");
  		return;
  	}
  
  	
  	target.innerHTML = `<span class="spinner-border spinner-border-sm" role="status" aria-hidden="true"></span> Wait`;
  	
  	$.ajax({
    url: `http://localhost:${PORT}/ResearcherService/api/v2/researcher/login`,
    type: "POST",
    data: JSON.stringify({
      email: email,
      password: password
    }),
    contentType: "application/json",
    dataType: "json",
    complete: function (response, status) {
		if(response.status === 403){
			alert("Invalid username or password");
			console.log(response)
		}else if(response.status === 200){
			localStorage.setItem("token", response.responseJSON.token);
			window.location.replace(`http://localhost:${PORT}/ResearcherService/index.jsp`);
		}	
		target.innerHTML = `Login`;
    },
  });
});


