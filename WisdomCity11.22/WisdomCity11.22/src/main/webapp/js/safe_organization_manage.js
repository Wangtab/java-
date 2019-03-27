    var orgizationName = null;
    var HAS_CHILD_NODE = 20;
    var HAS_NO_CHILD_NODE  = 10;
    var IMG_ON = 'images/tree_node+.png';
    var IMG_OFF = 'images/tree_node-.png';
    proUntil.OPERTION_BAD = '请先选择左侧组织树节点';
    var orderBy = "operTime";//排序字段
    var sort = "desc";
    var PARENT_ID = null;//
    $(function(){
        init_orgTree();
    });
    function init_orgTree(){
        proUntil.request('safe/getOrganizationTree',{},function(data){
            data = JSON.parse(data);
            dealTreeHtmlJoin(data,$('.left_content_tree'));
            dealNodeTreeClick($('.tree_node').eq(0));

        });
    }
    function dealNodeTreeClick(nodeList){
        nodeList.show(manage_table_time);
        nodeList.find('p[has_child="'+HAS_CHILD_NODE+'"]').unbind("click").click(function(){
            addTreeClick($(this));
        });
        nodeList.find('p[has_child="'+HAS_NO_CHILD_NODE+'"]').unbind("click").click(function(){
            var _this = $(this);
            var table = $('.table_box').find('table');
            var headTile = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            var html = '';
            table.html(headTile + html);
            check_table_data();
            var win_box = $('.table_win');
            win_box.find('input[name="pid"]').val(_this.attr('data_id'));
        });
    }
    function dealTreeHtmlJoin(dataList,TreeJQuery){
        var html = '<div class="tree_node">';
        $.each(dataList,function(index,val){
            var img_html = "";
            if(HAS_NO_CHILD_NODE == val.has_child){
                img_html = '<img src="images/tree_node-.png"/>';
            }else {
                img_html = '<img src="images/tree_node+.png"/>';
            }
            html += '<p data_id = '+val.id+' has_child = '+val.has_child+' data_name = "'+val.org_name+'" mark = "shrink">'
                + img_html + val.org_name +'</p>';
        });
        TreeJQuery.html(html);
    }
    function getOrganizationTreeById(data_id,_this){
        proUntil.request('safe/getOrganizationTreeById',{"pid":data_id,"orderBy":orderBy,"sort":sort},function(data){
            data = JSON.parse(data);
            var html = '<div class="tree_node">';
            $.each(data,function(index,val){
                var img_html = "";
                if(HAS_NO_CHILD_NODE == val.has_child){
                    img_html = '<img src="images/tree_node-.png"/>';
                }else {
                    img_html = '<img src="images/tree_node+.png"/>';
                }
                html += '<p data_id = '+val.id+' has_child = '+val.has_child+' data_name = "'+val.org_name+'" mark = "shrink">'
                    + img_html + val.org_name +'</p>';
            });
            html+= '</div>';
            _this.after(html);
            dealNodeTreeClick(_this.nextAll('.tree_node'));
            _this.find('img').attr('src', 'images/tree_node-.png');
            _this.attr('mark',"spread");
            init_table_data(data);
            deal_table_th();
        });
    }

    function addTreeClick(obj){
        var _this = obj;
        var data_id = _this.attr('data_id');
        PARENT_ID = data_id;
        orgizationName = _this.attr('data_name');
        var mark = _this.attr('mark');
        var hasChild = _this.attr('has_child');
        if(HAS_NO_CHILD_NODE == hasChild){
            _this.find('img').attr("src","images/tree_node-.png");
            return;
        }
        _this.find('img').attr("src","images/tree_node+.png");
        if("shrink" == mark){
            var len = _this.nextAll('.tree_node').length;
            if(len == 0){
                getOrganizationTreeById(data_id,_this);
            }else{
                dealNodeTreeClick(_this.nextAll('.tree_node'));
                proUntil.request('safe/getOrganizationTreeById',{"pid":data_id},function(data){
                    data = JSON.parse(data);
                    init_table_data(data);
                });
            }
            _this.find('img').attr('src', 'images/tree_node-.png');
            _this.attr('mark',"spread");
        }else{
           _this.nextAll('.tree_node').hide(manage_table_time);
           _this.attr('mark',"shrink");
            proUntil.request('safe/getOrganizationTreeById',{"pid":data_id},function(data){
                data = JSON.parse(data);
                init_table_data(data);
            });
        }
    }

    function init_table_data(data){
        var table = $('.table_box').find('table');
        var headTile = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
        var html = '';
        $.each(data, function(index, val){
            html += '<tr>\n' +
                '<td>'+val.org_name+'</td>\n' +
                '<td>'+val.org_des+'</td>\n' +
                '<td>'+val.real_name+'</td>\n' +
                '<td>'+val.oper_time+'</td>\n' +
                '<td>' +
                '<a title = "修改" data_id = "'+val.id+'" class = "edit_button" href="javascript:void(0);"></a>\n' +
                '<a title = "删除" data_id = "'+val.id+'" class = "delete_button" href="javascript:void(0);"></a>\n' +
                '</td>\n' +
                '</tr>';
        });
        table.html(headTile + html);
        check_table_data();
        init_btn();
        deal_table_th();
    }

    function init_btn(){
        $('.add_btn').unbind("click").click(function(){
            var win_box = $('.table_win');
            if(null==orgizationName||""==orgizationName){
                proUntil.alert_win(proUntil.OPERTION_BAD);
                win_box.hide();
            }else{
                proUntil.clear_form_data(win_box);
                win_box.find('input[name="pid"]').val(PARENT_ID);
                win_box.find('input[name="showParentName"]').val(orgizationName);
            }


        });
        $('.edit_button').click(function(){
            $.ajax({
                type:"post",
                dataType:"text",
                url:"safe/getOrganizationById",
                data:{"id": $(this).attr('data_id')},
                success: function (data){
                    var val = JSON.parse(data)[0];
                    var box = $('.table_win');
                    box.find('input[name="id"]').val(val.id);
                    box.find('input[name="showParentName"]').val(orgizationName);
                    box.find('input[name="pid"]').val(val.pid);
                    box.find('input[name="orgName"]').val(val.org_name);
                    box.find('input[name="orgDes"]').val(val.org_des);
                    box.show(manage_table_time);
                }
            });
        });
        $('.delete_button').unbind('click').click(function() {
            var _this = $(this);
            $('.sure_delete_judge_win').attr('id',_this.attr('data_id'));
            table_delete_object =  _this.parents('tr');
            $('.judge_win').show(manage_table_time);
        });
        $('.sure_delete_judge_win').unbind('click').click(function(){
            proUntil.request("safe/delOrganizationById",{"id":$(this).attr('id')},function(data){
                data = JSON.parse(data)[0];
                if(data.operation == "success"){
                    proUntil.alert_win(proUntil.OPERTION_SUCCESS);
                }else if(data.operation == "hasChild"){
                    proUntil.alert_win('请先删除该组织下的组织信息');
                }else{
                    proUntil.alert_win(proUntil.OPERTION_FAIL);
                }
                $('.judge_win').hide(manage_table_time);
                $('.left_content_tree').html('');
                init_orgTree();
            });
        });
    }
    function saveData(){
        proUntil.saveData("safe/saveOrganization",$(".table_win"),function(){
            init_orgTree();
        });
    }
    function deal_table_th(pid){
        $('.table_box').find('th').not(':last').click(function(){
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
            proUntil.request('safe/getOrganizationTreeById',{"pid":PARENT_ID,"orderBy":orderBy,"sort":sort},function(data){
                data = JSON.parse(data);
                init_table_data(data)
            });
        });
    }