    $(function() {
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
        proUntil.getSelData("lampCommon/getMenuDataForSelect",$('.work_content_box').find('select[name ="menuId"]'),{},'<option value = "">请选择模块</option>');
        selectAll();
    });

    function selectAll() {
        var searchBox = $('.search_box');
        var param = {
            menuId : searchBox.find('select[name ="menuId"]').val(),
            kindId : searchBox.find('select[name ="kindId"]').val(),
            startDate: searchBox.find('input[name ="startDate"]').val(),
            endDate: searchBox.find('input[name ="endDate"]').val()
        };
        proUntil.commonSelect("Maintain/getOperationLog",param,null,null,null,"no_btn");
    }