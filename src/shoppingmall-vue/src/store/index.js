import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //헤더랑 리스트만 있으면 됨,
    userlist_headers: [
      { text: '아이디', value: 'username'},
      { text: '비밀번호', value: 'password'},
      { text: '이름', value: 'name' },
      { text: '주소', value: 'address' },
      { text: '전화번호', value: 'phone' },
      { text: '이메일', value: 'email' },
      { text: '포인트', value: 'point' },
      { text: '관리', value: 'management' },
    ],
    userlist:[],

    ranking_header: [
      { text: '순위', value: 'name', },
      { text: '상품명', value: 'product' },
    ],
    ranking_item: [
      {
        name: '1',
        product: '주방등',
      },
      {
        name: '2',
        product: '거실등',
      },
      {
        name: '3',
        product: '콘센트',
      },
      {
        name: '4',
        product: '멀티탭',
      },
      {
        name: '5',
        product: '매입등',
      },
      {
        name: '6',
        product: '기타품목',
      },
      {
        name: '7',
        product: '기타품목',
      },
      {
        name: '8',
        product: '기타품목',
      },
      {
        name: '9',
        product: '기타품목',
      },
      {
        name: '10',
        product: '기타품목',
      },
    ],
    category_headers: [
      {
        text: '분류코드',
        align: 'start',
        sortable: false,
        value: 'id',
      },
      { text: '분류명', value: 'name' },
      { text: '상품수', value: 'product_count' },
      { text: '관리', value: 'management' },

    ],
    categorylist:[],
  },
  mutations: {
      SET_USER(state, data) {
        state.userlist = data
    },
      SET_CATEGORY(state, data) {
      state.categorylist = data
  },
  },
  actions: {
    UserList({commit}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/userlist')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_USER', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    CategoryList({commit}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/categorylist')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_CATEGORY', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    }
  },
  modules: {
  }
})
