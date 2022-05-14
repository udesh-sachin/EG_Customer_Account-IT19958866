<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@page import="com.CusAccount"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Bill details</title>
<link rel="stylesheet" href="Views/bootstrap.min.css">
<script src="Components/jquery-3.4.1.min.js"></script>
<script src="Components/main.js"></script>
</head>
<body>

	<div class="container">
		<div class="card">
			<div class="card-header bg-info text-light d-flex align-items-center">
				<h1>Account Details</h1>
			</div>
			<div class="card-body">
				<form id="formAccount" name="formAccount" method="post" action="CusAccount.jsp">
					Account Number: <input id="AcNumber" name="AcNumber" type="text"
						class="form-control form-control-sm"> <br> Name: <input
						id="Name" name="Name" type="text"
						class="form-control form-control-sm"> <br> NIC: <input
						id="NIC" name="NIC" type="text"
						class="form-control form-control-sm"> <br> Phone : <input
						id="Phone" name="Phone" type="text"
						class="form-control form-control-sm"><br> Email:
					: <input id="Email" name="Email" type="text"
						class="form-control form-control-sm"> <br>
					<div class="text-right">
						<input id="btnSave" name="btnSave" type="button" value="SAVE"
							class="btn btn-primary"> <input type="hidden"
							id="hidCustomerIDSave" name="hidCustomerIDSave" value="">
					</div>
				</form>
				<div id="alertSuccess" class="alert alert-success" style="margin-top: 15px"></div>
				<div id="alertError" class="alert alert-danger" style="margin-top: 15px"></div>
				<div id="divItemsGrid" class="table-responsive">
					<%
					CusAccount CusAccount1 = new CusAccount();
					out.print(CusAccount1.readAccount());
					%>
				</div>
			</div>

		</div>
	</div>
</body>
</html>