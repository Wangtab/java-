    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "operTime";//排序字段
    var sort = "desc";

    $(function() {
        selectAll();
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd"
        });
        proUntil.getSelData("lampCommon/getDeviceTypeDataForSelect",$('.search_box').find('select[name="cname"]'),{},'<option value = "">请选择类别</option>');
        proUntil.getSelData("lampCommon/getDeviceTypeDataForSelect",$('.table_win').find('select[name="catId"]'),{},'<option value = "">请选择类别</option>');
    });

    //保存信息
    function saveData(){
        proUntil.saveData("Maintain/saveStockData",$('.table_win'),function(){
            selectAll();
        });
    }
    //查询全部
    function selectAll(){
        var box = $('.search_box');
        var cname = box.find('select[name="cname"]').val();
        var equipName = box.find('input[name="equipName"]').val();
        proUntil.commonSelect("Maintain/getStockData",{cname:cname,equipName:equipName},function(){
            init_btn();
        });
    }
    function init_btn() {
        $('.edit_button').click(function () {
            proUntil.request("Maintain/getStockDataById", {"id": $(this).attr('data_id')}, function (data) {
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('select[name="catId"]').val(val.catId);
                box.find('input[name="equipName"]').val(val.equipName);
                box.find('input[name="totime"]').val(val.totime);
                box.find('input[name="stocknum"]').val(val.stocknum);
                box.find('input[name="node"]').val(val.node);
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }
