<template>
  <v-app id="inspire">
    <v-navigation-drawer
      v-model="drawer"
      app
    >   
     <v-card
        class="mx-auto"
        max-width="300"
        tile
      >
       <v-subheader>목록</v-subheader>
        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"             
                v-bind="attrs"
                v-on="on"
              >
                회원관리
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item1"
                :key="index"
                 router :to="{name: item.name}"
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>

        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"
                v-bind="attrs"
                v-on="on"
              >
                쇼핑몰관리
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item2"
                :key="index"
                router :to="{name: item.name}"
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>

        <v-list dense>
          <v-menu
            open-on-hover
            top
            offset-x
          >
            <template v-slot:activator="{ on, attrs }">
              <v-btn
                block
                depressed
                color="gray"
                v-bind="attrs"
                v-on="on"
              >
                쇼핑몰현황
              </v-btn>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in item3"
                :key="index"
                 router :to="{name: item.name}" 
              >
              <v-list-item-title>{{ item.title }}</v-list-item-title>
            </v-list-item>
            </v-list>
          </v-menu>
        </v-list>
    </v-card>
    </v-navigation-drawer>

    <v-app-bar app>
      <v-app-bar-nav-icon @click="drawer = !drawer"></v-app-bar-nav-icon>
        <v-toolbar-title>
          <v-btn router :to="{name: 'Admin'}" text>관리자 페이지 (메인) </v-btn>    

          <span style="padding-left:0px;" v-if="this.$store.state.login_flag == false">            
           <v-btn color="#CACACA" class= "ml-3" router :to="{name: 'Login'}">로그인</v-btn>
          </span>
          <span style="padding-left:0px;" v-else>
            <v-btn color="#CACACA" class="ml-3" @click="LogOut()">로그아웃</v-btn>
          </span>
          <v-btn color="indigo lighten-1" class="mr-15" absolute right  router :to="{name: 'Home'}"> <span class="white--text">홈페이지로 이동</span> </v-btn>
        </v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-row style="margin-left:0px;" v-if="$route.name == 'Admin'">
 
        <v-card
          max-width="600"
          style="margin-left:30px; margin-top:20px;"
        >
          <v-img
            class="white--text align-end"
            height="180px"
            src="@/images/mall.jpg"
          >
           </v-img>
          <v-card-text>
 
            <p class="display-1 text--primary">
              쇼핑몰 관리시스템
            </p>
            <div class="text--primary card_text" style="margin-top:15px;">
              개요<br style="margin-bottom:5px;">
              : 웹사이트 관리자가 쇼핑몰의 데이터를 관리 할 수 있는 솔루션
            </div>
             <div class="text--primary card_text" style="margin-top:25px;">
              개발인원<br>
             : 1인 (개인프로젝트)
            </div>
             <div class="text--primary card_text" style="margin-top:25px;">
              개발언어<br>
              Front-end: Vue.js<br>
              Back-end: Java, SpringBoot
            </div>
             <div class="text--primary card_text" style="margin-top:25px;">
              데이터베이스<br>
              : Mysql
            </div>
             <div class="text--primary card_text" style="margin-top:25px;">
              배포<br>
              : AWS EC2
            </div>

          </v-card-text>
        </v-card>
      </v-row>
      <router-view :key="$route.fullPath"/>
    </v-main>
  </v-app>
</template>


<style scoped>
.text{
  color:rgb(0, 0, 0);
}
.card_text{
  font-size:19px;
  color:rgb(218, 213, 213);
}
.v-application .display-1 {
    font-size: 30px!important;
    font-weight: 400;
    line-height: 2.5rem;
    letter-spacing: 0.0073529412em !important;
    font-family: "Roboto", sans-serif !important;
}
.v-card__subtitle, .v-card__text {
    font-size: 0.875rem;
    font-weight: 400;
    line-height: 1.5rem;
    letter-spacing: 0.0071428571em;
}
</style>

<script>
import { mapActions } from "vuex"
  export default {
    created(){
      this.$store.state.login_prev = 2;
    },
    data: () => ({ 
      drawer: null, 
      selectedItem: 1,
      item1: [
        { title: '회원정보', name: 'UserList' },
        { title: '포인트관리', name: 'Point'},
      ],
      item2: [
        { title: '주문내역', name: 'Order'},
        { title: '분류관리', name: 'Category'},
        { title: '상품관리', name: 'Product'},
      ],
      item3: [
        { title: '판매순위', name: 'Ranking'},
        { title: '매출통계', name: 'Sales'},
      ],
    }),
    methods:{
      ...mapActions(["LogOut"]),
    },
  }
</script>