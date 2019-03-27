    $(function() {
        selectAll();
    });

    //查询全部
    function selectAll(){
        proUntil.commonSelect("Maintain/getBuildStandard",{cname:$('.search_box').find('input[name="buildname"]').val()},function(){
            init_btn();
        });
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("Maintain/getBuildStandardById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                box.find('input[name="id"]').val(val.id);
                box.find('input[name="buildname"]').val(val.buildname);
                box.find('input[name="builddescribe"]').val(val.builddescribe );
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }

    //保存信息
    function saveData(){
        proUntil.saveData("Maintain/saveBuildStandard",$('.table_win'),function(){
            selectAll();
        });
    }

    function moreInfo() {
        $(".set_pwd_button").click(function(){
            $("#moreInfo_win").show(manage_table_time);
        });
        $(".close").click(function(){
            $("#moreInfo_win").hide(manage_table_time);
        });
    }

    //文件上传
    function sub() {
        $("#queryForm").ajaxSubmit({
            type : "POST",
            url : "upload.action",
            dataType : "json",
            success : function(data) {
                alert(data);
            }
        });
    }