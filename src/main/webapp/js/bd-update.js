$(document).ready(function() {

    function set() {
        $.getJSON("/admin/status", function (json) {

            $('.dpm-bar2 div').css('width', (100 / (json.total / json.status)).toFixed(2) + '%');
            $('.dpm-bar2 b').text("Database update: "+(100 / (json.total / json.status)).toFixed(2)+ '%');

            if(json.status==json.total){
                // alert("БАЗУ ДАНИХ УСПІШНО ОНОВЛЕНО!\n\n" +
                //     "Лісорубні квитки: додано " + json.new_tickets + ", оновлено " + json.updated_tickets + ", перевірено " + json.checked_tickets + "\n" +
                //     "Лісорубні ділянки: додано " + json.new_tracts + ",  оновлено " + json.updated_tracts + ",  перевірено " + json.checked_tracts);
                //
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




        })

        .on("click","#stop-update-bd", function () {
            var resultActionUser = confirm("Do you really want to stop updating the database?");
            if (resultActionUser) {
                $.get("/parser-stop");

            }
        });



    $.getJSON("/admin/status", function (checker) {
        if(checker.is_finished="0"){
            intervalID=setInterval(set,750);
            console.log("if ok "+checker.status+" "+checker.total)
        }
    });
});