/*
 * author: pjavac
 * version: 1.0
 * name: user-info
 * describe: 解决修改用户信息中同用户名获取同邮箱的问题
 */
function checkUserName(){
    var  userName = $("#frist_name").val().trim();
    var oldUserName = $("#frist_name1").val().trim();
    var userNameNotSame;
    if(userName===oldUserName){
        $("#userNameError").text("");
        userNameNotSame = true;
    } else{
        $.ajax({
            url:"user/find/"+userName,
            data:{} ,
            type:"get",
            async:false,
            success:function(str){
                if ("null"===str){
                    $("#userNameError").text("");
                    userNameNotSame = true;
                }else{
                    $("#userNameError").text("用户名存在!");
                    $("#userNameError").css("color","red");
                    userNameNotSame = false;
                }
            }
        });
    }
    return userNameNotSame;
}
function checkUserEmail(){
    var  userEmail = $("#oldEmail").val().trim();
    var oldUserEmail = $("#oldEmail1").val().trim();
    var reg =/^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
    $("#userEmailError").text(" ");
    $("#emailCode").attr("disabled",false);
    var userEmailNotSame;
    if (userEmail==""){
        $("#userEmailError").text(" ");
        $("#emailCode").attr("disabled",true);
    }else if(userEmail===oldUserEmail){
        $("#userEmailError").text("邮箱未变动哦");
        $("#emailCode").attr("disabled",true);
        $("#userEmailError").css("color","red");
        userEmailNotSame = true;
    }else if(!reg.test(userEmail)){
        $("#userEmailError").text("你的邮箱不规范!");
        $("#userEmailError").css("color","red");
        userEmailNotSame = false;
    } else {
        $.ajax({
            url:"user/find/"+userEmail,
            data:{} ,
            type:"get",
            async:false,
            success:function(str){
                if ("null"===str){
                    $("#userEmailError").text("");
                    userEmailNotSame = true;
                }else{
                    $("#userEmailError").text("邮箱已经存在");
                    $("#userEmailError").css("color","red");
                    userEmailNotSame = false;
                }
            }
        });
    }
    return userEmailNotSame;
}
/**
 *
 * 提交的最终判断
 */
function sureSubmit(){
   // alert("已经进行处理");
    var province = $("#input_province").find("option:selected").text();
    var city = $("#input_city").find("option:selected").text();
    var area = $("#input_area").find("option:selected").text();
    var content = "";
    if(null!=province&&province!=""){
        content = province;
        if(null!=city&&city!=""){
            content += "-"+city;
            if(null!=area&&area!=""){
                content += "-"+area;
            }
        }
        $("#input_province").find("option:selected").val(content);
    }
    return checkUserName() && checkUserEmail();
}