<%-- 
    Document   : login
    Created on : May 4, 2020, 11:46:22 PM
    Author     : TiTi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="google-signin-scope" content="profile email">
        <meta name="google-signin-client_id" content="615970847308-ckctakgf16lcc543nhp8jkb3rsc3pjqv.apps.googleusercontent.com">
        <script src="https://apis.google.com/js/platform.js" async defer></script>
        <title>Login Page</title>
    </head>
    <body>
        <h1>Welcome to Hana Shop</h1>
        <h2>Login</h2>
        <form action="login" method="POST">
            Username <input type="text" name="username" value="" required/><br/>
            Password <input type="password" name="password" value="" required/><br/>
            <input type="submit" value="Login" />
            <input type="reset" value="Reset" /><br/>
        </form>
        <br/><div class="g-signin2" data-onsuccess="onSignIn"></div>
        <br/> <a href="#" onclick="signOut();">Sign out</a><br/>
        <a id="email" href="#" onclick="goUrl()"></a>
        <script>

            function onSignIn(googleUser) {
                console.log(googleUser.getBasicProfile());
                var profile = googleUser.getBasicProfile();
                document.getElementById("email").innerHTML = profile.getEmail();
            }
            function goUrl() {
                var email = document.getElementById("email").textContent;
                location.replace('login?email=' + email);
            }
            function signOut() {
                var auth2 = gapi.auth2.getAuthInstance();
                auth2.signOut().then(function () {
                    console.log('User signed out.');
                });
            }
        </script>
        <s:if test="message != null || !message.isEmpty()">
            <h3 style="color: orangered"><em><s:property value="message"/></em></h3>
                </s:if>
    </body>
</html>
