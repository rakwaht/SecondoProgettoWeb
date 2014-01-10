//aggiungo dinamicamente input file alla crea post
function add_upload_file() {
    $('form input:file').last().after($('<br /><input type="file" name="file" class="file" onchange="add_upload_file();" />'));
}
;


//creo datatable per moderator page
$(document).ready(function() {
    $('#moderator_table_id').dataTable({
        "aaSorting": [[4, "desc"]],
        "oLanguage": {
            "sLengthMenu": "Display _MENU_ records per pagina",
            "sZeroRecords": "Nessun gruppo presente",
            "sInfo": "Mostro da _START_ a _END_ di _TOTAL_ ",
            "sInfoEmpty": "Mostro da 0 a 0 di 0",
            "sInfoFiltered": "(filtrati da _MAX_ record totali)",
            "sSearch": "Filtra"
        }
    }
    );
});

$(document).ready(function() {
    //creo popover show gruppo
    $(".qrpopup").popover({
        html: true,
        trigger: 'hover',
        placement: 'right'
    });

    //toggle partecipanti
    $("#members").click(function() {
        $("#members-toggle").toggle("slow");
    });
    
    //toggle files
    $("#files").click(function() {
        $("#files-toggle").toggle("slow");
    });
});