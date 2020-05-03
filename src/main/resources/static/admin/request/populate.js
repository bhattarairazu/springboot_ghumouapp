var dict = [];
var count=null;
var packages = null;
var thumnail = null;
var names = [];
	
$(function(){

	
	$("#addpackage").click(function(){
		//editiordata();
		//alert("hello");
		
		var packagetitle = $("#packagetitle").val();
		var packagedescription = $("#packagedescription").val();
		var sellar = $("#sellar").val();
		var regularprice = $("#regularprice").val();
		var saleprice = $("#saleprice").val();
		var offerprice = $("#offerprice").val();
		var duration = $("#duration").val();
		var packagetype = $("#packagetype option:selected").text();
		
		 packages = {
				
				packageTitle: packagetitle,
				packageDescription: packagedescription,
				packageSellar : sellar,
				regularPrice : regularprice,
				salePrice : saleprice,
				offers : offerprice,
				duration : duration,
				//packagetype : document.getElementById('packagetype').value;
				packageType : packagetype
				
			}
		console.log(packages);
		 
		toastr.info('Package is Set.')
		$("#addpackage").addClass("btn btn-block btn-primary disabled").removeClass("btn btn-block btn-primary");
		
	});
	//inclusion exclusion and itenary on click
	$("#add").click(function(){
		//adding package data
		editiordata();
		
	
		
	});
	
	$("#saves").click(function(){
		console.log(JSON.stringify(packages));
		
		var formData = new FormData();
		console.log("name is"+names);
		for(var i = 0;i<names.length;++i){
			formData.append('images',names[i]);
		}
		formData.append('thumnail',thumnail);
		console.log("packages "+JSON.stringify(packages));
		//formData.append('packages',JSON.stringify(packages));
		formData.append('packages',new Blob([JSON.stringify(packages)],{
			type:'application/json'
		}));
		
		$.ajax({
            url: '/api/v2/packages',
            type: 'post',
            data: formData,
            //enctype: 'multipart/form-data',
            contentType:false,
            processData:false,
            success: function(response){
            	toastr.success(response.msg);
            },
            error: function(error){
            	console.log("error");
            	toastr.error(error.message);
            }
        });
		
		
	});
	
	
	function editiordata(){
		var radioValue = $("input[name='customRadio']:checked").val();
		var editor_data = $("#editordata").val();
		
		//if(radioValue.localeCompare("inclusion")==0|| radioValue.localeCompare("exclusion")==0 || radioValue.localeCompare("itenary")==0){
			//count++;
		//}
		if(radioValue=="inclusion" ){
			count++;
			packages = {...packages,inclusions :[{inclusion:editor_data}]};
			console.log("count"+packages);
			toastr.info('Inclusion is Set.')
		}
		if(radioValue=="exclusion" ){
			count++;
			packages = {...packages,exclusions :[{exclusion:editor_data}]};
			console.log("count"+packages);
			toastr.info('Exclusion is Set.')
		}
		if(radioValue=="itenary" ){
			count++;
			packages = {...packages,itenarys :[{itenary:editor_data}]};
			console.log("count"+packages);
			toastr.info('Itenary is Set.')
		}
		
		
		if(count==3){
			$("#saves").addClass("btn btn-block btn-success").removeClass("btn btn-block btn-success disabled");
		}
		
		
		console.log("editor data"+editor_data+" radio data is "+radioValue);
		dict.push({
			key:radioValue,
			value:editor_data
		});
		//console.log("all values"+dict);
	}
	
  
	
	//getting single files
	$("#thumnail").on('change', function(e){
		thumnail = $('#thumnail')[0].files[0];
		  
		 })
		 //getting multiple files
	
	 $("#packageimage").on('change',function() {
		    
		    for (var i = 0; i < $(this).get(0).files.length; ++i) {
		        names.push($(this).get(0).files[i]);
		    }
		    console.log(names);
		});
});