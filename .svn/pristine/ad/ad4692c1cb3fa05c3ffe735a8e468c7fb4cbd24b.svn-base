
    $(function() {
        $(document).keypress(function(event){
            var keycode = (event.keyCode ? event.keyCode : event.which);
            if(keycode == '13'){
                $("form").find('input[type=submit]').trigger('click');
            }
        });
        init_loginBtn();
    });

    function init_loginBtn(){
        if($("#saveLoginInfod").attr("checked") == "checked"){
            $("#saveLoginInfod").val("yes");
        }else{
            $("#saveLoginInfod").val("no");
        }
        if($("#autoLogind").attr("checked") == "checked"){
            $("#autoLogind").val("yes");
            login();
        }else{
            $("#autoLogind").val("no");
        }

        /*保存账号密码和自动登录*/
        $(".login_checkbox").click(function(){
            if($(this).find("#saveLoginInfod").is(":checked")){
                $(this).find("#saveLoginInfod").val("yes");
            }else{
                $(this).find("#saveLoginInfod").val("no");
            }
            if($(this).find("#autoLogind").is(":checked")){
                $(this).find("#autoLogind").val("yes");
            }else{
                $(this).find("#autoLogind").val("no");
            }
        });
    }

    function login(){
        var username = $('#username').val();
        var password = $('#password').val();
        if(proUntil.strIsEmpty(username)){
            proUntil.alert_win("请输入用户名");
            return;
        }

        if(proUntil.strIsEmpty(password)){
            proUntil.alert_win("请输入密码");
            return;
        }

        if(password.length != 32){
            $('#password').val(hex_md5(password));
        }

        $.ajax({
            type:"post",
            dataType:"text",
            url:"/login",
            data:$('.login').serialize(),
            success: function (msg) {
                if(msg==1){
                    location.href = "mainPage.jsp";
                }else{
                    proUntil.alert_win("用户名或密码有误");
                }
            }
        });
    }
