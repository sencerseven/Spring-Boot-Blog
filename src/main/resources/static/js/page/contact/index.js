$(document).ready(function() {


    var $form = $('#commentForm');

        $form.submit(function(e){
            e.preventDefault();
            e.stopPropagation();

            var item = {
                usersDetailCommand : {}
            };

            $.each($form.serializeArray(), function() {
                var split = this.name.split('.');
                if(split[1] != null)
                    item.usersDetailCommand[split[1]] = this.value;
                else
                    item[this.name] = this.value;

            });

            jQuery.ajax({
                type:'post',
                url: $form.attr("action") + '/',
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                data:JSON.stringify(item),
                success: function (data) {

                    swal('Teşekkürler',
                    'Mesajınız Tarafımıza iletilmiştir',
                        'success');

                    $("form :input[class='form-control']").val('');

                   $(this).closest('form').find(".form-control").val("");
                }

            });
        });




});