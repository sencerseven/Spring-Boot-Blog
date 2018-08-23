$(document).ready(function() {

    $('.select2').select2();

    var searchButton = $("#searchButton");
    var searchForm = $("#searchForm");
    var showResult = $("#showResult");
    var item = {};
    searchButton.on("click",function (e) {
        e.preventDefault();
        


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
                    sLoader.success({message : 'İçerik Bulunamadı!'})
                }else{
                    dataTable(showResult,data);

                }

            }

        });

    });


    function dataTable(table,data){
        $('div[class^="box-body"]',table).html(dataHtml(data.object,true));
        $('#example1').DataTable();

        console.log(table.offset().top + 80);

        $("html, body").animate({scrollTop: table.offset().top + 200}, 450,function(){
            table.removeAttr("style");
            sLoader.stop();
        });



        $('i[id^="e"]',table).on('click',function () {
            var button = $(this);
            var data_id = button.attr('id').substr(1);
            sLoader.start();
            $.ajax({
                type: "POST",
                url: "/admin/comments/changestatus/" + data_id,
                contentType: 'application/json; charset=utf-8',
                dataType: "json",
                data: JSON.stringify(item),
                success: function(data) {
                    if(data.status != "success"){
                        sLoader.success({message : 'İçerik Bulunamadı!'})
                    }else{
                        dataTable(showResult,data);

                    }

                }

            });
        });

    }

});

    function dataHtml(data, rowTool=false){
        var table = '<table id="example1" class="table table-bordered table-striped">\n' +
            '                        <thead>\n' +
            '                        <tr>\n' +
            '                            <th>ID</th>\n' +
            '                            <th>Ad</th>\n' +
            '                            <th>Soyad</th>\n' +
            '                            <th>Email</th>\n' +
            '                            <th>Yazı</th>\n' +
            '                            <th>İçerik Başlık</th>\n' +
            '' + (rowTool == true ? '<th></th>' : '') + '\n'+
            '                        </tr>\n' +
            '                        </thead>\n' +
            '                        <tbody>\n';

            $.each(data,function () {
                table +=  '<tr>\n' +
                    '         <td>'+this.id+'</td>\n' +
                    '         <td>'+this.usersDetailCommand.firstName+'</td>\n' +
                    '         <td>'+this.usersDetailCommand.lastName+'</td>\n' +
                    '         <td>'+this.usersDetailCommand.email+'</td>\n' +
                    '         <td>'+this.text+'</td>\n' +
                    '         <td>'+this.postCommand.title+'</td>\n' +
                    '' + (rowTool == true ? '<td>'+createRowTool(this)+'</td>' : '') + '\n'+
                    '     </tr>\n' +
                    '\n';
            });

            table +='                </tbody>\n' +
            '                        <tfoot>\n' +
            '                        <tr>\n' +
            '                            <th>ID</th>\n' +
            '                            <th>Ad</th>\n' +
            '                            <th>Soyad</th>\n' +
            '                            <th>Email</th>\n' +
            '                            <th>Yazı</th>\n' +
            '                            <th>İçerik Başlık</th>\n' +
            '' + (rowTool == true ? '<th></th>' : '') + '\n'+
            '                        </tr>\n' +
            '                        </tfoot>\n' +
            '                    </table>';



       return table;

    }

function createRowTool(data) {
    nCloneTd = '<i id="e'+data.id+'" class="'+(data.active == 1 ? 'fa fa-pause' : 'fa fa-play')+'"></i>';
    return nCloneTd;
}