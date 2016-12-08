/**
 * Created by Red on 2016/12/2.
 */

var Message = function (arg) {
    this.draw = function () {
        return function () {
            var $message;
            $message = $($('.message_template').clone().html());
            $message.addClass(arg.message_side).find('.text').html(arg.text);
            $('.messages').append($message);
            return setTimeout(function () {
                return $message.addClass('appeared');
            }, 0);
        }();
    };
};

var getMessageText = function () {
    return $('.message_input').val();
};

var showMessage = function (text, side) {
    if (text.trim() === '') {
        return;
    }
    $('.message_input').val('');
    side = side || "left";
    var message = new Message({
        text: text,
        message_side: side
    });
    message.draw();
    var $messages = $('.messages');
    return $messages.animate({ scrollTop: $messages.prop('scrollHeight') }, 300);
};

$(function () {
    $('.send_message').click(function (e) {
        showMessage(getMessageText(), 'right');
    });
    $('.message_input').keyup(function (e) {
        if (e.which === 13) {
            showMessage(getMessageText(), 'right');
        }
    });
    setTimeout(function () {
        showMessage('小伙子');
    }, 1000);
    setTimeout(function () {
        showMessage('哈喽啊');
    }, 2000);
    setTimeout(function () {
        showMessage('卧槽');
    }, 3000);
});