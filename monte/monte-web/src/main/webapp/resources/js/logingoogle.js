/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


//function onSignIn(googleUser) {
//    var id_token = googleUser.getAuthResponse().id_token;
//    var xhr = new XMLHttpRequest();
//    xhr.open('POST', 'http://localhost:8080/monte-web/api/hola/loginGoogle');
//    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//    xhr.onload = function() {
//      console.log('Signed in as: ' + xhr.responseText);
//    };
//    xhr.send("idtokenString=" + id_token);
//}



//function checkIfLoggedIn()
//{
//    alert("ejecuto");
//  if(sessionStorage.getItem('myUserEntity') == null){
//    //Redirect to login page, no user entity available in sessionStorage
//    alert("valiste verga wey");
//    //window.location.href='http://localhost:8080/monte-web/login.xhtml';
//  } else {
//    //User already logged in
//    var userEntity = {};
//    userEntity = JSON.parse(sessionStorage.getItem('myUserEntity'));
//    //window.location.replace("http://localhost:8080/monte-web/faces/protegido/principal.xhtml");
//    alert("Al parecer reconoce que la de google esta abiera");
//        //signOut();
//        logout();
//        console.log(userEntity.Name);
//    }
//}
//
//function logout()
//{
//  //Don't forget to clear sessionStorage when user logs out
//  sessionStorage.clear();
//}


function onSignIn(googleUser) {
    var profile = googleUser.getBasicProfile();
    var id_token = googleUser.getAuthResponse().id_token;
    console.log('Id token : ' + id_token);
    console.log('Id : ' + profile.getId());
    console.log('Name: ' + profile.getName());
    console.log('Image URL: ' + profile.getImageUrl());
    console.log('Email: ' + profile.getEmail());
    
    var xhr = new XMLHttpRequest();
    xhr.open('POST', 'http://localhost:8080/monte-web/api/hola/sesionGoogle');
    xhr.setRequestHeader('Content-Type', 'application/x-www-form-urlencoded');
//    xhr.onload = function() {
//      console.log('Signed in as: ' + xhr.responseText);
//    };
    xhr.send(
            "apellido=" + profile.getFamilyName() +
            "&nombre=" + profile.getGivenName() + 
            "&email=" + profile.getEmail());
    
    PF('wve').jq.val(profile.getEmail());
    
    window.onload = function(){
        var button = document.getElementById('clickButton');
        button.form.submit();
    }
    
    var auth2 = gapi.auth2.getAuthInstance();
    auth2.signOut().then(function () {
      console.log('User signed out.');
  });  
    window.location.replace("http://localhost:8080/monte-web/faces/protegido/report.xhtml");
    
}

//  function signOut() {
//    var auth2 = gapi.auth2.getAuthInstance();
//    auth2.signOut().then(function () {
//      console.log('User signed out.');
//    });
    
//     var auth2;
//
///**
// * Initializes the Sign-In client.
// */
//var initClient = function() {
//    gapi.load('auth2', function(){
//        /**
//         * Retrieve the singleton for the GoogleAuth library and set up the
//         * client.
//         */
//        auth2 = gapi.auth2.init({
//            client_id: '41773803941-p9l0sms3ggnmto9rj2qphfcukfufj4bo.apps.googleusercontent.com'
//        });
//
//        // Attach the click handler to the sign-in button
//        auth2.attachClickHandler('signin-button', {}, onSuccess, onFailure);
//    });
//};
//
///**
// * Handle successful sign-ins.
// */
//var onSuccess = function(user) {
//    console.log('Signed in as ' + user.getBasicProfile().getName());
// };
//
///**
// * Handle sign-in failures.
// */
//var onFailure = function(error) {
//    console.log(error);
//};
  