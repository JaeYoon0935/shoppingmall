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
            <!-- 홈탭 -->
            <v-tab style="width:140px;" @click="category('Home')">Home</v-tab>

            <!-- LED 거실등탭 -->
            <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab  v-bind="attrs"
                      v-on="on"
                      @click="category('Living')"
                      style="width:140px;">LED 거실등                  
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Living"
                :key="index"
                @click="move()"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>

            <!-- LED 방등탭 -->
            <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab    v-bind="attrs"
                        v-on="on"
                        style="width:140px;">LED 방등
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Room"
                :key="index"
                router :to="{name: item.name}"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>

            <!-- LED 식탁등탭 -->
            <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab    v-bind="attrs"
                        v-on="on"
                        style="width:140px;">LED 식탁등
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Dining"
                :key="index"
                router :to="{name: item.name}"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>

            <!-- LED 주방등탭 -->
            <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab    v-bind="attrs"
                        v-on="on"
                        style="width:140px;">LED 주방등
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Kitchen"
                :key="index"
                router :to="{name: item.name}"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>

            <!-- 스탠드 -->
            <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab    v-bind="attrs"
                        v-on="on"
                        style="width:140px;">스탠드
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Stand"
                :key="index"
                router :to="{name: item.name}"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu>

            <!-- 전기재료 -->
            <!-- <v-menu
              open-on-hover
              bottom
              offset-y
            >
            <template v-slot:activator="{ on, attrs }">
              <v-tab    v-bind="attrs"
                        v-on="on"
                        style="width:140px;">전기재료
              </v-tab>
            </template>
            <v-list>
              <v-list-item
                v-for="(item, index) in Materials"
                :key="index"
                router :to="{name: item.name}"
              >
                <v-list-item-title>{{ item.title }}</v-list-item-title>
              </v-list-item>
            </v-list>
            </v-menu> -->
            <v-tab style="width:140px;" @click="category('Materials')">전기재료 </v-tab>

            <v-tab style="background-color:lightgrey; color:black; width:140px;" router :to="{name: 'Admin'}">관리자 페이지</v-tab>
        </v-tabs>
       </v-layout>
      </v-container>
    </v-app-bar>

    <v-main>
      <v-container>
  
      <v-row style="text-align:center">

        <!-- 원래코드
         <Main v-if="$route.name == 'Home'"></Main>
         <router-view/>
         -->

        <Main v-if="'Home' == this.Tab"></Main>
        <Living v-if="'Living' == this.Tab"></Living>
        <Materials v-if="'Materials' == this.Tab"></Materials> 
         <router-view/>

        <!-- 
        <Main v-if="'Home' == this.Tab"></Main>
        <Living v-if="'Living' == this.Tab"></Living>
        <Materials v-if="'Materials' == this.Tab"></Materials> 
        -->

        <!-- <Main v-if="this.Tab == 'Home'"></Main>
        <Living v-if="this.Tab == 'Living'"></Living> -->
        <!-- <router-view :key="$route.fullPath"/> -->
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

.text{
  color:grey
}
</style>

<script>
// @ is an alias to /src
import { mapActions } from "vuex"
import Main from '../views/User/Main.vue'
import Living from '../views/User/Tab/Living.vue'
import Materials from '../views/User/Tab/Materials.vue'

export default {
  created(){
      this.$store.state.login_prev = 1;
    },
  name: 'Home',
  components: {
    Main, Living, Materials
  },
   data: () => ({ 
      Tab: 'Home',
      Living: [
        { title: '거실등 30평형', name:'Product'},
        { title: '거실등 40평형', name:'Product'},
      ],
      Room: [
        { title: '소형 방등', name:'Product'},
        { title: '대형 방등', name:'Product'},
      ],
      Dining: [
        { title: '소형 식탁등', name:'Product'},
        { title: '중형 식탁등', name:'Product'},
      ],
      Kitchen: [
        { title: '싱크대', name:'Product'},
        { title: '레일형', name:'Product'},
      ],
      Stand: [
        { title: 'LED 스탠드', name:'Product'},
        { title: '일반 스탠드', name:'Product'},
      ],
      Materials: [
        { title: '스위치', name:'Product'},
        { title: '멀티탭', name:'Product'},
        { title: '콘센트', name:'Product'},
      ]
    }),
  methods:{
    ...mapActions(["LogOut"]),
    category(text){
      this.Tab = text;
    },
    move(){
      window.scrollTo(0,500);
    }
  },
}
</script>