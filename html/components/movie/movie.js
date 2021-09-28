class Purchase {
    constructor() { }

	static load(){
		PurchaseService.getTotals();
		
	    $('#btnShow').click(function () {
	        PurchaseService.getAll();
	    });
	
	    $('#btnAdd').click(function () {
	        $('.table').empty();
	        $('.table').hide();
	        $('#saveForm').show();
	    })
	
	    $('#saveForm').submit(function (event) {
	        event.preventDefault();
	
	        var id = document.getElementById('purchaseId').value;
			var dateTime = document.getElementById('dateTime').value + 'T00:00:00Z';
			var totalNumberOfItems = document.getElementById('totalNumberOfItems').value;
			var totalPrice = document.getElementById('totalPrice').value;
			var totalDiscount = document.getElementById('totalDiscount').value;
			var status = document.getElementById('status').value;
			var customerId = document.getElementById('customerId').value;
	
	        var formData = {
	            'dateTime': dateTime,
				'totalNumberOfItems': totalNumberOfItems,
				'totalPrice': totalPrice,
				'totalDiscount': totalDiscount,
				'status': status,
				'customerId': customerId
	        };
	
			var action;
	        if (id) {
				formData.id = id;
	            action = PurchaseService.put;
	        } else {
	            action = PurchaseService.post;
	        }
			action(JSON.stringify(formData));
	    });
	}
}

