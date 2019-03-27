    $(function() {
        showLocationTime();
        setInterval(showLocationTime,1000);
        init_model_style();
    });

    function init_model_style(){
        $('.models').find('img').each(function(index, el) {
            var i = index + 1;
            if(i % 3 == 0){
                $(el).css('margin-right', '0');
            }
        });
    }