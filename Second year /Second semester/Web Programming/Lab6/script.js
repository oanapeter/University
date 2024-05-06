function filterRooms() {
    var form = document.getElementById("filter-form");
    var formData = new FormData(form);

    fetch("search_rooms.php", {
        method: "POST",
        body: formData,
    })
    .then(response => response.json())
    .then(data => {
        updateTable(data.rooms);
        updatePagination(data);
    });
}

function updateTable(rooms) {
    var table = document.querySelector("table");
    while (table.rows.length > 1) {
        table.deleteRow(1);
    }

    rooms.forEach(room => {
        var row = table.insertRow();
        row.insertCell(0).innerText = room.hotel_name;
        row.insertCell(1). innerText = room.category;
        row.insertCell(2). innerText = room.type;
        row.insertCell(3). innerText = room.price;
        row.insertCell(4). innerHTML = `<a href='add_reservation.php?room_id=${room.room_id}'>Book</a>`;
    });
}

function updatePagination(data) {
    var pagination = document.querySelector(".pagination");
    pagination.innerHTML = "";

    if (data.previous_page) {
        pagination.innerHTML += `<a href='#' onclick='loadPage(${data.previous_page})'>Previous</a>`;
    }

    for (var i = 1; i <= data.total_pages; i++) {
        pagination.innerHTML += `<a href='#' onclick='loadPage(${i})'>${i}</a>`;
    }

    if (data.next_page) {
        pagination.innerHTML += `<a href='#' onclick='loadPage(${data.next_page})'>Next</a>`;
    }
}
