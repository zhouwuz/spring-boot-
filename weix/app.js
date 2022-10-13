// app.js
const app = getApp()
App({
    onLaunch() {

        
        this.loginWithCode()
        this.getTreeInfo()
  


    },



    globalData: {
        userInfo: null,
        sessionId: null,
        expiredTime: null,
        treeInfo: null,
        treeAck: false

    },

    

    loginWithCode: function () {
        var that = this;
        wx.login({
            success(res) {
                if (res.code) {
                    console.log("code:" + res.code);
                    wx.request({
                        url: 'http://localhost:1316/aaaa/loginWithCode',
                        method: "GET",
                        header: {
                            'content-type': 'application/json'
                        },
                        data: {
                            code: res.code
                        },
                        success(res) {
                            if (res.data.errcode == 0) {
                                console.log("session id:" + res.data.value.sessionId)
                                that.globalData.sessionId = res.data.value.sessionId;
                                var expiredTime = +new Date() + 24 * 60 * 60 * 1000;
                                that.globalData.expiredTime = expiredTime;
                                console.log("登陆成功")
                            } else {
                                console.log("登陆失败")
                                console.log(res.data.errmsg)
                            }
                        }
                    })
                }
            }
        })
    },



    getTreeInfo: function () {
        var that = this;
        if (that.globalData.sessionId == null) {
            //等
            setTimeout(that.getTreeInfo, 1000); //设置定时器，第一个参数是方法名，第二个超时时间
            return;
        }

        //如果当前时间大于请求时间那么sessionID无效了
        if (+new Date() > that.globalData.expiredTime) {
            //重新获取sessionId
            that.globalData.sessionId = null;
            that.loginWithCode();
            setTimeout(that.getTreeInfo(), 1000)
            return;
        }

        wx.request({
            url: 'http://localhost:1316/getTreeInfosessionId',
            data: {
                sessionId: that.globalData.sessionId
            },
            method: "GET",
            header: {
                'content-type': 'application/json'
            },
            success(res) {
                console.log("get tree info sucess.....")
                that.globalData.treeAck = true
                if (res.data.errcode == 0) {
                    that.globalData.treeInfo = res.data.value.tree;
                    console.log("获取树的信息成功")
                    console.log(res.data.value.tree)
                } else {
                    that.globalData.treeInfo = null;
                    console.log("获取树的信息失败")
                }
            }

        })
    },

    
    


})