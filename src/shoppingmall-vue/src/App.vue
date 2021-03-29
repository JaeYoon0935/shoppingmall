<template>
  <v-app id="inspire">
   <router-view/>
  </v-app>
</template>

<script>
import {mapMutations, mapState} from "vuex"

  export default {
    data: () => ({
      drawer: null,
    }),
    computed: {
      ...mapState(["Userinfo"])
    },
    methods: {
      ...mapMutations(["logout"])
    },
    created() {
      //새로고침을 해서 state의 토큰은 초기화 됬지만, 로컬스토리지에 토큰은 있을때, 즉 로그인을 한 경우
      //다시 토큰을 가져와주는 작업을 해준다.
      if(this.Userinfo.User_token === null && localStorage.getItem("token") !== null) {
        this.$store.commit("INSERT_TOKEN"),
        console.log("로그인 된 상태, 토큰 값: " + localStorage.getItem("token"))
      }  
      //로그인이 되어있지 않은 경우, 아무작업을 해주지 않는다.
      else if (this.Userinfo.User_token === null && localStorage.getItem("token") === null) {
        console.log("로그인 되어있지 않은 상태")
        this.$store.dispatch('imageByRank')
      }
    }
  }
</script>