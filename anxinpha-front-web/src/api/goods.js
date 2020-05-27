import http from './public'
// 商品列表
export const getAllGoods = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/item/goods/allGoods', params)
}
// 获取购物车列表
export const getCartList = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/user/cartList?userId=' + params)
}
// 加入购物车
export const addCart = (params) => {
  return http.fetchPost('http://api.anxinpha.com/api/user/addCart', params)
}
// 删除购物车勾选商品
export const delCartChecked = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/delCartChecked', params)
}
// 编辑购物车
export const cartEdit = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/cartEdit', params)
}
// 全选
export const editCheckAll = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/editCheckAll', params)
}
// 删除商品
export const cartDel = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/cartDel', params)
}
// 获取用户地址
export const addressList = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/user/addressList?userId=' + params)
}
// 通过id获取地址
export const getAddress = (params) => {
  return http.fetchPost('http://api.anxinpha.com/api/user/address', params)
}
// 修改收货地址
export const addressUpdate = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/updateAddress', params)
}
// 添加收货地址
export const addressAdd = (params) => {
  return http.fetchPost('http://api.anxinpha.com/api/user/addAddress', params)
}
// 删除收货地址
export const addressDel = (params) => {
  return http.fetchDelete('http://api.anxinpha.com/api/user/delAddress/' + params)
}
// 生成订单
export const submitOrder = (params) => {
  return http.fetchPost('http://api.anxinpha.com/api/user/addOrder', params)
}
// 支付
export const payMent = (params) => {
  return http.fetchPost('http://api.anxinpha.com/api/user/payOrder', params)
}
// 获取用户订单
export const orderList = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/user/orderList', params)
}
// 获取单个订单详情
export const getOrderDet = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/user/orderDetail', params)
}
// 确认收货
export const confirmReceipt = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/confirmReceipt', params)
}
// 取消订单
export const cancelOrder = (params) => {
  return http.fetchPut('http://api.anxinpha.com/api/user/cancelOrder', params)
}
// 商品详情
export const productDet = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/item/goods/goodsDetails', params)
}
// 删除订单
export const delOrder = (params) => {
  return http.fetchDelete('http://api.anxinpha.com/api/user/delOrder/' + params)
}
// 商品列表
export const getSearch = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/item/goods/search', params)
}
// 快速搜索
export const getQuickSearch = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/item/goods/quickSearch', params)
}
export const getMenu = (params) => {
  return http.fetchGet('http://api.anxinpha.com/api/item/category/all', params)
}
