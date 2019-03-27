    var cur_page = 1;//当前页数
    var count_page = 1;//总共页数
    var page_show_Num = 10;//每页显示多少条数据
    var orderBy = "record_time";//排序字段
    var sort = "desc";
    $(function() {
        selectAll();
    });

    function selectAll(){
        proUntil.request("getPlatformDianSumPrice",{curPage:cur_page,showNum:page_show_Num,orderBy:orderBy,sort:sort},function(data) {
            var table = $('.table_box').find('table');
            var fristTr = '<tr>' + table.find('tr').eq(0).html() + '</tr>';
            if ('n' == data) {
                table.html(fristTr);
                check_table_data();
                return;
            }

            data = JSON.parse(data)[0];
            var html = "";
            count_page = Math.ceil(parseInt(data.count) / page_show_Num);
            $.each(data.data, function (index, val) {
                html += '<tr>\n' +
                    '<td>' + val.price + '</td>\n' +
                    '<td>' + val.energy + '</td>\n' +
                    '<td>' + val.sum_price  + '</td>\n' +
                    '<td>' + val.record_time + '</td>\n' +
                    '<td>' + val.org_name + '</td>\n' +
                    '</tr>';
            });
            table.html(fristTr + html);
            $("#controllerPage").html('');
            page({
                id: 'controllerPage',
                nowNum: cur_page,
                allNum: count_page
            });

            //网络数据分页点击
            $("#controllerPage").find("a").unbind('click').click(function () {
                var _this = $(this);
                var curpage = _this.attr('cur');
                cur_page = parseInt(curpage);
                selectAll();
            });
            deal_table_th();
        });
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
