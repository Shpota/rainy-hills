/**
 * Parses user input. Performs GET call to solving endpoint.
 * Updates UI according to calculation result.
 */
function calculateVolumes() {
    const volumesInput = document.getElementById('volumes-input');
    const volumes = volumesInput.value.trim();
    const query = '/api/calculate-water-volume?hills=' + volumes.replace(/ +/g, '&hills=');
    const request = new XMLHttpRequest();
    request.onreadystatechange = function () {
        document.getElementById('result').innerHTML = request.responseText;
        document.getElementById('result-text').style.visibility = 'visible';
    };
    request.open('GET', query, true);
    request.send();
}

/**
 * Validates input. Disables calculation button and shows alert
 * message if the input is empty or holds elements different from
 * numbers and spaces. Hides alert message id input is valid.
 */
function validateInput() {
    const volumesInput = document.getElementById('volumes-input');
    const alertLabel = document.getElementById('alert');
    const button = document.getElementById("calculate-button");
    const volumesInputContent = volumesInput.value.trim();
    if (/^[0-9 ]+$/.test(volumesInputContent)) {
        button.disabled = false;
        alertLabel.style.visibility = 'hidden';
    } else {
        button.disabled = true;
        alertLabel.style.visibility = 'visible';
    }
}
