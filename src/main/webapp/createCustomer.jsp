    <%@ page contentType="text/html;charset=UTF-8" language="java" %>
    <html>
    <head>
        <title>Create Customer</title>
    </head>
    <body>
    <form action="/createCustomer" method="post" style="width: 400px; margin: 50px auto; padding: 20px; background-color: #f9f9f9; border-radius: 8px; box-shadow: 0 2px 10px rgba(0, 0, 0, 0.1);">
        <h2 style="text-align: center;">Create Customer</h2>

        <input type="text" name="fullName" placeholder="Full Name" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="lastName" placeholder="Last Name" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="email" placeholder="Email" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="phone" placeholder="Phone" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>

        <select name="accountType" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;">
            <option value="SAVINGS">SAVINGS</option>
            <option value="CURRENT">CURRENT</option>
            <option value="BUSINESS">BUSINESS</option>
        </select>

        <input type="text" name="balance" placeholder="Balance" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="accountNumber" placeholder="Account Number" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="code" placeholder="Code" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="userName" placeholder="Username" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="password" name="password" placeholder="Password" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>
        <input type="text" name="customerNumber" placeholder="Customer Number" style="width: 100%; padding: 8px; margin: 10px 0; border: 1px solid #ccc; border-radius: 4px; box-sizing: border-box;"/>

        <button type="submit" style="width: 100%; padding: 10px; background-color: #4CAF50; color: white; border: none; border-radius: 4px; cursor: pointer; font-size: 16px;">Submit</button>
    </form>
    </body>
    </html>
