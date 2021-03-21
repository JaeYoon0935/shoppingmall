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

          <span style="padding-left:0px;" v-if="this.$store.state.login_flag == 0">            
           <v-btn color="#CACACA" class= "ml-3" router :to="{name: 'Login'}">로그인</v-btn>
          </span>
          <span style="padding-left:0px;" v-else>
            <v-btn color="#CACACA" class="ml-3" @click="logOut()">로그아웃</v-btn>
          </span>
<!-- 
          <v-btn color="#CACACA" class= "ml-5" router :to="{name: 'Login'}"> <span>로그인</span> </v-btn> -->
          <v-btn color="indigo lighten-1" class="mr-15" absolute right  router :to="{name: 'Home'}"> <span class="white--text">홈페이지로 이동</span> </v-btn>
        </v-toolbar-title>
    </v-app-bar>

    <v-main>
      <v-row style="margin-left:0px;" v-if="$route.name == 'Admin'">
        <div>
          <h1>
            관리자 메인페이지 입니다.
          </h1>
          <!-- <h1>
            * 쇼핑몰 관리시스템
          </h1>
        </div>
        <div>
           <h2>
              *소개: 쇼핑몰의 ~ 를 관리하는 시스템입니다.
          </h2>
        </div>
        <div>
          <h2>
              *개발언어 : ~
          </h2>
        </div>
        <div>
          <h2>
              *데이터베이스 : ~
          </h2> -->
        </div>
      </v-row>   
      <router-view :key="$route.fullPath"/>
    </v-main>
  </v-app>
</template>

<script>
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
      logOut(){
        this.$store.state.login_flag = 0;
      },
    },
  }
</script>