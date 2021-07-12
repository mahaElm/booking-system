function validate() {
//new
    var user = (name:document.myform.name.value ,
        mail: ocument.myform.mail.value ,
        phonee:document.myform.phonee.value,
        btime: document.myform.btime.value  ,
        bdate:document.myform.bdate.value );


    //for ID
    if (document.myForm.name.value.charAt(0) != "1") {
        alert("The ID should starts with 1");
        document.myForm.name.focus();
        return false;
    }
    if (isNaN(document.myForm.name.value) ||
        document.myForm.name.value.length != 10) {
        alert("Please enter a valid id");
        document.myForm.name.focus();
        return false;
    }

    //for mail
    var mailformat = /^[a-zA-Z0-9.!#$%&'*+/=?^_`{|}~-]+@[a-zA-Z0-9-]+(?:\.[a-zA-Z0-9-]+)*$/;
    if (!document.myForm.mail.value.match(mailformat)) {

        alert("You have entered an invalid email address!");
        return false;
    }
    //for phone
    var phoneformat = /^(009665|9665|\+9665|05|5)(5|0|3|6|4|9|1|8|7)([0-9]{7})$/;
    if (!document.myForm.phonee.value.match(phoneformat)) {

        alert("You have entered an invalid phone number!");
        return false;
    }

    function sendRequest (user) {



        $.ajax({
            url: 'http://localhost:8080/add-user',
            type: 'post',
            data: user,
            dataType: 'json'
        });





    }

    return (true);


}