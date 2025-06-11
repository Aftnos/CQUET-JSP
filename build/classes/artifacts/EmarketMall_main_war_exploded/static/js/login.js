$(function () {
    validateKickout();
    validateRule();
    /*$('.imgcode').click(function() {
        var url = ctx + "captcha/captchaImage?type=" + captchaType + "&s=" + Math.random();
        $(".imgcode").attr("src", url);
    });*/
});

$.validator.setDefaults({
    submitHandler: function () {

    }
});

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#signupForm").validate({
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: icon + "请输入您的用户名",
            },
            password: {
                required: icon + "请输入您的密码",
            }
        }
    })
}

function validateKickout() {
    if (getParam("kickout") == 1) {
        layer.alert("<font color='red'>您已在别处登录，请您修改密码或重新登录</font>", {
                icon: 0,
                title: "系统提示"
            },
            function (index) {
                //关闭弹窗
                layer.close(index);
                if (top != self) {
                    top.location = self.location;
                } else {
                    var url = location.search;
                    if (url) {
                        var oldUrl = window.location.href;
                        var newUrl = oldUrl.substring(0, oldUrl.indexOf('?'));
                        self.location = newUrl;
                    }
                }
            });
    }
}

function getParam(paramName) {
    var reg = new RegExp("(^|&)" + paramName + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if (r != null) return decodeURI(r[2]);
    return null;
}