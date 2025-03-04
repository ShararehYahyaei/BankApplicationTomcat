<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Transfer</title>
</head>
<body>
<form action="transfer" method="POST">
    <input type="text" name="cardNumberSource" placeholder="Card Number Source" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="cardNumberDestination" placeholder="Card Number Destination" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="amount" placeholder="Amount" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="userName" placeholder="Username" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="password" name="cvv2" placeholder="CVV2" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="expiryDate" placeholder="Expiry Date (yyyy-MM-dd)" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="password" placeholder="Password" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <input type="text" name="customerNumber" placeholder="Customer Number" required
           style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
    <button type="submit"
            style="width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">
        Submit
    </button>
</form>
</body>
</html>
