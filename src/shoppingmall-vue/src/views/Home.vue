<template>
  <v-app>
      <div class="home">
        <router-link to='/'>
          <p style="border:1px solid;">
            쇼핑몰 메인페이지
          </p> 
        </router-link>
          <v-btn router :to="{name: 'Admin'}">관리자 페이지</v-btn>    
          <span>
              <router-link to="product">
                <img :src="require('@/assets/null.jpg')" router :to="{name:'Product_User'}">
              </router-link>
          </span>
       </div>     
       <Main v-if="$route.name == 'Home'"></Main>
       <router-view/>
  </v-app>


</template>

<style scoped>
  span{
     padding:20px;
  }
  div {
      width:1500px;    /* 가로 너비 */
      height:300px;   /* 세로 너비 */
      background-color:rgb(219, 243, 212);    /* 선택자 배경 색 */
      margin:0 auto;     /* 바깥 여백 
      / margin 속성을 0 auto로 지정하면 위, 아래 여백은 0, 왼쪽 오른쪽은 auto로 가운데 정렬 */
      padding:20px;    /* 내부 여백 */
      border:1px solid rgba(44, 44, 48, 0.021);  /* 테두리 */
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
import HelloWorld from '@/components/HelloWorld.vue'
import Main from '../views/User/Main.vue'

export default {
  name: 'Home',
  components: {
    Main
  }
}
</script>
