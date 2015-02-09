function doQuery() {
	document.form1.submit();
}

function save() {
	document.getElementById("modalFrame").contentWindow.save();
}

function closeModalDialog() {
	$("#modalDialog").modal("hide");
}

function checkAll(obj) {
	$("input[type=checkbox]").each(function() {
		$(this).prop("checked", obj.checked);
	});
}

function isEmpty(obj) {
	if (typeof(obj) == "undefined") {
		return true;
	}
	if (obj == null) {
		return true;
	}
	if (obj == '') {
		return true;
	}
	return false;
}

$(function() {
	$('#modalDialog').on('hidden.bs.modal', function (e) {
		  doQuery();
	});
});
