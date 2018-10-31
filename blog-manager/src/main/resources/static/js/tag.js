function initTags() {
    $('#container').html('');
    $.post('/tags', {}, function (e) {
        if (e.code == 1) {
            var data = e.data;
            for (var index in data) {
                floatItem.add(
                    $('#container')[0],
                    data[index].id,
                    data[index].name,
                    'clickClose',
                    'click');
            }
        }
    });
}

function onClickAdd() {
    layer.prompt({
        title: '请输入标签名称'
    },function(value, index, elem){
        $.post('/tags/add',{name:value},function (e) {
            layer.msg(e.data);
            layer.close(index);
            initTags();
        });
    });
}

function clickClose(id,text) {
    layer.confirm('确认删除'+text+'?',function (index) {
        $.post('/tags/delete',{id:id},function (e) {
            layer.msg(e.data);
            if (e.code==1){
                layer.close(index);
                floatItem.remove(id)
            }
        });

    });
    return false;

}
function click(id,text) {

    layer.prompt({
        title: '修改标签名称',
        value: text
    },function(value, index, elem){
        $.post('/tags/update',{id:id,name:value},function (e) {
            layer.msg(e.data);
            layer.close(index);
            initTags();
        });
    });
}
var layer;

layui.use('layer', function(){
    layer = layui.layer;


    initTags();
});
