
<template:loggedOut htmlTitle="Log In" bodyTitle="Log In">
		<form method="POST" action="<c:url value="/createAccount" />" >
		<c:if test="${Confirmpassword}">
        <b>Passwords do not match.</b><br /><br />
    </c:if>
    <c:if test="${ConfirmAccount}">
        <b>All fields must be filled out.</b><br /><br />
    </c:if>
		First Name<br />
		<input type="text" name="firstName" required="required"/><br /><br />
		Last Name<br />
		<input type="text" name="lastName" required="required"/><br /><br />
		User Name<br />
		<input type="text" name="userName" required="required"/><br /><br />
		User ID<br />
		<input type="number" name="userID" required="required"/><br /><br />
        Password<br />
        <input type="password" name="password" required="required"/><br /><br />
        Confirm password<br />
        <input type = "password" name = "newPassword" required="required"/><br /><br />
        <input type="submit" value="Create Account" /> 
        </form>

</template:loggedOut>
