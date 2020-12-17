import Vue from 'vue'
import VueRouter from 'vue-router'
import Home from '../views/Home.vue'
import UserList from '../views/User/UserList.vue'
import Point from '../views/User/Point.vue'
import Category from '../views/Management/Category.vue'
import Information from '../views/Management/Information.vue'
import Inquire from '../views/Management/Inquire.vue'
import Order from '../views/Management/Order.vue'
import Product from '../views/Management/Product.vue'
import Stock from '../views/Management/Stock.vue'
import Ranking from '../views/Situation/Ranking.vue'
import Sales from '../views/Situation/Sales.vue'
import ProductRegistration from '../views/Management/ProductRegistration.vue'

Vue.use(VueRouter)

const routes = [
  {
    path: '/',
    name: 'Home',
    component: Home
  },
  {
    path: '/userlist',
    name: 'UserList',
    component: UserList
  },
  {
    path: '/point',
    name: 'Point',
    component: Point
  },
  {
    path: '/category',
    name: 'Category',
    component: Category
  },
  {
    path: '/information',
    name: 'Information',
    component: Information
  },
  {
    path: '/inquire',
    name: 'Inquire',
    component: Inquire
  },
  {
    path: '/order',
    name: 'Order',
    component: Order
  },
  {
    path: '/product',
    name: 'Product',
    component: Product
  },
  {
    path: '/stock',
    name: 'Stock',
    component: Stock
  },
  {
    path: '/sales',
    name: 'Sales',
    component: Sales
  },
  {
    path: '/ranking',
    name: 'Ranking',
    component: Ranking
  },
  {
    path: '/productRegistration',
    name: 'ProductRegistration',
    component: ProductRegistration
  },
  {
    path: '/about',
    name: 'About',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () => import(/* webpackChunkName: "about" */ '../views/About.vue')
  }
]

const router = new VueRouter({
  mode: 'history',
  base: process.env.BASE_URL,
  routes
})

export default router
