    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    $(function(){
        selectAll();

    });

    function init_data(){
        proUntil.getSelData("lampCommon/getDimmingModelForSelect",$('.table_win').find('select[name ="dimmingmode"]'),{});
    }

    function init_btn() {
        //打开修改窗口
        $('.edit_button').unbind('click').click(function(){
            var lampId = $(this).attr('data_id');
            var url = "/updateTypeManage.do";
            $(".table_win").attr("action",url);
            /*灯具类型*/
            var lampType = document.getElementById("lamptypename");
            lampType.length=1;
            lampType[1] = new Option("普通路灯",1);
            lampType[2] = new Option("太阳能路灯",2);
            /*根据ID查询灯具信息*/
            $.post("/queryLampType.do",{id:lampId},function(data){
                var box = $('.table_win');
                var lamp = eval(data);
                lampType[lamp[0].lamptypename].selected = true;
                if(lamp[0].lamptypename == 1){
                    $("#spowerBox").css("display","none");
                    $("#bpowerBox").css("display","none");
                }else{
                    $("#spowerBox").css("display","block");
                    $("#bpowerBox").css("display","block");
                }
                $("#lamptypedes").val(lamp[0].lamptypedes);
                $("#lampModel").val(lamp[0].lampModel);
                $("#lMsg").val(lamp[0].lampModel);
                $("#power").val(lamp[0].power);
                $("#lampFactory").val(lamp[0].lampFactory);
                dimmingSelect(lamp[0].dimmingmode);
                $("#imgurl").attr("src",lamp[0].imgurl);
                $("#lId").val(lamp[0].id);
                $("#lPath").val(lamp[0].imgurl);
                $("#spower").val(lamp[0].spower);
                $("#bpower").val(lamp[0].bpower);

                box.show(manage_table_time);
            });
        });

        $('.delete_button').unbind('click').click(function () {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id', _this.attr('data_id'));
            table_delete_object = _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });
        //删除窗口 确定按钮
        $('.sure_delete_judge_win').unbind('click').click(function () {
           console.log("YYY");
            proUntil.request("../deleteTypeManage.do", {"id": $(this).attr('id')}, function (msg) {
                var msg = $.trim(msg);
                if ('y' == msg) {
                    $('.judge_win').hide(manage_table_time);
                    proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                    selectAll();
                } else if ('n' == msg) {
                    proUntil.alert_win(proUntil.OPERTION_FAIL);
                }
            });
        });



    }
    //查询全部
    function selectAll(){
        var box = $('.search_box');
        var typeName = box.find('input[name="typeName"]').val();
        $.ajax({
            type:"post",
            dataType:"json",
            url:"../getlampTypeManage",
            data:{"showNum":page_show_Num,"curPage":cur_page,"typeName":typeName},
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
                console.log("count_page:"+count_page);
                $.each(data.datas, function(index, val){
                    var lamptypename = val.lamptypename;
                  var ima=val.bpower;
                  var imb=val.spower;
                    if(1 == lamptypename){
                        lamptypename = "普通路灯";
                    } else if(2 == lamptypename){
                        lamptypename = "太阳能路灯";
                    }
                    if(imb!=null){
                        sp = imb;
                    }else{
                        sp= '--';
                    }
                    if(ima!=null){
                        bp = ima;
                    }else{
                        bp= '--';
                    }
                        imm= '<img src="images/road_default.png" width = "50" height = "50"/>'
                      /*  imm= '<img src="ima" width = "50" height = "50"/>'*/
                    html += '<tr>\n' +
                       /* '<td><input type="checkbox" value="'+val.id+'"></td>\n' +*/
                        '<td>' +lamptypename+'</td>\n' +
                    '<td>'+val.lamptypedes+'</td>\n' +
                    '<td>'+val.lampModel+'</td>\n' +
                    '<td>'+imm+'</td>\n' +
                    '<td>'+val.power+'</td>\n' +
                    '<td>'+val.lampFactory+'</td>\n' +
                    '<td>'+val.dimmingmode+'</td>\n' +
                    '<td>'+sp+'</td>\n' +
                    '<td>'+bp+'</td>\n' +
                    '<td>'+val.operator+'</td>\n' +
                    '<td>'+val.opertime+'</td>\n' +
                        '<td>\n' +
                        '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                        '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                        '</td>\n' +
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
                init_btn();
            }
        });
        return false;
    }

