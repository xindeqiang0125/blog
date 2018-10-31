var params = getRequestParams();
var id = params.articleId;

function getRequestParams() {
    var url = location.search;  //"?username=gdf&password=gfg"
    var Request = new Object();
    if (url.indexOf("?") != -1) {
        var str = url.substr(1);　//截取?之后的字符串
        strs = str.split("&");//将字符串以符号"&"分隔成两段
        for (var i = 0; i < strs.length; i++) {
            Request[strs[i].split("=")[0]] = unescape(strs[i].split("=")[1]);//将每一段字符串又以"="分为两段
        }
    }
    return Request;
}

function initContent() {
    $.get('/articles/' + id, {}, function (o) {
        $('#content').html(o.content);
        $('#title').html(o.title);
        $('#views').html(o.pageViews);
        $('#time').html(o.updateTime);
    })
}

function initTags() {
    $.get('/articles/' + id + '/tags', {}, function (o) {
        for (var i in o) {
            $('#tags').append(
                '<span class="separator">&#x2F;</span>\n' +
                '<a href="#">' + o[i].name + '</a>');
        }
    })
}

function initPreNextBar() {
    $.get('/articles/' + id + '/pre_next', {}, function (o) {
        if (o.pre==null){
            $('#btn_pre').hide();
        } else {
            $('#btn_pre').show();
            $('#btn_pre').text('上一篇:'+o.pre.title);
            $('#btn_pre').attr('href','/content.html?articleId='+o.pre.id);
        }
        if (o.next==null){
            $('#btn_next').hide();
        } else {
            $('#btn_next').show();
            $('#btn_next').text('下一篇:'+o.next.title);
            $('#btn_next').attr('href','/content.html?articleId='+o.next.id);
        }
    })
}