﻿    $(function() {
        $('.form-control').datepicker({
            language: 'zh-CN',
            format: "yyyy-mm-dd",
            viewMode:'days'
        });
        var searchBox = $('.search_box');
        proUntil.searchLinkSelect(searchBox.find('select[name ="areaId"]'),searchBox.find('select[name ="roadId"]'),searchBox.find('select[name ="lineId"]'),searchBox.find('select[name ="lampId"]'));
        $('.excel_btn').click(function(){
            window.location.href="Maintain/exportOperationLampData?"+$('.search_box').serialize();
        });
        selectAll();
    });

    function selectAll(){
        var searchBox = $('.search_box');
        var param = {
            areaId :searchBox.find('select[name ="areaId"]').val(),
            roadId :searchBox.find('select[name ="roadId"]').val(),
            lineId :searchBox.find('select[name ="lineId"]').val(),
            lampId :searchBox.find('select[name ="lampId"]').val(),
            startDate:searchBox.find('input[name ="startDate"]').val(),
            endDate:searchBox.find('input[name ="endDate"]').val(),
            typeId:1
        };
        //proUntil.commonSelect("Maintain/getOperationLogData",param,null,null,null,"no_btn");
    }
