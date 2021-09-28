class MovieService {
    constructor() {}

    static getAll() {
        BaseService.getAll(MovieService.path)
            .then(function (movieDtoList) {
                var content = '<table class="aca-fill">';
                content += '<tr>'
                    + '<th scope="col">Id</th>'
                    + '<th scope="col">Date</th>'
                    + '<th scope="col">Total Number</th>'
                    + '<th scope="col">Total Price</th>'
                    + '<th scope="col">Total Discount</th>'
                    + '<th scope="col">Status</th>'
					+ '<th scope="col">Customer ID</th>'
                  	+ '<th scope="col">Detail</th>'
                    + '<th scope="col">Action</th>'
                    + '</tr>';
                jQuery.each(movieDtoList, function (i, val) {
                    content += '<tr>'
                        + '<td>' + val.id + '</td>'
                        + '<td>' + MovieService.formatDate(val.dateTime) + '</td>'
                        + '<td>' + val.totalNumberOfItems + '</td>'
                        + '<td>' + val.totalPrice + '</td>'
                        + '<td>' + val.totalDiscount + '</td>'
                        + '<td>' + val.status + '</td>'
						+ '<td>' + val.customerId + '</td>'
                        + '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="MovieService.detail(' + val.id + ')">detail</button></td>'
                        + '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="MovieService.delete(' + val.id + ')">delete</button></td>'
                        + '</tr>';
                });
                content += '</table>';

                $('#saveForm').trigger('reset');
                $('#saveForm').hide();
				MovieService.setHtmlContent('.table', content);
                $('.table').show();
            });
    }

    static post(formData) {
        BaseService.post(MovieService.path, formData)
            .then(function (movieDto) {
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();

                MovieService.getAll();
				MovieService.getTotals();
            });
    }

    static put(formData) {
        BaseService.put(MovieService.path, formData)
            .then(function (movieDto) {
                window.alert("L'ordine è stato Aggiornato")
                $('#saveForm').trigger('reset');
                $('#saveForm').hide();

                MovieService.getAll();
				MovieService.getTotals();
            });
    }

    static delete(id) {
        BaseService.delete(MovieService.path, id)
            .then(function (result) {
                window.alert('Ordine ' + id + ' eliminato con successo!')

                MovieService.getAll();
				MovieService.getTotals();
            });
    }


    static detail(id) {
        BaseService.get('/movies/v1', id)
            .then(function (movieDto) {
                document.getElementById('movieId').value = movieDto.id;
                document.getElementById('dateTime').value = MovieService.formatDate(movieDto.dateTime);
                document.getElementById('totalNumberOfItems').value = movieDto.totalNumberOfItems;
                document.getElementById('totalPrice').value = movieDto.totalPrice;
                document.getElementById('totalDiscount').value = movieDto.totalDiscount;
                document.getElementById('status').value = movieDto.status;
				document.getElementById('customerId').value = movieDto.customerId;

                $('.table').empty();
                $('.table').hide();
                $('#saveForm').show();
            });
    }
}

MovieService.path = '/movies/v1';