$(function () {
    $("#search-btn").click(
        function () {
            const key = $("#search-key").val();
            window.open("search.html?key=" + key);
        }
    )
})