


function saveDate(startDate, endDate) {
    sessionStorage.setItem("startDate", startDate);
    sessionStorage.setItem("endDate", endDate);

    console.log(date);
}

function getDate() {
    return {
        startDate: sessionStorage.getItem("startDate"),
        endDate: sessionStorage.getItem("endDate")
    };
}