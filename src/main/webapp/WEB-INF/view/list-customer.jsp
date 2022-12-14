<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ page import="org.example.model.Utility" %>

<html>
<head>
    <title>List Customers</title>
<%--    reference to our style sheet--%>
    <link href="<c:url value="/resources/css/style.css" />" rel="stylesheet">
</head>
<body>

    <div id="wrapper">
        <div id="header">
            <h2>CRM - Customer Relationship Manager</h2>
        </div>
    </div>

    <div id="container">
        <div id="content">

            <!-- put a new button: Add Customer -->
            <input type="button" value="Add Customer"
                   onclick="window.location.href='showFormForAdd'; return false"
                   class="add-button"
            />
            <form:form action="search" method="GET">
                Search customer: <input type="text" name="theSearchName" />

                <input type="submit" value="Search" class="add-button" />
            </form:form>
            <!-- add our html table here -->

            <table>
                <!-- setup header links for sorting -->

                <!-- construct a sort link for first name -->
                <c:url var="sortLinkFirstName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(Utility.FIRST_NAME) %>" />
                </c:url>

                <!-- construct a sort link for last name -->
                <c:url var="sortLinkLastName" value="/customer/list">
                    <c:param name="sort" value="<%= Integer.toString(Utility.LAST_NAME) %>" />
                </c:url>

                <!-- construct a sort link for email -->
                <c:url var="sortLinkEmail" value="/customer/list">
                        <c:param name="sort" value="<%= Integer.toString(Utility.EMAIL) %>" />
                </c:url>

                <tr>
                    <th><a href="${sortLinkFirstName}">First Name</a></th>
                    <th><a href="${sortLinkLastName}">Last Name</a></th>
                    <th><a href="${sortLinkEmail}">Email</a></th>
                    <th>Action</th>
                </tr>

                <c:forEach var="tempCustomer" items="${customers}">
                    <c:url var="updateLink" value="/customer/showFormForUpdate">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>

                    <c:url var="deleteLink" value="/customer/delete">
                        <c:param name="customerId" value="${tempCustomer.id}"/>
                    </c:url>
                    <tr>
                        <td>${tempCustomer.firstName}</td>
                        <td>${tempCustomer.lastName}</td>
                        <td>${tempCustomer.email}</td>
                        <td>
                            <a href="${updateLink}">Update</a>
                            |
                            <a href="${deleteLink}"
                                onclick="if (!(confirm('Are you sure you want to delete this customer?'))) return false" >
                                Delete</a>
                        </td>
                    </tr/>
                </c:forEach>
            </table>
        </div>
    </div>

</body>
</html>
