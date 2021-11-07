function sendRequest(name) {
    let form = document.forms[name]
    jQuery.ajax({
        url: '/rest',
        data: $('form').serialize(),
        cache: false,
        contentType: false,
        processData: false,
        method: 'GET',
        success: calculate,
        error: exception,
    });
}

function exception(data){
    let result = document.getElementById("result");
    result.innerText = data.result;
    return result;
}

function calculate(data){
    let result = document.getElementById("result");
    result.innerText = data.result;
}