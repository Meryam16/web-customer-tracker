<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <title>Save Customer</title>
    <link href="<c:url value="/resources/css/style.css" />"
          rel="stylesheet">
    <link href="<c:url value="/resources/css/add-customer-style.css" />"
          rel="stylesheet">
</head>
<body>
<div id="wrapper">
    <div id = "header">
        <h2>CRM-Customer Relationship Manager</h2>
    </div>
    <div id="container">
        <h3>Save Customer</h3>
        <form:form action="saveCustomer" modelAttribute="customer" method="POST">
            <form:hidden path="id" />

            <table>
                <tbody>
                    <tr>
                        <td><label>FirstName:</label></td>
                        <td><form:input path="firstName"></form:input></td>
                    </tr>
                    <tr>
                        <td><label>LastName:</label></td>
                        <td><form:input path="lastName"></form:input></td>
                    </tr>
                    <tr>
                        <td><label>Email:</label></td>
                        <td><form:input path="email"></form:input></td>
                    </tr>
                    <tr>
                        <td><label></label></td>
                        <td><input type="submit" value="Save"></td>
                    </tr>

                </tbody>
            </table>
        </form:form>
        <div style="clear: both;"></div>
        <p>
            <a href="${pageContext.request.contextPath}/customer/list">Back to List</a>
        </p>
    </div>
</div>
</body>
</html>
