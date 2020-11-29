import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {

    rank: 0,

    //헤더랑 배열만 있으면 됨,
    userlist_headers: [

      { text: '아이디', value: 'username'},
      { text: '비밀번호', value: 'password'},
      { text: '이름', value: 'name'},
      { text: '주소', value: 'address'},
      { text: '전화번호', value: 'phone'},
      { text: '이메일', value: 'email'},
      { text: '포인트', value: 'point'},
      { text: '관리', value: 'management'},
    ],
    userlist:[],

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

    ranking_header: [
      { text: '순위', value: 'rank', },
      { text: '누적판매량', value: 'order_count', },
      { text: '상품명', value: 'name' },
    ],
    ranking:[],
  },
  mutations: {
      SET_USER(state, data) {
        state.userlist = data
    },
      SET_CATEGORY(state, data) {
      state.categorylist = data
    },
      SET_RANKING(state, data) {

        function oc_Sort(a, b) { 
          return b.order_count - a.order_count;
        }

        data.sort(oc_Sort);

        //위에서 정렬을 끝내고 오면, 거기에 순위를 매겨주는 로직
        //모두 null로 넘어오기에 정렬이 끝났다면 거기에 순위를 붙여줌
        for(var i = 0; i<data.length; i++){
          data[i].rank = i+1;
        }

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
    UserDelete({commit},payload) {
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/userdelete',payload)
              .then(Response => {
                  if(confirm('정말 해당 회원을 탈퇴처리를 하시겠습니까?') == true){
                    console.log(Response.data)
                    commit('SET_USER', Response.data)
                  } else{
                    return;
                  }
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
