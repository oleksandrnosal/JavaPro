$(document).ready(function(){
    loadPages();
    loadData(0);
});

function loadPages() {
    $.getJSON('/admin/count', function(data) {
        let pageCount = (data.count / data.pageSize) +
            (data.count % data.pageSize > 0 ? 1 : 0);

        for (let i = 1; i <= pageCount; i++) {
            $('#pages').append(
                $('<li>').attr('class', 'page-item').append(
                    $('<a>').attr('class', 'page-link').attr('id', i - 1)
                        .append('Page ' + i))
            );
        }
    });

    $("#pages").on("click", ".page-link", function(event) {
        loadData(event.target.id);
    });
}

function loadData(page) {
    $("#data > tbody").empty();

    $.getJSON('/admin/geo?page=' + page, function(data) {
        for (let i = 0; i < data.length; i++) {
            $('#data > tbody:last-child').append(
                $('<tr>')
                    .append($('<td>').append(data[i].ip))
                    .append($('<td>').append(data[i].city))
                    .append($('<td>').append(data[i].region))
                    .append($('<td>').append(data[i].country))
            );
        }
    });
}