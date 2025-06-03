$(function () {
    validateRule();
});

$.validator.setDefaults({
    submitHandler: function () {
        //register();
    }
});

function validateRule() {
    var icon = "<i class='fa fa-times-circle'></i> ";
    $("#registerForm").validate({
            rules: {
                username: {
                    required: true,
                    minlength: 2
                },
                password: {
                    required: true,
                    minlength: 5
                },
                confirmPassword: {
                    required: true,
                    equalTo: "[name='password']"
                },
                phone: {
                    required: true,
                    isPhone: true
                }
            },
            messages: {
                username: {
                    required: icon + "请输入您的用户名",
                    minlength: icon + "用户名不能小于2个字符"
                },
                password: {
                    required: icon + "请输入您的密码",
                    minlength: icon + "密码不能小于5个字符",
                },
                confirmPassword: {
                    required: icon + "请再次输入您的密码",
                    equalTo: icon + "两次密码输入不一致"
                },
                phone: {
                    required: icon + "请输入手机号",
                    isPhone: icon + "请填写正确的11位手机号"
                }
            }
        }
    )
}
