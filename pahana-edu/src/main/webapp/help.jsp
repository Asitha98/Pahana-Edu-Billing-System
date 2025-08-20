<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"/>
  <title>Help — Pahana Billing</title>
  <link rel="stylesheet" href="<c:url value='/assets/css/styles.css'/>">
</head>
<body>
  <jsp:include page="header.jsp"/>

  <div class="container">
    <h1>Help</h1>
    <p class="muted">Quick guide to using the system. If you get stuck, see Troubleshooting below.</p>

    <div class="grid">
      <div class="card">
        <h2>Quick Start</h2>
        <ol>
          <li>Login at <code class="code"><c:url value='/login'/></code> (admin/cashier).</li>
          <li>Open <strong>Customers</strong> → <em>+ Add Customer</em>.</li>
          <li>Open <strong>Items</strong> → <em>+ Add Item</em>.</li>
          <li>Open <strong>Bills</strong> → <em>Generate Bill</em> form.</li>
        </ol>
      </div>

      <div class="card">
        <h2>Navigation</h2>
        <ul>
          <li>Dashboard → <code class="code"><c:url value='/admin'/></code></li>
          <li>Customers → list/add/edit/delete</li>
          <li>Items → list/add/edit/delete</li>
          <li>Bills → view & quick generate</li>
        </ul>
        <p class="note">Admin-only: cashier management, deletes (if RoleFilter is enabled).</p>
      </div>

      <div class="card">
        <h2>Roles & Access</h2>
        <ul>
          <li><strong>admin</strong>: full access (may delete, manage cashiers)</li>
          <li><strong>cashier</strong>: create/edit, but restricted deletes</li>
        </ul>
      </div>

      <div class="card">
        <h2>Common Tasks</h2>
        <ul>
          <li><strong>Add Customer</strong>: <code class="code"><c:url value='/customer/add'/></code></li>
          <li><strong>Edit Customer</strong>: from the Customers table → <em>Edit</em></li>
          <li><strong>Add Item</strong>: <code class="code"><c:url value='/item/add'/></code></li>
          <li><strong>Generate Bill</strong>: <code class="code"><c:url value='/bills'/></code> form</li>
        </ul>
      </div>
    </div>

    <div class="card" style="margin-top:16px;">
      <h2>Troubleshooting</h2>

      <details class="faq">
        <summary>Login says “Invalid username or password”.</summary>
        <div>
          Try the seeded admin user:
          <code class="code">username: admin</code>, <code class="code">password: admin</code>.
          If it still fails, check the <code class="code">users</code> table and DB connection in
          <code class="code">DBConnection.java</code>.
        </div>
      </details>

      <details class="faq">
        <summary>“Admin only” error when deleting.</summary>
        <div>
          You’re logged in as a cashier. Use an admin account or adjust <code class="code">RoleFilter</code>.
        </div>
      </details>

      <details class="faq">
        <summary>Database errors (cannot connect / table doesn’t exist).</summary>
        <div>
          Confirm DB URL/user/pass in <code class="code">DBConnection</code>, create the DB, and run the schema SQL.
          Also ensure MySQL is running and reachable.
        </div>
      </details>

      <details class="faq">
        <summary>Build errors: Java method not found (e.g., <code>Set.of</code>, <code>isBlank</code>).</summary>
        <div>
          You’re on Java 8. Use the Java-8 compatible code (we provided replacements) or upgrade Maven
          compiler target to 11+.
        </div>
      </details>
    </div>

    <div class="card" style="margin-top:16px;">
      <h2>About</h2>
      <p>
        Backend: Servlets + JSP (JSTL), DAO + Service layers, MySQL.  
        Paths follow: <code class="code">/login</code>, <code class="code">/admin</code>,
        <code class="code">/customers</code>, <code class="code">/items</code>, <code class="code">/bills</code>.
      </p>
    </div>
  </div>

  <jsp:include page="footer.jsp"/>
</body>
</html>
