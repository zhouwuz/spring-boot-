// pages/plant/plant.js
const app = getApp()
Page({

  data: {


  },

  onLoad(options) {
      this.plantTree()

  },

  begiPlant: function () {
    var that = this;
    wx.getLocation({
      type: 'gcj02',
      success(res) {
        const latitude = res.latitude
        const longitude = res.longitude
        console.log(latitude)
        console.log(longitude)
        that.plantTree(longitude, latitude)
      }
    })
  },

  plantTree: function (longitude, latitude) {
    var treeObj = {
      sessionId:app.globalData.sessionId,
      name: 'dada大树',
      life: 1001,
      longitude: longitude,
      latitude: latitude,
      time: "2021-05-06 02:48:30"

    }

    wx.request({

      url: 'http://localhost:1316/plantTree',
      method: "POST",
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      data: treeObj,

      success: function (res) {
        console.log(res.data)
        if (res.data.errcode == 0) {
            console.log("种树成功去浇水吧")
          wx.showToast({
            title: '成功',
          })
          wx.redirectTo({
            url: '/pages/water/water'
          })
        } else {
            console.log("种树失败")
            console.log(res.data.errmsg)
          wx.showToast({
            title: '失败',
          })
        }
      }
    })
  }



})