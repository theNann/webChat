/**
 * Created by pyn on 2016/12/15.
 */

$(function () {
    currentPage = 1;
    $('.send_message').click(function (e) {
        sendMsg();
    });
    $('.message_input').keyup(function (e) {
        if (e.which === 13) {
            sendMsg();
        }
    });
});

var request = function(strName) {
    var href = window.document.location.href;
    var pos = href.indexOf("=");
    return href.substring(pos+1,href.length);
};

var getMessageText = function () {
    return $('.message_input').val();
};

function sendMsg() {
    if(getMessageText() == "") {
        alert("消息不能为空！");
    } else {
        var account = request("account");
        var shuoshuo = getMessageText();
        $.ajax({
            url: "/shuoshuo",
            dataType: "text",
            type: "post",
            async: true,
            data: {account: account, shuoshuo: shuoshuo},
            success: function (data) {
                if (data == "yes") {
                    alert("发表成功");
                }
            },
            error: function (data) {
                alert("发表失败");
            }
        });
        $(".message_input").val("");
    }
}

function getJson(currentPage) {
    $('ul li').remove();
    var htmlobj = $.ajax({
        url: "/zone",
        async: true,
        data: {page: currentPage},
        success: function (data) {
            var htmlobj = jQuery.parseJSON(data);
            $.each(htmlobj,function (entryId,entry) {
                if(entry['type'] == "yes") {
                    drawZones(entry['account'], entry['shuoshuo'], entry['date']);
                } else {
                    alert("已是最后一页");
                }
            })
        },
        error: function () {
            alert("error");
        }
    });
}

function doPrepage() {
    if(currentPage == 1) {
        alert("已是第一页");
    } else {
        currentPage -= 1;
        getJson(currentPage);
    }
}

function doNextpage() {
    currentPage += 1;
    getJson(currentPage);
}

function drawZones(account, shuoshuo, time) {
    return function () {
        var $message;
        $message = $($('.message_template').clone().html());
        $message.find('.text').html(shuoshuo);
        $message.find('.avatar_text').html(account);
        $message.find('.time').html(time);
        $('.messages').append($message);
        return setTimeout(function () {
            return $message.addClass('appeared');
        }, 0);
    }();
}