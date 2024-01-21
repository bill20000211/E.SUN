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
            <th>Inventory ID</th>
            <th>ISBN</th>
            <th>Store Time</th>
            <th>Status</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach var="book" items="${allBooks}">
            <tr>
                <td>${book.inventory_id}</td>
                <td>${book.isbn}</td>
                <td>${book.store_time}</td>
                <td>${book.status}</td>
                <td>
                    <form method="post" action="book_borrow">
                        <input type="hidden" name="inventory_id" value="${book.inventory_id}">
                        <button type="submit" class="button-link">借閱</button>
                    </form>
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>

</body>
</html>
