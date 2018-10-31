//获取标签
function initTags() {
    $.get('/tags', {}, function (o) {
        for (var key in o) {
            $('#tags').append('<li><a href="#articles" onclick="searchByTag(' + o[key].id + ')">' + o[key].name + '</a></li>')
        }
    })
}

//通过标签搜索
function searchByTag(tagId) {
    initArticles('/articles/tag_' + tagId, 1);
}

//通过标题搜索
function searchByTitle() {
    var title = $('#searchbox').val();
    initArticles('/articles/' + title, 1);
}

//获取最新文章
function initLatestArticles() {
    $.get('/articles/latest', {}, function (o) {
        for (var key in o) {
            $('#latestArticles').append('<li><a href="/content.html?articleId=' + o[key].id + '">' + o[key].title + '</a></li>')
        }
    })
}

//获取热门文章
function initHotArticles() {
    $.get('/articles/hot', {}, function (o) {
        for (var key in o) {
            $('#hotArticles').append('<li><a href="/content.html?articleId=' + o[key].id + '">' + o[key].title + '</a></li>')
        }
    })
}

var pageInfo;
var articlesUrl;

//获取文章
function initArticles(url, pageNum) {
    articlesUrl = url;
    $('#articles').html('');
    $.get(url + '/page_' + pageNum, {}, function (o) {
        for (var key in o.result) {
            var element = o.result[key];
            $('#articles').append(
                '<article class="blog-teaser">\n' +
                '    <header>\n' +
                '        <h3><a href="/content.html?articleId=' + element.id + '">' + element.title + '</a></h3>\n' +
                '        <span class="meta">' + element.updateTime + '</span>\n' +
                '        <hr/>\n' +
                '    </header>\n' +
                '</article>');
        }
        pageInfo = o;
        ensureBtn();
    });
}

function nextPage() {
    initArticles(articlesUrl, pageInfo.nextPage);
}

function prePage() {
    initArticles(articlesUrl, pageInfo.prePage);
}

function ensureBtn() {
    if (pageInfo.nowPage <= 1) {
        $('#btn_pre').hide();
    } else {
        $('#btn_pre').show();
    }
    if (pageInfo.nowPage >= pageInfo.pageCount) {
        $('#btn_next').hide();
    } else {
        $('#btn_next').show();
    }

}