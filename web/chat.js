/**
 * Created by Red on 2016/12/2.
 */

var Message = function (arg) {
    this.draw = function () {
        return function () {
            var $message;
            $message = $($('.message_template').clone().html());
            $message.addClass(arg.message_side).find('.text').html(arg.content);
            $message.find('.avatar_text').html(arg.account);
            $('.messages').append($message);
            return setTimeout(function () {
                return $message.addClass('appeared');
            }, 0);
        }();
    };
};

var drawTime = function() {
    currentTime = new Date();
    if(displayTime === true || (currentTime.getTime() - lastTime.getTime() >= 60000) ){
        if(displayTime === true) {
            displayTime = false;
        }
        lastTime = currentTime;
        return function () {
            var $message;
            $message = $($('.time_template').clone().html());
            $message.find('.time').html(currentTime.toLocaleDateString()+" "+currentTime.toLocaleTimeString());
            $('.messages').append($message);
            return setTimeout(function () {
                return $message.addClass('appeared');
            }, 0);
        }();
    }
};

var showMessage = function (account, content) {
    $('.message_input').val('');
    var side;
    if(account == request("account")) {
        side = "right";
    } else {
        side = "left";
    }
    var message = new Message({
        account: account,
        content: content,
        message_side: side
    });
    // alert("account: " + account);
    message.draw();
    var $messages = $('.messages');
    return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
};

var getMessageText = function () {
    return $('.message_input').val();
};

function load() {
    ws = null;
    ws = new WebSocket("ws://localhost:8080/websocket");
    displayTime = true;
    lastTime = new Date();
    ws.onmessage = function(evt) {
        drawTime();
        var message = JSON.parse(evt.data);
        var type = message.type;
        if(type === "chatMessage") {
            var account = message.account;
            var content = message.content;
            showMessage(account, content);
        } else if(type === "onlineNumMessage") {
            showMessage("系统", "当前在线人数：" + message.onlineNum);
        }
    };
    ws.onclose = function(evt) {
        alert("connection closed");
    };

    ws.onopen = function(evt) {
        $.globalMessenger().post({
            message: "welcome "+request("account")+" to chat room...",//提示信息
            type: 'success',//消息类型。error、info、success
            hideAfter: 3,//多长时间消失
            showCloseButton:true,//是否显示关闭按钮
            hideOnNavigate: true //是否隐藏导航
        });
    };
}

function request(strName) {
    var href = window.document.location.href;
    var pos = href.indexOf("=");
    return href.substring(pos+1,href.length);
}

function sendMsg() {
    if(getMessageText() == "") {
        alert("消息不能为空！");
    } else {
        var message = {};
        message.type = "chatMessage";
        message.account = request("account");
        message.content = getMessageText();
        ws.send(JSON.stringify(message));
    }
}
//
// function doFriendsCircle() {
//     window.open("friendsCircle.jsp?account="+request("account"));
// }

$(function () {
    $('#frindsCircleLink').attr("href","friendsCircle.jsp?account="+request("account"));
    $('.send_message').click(function (e) {
        sendMsg();
    });
    $('.message_input').keyup(function (e) {
        if (e.which === 13) {
            sendMsg();
        }
    });
});

