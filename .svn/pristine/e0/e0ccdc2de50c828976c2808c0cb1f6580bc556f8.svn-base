    $(function(){
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd"
        });
        var winTable = $('.table_win');
        proUntil.winLinkSelect(winTable.find('select[name ="areaId"]'),winTable.find('select[name ="roadid"]'));
        proUntil.getSelData("lampCommon/getRepairPeopleForSelect",$('.table_win').find('select[name ="uid"]'),{},'<option value = "">选择人员</option>');
        selectAll();
    });

    //查询全部
    function selectAll(){
        proUntil.commonSelect("Maintain/getRoutingData",{cname:$('.search_box').find('input[name="peopleName"]').val()},function(){
                init_btn();
        });
    }

    //保存信息
    function saveData(){
        var box = $('.table_win');
        var start = box.find('input[name="startime"]').val();
        var end = box.find('input[name="endtime"]').val();
        /*时间校验*/
        if (CompareDate(start,end)){
            proUntil.alert_win('开始时间不能晚于结束时间');
            return;
        }
        proUntil.saveData("Maintain/saveRoutingData",$('.table_win'),function(){
            selectAll();
        });
    }

    function init_btn(){
        $('.edit_button').click(function(){
            proUntil.request("Maintain/getRoutingDataById",{"id": $(this).attr('data_id')},function(data){
                var val = JSON.parse(data)[0];
                var box = $('.table_win');
                proUntil.ROAD_ID = val.roadid;
                box.find('input[name="id"]').val(val.id);
                box.find('select[name="areaId"]').val(val.areaid);
                box.find('select[name="areaId"]').change();
                box.find('select[name="uid"]').val(val.uid);
                box.find('input[name="startime"]').val(val.startime);
                box.find('input[name="endtime"]').val(val.endtime);
                box.find('input[name="checkdescribe"]').val(val.checkdescribe);
                box.show(manage_table_time);
            });
        });
        proUntil.init_common_btn();
    }

    function CompareDate(d1,d2)
    {
        return ((new Date(d1.replace(/-/g,"\/"))) > (new Date(d2.replace(/-/g,"\/"))));
    }