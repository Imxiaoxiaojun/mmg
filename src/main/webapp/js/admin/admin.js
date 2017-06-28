var updateAdmin = function (val) {
    getElementById("EEE").innerHTML = "";

    var adminId = "";
    $('input[name="adminBox"]:checked').each(function () {
        adminId = $(this).val();
    })

    if (adminBox.length != 1){
        getElementById("EEE").innerHTML = "只能修改单个用户";
        return;
    }
    $("#form").action = "admin/updateAdmin.hel";
    $("#adminId").val(adminId);
    $("#form").submit();
}

var addAdmin = function () {
    $("#form1").attr("action","updateAdmin.hel");
    $("#form1").submit();
}