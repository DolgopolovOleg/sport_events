<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<%--<script>--%>
    <%--// This is called with the results from from FB.getLoginStatus().--%>
    <%--function statusChangeCallback(response) {--%>
        <%--console.log('statusChangeCallback');--%>
        <%--console.log(response);--%>
        <%--// The response object is returned with a status field that lets the--%>
        <%--// app know the current login status of the person.--%>
        <%--// Full docs on the response object can be found in the documentation--%>
        <%--// for FB.getLoginStatus().--%>
        <%--if (response.status === 'connected') {--%>
            <%--// Logged into your app and Facebook.--%>
            <%--testAPI();--%>
        <%--} else if (response.status === 'not_authorized') {--%>
            <%--// The person is logged into Facebook, but not your app.--%>
            <%--document.getElementById('status').innerHTML = 'Please log ' +--%>
                    <%--'into this app.';--%>
        <%--} else {--%>
            <%--// The person is not logged into Facebook, so we're not sure if--%>
            <%--// they are logged into this app or not.--%>
            <%--document.getElementById('status').innerHTML = 'Please log ' +--%>
                    <%--'into Facebook.';--%>
        <%--}--%>
    <%--}--%>

    <%--// This function is called when someone finishes with the Login--%>
    <%--// Button.  See the onlogin handler attached to it in the sample--%>
    <%--// code below.--%>
    <%--function checkLoginState() {--%>
        <%--FB.getLoginStatus(function(response) {--%>
            <%--statusChangeCallback(response);--%>
        <%--});--%>
    <%--}--%>

    <%--window.fbAsyncInit = function() {--%>
        <%--FB.init({--%>
            <%--appId      : '786494378130597',--%>
            <%--cookie     : true,  // enable cookies to allow the server to access--%>
            <%--// the session--%>
            <%--xfbml      : true,  // parse social plugins on this page--%>
            <%--version    : 'v2.2' // use version 2.2--%>
        <%--});--%>

        <%--// Now that we've initialized the JavaScript SDK, we call--%>
        <%--// FB.getLoginStatus().  This function gets the state of the--%>
        <%--// person visiting this page and can return one of three states to--%>
        <%--// the callback you provide.  They can be:--%>
        <%--//--%>
        <%--// 1. Logged into your app ('connected')--%>
        <%--// 2. Logged into Facebook, but not your app ('not_authorized')--%>
        <%--// 3. Not logged into Facebook and can't tell if they are logged into--%>
        <%--//    your app or not.--%>
        <%--//--%>
        <%--// These three cases are handled in the callback function.--%>

        <%--FB.getLoginStatus(function(response) {--%>
            <%--statusChangeCallback(response);--%>
        <%--});--%>

    <%--};--%>

    <%--// Load the SDK asynchronously--%>
    <%--(function(d, s, id) {--%>
        <%--var js, fjs = d.getElementsByTagName(s)[0];--%>
        <%--if (d.getElementById(id)) return;--%>
        <%--js = d.createElement(s); js.id = id;--%>
        <%--js.src = "//connect.facebook.net/en_US/sdk.js";--%>
        <%--fjs.parentNode.insertBefore(js, fjs);--%>
    <%--}(document, 'script', 'facebook-jssdk'));--%>

    <%--// Here we run a very simple test of the Graph API after login is--%>
    <%--// successful.  See statusChangeCallback() for when this call is made.--%>
    <%--function testAPI() {--%>
        <%--console.log('Welcome!  Fetching your information.... ');--%>
        <%--FB.api('/me', function(response) {--%>
            <%--console.log('Successful login for: ' + response.username);--%>
            <%--console.log('Response: ' + response);--%>
            <%--console.log(response);--%>
            <%--document.getElementById('status').innerHTML =--%>
                    <%--'Thanks for logging in, ' + response.username + '!';--%>
        <%--});--%>
    <%--}--%>
<%--</script>--%>

<!--
Below we include the Login Button social plugin. This button uses
the JavaScript SDK to present a graphical Login button that triggers
the FB.login() function when clicked.
-->



<%--<fb:login-button scope="public_profile,email,user_friends" onlogin="checkLoginState();">--%>
<%--</fb:login-button>--%>

<%--<div id="status">--%>
<%--</div>--%>


<form action="/connect/facebook" method="POST">
    <input type="hidden" name="scope" value="email">
    <input type="submit" value="FB connect"/>
</form>




<div>Das ist header - ${title} |
    <sec:authorize access="isAnonymous()">
        <a href="/login">Login</a> /
        <a href="/user/register">Registration</a>
    </sec:authorize>

    <sec:authorize access="isAuthenticated()">
        <a href="/logout">Logout</a>
    </sec:authorize>


    <sec:authorize access="hasRole('ADMIN')">
        <b>Вы админ!</b>
    </sec:authorize>

</div>

