var updateAdmin = function (val) {
    getElementById("EEE").innerHTML = "";

    var adminId = "";
    var adminBox =[];
    $('input[name="adminBox"]:checked').each(function () {
        adminId = $(this).val();
        adminBox.push(adminId);
    })
    
    if(adminBox.length == 0){
    	getElementById("EEE").innerHTML = "请勾选要修改的用户";
        return;
    }
    
    if (adminBox.length > 1){
        getElementById("EEE").innerHTML = "只能修改单个用户";
        return;
    }
    $("#form1").attr("action","showAdmin.xhtml");
    $("#adminId").val(adminId);
    $("#form1").submit();
}

var addAdmin = function () {
    $("#form1").attr("action","showAdmin.xhtml");
    $("#form1").submit();
}