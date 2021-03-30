import Vue from 'vue'
import Vuex, { Store } from 'vuex'
import router from "../router";
import axios from 'axios'

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    rank: 0,
    temp: 12345678911, //temp에 우선 쓰레기값 넣어놓음.
    all: '전체',
    sales_flag: 0,
    login_flag: false, //로그인 성공시 1로 바뀌고 로그인 하지않았을 때나 로그아웃 시 0으로 바뀐다. 
    login_prev: 0,


    //사용자단
    imageByRank:[], //홈화면 랭킹
    imageLiving:[], //홈화면 거실등
    imageRoom:[], //홈화면 방등
    imageMaterials:[], //홈화면 전기재료

    //관리자단 

    //user
    userlist_headers: [
      { text: '아이디', value: 'username'},
      { text: '비밀번호', value: 'password'},
      { text: '이름', value: 'name'},
      { text: '주소', value: 'address'},
      { text: '전화번호', value: 'phone'},
      { text: '이메일', value: 'email'},
      { text: '관리', value: 'management'},
    ],
    
    //회원 목록에 뿌려질 사용자를 담을 리스트 배열
    userlist:[],  

    //로그인 된 사용자의 정보 
    Userinfo:{User_Id:null,User_Name:null,User_auth:[],User_token:null},

    //point
    point_headers:[
      { text:'아이디', value:'username'},
      { text:'이름', value:'name'},
      { text:'포인트내용', value:'content'},
      { text:'포인트', value:'point'},
      { text:'일시', value:'date'},
      { text:'포인트합계', value:'total_point'},
    ],
    point:[],


    //category
    category_headers: [
      {
        text: '분류코드',
        align: 'start',
        sortable: false,
        value: 'id',
      },
      { text: '분류명', value: 'name' },
      { text: '상품재고수', value: 'product_count' },
      { text: '관리', value: 'management' },
    ],
    categorylist:[],
    categoryname:[],

    //ranking
    ranking_header: [
      { text: '순위', value: 'rank', },
      { text: '누적판매량', value: 'order_count', },
      { text: '상품명', value: 'name' },
    ],
    ranking:[],

    //product
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

    //order
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

    //sales
    sales_headers:[
      {text:'주문번호', value:'id'},
      {text:'주문날짜', value:'date'},
      {text:'주문자아이디', value:'id'},
      {text:'주문자성명', value:'name'},
      {text:'휴대폰', value:'phone'},
      {text:'주문합계', value:'total_price'},
      {text:'상세보기', value:''},
    ],
    salesdata:[],
  },
  getters: {
    get_orderDetailList: state => {
      return state.orderDetailList
    }
  },
  mutations: {

    SET_IMAGE_BY_RANK(state,data){
      state.imageByRank = data
    },
    SET_IMAGE_LIVING(state, data){
      state.imageLiving = data
    },
    SET_IMAGE_ROOM(state, data){
      state.imageRoom = data
    },
    SET_IMAGE_MATERIALS(state, data){
      state.imageMaterials = data
    },
    SET_USERDATA(state, data) {
       //Userinfo는 객체고, userlist는 배열인데 어차피 배열로 회원목록 뿌려줘야하므로 userlist 그대로 사용하도록 한다.
        state.userlist = data      
    },
    SET_USER(state, data) {
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name = data.name
      state.Userinfo.User_auth = data.roles
      state.Userinfo.User_token = data.token
      state.login_flag = true
   },
    SET_USER_REFRESH(state,data) {
      state.Userinfo.User_Id = data.username
      state.Userinfo.User_Name = data.name
      state.Userinfo.User_auth = data.authorities
      state.Userinfo.User_token = data.token
    },
    LogOut(state) {
      state.Userinfo.User_Id = null
      state.Userinfo.User_Name = null
      state.Userinfo.User_auth = null
      state.Userinfo.User_token = null
      state.login_flag = false
      localStorage.removeItem("token")
      // localStorage.removeItem("Authorization")
      console.log(state.Userinfo)
      console.log("로그아웃됐니?, 토큰 값: "+localStorage.getItem("token"))
      // console.log("로그아웃됐니?, 토큰 값: "+localStorage.getItem("Authorization"))
    },
    INSERT_TOKEN(state) {
      state.Userinfo.User_token = localStorage.getItem("token")
      state.login_flag = true
    },
    SET_POINT(state, data){
        
        for(var item=0; item <data.length; item++)  {

          var part1 = data[item].date.slice(0,8);
          var part2 = data[item].date.slice(9,15);
          
          var time = part1 + part2;
          data[item].time = time
        }
  
        function date_Sort(a, b) { 
          return b.time - a.time;
        }
        //날짜 순으로 정렬
        data.sort(date_Sort);

        state.point = data
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
    SET_SALES_DATA(state,data){
        state.salesdata = data
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
    UserList({commit, state}) {
      return new Promise((resolve, reject) => {
          axios.get('http://localhost:9000/api/admin/userlist')
              .then(Response => {
                  console.log('------ response.data')
                  console.log(Response.data)
                  commit('SET_USERDATA', Response.data)

                  console.dir(state.Userinfo.User_Id)
                  console.dir(state.Userinfo.User_Name)
                  console.log('----------------------------')
                  console.dir(state.Userinfo)
                  console.dir(state.Userinfo.User_auth)
                  console.dir(state.Userinfo.User_auth[0]) // login
                  console.dir(state.Userinfo.User_auth[0].authority) // 새로고침시
              })
              .catch(Error => {
                  console.log('error')
                  reject(Error)
              })
      })
    },
    UserUpdate({commit, state},payload) {
      console.log(payload)
      if(confirm('회원정보를 수정하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/userupdate',payload)
              .then(Response => {
                    console.log(Response.data)
                    console.log(this.state.temp)
                    this.state.temp=0
                    console.log(this.state.temp)
                    commit('SET_USERDATA', Response.data) 
              })
              .catch(Error => {
                  alert('권한이 없습니다.')
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
                        commit('SET_USERDATA', Response.data)
                  })
                  .catch(Error => {
                      alert('권한이 없습니다.')
                      console.log('error')
                      reject(Error)
                  })
          })
      }else{
        return;
      }
    },
      Login({ commit }, payload) {
        return new Promise((resolve, reject) => {
            axios.post('http://localhost:9000/api/auth/signin', payload)
                .then(Response => {
                    console.log(Response.data)
                    if (Response.data.username != null) {
                      //로그인 시 헤더에 디폴트 값으로 포함되는 권한을 추가함.
                        axios.defaults.headers.common['Authorization'] = `Bearer ${Response.data.token}`
                        localStorage.setItem("token", Response.data.token)
                        commit('SET_USER', Response.data)  
                          if(this.state.login_prev == 0){
                            router.push({ name: 'Home' })   
                          }
                          else if(this.state.login_prev == 1){
                            router.push({ name: 'Home' })   
                          }
                          else if(this.state.login_prev == 2){
                            router.push({ name: 'Admin' })
                          }  
                    }
                })
                .catch(Error => {
                    alert('아이디 또는 비밀번호를 확인해주세요.')
                    console.log('error')
                    reject(Error)
                })
        })
    },
    UnpackToken({commit}) {
        return new Promise((resolve, reject) => {
          axios.defaults.headers.common['Authorization'] = `Bearer ${localStorage.getItem("token")}`
          axios.get('http://localhost:9000/api/auth/unpackToken')
              .then(Response => {
                console.log(Response.data)
                commit('SET_USER_REFRESH',Response.data)
              })
              .catch(Error => {
                console.log(Error)
                  console.log('unpackToken_error')
              })
      })
    },
    LogOut({commit}){
      return new Promise((resolve, reject) => {
        //로그아웃시 헤더에 디폴트 값으로 포함되는 권한을 null로 처리함.
        axios.defaults.headers.common['Authorization'] = null;
        commit('LogOut')
      })
    },
    Join({commit},payload){
        console.log(payload)
        return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/auth/signup',payload)
              .then(Response => {
                if(Response.data == 'success'){
                  console.log(Response.data)
                  alert("회원가입이 완료되었습니다.");      
                  router.push({ name: 'Home' })      
                }else{
                  alert('이미 사용중인 아이디입니다.')
                }
              })
              .catch(Error => {
                  alert('입력양식을 확인해주세요.')
                  console.log('error')
                  reject(Error)
              })
      })
    },
    duplicate({commit},payload){
      console.log(payload)
      return new Promise((resolve, reject) =>{
        axios.post('http://localhost:9000/api/auth/duplicate',payload)
          .then(Response =>{
            console.log(Response.data)
            if(Response.data == 'success'){
              alert('사용 가능한 아이디입니다.')
            }else{
              alert('이미 사용중인 아이디입니다.')
            }
          })
          .catch(Error => {
            console.log('error')
            reject(Error)
        })
      })
    },
    Point({commit}){
      return new Promise((resolve,reject) => {
        axios.get('http://localhost:9000/api/admin/point')
        .then(Response => {
            console.log(Response.data)
            commit('SET_POINT', Response.data)
        })
        .catch(Error => {
            console.log('error')
            reject(Error)
        })
      })
    },
    PointAdd({commit}, payload){
      console.log(payload)
      return new Promise((resolve,reject) => {
        axios.post('http://localhost:9000/api/admin/pointadd',payload)
        .then(Response => {
          if(Response.data == 'success'){
            console.log(Response.data)
            router.push({name:'Point'})
          }else{
            alert('존재하지 않는 아이디입니다.');
          }
        })
        .catch(Error =>{
            alert('입력양식을 확인해주세요.');
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
    CategoryUpdate({commit},payload) {
    if(confirm('분류명을 수정하시겠습니까?') == true){  
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/categoryupdate',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_CATEGORY', Response.data)
              })
              .catch(Error => {
                  alert('권한이 없습니다.')
                  console.log('error')
                  reject(Error)
              })
      })
      }else{
        return;
      }
    },
    CategoryAdd({commit},payload) {
      var name;
      if(name = prompt('하위분류명을 입력해주세요.')){
      alert(name)
      payload.name = name //이렇게 하면, 부모카테고리의 내용물에서 분류명만 현재 입력받은 분류명으로 바뀌어서 스프링으로 넘어가게 된다.
      console.log(payload)
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/categoryadd',payload)
              .then(Response => {
                  console.log(Response.data)
                  commit('SET_CATEGORY', Response.data)
              })
              .catch(Error => {
                  alert('권한이 없습니다.')
                  console.log('error')
                  reject(Error)
              })
      })
      }else{
        return;
      }
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
    SalesByTime({commit}, payload){
      console.log(payload)
      return new Promise((resolve, reject) =>{
          axios.post('http://localhost:9000/api/admin/salesbytime', payload)
               .then(Response =>{
                  console.log(Response.data)
                  commit('SET_RANKING', Response.data)
          })
          .catch(Error =>{
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
      let formData = new FormData()
      formData.append('file', payload.fileinput)
      formData.append('id', payload.id)
      formData.append('name', payload.name)
      formData.append('price', payload.price)
      formData.append('category', payload.category)
      formData.append('quantity', payload.quantity)
      formData.append('text', payload.text)

      if(confirm('상품을 등록하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productcreate',
            formData,
            {
              headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Origin': '*'
              }
          })
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
    
    ProductDataUpdate({commit},payload) {
      let formData = new FormData()
      if(payload.fileinput != null){
        formData.append('file', payload.fileinput)
      }
      formData.append('id', payload.id)
      formData.append('name', payload.name)
      formData.append('price', payload.price)
      formData.append('category', payload.category)
      formData.append('quantity', payload.quantity)
      formData.append('text', payload.text)

      if(confirm('상품정보를 수정하시겠습니까?') == true){
      return new Promise((resolve, reject) => {
          axios.post('http://localhost:9000/api/admin/productdataupdate', 
            formData,
            {
              headers: {
                'Content-Type': 'multipart/form-data',
                'Access-Control-Allow-Origin': '*'
              }  
          })
          .then(Response => {
                console.log(Response.data)
                commit('SET_PRODUCT_LIST', Response.data) 
          })
          .then(() => router.push({ name: 'Product' }))
          .catch(Error => {
              console.log('error')
              reject(Error)
              alert('권한이 없습니다.')
              // alert('상품수정에러')
          })           
        })
      } else{
         return;
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
                  alert('권한이 없습니다.')
                  console.log('error')
                  reject(Error)
              })
        })
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
                  alert('권한이 없습니다.')
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

    SalesData({commit},payload){
      var dateinfo = {dateinfo:router.currentRoute.params}
      console.log(dateinfo)
      payload = dateinfo
      commit('SET_SALES_DATA', null) //SalesData 페이지로 넘어가기 전에 우선 state.salesdata의 내용물을 null로 초기화함.
      return new Promise((resolve,reject) =>{
          axios.post('http://localhost:9000/api/admin/salesdata',payload)
              .then(Response =>{
                console.log(Response.data)
                if(Response.data == 'empty'){        
                  router.push({ name: 'Sales' })      
                  alert('표시할 데이터가 없습니다.')
                  return;
                }else{
                  commit('SET_SALES_DATA', Response.data)
                }
              })
              .catch(Error =>{
                  console.log('error')
                  reject(Error)
              })
      })
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
    imageByRank({commit}){
      console.log('상품랭킹 순 이미지')
      return new Promise((resolve, reject) => {
        axios.get('http://localhost:9000/api/imageByRank')
        .then(Response => {
          console.log(Response.data)
          commit('SET_IMAGE_BY_RANK',Response.data)
        })
        .catch(Error => {
          console.log('error')
          reject(Error)
        })
      })      
    },
    imageLiving({commit}){
      return new Promise((resolve, reject) => {
        axios.get('http://localhost:9000/api/imageLiving')
        .then(Response => {
          console.log(Response.data)
          commit('SET_IMAGE_LIVING',Response.data)
        })
        .catch(Error => {
          console.log('error')
          reject(Error)
        })
      })      
    },
    imageRoom({commit}){
      return new Promise((resolve, reject) => {
        axios.get('http://localhost:9000/api/imageRoom')
        .then(Response => {
          console.log(Response.data)
          commit('SET_IMAGE_ROOM',Response.data)
        })
        .catch(Error => {
          console.log('error')
          reject(Error)
        })
      })      
    },
    imageMaterials({commit}){
      return new Promise((resolve, reject) => {
        axios.get('http://localhost:9000/api/imageMaterials')
        .then(Response => {
          console.log(Response.data)
          commit('SET_IMAGE_MATERIALS',Response.data)
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
