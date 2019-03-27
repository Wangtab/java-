
$(function() {
   $(".dimming_light").slider({
        formatter: function (value){
            return  value + "%";
        }
   });
});

function isEmpty(str){
    if (str !== null || str !== undefined || str !== '') {
        return true;
    }
    return false;
}