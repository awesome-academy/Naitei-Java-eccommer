 $(document).ready(function() {
        $('.category-item').click(function() {
            const category = $(this).data('category');
            const baseUrl = 'http://localhost:8080/ecommerce/';

            const url = baseUrl + '#category/' + encodeURIComponent(category);

            $.ajax({
                url: url,
                type: 'GET',
                success: function(data) {
                    
                    $('#selected-category').text(category);

                    $('#product-list').html(data);
                    
                    window.location.href = url; 
                },
                error: function(error) {
                    console.log(error);
                }
            });
        });
    });