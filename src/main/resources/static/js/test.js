$().ready(function () {
    var cell = $("#cell");
    var loading = weui.loading("loading");
    $.ajax({
        type: "post",
        url: "getPrintStatus",
        success: function (res) {
            if (res.code && res.code === "0") {
                var data = res.data;
                if (data.length === 0) {
                    cell.append("<div class='weui-cell'><p>您的账号下暂时没有设备，无法测试打印。</p></div>")
                } else {
                    $.each(data, function (index, obj) {
                        var no = obj.device_no;
                        cell.append("<div class='weui-cell'><div class='weui-cell__bd'><p>" + no + "</p></div>" +
                            "<div class='weui-cell__ft'>" +
                            "<a class='weui-btn weui-btn_primary' onclick='testPrint(" + no + ")'>测试打印</a>" +
                            "</div></div>");
                    });
                }
            } else {
                weui.alert(res.msg);
            }
        },
        error : function () {
            weui.alert("系统服务异常，请重试");
        },
        complete: function () {
            loading.hide();
        }
    });
});

function testPrint(no) {
    var loading = weui.loading("正在打印");
    $.ajax({
        type: "post",
        url: "testPrint",
        data: {
            "no": no
        },
        success: function (res) {
            if (res.code && res.code === "0") {
                weui.alert("发送打印成功");
            } else {
                weui.alert(res.msg);
            }
        },
        error : function () {
            weui.alert("系统服务异常，请重试");
        },
        complete: function () {
            loading.hide();
        }
    });
}