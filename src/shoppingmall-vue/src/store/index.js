import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {

    rank: 0,
    temp: 12345678911, //temp에 우선 쓰레기값 넣어놓음.
    all: '전체',

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

    categoryname:[],

    ranking_header: [
      { text: '순위', value: 'rank', },
      { text: '누적판매량', value: 'order_count', },
      { text: '상품명', value: 'name' },
    ],
    ranking:[],

    product_headers:[
      { text: '상품코드', value: 'id', },
      { text: '상품정보', value: 'price', },
      { text: '분류', value: 'category', },
      { text: '통계', value: 'order_count', },
      { text: '재고', value: 'quantity', },
      { text: '관리', value: 'management', },
    ],
    productlist:[],  
    product:[
      {id:' ', name:' ', price:' ', category:' ', quantity:' ', text:' '}
    ],

    order_headers:[
      {text:'주문번호', value:'id'},
      {text:'주문상품', value:'product'},
      {text:'주문자', value:'user_id'},
      {text:'주문일자', value:'date'},
      {text:'총 주문금액', value:'total_price'},
      {text:'주문상태', value:'state'},
      {text:'보기', value:''},
    ],
    orderdetail_headers_1:[
      {text:'상품코드', value:'id'},
      {text:'상품정보', value:'id'},
      {text:'주문수량', value:'product'},
      {text:'판매가격', value:'total_price'},
      {text:'관리', value: ''}
    ],
    orderlist:[],
    orderDetailList:{},
  },
  getters: {
    get_orderDetailList: state => {
      return state.orderDetailList
    }
  },
  mutations: {
      SET_USER(state, data) {
        state.userlist = data      
    },
      SET_PRODUCT_LIST(state, data){
        state.productlist = data
    },
      SET_PRODUCT(state, data){
        state.product = data
        console.log(state.product)
    },
      UPDATE_PRODUCT(state, data){
        state.product = data
    },
      SET_ORDER(state, data){
        state.orderlist = data
    },
      SET_ORDER_DETAIL(state, data){
        state.orderDetailList = data
    },
      SET_CATEGORY(state, data) {
        state.categorylist = data
    },
      SET_CATEGORY_NAME(state, data) {

        for(var item=0; item <data.length; item++)  {
          data[item] = data[item].name;
        }

        state.categoryname = data
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
    UserUpdate({commit},payload) {
      console.log(payload)
      if(confirm('회원정보를 수정하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/userupdate',payload)
              .then(Response => {
                    console.log(Response.data)
                    console.log(this.state.temp)
                    this.state.temp=0
                    console.log(this.state.temp)
                    commit('SET_USER', Response.data) 
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })           
          })
      } else{
         return;
      }
    },
    UserDelete({commit},payload) {
      console.log(payload)
      if(confirm('회원을 탈퇴처리 하시겠습니까?') == true){
          return new Promise((resolve, reject) => {
              axios.post('http://localhost:9000/api/admin/userdelete',payload)
                  .then(Response => {
                        console.log(Response.data)
                        commit('SET_USER', Response.data)
                  })
                  .catch(Error => {
                      console.log('error')
                      reject(Error)
                  })
          })
      }else{
        return;
      }
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
    CategoryUpdate({commit},payload) {
    if(confirm('분류명을 수정하시겠습니까?') == true){  
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/categoryupdate',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_CATEGORY', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
      }else{
        return;
      }
    },
    CategoryAdd({commit},payload) {
      var input = prompt('하위분류명을 입력해주세요.')
      alert(input)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/categoryadd',payload)
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
    },
    CategoryName({commit}, all) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/categoryname')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_CATEGORY_NAME', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    CategorySelect({commit},payload) {
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/categoryselect',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_RANKING', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    ProductList({commit}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/productlist')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_PRODUCT_LIST', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    ProductCreate({commit},payload) {
      console.log(payload)
      if(confirm('상품을 등록하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productcreate',payload)
          .then(Response => {
            console.log(Response.data)
            commit('SET_PRODUCT_LIST', Response.data)},
            alert('상품이 등록되었습니다.'))    
          .then(() => router.push({ name: 'Product' }))
              .catch(Error => {
                  console.log('error')
                  reject(Error)
                  alert('상품등록에러')
                  .then(() => router.push({name:'ProductRegistration'}))
              })
      })
       }else{
         return;
       }
    },
    ProductDelete({commit},payload) {
      console.log(payload)
      if(confirm('상품을 삭제하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productdelete',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_PRODUCT_LIST', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
        })
      }
    },
    ProductUpdate({commit},payload) {
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productupdate',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('UPDATE_PRODUCT', Response.data)
              })
              .then(() => router.push({ name:'ProductUpdate'}))
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
        })     
    },
    ProductDataUpdate({commit},payload) {
      console.log(payload.files.name)
      console.log(payload)

      var frm = new FormData(); 
      /*
        여기서files는 파일의 정보를 담고있는 객체이다. 
        다른 곳에서는 파일이 여러개라면 files[0], files[1] 이렇게 사용되던데, 
        배열이 아닌 객체로 넘어와서 그런지 위와같이 사용이 안되는 것 같다.
      */
      frm.append("image", payload.files);

      if(confirm('상품정보를 수정하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productdataupdate',payload, 
          frm, { headers: { 'Content-Type': 'multipart/form-data' } })
              .then(Response => {
                    console.log(Response.data)
                    commit('SET_PRODUCT_LIST', Response.data) 
              })
              .then(() => router.push({ name: 'Product' }))
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })           
          })
      } else{
         return;
      }
    },
    Test({commit},payload) {
      console.log(payload)
      var frm = new FormData(); 
      /*
        여기서files는 파일의 정보를 담고있는 객체이다. 
        다른 곳에서는 파일이 여러개라면 files[0], files[1] 이렇게 사용되던데, 
        배열이 아닌 객체로 넘어와서 그런지 위와같이 사용이 안되는 것 같다.
      */
      frm.append("image", payload.files);
      if(confirm('업로드 하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/upload',payload, 
           frm, { headers: { 'Content-Type': 'multipart/form-data' } })
          .then(Response => {
            console.log(Response.data)
            commit('SET_PRODUCT_LIST', Response.data)},
            alert('상품이 등록되었습니다.'))    
          .then(() => router.push({ name: 'Product' }))
              .catch(Error => {
                  console.log('error')
                  reject(Error)
                  alert('상품등록에러')
                  .then(() => router.push({name:'ProductRegistration'}))
              })
      })
       }else{
         return;
       }
    },
    OrderList({commit}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/orderlist')
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_ORDER', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    OrderDetail({commit},payload) {
      var obj = {id: router.currentRoute.params.id};
      payload = obj;
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/orderdetail',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_ORDER_DETAIL', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    OrderDetailDelete({commit},payload) {
      console.log(payload)
      if(confirm('해당 주문을 삭제하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/orderdetaildelete',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_ORDER_DETAIL', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
        })
      } 
    },
    OrderDetailUpdate({commit},payload) {
      console.log(payload)
      if(confirm('주문정보를 수정하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/orderdetailupdate',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_ORDER_DETAIL', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
        })
      } else{
        return;
      }
    },
    // -------------- 아래부터 사용자 화면 ----------------
    Product({commit},payload) {
      var obj = {id: router.currentRoute.params.id};
      payload = obj;
      console.log(obj);
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/product',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_PRODUCT', Response.data)
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
  }, 
  modules: {
  }
})
