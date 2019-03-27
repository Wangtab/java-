$(function(){
    init_data();
});
function init_data(){
    var searchBox = $('.search_box');
    var areaSelect = searchBox.find('select[name ="selectAreaName"]');
    var roadSelect = searchBox.find('select[name ="selectRoadName"]');
    var roadLineSelect = searchBox.find('select[name ="selectRoadLine"]');
    var lampSelect = searchBox.find('select[name ="selectLampNum"]');
    //获取区域信息
    proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
    areaSelect.change(function(){
        var areaId = areaSelect.val();
        if(proUntil.strIsEmpty(areaId)){
            roadSelect.html('<option value = "">请选择道路</option>');
            roadLineSelect.html('<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯编号</option>');
            return;
        }
        proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
        roadLineSelect.html('<option value = "">请选择线路</option>');
        lampSelect.html('<option value = "">请选择单灯编号</option>');
    });

    //道路选择显示线路
    roadSelect.change(function(){
        var roadId = roadSelect.val();
        if(proUntil.strIsEmpty(roadId)){
            roadLineSelect.html('<option value = "">请选择线路</option>');
            lampSelect.html('<option value = "">请选择单灯编号</option>');
            return;
        }
        proUntil.getSelData("lampCommon/getRoadLineNameForSelect",roadLineSelect,{roadId:roadId},'<option value = "">请选择线路</option>');
        lampSelect.html('<option value = "">请选择单灯编号</option>');
    });

    //线路选择显示单灯编号
    roadLineSelect.change(function(){
        var lineId = roadLineSelect.val();
        if(proUntil.strIsEmpty(lineId)){
            lampSelect.html('<option value = "">请选择单灯编号</option>');
            return;
        }
        proUntil.getSelData("lampCommon/getLampNumForSelect",lampSelect,{lineId:lineId},'<option value = "">请选择单灯编号</option>');
    });
}