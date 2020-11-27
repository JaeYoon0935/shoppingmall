import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    //헤더랑 배열만 있으면 됨,
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
      { text: '순위', value: 'rank', },
      { text: '누적판매량', value: 'order_count', },
      // 이 부분에서 order_count를 통해서 순위를 정하고, 그 부분을 정렬해서
      // orderby, sort 등을 이용해서 order_count를 정렬할 것
      { text: '상품명', value: 'name' },
    ],
    ranking:[],

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
      SET_RANKING(state, data) {
        console.log('running mutation');

        function oc_Sort(a, b) { 
          return a.order_count < b.order_count ? -1 : a.order_count > b.order_count ? 1 : 0; 
        }
        data.sort(oc_Sort);
        console.log(data[0].order_count);
        
        state.ranking = data
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
    },
    Ranking({commit}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/ranking')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_RANKING', Response.data)
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
