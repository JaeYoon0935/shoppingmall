<template>
  <v-app id="inspire">
    <v-app-bar
      app
      height="180"
    >
    <v-container fluid ma-0 pa-0>
      <v-layout class="mt-3">
        <span style="padding-left:0px; padding-right:12px;" v-if="this.$store.state.login_flag == 0">
          <v-btn class="ml-8" router :to="{name: 'Login'}">로그인</v-btn>
        </span>
        <span style="padding-left:0px; padding-right:12px;" v-else>
          <v-btn class="ml-8" @click="LogOut()">로그아웃</v-btn>
        </span>
        <span style="padding-left:0px; padding-right:12px;" v-if="this.$store.state.login_flag == 0">
        <v-btn  router :to="{name: 'Join'}">회원가입</v-btn>
        </span>
      </v-layout>

      <v-layout>
        <v-spacer/>
        <router-link to='/' style="color:#000; text-decoration:none">  
           <v-toolbar-title style="font-size:30px; font-weight:750">ShoppingMall</v-toolbar-title>       
        </router-link>
        <v-spacer/>
      </v-layout>

      <v-layout>
      <v-tabs
          centered
          class="mt-9"
          color="blue darken-1"
        >
          <v-tab style="width:140px;">Home</v-tab>
          <v-tab style="width:140px;">LED 거실등</v-tab>
          <v-tab style="width:140px;">LED 방등</v-tab>
          <v-tab style="width:140px;">LED 식탁등</v-tab>
          <v-tab style="width:140px;">LED 주방등</v-tab>
          <v-tab style="width:140px;">스탠드</v-tab>
          <v-tab style="width:140px;">전기재료</v-tab>
          <v-tab style="background-color:lightgrey; color:black; width:140px;" router :to="{name: 'Admin'}">관리자 페이지</v-tab>
        </v-tabs>
      </v-layout>
        </v-container>
    </v-app-bar>

    <v-main>
      <v-container>
  
      <v-row style="text-align:center">
        <Main v-if="$route.name == 'Home'"></Main>
        <router-view/>
      </v-row>

      </v-container>
    </v-main>
  </v-app>
</template>

<style scoped>
::v-deep .v-toolbar__content {
  padding: 0px !important;
}

@media(min-width: 0px) {
  .container, .container-lg, .container-md, .container-sm, .container-xl {
    max-width: 100%;
  }
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
   data: () => ({ 
      drawer: null, 
      selectedItem: 1,
      items: [
        { title: '회원정보'},
        { title: '포인트관리'},
      ]
    }),
  methods:{
    ...mapActions(["LogOut"]),
  },
}
</script>