$(document).ready(function(){
	//getting list of packages
	gettingPackageList();
	
function gettingPackageList(){
	$.ajax({
		type:'GET',
		url:'/api/v2/packages',
		success: function(result){
			console.log(result.results);
			var items = [];
			$.each(result.results,function(key,val){
				items.push("<tr>");

				items.push("<td id=''"+key+"'' class=\"ri\">"+val.id+"</td>");

				items.push("<td id=''"+key+"''>"+val.createdAt+"</td>");

				items.push("<td id=''"+key+"''><img src="+"http://localhost:8888/api/v2"+val.thumnail+" width=\"50%\" height=\"25%\"></td>");

				items.push("<td id=''"+key+"''>"+val.packageTitle+"</td>");

				items.push("<td id=''"+key+"''>"+val.packageSellar+"</td>");
				
				items.push("<td id=''"+val.id+"'' class=\"project-actions text-right\">"+
                          "<a class=\"btn btn-primary btn-sm\" href=\"/admin/packagedetails/"+val.id+"\">"+
                              "<i class=\"fas fa-folder\">"+
                              "</i>View</a>"+   
                          "<button id=\"deletebutton\" class=\"btn btn-danger btn-sm\" >"+
                              "<i class=\"fas fa-trash\"></i>Delete</button></td>");
                             
				
				items.push("</tr>");
			});
			$("<tbody/>",{html:items.join("")}).appendTo("table");
		},
		error: function(error){
			console.log(error);
			
		}
		
		
	});
}

});
	//on delete button click
	$(document).on('click','#deletebutton', function()
		{
		    if(confirm("Do You Want To Delete this Package?")){
		    var $row = $(this).closest("tr");    // Find the row
		    var $text = $row.find(".ri").text(); // Find the text
		   
		    
		    //deleting the package
		    $.ajax({
		    	type:'DELETE',
		    	url:'/admin/package/delete/'+$text,
		    	success : function(result){
		    		console.log(result);
		    		toastr.success('Successfully Deleted Package')
		    		this.gettingPackageList();
		    	},
		    	error: function(error){
		    		console.log(error);

		    		toastr.success('Package Deletion Failed')
		    	}
		    });
		    }    
		});
		
