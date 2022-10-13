// index.js
// 获取应用实例
const app = getApp()

Page({

    data: {


    },

    onLoad() {
        // this.getTreeByUserId();
        this.waitFortreeAck();
        

    },
    getTreeByUserId: function (res) {

        wx.request({
            url: 'http://localhost:1316/getTreeByUserId',
            data: {
                userId: 10040
            },
            success: function (res) {
                if (res.data.errcode == 0) {

                    console.log("成功找到你的树")
                      console.log(res.data)
                      console.log(res.data.value.tree)
                    // wx.redirectTo({
                    //     url: '/pages/water/water'
                    // })
                } else {
                    console.log("没有这棵树")
                    console.log("准备去种树")
                    // wx.redirectTo({
                    //     url: '/pages/plant/plant'
                    // })
                }
            },

        })
    },


    //等待treeAck为true才执行其他操作
    //否则设置定时器一直等treeAck为true
    waitFortreeAck() {
        if (app.globalData.treeAck == false) {
            setTimeout(this.waitFortreeAck, 1000)
            return;
        }

        //代码走到此处说明treeAck为true，代表后端的信息返回到前端。
        if (app.globalData.treeInfo == null) {
            //种树页面
            wx.redirectTo({
              url: '/pages/plant/plant'
            })

           
        }
        else {
            //浇水页面
            wx.redirectTo({
                url: '/pages/water/water'
              })
            
        }

    },
    

})