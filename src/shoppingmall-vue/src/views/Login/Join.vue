<template>
  <div style="text-align:center; 
              margin-top:50px;
              position: absolute;
              text-align: center;
              width: 100%;
              height: 100%;">
   <div style="width:300px; margin: 0 auto; text-align:center;">
      <h1 style="text-align:center; margin-bottom:20px;">
          회원가입
      </h1>


      <p style="margin-bottom:0px; text-align:left;">
          아이디
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="username">
      </p>

      <p style="margin-bottom:0px; text-align:left;">
          비밀번호
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="password">
      </p>

      <p style="margin-bottom:0px; text-align:left;">
          이름
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="name">
      </p>

      <p style="margin-bottom:0px; text-align:left;">
          주소
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="address">
      </p>

      <p style="margin-bottom:0px; text-align:left;">
          전화번호
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="phone">
      </p>

      <p style="margin-bottom:0px; text-align:left;">
          이메일
      </p>
      <p>
        <input style="border: 0.5px solid; width:300px; height: 45px;" v-model="email">
      </p>
      <v-btn width="300px" height="50px" @click="Join({username, password, name, address, phone, email})">가입하기</v-btn>
    </div>
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