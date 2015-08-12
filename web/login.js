$(document).ready(function () {

    function showGames(data) {
        $.mobile.navigate("#game_list_page")
        showAllExistingGames();
    };
    
    $("#registrationForm").submit(function (event) {
        event.preventDefault();
        var $form = $(this),
                userName = $form.find("input[name='username']").val(),
                password = $form.find("input[name='password']").val(),
                url = $form.attr("action");
        var data = {userName: userName, userPassword: password};
        $.ajax({
            type: "POST"
            , url: url
            , contentType: "application/json"
            , data: JSON.stringify(data)
            , dataType: "json"
        }).done(function (data) {
            
            if (data.success === true) {
                currentUser = userName;
                showGames(data);
            }
        }).fail(function (data) {
            console.log(data);
        });

    });
});