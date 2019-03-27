    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var cur_page = 1;//当前页数
    var orderBy = "uptime";//默认排序字段
    var sort = "desc";

    $(function() {
        $('.table_box').find('img').hover(function() {
            var _this = $(this);
            var add_distance = 50;
            var top = _this.offset().top - add_distance * 3;
            var left = _this.offset().left + add_distance;
            $('.win_show_img').find('img').attr('src', _this.attr('src'));
            $('.win_show_img').css({'left':left + 'px','top': top + 'px'});
            $('.win_show_img').show(300);
        }, function() {
            $('.win_show_img').hide(300);
        });
        selectAll();
    });

    function init_btn() {
        $('.edit_button').unbind('click').click(function(){

            proUntil.request("PlatFomSetting/getLogoInfoById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="name"]').val(val.name);
                box.show(manage_table_time);
            });
        });
    }

    //查询全部
    function selectAll(){
        var btn_obj = {title:"修改",className:"edit_button"};
        var data_arr = [];
        data_arr[0] = btn_obj;
        proUntil.commonSelect("PlatFomSetting/getLogoInfo",{},function(){
            init_btn();
        },null,null,data_arr);
    }

    //保存信息
    function saveData(){
        /*proUntil.saveData("PlatFomSetting/updateLogoInfo",$('.table_win'),function(){
            selectAll();
        });*/

        $('.table_win').ajaxSubmit({
            type : "POST",
            url : "PlatFomSetting/updateLogoInfo",
            dataType : "json",
            success : function(data) {
                alert(data);
            }
        });

    }