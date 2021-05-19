const Toast = Swal.mixin({
	  toast: true,
	  position: 'top-start',
	  showConfirmButton: false,
	  width: 270,
	  timer: 4000,	  
	  timerProgressBar: true,
	  onOpen: (toast) => {
	    toast.addEventListener('mouseenter', Swal.stopTimer)
	    toast.addEventListener('mouseleave', Swal.resumeTimer)
	  }
});


// https://sweetalert2.github.io/#examples
// ICONS: success error warning info question

function fireToast( icon, title, text ){
	
	Toast.fire({
		title: title,
		text: text,
		icon: icon,
		footer: '<a href="#">Need Help?</a>',
	});

}


function trySwal(){
	
	
	Swal.fire({
		  title: 'Submit your Github username',
		  input: 'text',
		  inputAttributes: {
		    autocapitalize: 'off'
		  },
		  showCancelButton: true,
		  confirmButtonText: 'Look up',
		  showLoaderOnConfirm: true,
		  preConfirm: (login) => {
		    return fetch(`//api.github.com/users/${login}`)
		      .then(response => {
		        if (!response.ok) {
		          throw new Error(response.statusText)
		        }
		        console.log( response.json() );
		      })
		      .catch(error => {
		        Swal.showValidationMessage(
		          `Request failed: ${error}`
		        )
		      })
		  },
		  allowOutsideClick: () => !Swal.isLoading()
		}).then((result) => {
		  if (result.isConfirmed) {
		    Swal.fire({
		      title: `${result.value.login}'s avatar`,
		      imageUrl: result.value.avatar_url
		    })
		  }
		})	
}



function trySwal2(){
	
	const ipAPI = '//api.ipify.org?format=json'

		Swal.queue([{
		  title: 'Your public IP',
		  confirmButtonText: 'Show my public IP',
		  text:
		    'Your public IP will be received ' +
		    'via AJAX request',
		  showLoaderOnConfirm: true,
		  preConfirm: () => {
		    return fetch(ipAPI)
		      .then(response => response.json())
		      .then(data => Swal.insertQueueStep(data.ip))
		      .catch(() => {
		        Swal.insertQueueStep({
		          icon: 'error',
		          title: 'Unable to get your public IP'
		        })
		      })
		  }
		}]);	
	
}