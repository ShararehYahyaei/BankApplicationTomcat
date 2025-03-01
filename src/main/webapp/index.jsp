<h2>Welcome to the Bank Application</h2>

<a href="/createCustomer" style="display: inline-block;
 padding: 10px 15px; background: #52a883; color: white;
 text-decoration: none; border-radius: 5px; margin-bottom: 10px; margin-right: 10px;">
    Go to Create Customer
</a>

<a href="/getAllCustomers" style="display: inline-block;
 padding: 10px 15px; background: #52a883; color: white;
 text-decoration: none; border-radius: 5px; margin-bottom: 10px;">
    Show All Customers
</a>

<form action="/getCustomer" method="get" style="display: flex; gap: 8px; align-items: center; margin-top: 15px;">
    <input type="text" name="customerNumber" placeholder="Enter Customer Number" required
           style="padding: 6px; border: 1px solid #ccc; border-radius: 4px;">
    <button type="submit" style="padding: 10px 15px; background: #52a883; color: white;
    text-decoration: none; border-radius: 5px; border: none; cursor: pointer;">
        Show Customer
    </button>
</form>


<form action="/getAccount" method="get" style="display: flex; gap: 8px; align-items: center; margin-top: 15px;">
    <input type="text" name="customerNumber" placeholder="Enter Customer Number" required
           style="padding: 6px; border: 1px solid #ccc; border-radius: 4px;">
    <button type="submit" style="padding: 10px 15px; background: #52a883; color: white;
    text-decoration: none; border-radius: 5px; border: none; cursor: pointer;">
        Show Account
    </button>
</form>
