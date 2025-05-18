<%@ page import="java.sql.*" %>
<html>  
<head>
    <title>Student Marks</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f5f7fa;
            margin: 40px;
        }

        h1 {
            text-align: center;
            color: #2c3e50;
        }

        form {
            background-color: #ffffff;
            padding: 20px;
            width: 400px;
            margin: auto;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
            border-radius: 10px;
        }

        form input[type="text"],
        form input[type="email"] {
            width: 100%;
            padding: 8px;
            margin: 6px 0 12px 0;
            border: 1px solid #ccc;
            border-radius: 5px;
        }

        form input[type="submit"] {
            background-color: #3498db;
            color: white;
            border: none;
            padding: 10px 20px;
            border-radius: 5px;
            cursor: pointer;
        }

        form input[type="submit"]:hover {
            background-color: #2980b9;
        }

        table {
            width: 90%;
            margin: 40px auto;
            border-collapse: collapse;
            background-color: #fff;
            box-shadow: 0 0 10px rgba(0,0,0,0.1);
        }

        th, td {
            padding: 12px 15px;
            border: 1px solid #ddd;
            text-align: center;
        }

        th {
            background-color: #2c3e50;
            color: white;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        .error {
            color: red;
            text-align: center;
        }

        .success {
            color: green;
            text-align: center;
        }
    </style>
</head>
<body>  

<% out.print("<div class='success'>Hello World !! Welcome to JSP</div>"); %>

<h1>Welcome to Sanjivani College of Engineering, IT Department</h1>

<!-- Form to take student data input -->
<form method="post">
    <h3>Enter Student Details</h3>
    Roll No: <input type="text" name="roll" required><br>
    Name: <input type="text" name="name" required><br>
    Email: <input type="email" name="email" required><br>
    Maths Marks: <input type="text" name="maths" required><br>
    Science Marks: <input type="text" name="science" required><br>
    <input type="submit" value="Submit">
</form>

<%
    String roll = request.getParameter("roll");
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String maths = request.getParameter("maths");
    String science = request.getParameter("science");

    try {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/minibini", "root", "");

        // Insert data if form is submitted
        if (roll != null && name != null && email != null && maths != null && science != null) {
            String insertQuery = "INSERT INTO student VALUES (?, ?, ?, ?, ?)";
            PreparedStatement ps = con.prepareStatement(insertQuery);
            ps.setString(1, roll);
            ps.setString(2, name);
            ps.setString(3, email);
            ps.setString(4, maths);
            ps.setString(5, science);
            ps.executeUpdate();
        }

        // Display all students
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT * FROM student");
%>

<table>
    <tr>
        <th>Roll num</th>
        <th>Name</th>
        <th>Email</th>
        <th>Maths Marks</th>
        <th>Science Marks</th>
    </tr>

<%
        while(rs.next()) {
%>
    <tr>
        <td><%= rs.getString(1) %></td>
        <td><%= rs.getString(2) %></td>
        <td><%= rs.getString(3) %></td>
        <td><%= rs.getString(4) %></td>
        <td><%= rs.getString(5) %></td>
    </tr>
<%
        }

        rs.close();
        stmt.close();
        con.close();

    } catch(Exception e) {
        out.println("<p class='error'>Error: " + e.getMessage() + "</p>");
    }
%>

</table>
</body>  
</html>
