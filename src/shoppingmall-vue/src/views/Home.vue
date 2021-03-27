<template>
<v-app>
    <div class="parent" style="height: 150px;">
        <div id="top" class="child">

          <span style="padding-left:0px; padding-right:12px;" v-if="this.$store.state.login_flag == 0">
           <v-btn class="ml-8" router :to="{name: 'Login'}">로그인</v-btn>
          </span>
          <span style="padding-left:0px; padding-right:12px;" v-else>
            <v-btn class="ml-8" @click="LogOut()">로그아웃</v-btn>
          </span>

          <span style="padding-left:0px; padding-right:12px;" v-if="this.$store.state.login_flag == 0">
          <v-btn  router :to="{name: 'Join'}">회원가입</v-btn>
          </span>
        </div>
        <div class="child">
          <p align="center" style="margin-top:20px; width:100%;">
            <router-link to='/' style="color:#000; text-decoration:none">
                ShoppingMall           
            </router-link>
          </p>
        </div>
        <div class="child">
        </div>
    </div>

    <!--우선은 테스트용으로 v-btn으로 네비게이션 바를 만들어서 사용하고, 
        홈화면 작업에 들어갈때 그떄 제대로 작업하도록 한다. -->

    <p>
      <v-btn style="width:135px;">Home</v-btn>
      <v-btn style="width:135px;">LED 거실등</v-btn>    
      <v-btn style="width:135px;">LED 식탁등</v-btn>
      <v-btn style="width:135px;">LED 방등</v-btn>    
      <v-btn style="width:135px;">LED 주방등</v-btn>
      <v-btn style="width:135px;">스탠드</v-btn>    
      <v-btn style="width:135px;">전기재료</v-btn>
      <v-btn color="brown lighten-2" style="color:white; width:135px;" router :to="{name: 'Admin'}">관리자 페이지</v-btn>    
    </p>
    <div>
      <Main v-if="$route.name == 'Home'"></Main>
      <router-view/>
    </div> 
</v-app>
</template>

<style scoped>
  #top{
    text-align: left;
  }
  .parent {
      display: flex;
  }
  .child {
      flex: 1;
  }
  span{
     padding:20px;
  }
  div {
      width:1500px;    /* 가로 너비 */
      height:1000px;   /* 세로 너비 */
      background-color:rgb(245, 240, 235);    /* 선택자 배경 색 */
      /* background-color:rgb(223, 222, 207);   */
      margin:0 auto;     /* 바깥 여백 
      / margin 속성을 0 auto로 지정하면 위, 아래 여백은 0, 왼쪽 오른쪽은 auto로 가운데 정렬 */
      padding:0px;    /* 내부 여백 */
      /* border:1px solid rgba(44, 44, 48, 0.021);  div의 테두리*/
      border-radius: 10px;    /* 테두리 곡선 */
      font-size:32px;       /* 글자 크기 */
      color:black;       /* 글자 색 */
      font-family: 'Times New Roman', Times, serif;       /* 글자체 */
      /* font-style: italic;    글자 모양  */
      font-weight: 700;       /* 글자 굵기 */
      line-height:100px;       /* 글자 높이 */
      text-align: center;       /* 글자 정렬 */
 }
</style>

<script>
// @ is an alias to /src
import { mapActions } from "vuex"
import Main from '../views/User/Main.vue'
export default {
  created(){
      this.$store.state.login_prev = 1;
    },
  name: 'Home',
  components: {
    Main
  },
  methods:{
    ...mapActions(["LogOut"]),
  }
}
</script>
