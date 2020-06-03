$(document).ready(function() {

    function set() {
        $.getJSON("/admin/status", function (json) {

            $('.dpm-bar2 div').css('width', (100 / (json.total / json.status)).toFixed(2) + '%');
            $('.dpm-bar2 b').text("Database update: "+(100 / (json.total / json.status)).toFixed(2)+ '%');

            if(json.status==json.total){
                $("#update-bd").removeAttr("disabled");
                clearInterval(intervalID);
            }else {

                // $(".dpm-menu-stats-list-bar").css("display","flex");
                $("#update-bd").attr("disabled");

            }console.log("ok "+json.status+" "+json.total)
        });
    } var intervalID;

    $(document)

        .on("click","#update-bd", function () {
            var resultActionUser = confirm("You really want to update the database?");
            if (resultActionUser) {
                $.get("/parser-start");
                intervalID=setInterval(set,750);
            }


        });


    $.getJSON("/admin/status", function (checker) {
        if(checker.is_finished="0"){
            intervalID=setInterval(set,750);
            console.log("if ok "+checker.status+" "+checker.total)
        }
    });
});