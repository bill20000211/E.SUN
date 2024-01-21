<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>User List</title>
</head>
<body>
    ${users}
    <h1>User List</h1>
    <table>
        <thead>
            <tr>
                <th>User ID</th>
                <th>Phone Number</th>
                <th>User Name</th>
            </tr>
        </thead>
        <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td><c:out value="${user.user_id}" /></td>
                    <td><c:out value="${user.phone_number}" /></td>
                    <td><c:out value="${user.user_name}" /></td>
                </tr>
            </c:forEach>
            
        </tbody>
    </table>
</body>
</html>
