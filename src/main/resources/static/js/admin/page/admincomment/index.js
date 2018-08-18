$(document).ready(function() {

    $('.select2').select2();

    var searchButton = $("#searchButton");
    var searchForm = $("#searchForm");
    var showResult = $("#showResult");

    searchButton.on("click",function (e) {
        e.preventDefault();
        
        var item = {};

        $.each(searchForm.serializeArray(),function () {
            item[this.name] = this.value;
        });

        sLoader.start();
        jQuery.ajax({
            type: 'post',
            url: searchForm.attr("action"),
            contentType: 'application/json; charset=utf-8',
            dataType: 'json',
            data: JSON.stringify(item),
            success: function (data) {
                if(data.status != "success"){

                }else{
                    dataTable(showResult,data);

                }

            }

        });

    });


    function dataTable(table,data){
        $('div[class^="box-body"]',table).html(dataHtml(data.object));
        $('#example1').DataTable();

        console.log(table.offset().top + 80);

        $("html, body").animate({scrollTop: table.offset().top + 200}, 450,function(){
            table.removeAttr("style");
            sLoader.stop();
        });

    }

});

    function dataHtml(data){
        var table = '<table id="example1" class="table table-bordered table-striped">\n' +
            '                        <thead>\n' +
            '                        <tr>\n' +
            '                            <th>ID</th>\n' +
            '                            <th>Yazı</th>\n' +
            '                            <th>Kategori Tanımı</th>\n' +
            '                        </tr>\n' +
            '                        </thead>\n' +
            '                        <tbody>\n';

            $.each(data,function () {
                table +=  '<tr>\n' +
                    '         <td>'+this.id+'</td>\n' +
                    '         <td>'+this.text+'</td>\n' +
                    '         <td>Edinburgh</td>\n' +
                    '     </tr>\n' +
                    '\n';
            });

            table +='                </tbody>\n' +
            '                        <tfoot>\n' +
            '                        <tr>\n' +
            '                            <th>ID</th>\n' +
            '                            <th>Yazı</th>\n' +
            '                            <th>Kategori Tanımı</th>\n' +
            '                        </tr>\n' +
            '                        </tfoot>\n' +
            '                    </table>';



       return table;

    }