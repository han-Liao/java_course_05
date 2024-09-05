<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Result</title>
</head>
<body>
    <h1>Result of Addition</h1>
    <%
        // Retrieve request parameters
        String num1Str = request.getParameter("num1");
        String num2Str = request.getParameter("num2");

        // Initialize result variable
        double result = 0;
        boolean validInput = true;

        // Check if inputs are not null and are numeric
        if (num1Str != null && num2Str != null && !num1Str.trim().isEmpty() && !num2Str.trim().isEmpty()) {
            try {
                // Try to parse both inputs as doubles
                double num1 = Double.parseDouble(num1Str);
                double num2 = Double.parseDouble(num2Str);

                // Compute the result
                result = num1 + num2;
            } catch (NumberFormatException e) {
                // If parsing fails, set validInput to false
                validInput = false;
            }
        } else {
            // If inputs are null or empty, set validInput to false
            validInput = false;
        }
    %>

    <%
        if (validInput) {
    %>
        <p>The sum of <%= request.getParameter("num1") %> and <%= request.getParameter("num2") %> is: <%= result %></p>
    <%
        } else {
    %>
        <p>Invalid input. Please enter valid numbers. Ensure you are entering numeric values only.</p>
    <%
        }
    %>

    <br>
    <a href="index.jsp">Back to Input Form</a>
</body>
</html>
