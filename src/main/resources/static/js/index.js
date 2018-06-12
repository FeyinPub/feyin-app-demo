$().ready(function (){
    var sel = $("#select");
    $.ajax({
        type: "post",
        url: "getMemberPrinter",
        success: function (res) {
            if (res.code && res.code === "0") {
                var data = res.data;
                if (data.length === 0) {
                    weui.alert("您未拥有飞印打印机");
                    var btn = $("#btn");
                    btn.attr("disabled", true);
                    btn.addClass("weui-btn_plain-primary");
                    btn.removeClass("weui-btn_primary");
                } else {
                    $.each(data, function (index, obj) {
                        sel.append("<option value='" + obj + "'>" + obj + "</option>");
                    });
                }
            } else {
                weui.alert(res.msg);
            }
        },
        error : function () {
            weui.alert("系统服务异常，请重试");
        }
    });

});

function printing() {
    var no = $("#select").find("option:selected").val();
    if (!no) {
        weui.alert("打印机为空");
        return;
    }
    var msg = $("#msg").val();
    if (!msg) {
        weui.alert("打印信息为空");
        return;
    }
    $.ajax({
        type: "post",
        url: "printTest",
        data: {
            "no" : no,
            "msg" : msg
        },
        success: function (res) {
            if (res.code && res.code === "0") {
                weui.alert("发送打印成功");
            } else {
                weui.alert("发送打印失败，" + res.msg);
            }
        },
        error : function () {
            weui.alert("系统服务异常，请重试");
        }
    });
}