class Movie {
	constructor() { }

	static load() {
		$('#btnShow').click(function() {
			MovieService.getAll();
		});

		$('#btnAdd').click(function() {
			$('.table').empty();
			$('.table').hide();
			$('#saveForm').show();
		})

		$('#saveForm').submit(function(event) {
			event.preventDefault();

			var id = document.getElementById('movieId').value;
			var name = document.getElementById('name').value;
			var movieLength = document.getElementById('movieLength').value;
			var releaseDate = document.getElementById('releaseDate').value + 'T00:00:00Z';

			var formData = {
				'name': name,
				'length': movieLength,
				'releaseDate': releaseDate
			};

			var action;
			if (id) {
				formData.id = id;
				action = MovieService.put;
			} else {
				action = MovieService.post;
			}
			action(JSON.stringify(formData));
		});
	}
}

