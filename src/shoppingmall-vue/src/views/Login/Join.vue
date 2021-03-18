<template>
  <div style="text-align:center; 
              margin-top:50px;
              position: absolute;
              text-align: center;
              width: 100%;
              height: 100%;">
      <h1 style="text-align:center; margin-bottom:20px;">
          회원가입
      </h1>


      <p style="padding-right:250px; margin-bottom:0px;">
          아이디
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="username">
      </p>

      <p style="margin-right:250px; margin-bottom:0px;">
          비밀번호
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="password">
      </p>

      <p style="margin-right:250px; margin-bottom:0px;">
          이름
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="name">
      </p>

      <p style="margin-right:250px; margin-bottom:0px;">
          주소
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="address">
      </p>

      <p style="margin-right:250px; margin-bottom:0px;">
          전화번호
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="phone">
      </p>

      <p style="margin-right:250px; margin-bottom:0px;">
          이메일
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="email">
      </p>
      <v-btn width="300px" height="50px" @click="Join({username, password, name, address, phone, email})">가입하기</v-btn>
  </div>
</template>

<script>
import {mapActions} from "vuex"
  export default {
    data () {
      return {     
        username: '',
        password: '',
        name:'',
        address:'',
        phone:'',
        email:'',
      }
    },
    methods:{
      ...mapActions(["Join"]),
    },
  }
</script>