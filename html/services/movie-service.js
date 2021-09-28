class MovieService {
	constructor() { }

	static getAll() {
		BaseService.getAll(MovieService.path)
			.then(function(movieDtoList) {
				var content = '<table class="aca-fill">';
				content += '<tr>'
					+ '<th scope="col">Id</th>'
					+ '<th scope="col">Name</th>'
					+ '<th scope="col">Length (minutes)</th>'
					+ '<th scope="col">Release Date</th>'
					+ '<th scope="col">Detail</th>'
					+ '<th scope="col">Action</th>'
					+ '</tr>';
				jQuery.each(movieDtoList, function(i, val) {
					content += '<tr>'
						+ '<td>' + val.id + '</td>'
						+ '<td>' + val.name + '</td>'
						+ '<td>' + val['length'] + '</td>'
						+ '<td>' + BaseService.formatDate(val.releaseDate) + '</td>'
						+ '<td><button id="btnDetail-' + val.id + '" class="btn btn-warning" onclick="MovieService.detail(' + val.id + ')">detail</button></td>'
						+ '<td><button id="btnDelete-' + val.id + '" class="btn btn-danger" onclick="MovieService.delete(' + val.id + ')">delete</button></td>'
						+ '</tr>';
				});
				content += '</table>';

				$('#saveForm').trigger('reset');
				$('#saveForm').hide();
				$(".table").empty();
				$(".table").append(content);
				$('.table').show();
			});
	}

	static post(formData) {
		BaseService.post(MovieService.path, formData)
			.then(function(movieDto) {
				$('#saveForm').trigger('reset');
				$('#saveForm').hide();

				MovieService.getAll();
			});
	}

	static put(formData) {
		BaseService.put(MovieService.path, formData)
			.then(function(movieDto) {
				window.alert("Movie updated.")
				$('#saveForm').trigger('reset');
				$('#saveForm').hide();

				MovieService.getAll();
			});
	}

	static delete(id) {
		BaseService.delete(MovieService.path, id)
			.then(function(result) {
				window.alert('Movie ' + id + ' removed.')

				MovieService.getAll();
			});
	}


	static detail(id) {
		BaseService.get('/movies/v1', id)
			.then(function(movieDto) {
				document.getElementById('movieId').value = movieDto.id;
				document.getElementById('name').value = movieDto.name;
				document.getElementById('movieLength').value = movieDto['length'];
				document.getElementById('releaseDate').value = BaseService.formatDate(movieDto.releaseDate);
				$('.table').empty();
				$('.table').hide();
				$('#saveForm').show();
			});
	}
}

MovieService.path = '/movies/v1';