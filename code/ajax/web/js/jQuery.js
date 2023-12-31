function jQuery(selector) {
    if (typeof selector === "string") {
        // if (selector.charAt(0) === "#") {
        //     domObj = document.getElementById(selector.substring(1));
        //     return new jQuery();
        // }
        domObj = document.querySelector(selector)
        return new jQuery()
    }
    //页面加载完成后,注册回调函数
    if (typeof selector === "function") {
        window.onload = selector
    }
    //封装innerHtml
    this.html = function (htmlText) {
        domObj.innerHTML = htmlText
    }
    //封装onclick
    this.click = function (fun) {
        domObj.onclick = fun
    }
    //封装下拉菜单option改变事件
    this.change = function(fun){
        domObj.onchange = fun
    }
    //封装文本框的值
    this.val = function (v) {
        if (v !== undefined) {//如果形参没有传参, 是默认为undefined数据类型的
            domObj.value = v
        }
        return domObj.value
    }
    /**
     * method: "post",
     * url: "/ajax/ajaxrequest11",
     * async: true,
     * data: "username=" + input.value,
     * success: function (responseText) {
     *      $("#div1").html(responseText)
     *      alert("封装ajax请求成功")
     * }
     */
    jQuery.ajax = function (jsonArgs) {
        //发送post请求
        //1.
        let xhr = new XMLHttpRequest()
        //2.
        xhr.onreadystatechange = function () {
            if (this.readyState === 4) {
                if (this.status === 200) {
                    // let jsonObj = JSON.parse(this.responseText)
                    // div.innerHTML = jsonObj.username
                    jsonArgs.success(this.responseText)
                }
            }
        }
        //3.
        if (jsonArgs.method.toUpperCase() === "POST") {
            xhr.open("POST", jsonArgs.url, jsonArgs.async)
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded")
            //4.
            xhr.send(jsonArgs.data)
        }
        if (jsonArgs.method.toUpperCase() === "GET") {
            xhr.open("GET", jsonArgs.url + "?" + jsonArgs.data, jsonArgs.async)
            //4.
            xhr.send()
        }

    }
}

$ = jQuery

//细节, 为了让静态方法生效(js)
new jQuery()