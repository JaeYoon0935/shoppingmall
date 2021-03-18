<template>
  <div style="text-align:center; 
              margin-top:50px;
              width:300px;  
              position: absolute;
              text-align: center;
              width: 100%;
              height: 100%;">
      <h1 style="text-align:center">
          Login
      </h1>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 50px;" v-model="username" placeholder="아이디">
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 50px;" v-model="password" placeholder="비밀번호">
      </p>
      <v-btn width="300px" height="50px" @click="Login({username, password})">로그인</v-btn>
  </div>
</template>

<script>
import {mapActions} from "vuex"
  export default {
    data () {
      return {     
        username: '',
        password: '',
      }
    },
    methods:{
      ...mapActions(["Login"]),
    },
  }
</script>