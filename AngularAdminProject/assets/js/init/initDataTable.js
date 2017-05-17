$(function () {
   $('.abc').on('click', function(){
            alert('ancanc');
        });
    $('#datatables').DataTable({
        "pagingType": "full_numbers",
        "lengthMenu": [[10, 25, 50, -1], [10, 25, 50, "All"]],
        responsive: true,
        language: {
            search: "_INPUT_",
            searchPlaceholder: "Search records",
        }

    });


    var table = $('#datatables').DataTable();
    
    //  Activate the tooltips
    $('[rel="tooltip"]').tooltip();
});