// pages/water/water.js
const app = getApp()
Page({

    /**
     * 页面的初始数据
     */
    data: {
        treeInfo: null


    },

    /**
     * 生命周期函数--监听页面加载
     */
    onLoad(options) {
        this.getTreeByUserId()
        this.life()
        // this.water2()
    },

    getTreeByUserId: function () {
        var that = this;
        wx.request({
            url: 'http://localhost:1316/getTreeByUserId',
            data: {
                userId: 10041
            },
            method: "POST",
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },

            success: function (res) {
                if (res.data.errcode == 0) {
                    console.log("成功获取树的信息")
                    console.log(res.data.value.tree)
                    that.setData({
                        treeInfo: res.data.value.tree
                    })
                } else {
                    console.log("获取树的信息失败")
                }
            }
        })
    },

    towater() {
        console.log(this.data.treeInfo)
        this.water2()
    },

    // water: function () {
    //     var that = this;
    //     wx.request({
    //         url: 'http://localhost:1315/waterTree',
    //         data: {
    //             userId:10008,
    //             water: 50
    //         },
    //         method: "POST",
    //         header: {
    //             'content-type': 'application/x-www-form-urlencoded'
    //         },
    //         success: function (res) {
    //             if (res.data.errcode == 0) {
    //                 console.log("浇水成功")
    //                 wx.redirectTo({
    //                     url: '/pages/water/water'
    //                 })
    //             } else {
    //                 console.log("浇水失败")
    //             }
    //         }
    //     })

    // },

    water2: function (event) {
        var that = this;
        wx.getWeRunData({
            success: function (res) {
                const encryptedData = res.encryptedData
                const iv = res.iv

                wx.request({
                    url: 'http://localhost:1316/waterTree',
                    data: {
                        encryptedData: encryptedData,
                        iv: iv,
                        sessionId: app.globalData.sessionId,
                    },
                    method: "POST",
                    header: {
                        'content-type': 'application/x-www-form-urlencoded'
                    },
                    success: function (res) {
                        if (res.data.errcode == 0) {

                            if (res.data.value.life >= 20000) {
                                wx.showModal({
                                    showCancel: false,
                                    title: "浇水完成，树结果啦！",
                                    success: function (e) {
                                        if (confirm = true) {
                                            wx.redirectTo({
                                                url: '/pages/dashu/da'
                                            })
                                        }
                                    }
                                })
                            }
                            console.log("步数浇水成功")

                            wx.showModal({
                                title: "浇水成功！！！！！",
                                content: "你今天浇水：" + res.data.value.water,
                            })

                        } else {
                            console.log("步数浇水失败")
                            wx.showModal({
                                title: "浇水失败！步数用完了",
                            })
                            console.log(res.data.errmsg)
                        }


                        // if (res.data.value.life>=2000) {
                        //     wx.redirectTo({
                        //         url: '/pages/dashu/da'
                        //       })
                        // }else{

                        //     if (res.data.errcode == 0) {
                        //         console.log("步数浇水成功")
                        //         wx.showModal({
                        //             title: "浇水成功！",
                        //             content: "你今天浇水" + res.data.value.water
                        //         })

                        //     } else {
                        //         console.log("步数浇水失败")
                        //         wx.showModal({
                        //             title: "浇水失败！",
                        //         })
                        //         console.log(res.data.errmsg)
                        //     }
                        // }
                    }

                })
            }
        })
    },
    chou: function () {
        wx.redirectTo({
            url: '/pages/11/111'
        })
    },

    life: function () {

        var that = this;
        wx.request({
            url: 'http://localhost:1316/life',
            data: {
                sessionId: app.globalData.sessionId,
            },
            method: "POST",
            header: {
                'content-type': 'application/x-www-form-urlencoded'
            },
            success: function (res) {
                console.log(res.data)
                if (res.data.value.life >= 20000) {
                    wx.showModal({
                        showCancel: false,
                        title: "树已经结果啦！",
                        success: function (e) {
                            if (confirm = true) {
                                wx.redirectTo({
                                    url: '/pages/dashu/da'
                                })
                            }
                        }
                    })
                }
            }
        })

    },
})