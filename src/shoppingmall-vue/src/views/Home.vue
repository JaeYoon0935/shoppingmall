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
          <v-tab style="width:140px;" router :to="{name: 'Home'}">Home</v-tab>
          




          <!-- 

            그림 이미지 클릭시, 해당 이미지로 이동하는 법,
            일단 스프링에서 p_id도 같이 넘겨준다.
            그래서 라우터에 router :to="{name: 'Product_User', params: {p_id}} (상품관리에 상품보기랑 똑같음.)
            이런식으로 해주면 바로 이동 가능하다. 



          -->







          <!-- LED 거실등탭 -->
          <v-menu
            open-on-hover
            bottom
            offset-y
          >
          <template v-slot:activator="{ on, attrs }">
            <v-tab    v-bind="attrs"
                      v-on="on"
                      style="width:140px;">LED 거실등                  
            </v-tab>
          </template>
          <v-list>
            <v-list-item
              v-for="(item, index) in Living"
              :key="index"
              router :to="{name: item.name}"
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
          <v-menu
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
          </v-menu>

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

.text{
  color:grey
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
  },
}
</script>