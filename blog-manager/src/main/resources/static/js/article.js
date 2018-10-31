layui.use(['layer','table'], function () {
    var layer = layui.layer;
    var table = layui.table;
    //第一个实例
    var tableIns = table.render({
        elem: '#article_table'
        , toolbar: '#article_table_toolbar'
        , title: '博客表'
        , limits: [5, 10, 20]
        , url: '/articles' //数据接口
        , page: true //开启分页
        , parseData: function (res) { //res 即为原始返回的数据
            var data = res.data;
            for (var index in data.data) {
                data.data[index].published = data.data[index].published == true ? "已发布" : "未发布";
            }
            return data;
        }
        , cols: [[ //表头
            {type: 'checkbox'}
            , {field: 'id', title: 'ID', sort: true, hide: true}
            , {field: 'title', title: '标题', edit: 'text'}
            , {field: 'pageViews', title: '浏览数', sort: true}
            , {field: 'published', title: '发布状态'}
            , {field: 'updateTime', title: '更新时间'}
        ]]
    });

    function onClickEdit(selected) {
        if (selected.length != 1) {
            layer.msg('只能选择一条数据');
            return;
        }
        layer.open({
            type: 2,
            area: ['800px', '550px'],
            content: ['/write?articleId=' + selected[0].id, 'no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
    }

    function onClickUpdate(selected) {
        if (selected.length != 1) {
            layer.msg('只能选择一条数据');
            return;
        }
        layer.open({
            type: 2,
            title:'编辑',
            area: ['600px', '400px'],
            content: ['/update_form?articleId=' + selected[0].id, 'no'] //这里content是一个URL，如果你不想让iframe出现滚动条，你还可以content: ['http://sentsin.com', 'no']
        });
    }

    function onClickAdd() {
        layer.prompt({
            formType: 2,
            value: '初始值',
            title: '请输入标题',
            area: ['400px', '100px'] //自定义文本域宽高
        }, function(value, index, elem){
            $.post('/article/add',{title:value},function (e) {
                layer.msg(e.data);
                if (e.code==1){
                    layer.close(index);
                    table.reload('article_table');
                }
            })

        });
    }

    function onClickDelete(selected){
        if (selected.length < 1) {
            layer.msg('未选择数据');
            return;
        }
        var ids=[];
        for (var index in selected) {
            ids.push(selected[index].id);
        }
        var message = '确认删除所选内容?';
        layer.confirm(message, function(index){
            //do something
            $.post('/article/delete',{ids:ids},function (e) {
                layer.msg(e.data);
                if (e.code==1){
                    layer.close(index);
                    table.reload('article_table');
                }
            })
        });
    }

    function onClickSearch(){
        table.reload('article_table',{
            url: '/articles/search'
            ,where: {title:$('#search_text').val()} //设定异步数据接口的额外参数
        });
    }

    table.on('toolbar(article_table)', function (obj) {
        var selected = table.checkStatus(obj.config.id).data;
        switch (obj.event) {
            case 'add':
                onClickAdd();
                break;
            case 'delete':
                onClickDelete(selected);
                break;
            case 'update':
                onClickUpdate(selected);
                break;
            case 'edit':
                onClickEdit(selected);
                break;
            case 'search':
                onClickSearch();
                break;
        }
    });
});