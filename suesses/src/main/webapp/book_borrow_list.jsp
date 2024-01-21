<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Book Inventory</title>
    <h1>User Name: ${loggedInUser.user_name}</h1>
    <button onclick="location.href='logout'">Logout</button>
    <button onclick="location.href='book_inventory'">Book Inventory</button>
    <button onclick="location.href='book_borrow_list'">Book Borrowed</button>
</head>
<body>

<h2>Book Inventory</h2>

<table border="1">
    <thead>
        <tr>
            <th>Record Id</th>
            <th>User Id</th>
            <th>Inventory Id</th>
            <th>Borrowing_time Time</th>
            <th>Return Time</th>
            <th>狀態</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="record" items="${allRecords}">
            <tr>
                <td>${record.record_id}</td>
                <td>${record.user_id}</td>
                <td>${record.inventory_id}</td>
                <td>${record.borrowing_time}</td>
                <td>${record.return_time}</td>
                <td>
                    <form method="post" action="/book_borrow_list" style="display:inline;">
                        <input type="hidden" name="record_id" value="${record.record_id}">
                        <button type="submit" class="button-link">歸還</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
