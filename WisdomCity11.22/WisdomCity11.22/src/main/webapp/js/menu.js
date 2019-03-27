    var area_is_full_screen = false;
    var menu_id = null;

        $(function() {
            showLocationTime();
            setInterval(showLocationTime,1000);
            init_menu_data();
            init_menu();
            init_location();
            //区域全屏
            $('#area_full_screen').click(function() {
                var window_width = $(window).width();
                var window_height = $(window).height();
                if(!area_is_full_screen){
                    $('.top').css('display','none');
                    $('.location').hide();
                    $('.menu').hide();
                    $('.work_content').css('padding-left', '0');
                    $("#main_iframe").attr({"height":window_height,"width":window_width});
                    $(this).css('top','30px');
                    area_is_full_screen = true;
                } else {
                    $('.top').css('display','block');
                    $('.location').show();
                    $('.menu').show();
                    var w = $('.menu').width();
                    $('.work_content').css('padding-left', w +'px');
                    $(this).css('top','190px');
                    $("#main_iframe").attr({"height":'100%',"width":'100%'});
                    area_is_full_screen = false;
                }
            });
        });

        //打开全屏
        function fullScreen(el) {
            var rfs = el.requestFullScreen || el.webkitRequestFullScreen || el.mozRequestFullScreen || el.msRequestFullScreen,
                wscript;
         
            if(typeof rfs != "undefined" && rfs) {
                rfs.call(el);
                return;
            }
         
            if(typeof window.ActiveXObject != "undefined") {
                wscript = new ActiveXObject("WScript.Shell");
                if(wscript) {
                    wscript.SendKeys("{F11}");
                }
            }
        }

        //退出全屏
        function exitFullScreen(el) {
            var el= document,
                cfs = el.cancelFullScreen || el.webkitCancelFullScreen || el.mozCancelFullScreen || el.exitFullScreen,
                wscript;
         
            if (typeof cfs != "undefined" && cfs) {
              cfs.call(el);
              return;
            }
         
            if (typeof window.ActiveXObject != "undefined") {
                wscript = new ActiveXObject("WScript.Shell");
                if (wscript != null) {
                    wscript.SendKeys("{F11}");
                }
          }
        }



    function init_menu(){
        //当点击左侧菜单时 位置栏上面响应的给出点击模块的顺序名称
        $('.menu').find('a').click(function() {
            var _this = $(this);
            var parent_li = _this.parents('li');
            var son_location = _this.text();
            var menu_box = _this.parents('.menu_box');
            var parent_location = menu_box.find('p').text();
            var location_object = $('.loc_box');
            var parent_url = menu_box.find('a').eq(0).attr('to_url');
            $('.menu').find('li').removeClass('menu_now_cur_li');
            parent_li.addClass('menu_now_cur_li');
            location_object.find('a').eq(2).text(parent_location).attr('to_url', parent_url);
            location_object.find('a').eq(3).text(son_location).attr('to_url', _this.attr('to_url'));
            $('.location').attr('now_menu',_this.html());
            menu_id = _this.attr("data_id");
            $('#main_iframe').attr('src', _this.attr('to_url'));
        });

       $('.menu_box').hover(function() {
            $(this).stop(true,true).find('ul').css('display', 'block').fadeIn(200).animate({"left":"190px","opacity":"1"},300);
        }, function() {
             $(this).stop(true,true).find('ul').css({'display':'none',"left":'150px','opacity':'0'});
        });

        $('#fullScreen_btn').click(function(event) {
            var content = document.getElementById('bodyd'); 
            var _this = $(this);
            fullScreen(content);
        });
    }

    //初始化位置标题栏
    function init_location(){
        $('.location').find('.loc_box').find('a').click(function(event) {
            var _this = $(this);
            var index = _this.index();
            if(1 == index) location.href = _this.attr('to_url');
            else if(2 == index){

            }else{
                $('#main_iframe').attr('src',_this.attr('to_url'));
            }
           
        });
    }

    /**
     * 初始化菜单信息
     */
    function init_menu_data(){
        $.ajax({
            type:"post",
            dataType:"text",
            url:"../menu/getMenuData",
            async: false,
            success: function (data){
                data = JSON.parse(data);
                var html = "";
                for (var i = 0; i < data.length; i++) {
                    html += '<div class="menu_box">';
                    var top_menu = data[i];
                    var list = top_menu.list;
                    html +='<p><img src="'+top_menu.img+'"/>'+top_menu.cName+'<b></b></p>';
                    html += '<ul>';
                    for (var j = 0; j < list.length; j++) {
                        var son_menu =  list[j];
                        html+='<li><a href="javascript:void(0);" to_url = "'+son_menu.curl+'" data_id = "'+son_menu.id+'">'+son_menu.cName+'</a></li>'
                    }
                    html += '</ul>';
                    html +='</div>';
                }
                $('.menu').html(html);
            }
        });
    }