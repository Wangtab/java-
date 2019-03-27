    function theTime(){
    var weekDayLabels = new Array("星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六");
    var now = new Date();
    var year = now.getFullYear();
    var month = now.getMonth() + 1;
    var day = now.getDate();
    var hour=now.getHours();   //获取小时
    var minues;  // 获取分钟
    if(now.getMinutes() < 10){
        minues = "0" + now.getMinutes();
    }else{
        minues = now.getMinutes();
    }
    var second;  //  获取秒
    if(now.getSeconds() < 9){
        var num = now.getSeconds()+1;
        second = "0" + num;
    }else{
        second = now.getSeconds()+1;
    }
        document.getElementById("now_date").innerHTML = year + "年" + month + "月" + day + "日 "+"&nbsp;&nbsp;"+hour+":" + minues +":"+ second +"&nbsp;&nbsp;"+ weekDayLabels[now.getDay()]
}


    $(function() {
        setInterval("theTime()",1000);
    });