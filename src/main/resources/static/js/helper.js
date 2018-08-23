(function () {
    return sLoader = {
        defaults: {
            message: 'yükleniyor',
            call: function () {}
        },
        timer: function (param, settings) {
            if (param != "stop") {
                var timer = setTimeout(function () {
                    sLoader.stop(param, settings);
                }, 2000);
            } else {
                clearTimeout(timer);
            }
        },
        start: function (settings) {
            settings = $.extend({}, sLoader.defaults, settings);
            this.timer("stop", settings);
            $('.page-loader').remove();
            if ($(".sl-backdrop").length == 0) {
                $("body").append('<div class="sl-backdrop fade in"></div>');
            }
            $("body").append('<div id="sLoader" class="page-loader"><span id="sLoaderMessage">' + settings.message + '...</span>' +
                '<div class="bottom-border">' +
                '<div class="color-bands green"></div>' +
                '<div class="color-bands purple"></div>' +
                '<div class="color-bands red"></div>' +
                '<div class="color-bands blue"></div>' +
                '</div>' +
                '</div>');

        },
        stop: function (id, settings) {
            id = id || "#sLoader";
            settings = settings || sLoader.defaults;
            $(id).fadeOut(250, function () {
                $(id).remove();
                $(".sl-backdrop").remove();
                settings.call();
            });
        },
        del: function (id) {
            id = id || "#sLoader";
            $(id).remove();
            $(".sl-backdrop").remove();
        },
        error: function (settings) {
            settings = $.extend({}, sLoader.defaults, settings);
            $('#sLoaderMessage').html(settings.message);
            $('#sLoader').attr("id", "sLoaderError");
            this.timer('#sLoaderError', settings);
        },
        success: function (settings) {
            settings = $.extend({}, sLoader.defaults, settings);
            $('#sLoaderMessage').html(settings.message);
            $('#sLoader').attr("id", "sLoaderSuccess");
            this.timer('#sLoaderSuccess', settings);
        },
        unsuccess: function (settings) {

            if (!$('#sLoader').length > 0) {
                this.start();
            }
            settings = $.extend({}, sLoader.defaults, settings);
            $('#sLoaderMessage').html(settings.message);
            $('#sLoader').attr("id", "sLoaderUnsuccess");

            $(".sl-backdrop").click(function (e) {
                sLoader.stop('.page-loader:not("#sLoader")', settings);
            });
        }
    };
}).call(this);
