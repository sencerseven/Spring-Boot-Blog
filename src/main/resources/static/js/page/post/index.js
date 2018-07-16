$(document).ready(function() {


    var $form = $('#commentForm');
    var $postId = $('#commentPostID');

    if($postId != null){
        $form.submit(function(e){
            e.preventDefault();
            e.stopPropagation();

            var item = {
                commentUserCommand : {}
            };

            $.each($form.serializeArray(), function() {
                var split = this.name.split('.');
                if(split[1] != null)
                    item.commentUserCommand[split[1]] = this.value;
                else
                    item[this.name] = this.value;

            });


            console.log(JSON.stringify(item));
            jQuery.ajax({
                type:'post',
                url: $form.attr("action") + '/' + $postId.val(),
                contentType : 'application/json; charset=utf-8',
                dataType : 'json',
                data:JSON.stringify(item),
                success: function (data) {

                    swal({
                        title: '<h4>Yorumunuz onaylandıktan sonra gözükecektir..</h4>',
                        width: 600,
                        padding: '3em',
                        background: '#fff url(/images/trees.png)',
                        backdrop: 'rgba(0,0,123,0.4) url("/images/nyan-cat.gif") center left no-repeat'});

                   /** $("#comment").val("");
                    var yorumCount = parseInt($("#yorumlarCount").text());
                    yorumCount += 1 ;
                    $("#yorumlarCount").text(yorumCount);

                    console.log(data);
                    if(data.status == "Success"){
                        $("#commentLine").append('<li class="media" th:each="comment,key : ${post.getComment()}">'+
                            '<img class="d-flex mr-3 rounded-circle" src="images/user/user-5.png" alt="Generic placeholder image">'+
                            '<div class="media-body">'+
                        '<h4 class="media-heading"><a href="">'+data.object.commentUserCommand.email+'</a></h4>'+
                        '<p class="text-muted post-date">Jun 23, 2017, 11:45 am</p>'+
                        '<p>'+data.object.text+'</p></div>'+
                        '</li>');
                    }

                    */

                   $(this).closest('form').find(".form-control").val("");
                }

            });
        });
    }



});