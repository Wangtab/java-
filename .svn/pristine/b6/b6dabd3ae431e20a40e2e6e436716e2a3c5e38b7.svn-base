     var win_map = null;
     var table_map = null;
     var cur_page = 1;//当前页数
     var count_page = 1;//总共页数
     var page_show_Num = 8//每页显示多少条数据
     var color = ["#CD3700","#B03060","#556B2F","#4A708B","#8B008B","#CDAD00"];
     var orderBy = "recordTime";//排序字段
     var sort = "desc";
     $(function() {
         selectAll();
         var searchBox = $('.search_box');
         var areaSelect = searchBox.find('select[name ="selectAreaName"]');
         var roadSelect = searchBox.find('select[name ="selectRoadName"]');
         //获取区域信息
         proUntil.getSelData("lampCommon/getAreaNameForSelect",areaSelect,{},'<option value = "">请选择区域</option>');
         areaSelect.change(function(){
             var areaId = areaSelect.val();
             if(proUntil.strIsEmpty(areaId)){
                 return;
             }
             proUntil.getSelData("lampCommon/getRoadNameForSelect",roadSelect,{areaId:areaId},'<option value = "">请选择道路</option>');
         });
     });

     //查询全部
     function selectAll(){
         var searchBox = $('.search_box');
         $.ajax({
             type:"post",
             dataType:"json",
             url:"../getammStatusData",
             data:{"areaId":searchBox.find('select[name ="selectAreaName"]').val(),
                 "roadId":searchBox.find('select[name ="selectRoadName"]').val(),
                 "showNum":page_show_Num,
                 "curPage":cur_page,"orderBy":orderBy,"sort":sort},
             success: function (data){
                 var table = $('.table_box').find('table');
                 var frist_tr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
                 var html = "";
                 if('n' == data){
                     table.html('');
                     check_table_data();
                     return;
                 }
                 data = data[0];
                 count_page = Math.ceil(parseInt(data.count)/page_show_Num);
                 $.each(data.datas, function(index, val){
                     html += '<tr>\n' +
                         '<td>'+val.areaName+'</td>\n' +
                         '<td>'+val.road_name+'</td>\n' +
                         '<td>'+val.name+'</td>\n' +
                         '<td>'+val.c_name+'</td>\n' +
                         '<td>'+val.num+'</td>\n' +
                         '<td>'+val.record_time+'</td>\n' +
                         '</tr>';

                 });
                 table.html(frist_tr + html);
                 $("#controllerPage").html('');
                 page({
                     id:'controllerPage',
                     nowNum: cur_page,
                     allNum: count_page
                 });
                 //网络数据分页点击
                 $("#controllerPage").find("a").unbind('click').click(function(){
                     var _this = $(this);
                     var curpage = _this.attr('cur');
                     cur_page = parseInt(curpage);
                     selectAll();
                 });
                 check_table_data();
                 deal_table_th();
             }
         });
         return false;
     }

     function deal_table_th(){
         $('.table_box').find('th').click(function(){
             var _this = $(this);
             var th_sort = _this.attr('sort');
             $('.table_box').find('th').removeAttr('sort');
             $('.table_box').find('th').find('img').remove();
             if(proUntil.strIsEmpty(sort)){
                 th_sort = "desc";
                 _this.html(_this.html() + '<img src="images/th_ico_down.png">');
             } else if ("asc" == sort){
                 th_sort = "desc";
                 _this.html(_this.html() + '<img src="images/th_ico_down.png">');
             } else if("desc" == sort){
                 th_sort = "asc";
                 _this.html(_this.html() + '<img src="images/th_ico_up.png">');
             }
             _this.attr('sort',th_sort);
             orderBy =  _this.attr("orderBy");
             sort = th_sort;
             selectAll();
         });
     }

     function exportExcel(){
         var searchBox = $('.search_box');
         var areaId = searchBox.find('select[name ="selectAreaName"]').val();
         var roadId = searchBox.find('select[name ="selectRoadName"]').val();
         window.location.href = "../ammStateExportExcel?areaId=" + areaId
             + "&roadId=" + roadId;
     }











