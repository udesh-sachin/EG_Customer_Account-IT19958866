$(document).ready(function() {

	$("#alertSuccess").hide();
	$("#alertError").hide();

});

function validateItemForm() {
	// CODE
	if ($("#billName").val().trim() == "") {
		return "Insert Customer Name.";
	}

	// PRICE-------------------------------
	if ($("#address").val().trim() == "") {
		return "Insert Customer Address.";
	}
	if ($("#date").val().trim() == "") {
		return "Insert Customer Bill Date.";
	}

	let units = $("#units").val().trim();
	if (!$.isNumeric(units)) {
		return "Insert a numerical value for Units.";
	}
	
	// UNIT PRICE------------------------
	let unitPrice = $("#unitPrice").val().trim();
	if (!$.isNumeric(unitPrice)) {
		return "Insert a numerical value for Unit Price.";
	}

	return true;

}

//Save Func
function onBillSaveComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully saved.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while saving.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while saving..");
		$("#alertError").show();
	}
	$("#hidCustomerIDSave").val("");
	$("#formBill")[0].reset();
}


// Save Btn
$(document).on("click", "#btnSave", function(event) {
	// Clear alerts---------------------  
	$("#alertSuccess").text("");
	$("#alertSuccess").hide();
	$("#alertError").text("");
	$("#alertError").hide();

	// Form validation-------------------  
	var status = validateItemForm();
	if (status != true) {
		$("#alertError").text(status);
		$("#alertError").show();
		return;
	}

	// If valid------------------------  
	var type = ($("#hidCustomerIDSave").val() == "") ? "POST" : "PUT";

	$.ajax(
		{
			url: "BillServlet",
			type: type,
			data: $("#formBill").serialize(),
			dataType: "text",
			complete: function(response, status) {
				onBillSaveComplete(response.responseText, status);
			}
		});
});


// UPDATE CLICK
$(document).on("click", ".btnUpdate", function(event) {
	$("#hidCustomerIDSave").val($(this).closest("tr").find('#hidCustomerIDUpdate').val());
	$("#billCode").val($(this).closest("tr").find('td:eq(0)').text());
	$("#billName").val($(this).closest("tr").find('td:eq(0)').text());
	$("#address").val($(this).closest("tr").find('td:eq(1)').text());
	$("#date").val($(this).closest("tr").find('td:eq(2)').text());
	$("#units").val($(this).closest("tr").find('td:eq(3)').text());
	$("#unitPrice").val($(this).closest("tr").find('td:eq(4)').text());
	$("#tax").val($(this).closest("tr").find('td:eq(5)').text());
});


//Delete Func
function onItemDeleteComplete(response, status) {
	if (status == "success") {
		var resultSet = JSON.parse(response);
		if (resultSet.status.trim() == "success") {
			$("#alertSuccess").text("Successfully deleted.");
			$("#alertSuccess").show();
			$("#divItemsGrid").html(resultSet.data);
		} else if (resultSet.status.trim() == "error") {
			$("#alertError").text(resultSet.data);
			$("#alertError").show();
		}
	} else if (status == "error") {
		$("#alertError").text("Error while deleting.");
		$("#alertError").show();
	} else {
		$("#alertError").text("Unknown error while deleting..");
		$("#alertError").show();
	}
}


// DELETE Click
$(document).on("click", ".btnRemove", function(event) {
	$.ajax(
		{
			url: "BillServlet",
			type: "DELETE",
			data: "billID=" + $(this).data("customid"),
			dataType: "text",
			complete: function(response, status) {
				onItemDeleteComplete(response.responseText, status);
			}
		});
});
