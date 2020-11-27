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
      { text: '상품수', value: 'password' },
      { text: '관리', value: 'management' },

    ],
    categorylist:[],
    category_item: [
      {
        id: '20',
        name: '상의/하의',
        password: 9,
        management: '추가/수정',

      },
      {
        id: '2010',
        name: '상의',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '201010',
        name: '셔츠',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '20101010',
        name: '체크/슬림',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '2010101010',
        name: '체크',
        password: 1,
        management: '추가/수정',

      },
      {
        id: '2010101020',
        name: '슬림',
        password: 1,
        management: '추가/수정',

      },
      {
        id: '201020',
        name: '맨투맨',
        password: 4,
        management: '추가/수정',

      },
      {
        id: '2020',
        name: '하의',
        password: 3,
        management: '추가/수정',

      },
      {
        id: '30',
        name: '테스트',
        password: 999999999,
        management: '추가/수정',
      },
    ],
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
