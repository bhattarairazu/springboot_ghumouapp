$(document).ready(function(){
    $("#login").click(function () {
        var username = $("#email").val();
        var password = $("#password").val();
        console.log(username,password);
        var login = {
            username:username,
            password:password
        }
        console.log(JSON.stringify({'username':username,'password':password}));
        $.ajax({
            url:'http://api.ghumou.com/ghumou/api/v2/account/login',
            type:'POST',
            data:JSON.stringify(login),
            dataType:'jsonp',
            cors: true ,
            contentType:false,
            processData:false,
            success:function (result) {
                console.log("Success"+result);
                window.location.href = "/admin_v1/dashboard";

            },
            error: function (error) {

                    //window.location.href = "/admin_v1/dashboard";
                    console.log("Failed to login try again " + error);

            }


        });

    });

});