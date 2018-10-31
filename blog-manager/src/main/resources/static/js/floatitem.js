function FloatItem(parent,id, text, onclose, onclick) {
    var that = this;
    this.id = id;
    this.text = text;
    this.onclose = onclose;
    this.onclick = onclick;
    this.element = document.createElement('floatitem');
    this.parent = parent;
    this.parent.appendChild(this.element);
    this.element.innerHTML =
        '<div class="floatitem">\n' +
        '    <button class="item">' + text + '</button>\n' +
        '    <button class="close"></button>\n' +
        '</div>';
    if (this.onclose != null) {
        this.element.getElementsByClassName('close')[0].onclick = function () {
            var r = eval(onclose + '("' + that.id + '","' + that.text + '")');
            if (r==false) return;
            that.remove();
        };
    }
    if (this.onclick != null) {
        this.element.getElementsByClassName('item')[0].onclick = function () {
            eval(onclick + '("' + that.id + '","' + that.text + '")')
        };
    }
    this.removeSlef=function () {
        that.parent.removeChild(that.element);
    }
}

var floatItem = {
    items:[],
    add: function (parent,id, text, onclose, onclick) {
        var item = new FloatItem(parent,id, text, onclose, onclick);
        this.items[id]=item;
    },
    remove:function (id) {
        var item = this.items[id];
        item.removeSlef();
    }
};
