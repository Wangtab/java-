
    $(function() {
        selectAll();
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
    });

    function selectAll(){
        var searchBox = $('.search_box');
        proUntil.commonSelect("Maintain/queryLogUserList",
            {startDate:searchBox.find('input[name="startDate"]').val(),endDate:searchBox.find('input[name="endDate"]').val()},
            null,null,null,"no_btn");
    }

