<template>
  <div style="text-align:center; 
              margin-top:50px;
              position: absolute;
              text-align: center;
              width: 100%;
              height: 100%;">
   <div style="width:600px; margin: 0 auto; text-align:center;">
     <div style="width:300px; display: inline-block; margin: 0 auto; margin-left:60px; text-align:center;">
      <h1 style="text-align:center; margin-bottom:20px;">
          회원가입
      </h1>
        <p style="margin-bottom:0px; text-align:left;">
            아이디
        </p>
        <p>
          <input style="border: 0.5px solid; width:300px; height: 45px;" required v-model="username">
        </p>
        <p style="margin-bottom:0px; text-align:left;">
            비밀번호
        </p>
        <p>
          <input required style="border: 0.5px solid; width:300px; height: 45px;" v-model="password">
        </p>
        <p style="margin-bottom:0px; text-align:left;">
            이름
        </p>
        <p>
          <input required style="border: 0.5px solid; width:300px; height: 45px;" v-model="name">
        </p>
        <p style="margin-bottom:0px; text-align:left;">
            주소
        </p>
        <p>
          <input required style="border: 0.5px solid; width:300px; height: 45px;" v-model="address">
        </p>
        <p style="margin-bottom:0px; text-align:left;">
            전화번호
        </p>
        <p>
          <input required style="border: 0.5px solid; width:300px; height: 45px;" v-model="phone">
        </p>
        <p style="margin-bottom:0px; text-align:left;">
            이메일
        </p>
        <p>
          <input required style="border: 0.5px solid; width:300px; height: 45px;" v-model="email">
        </p>
        <span v-if="username == '' || password == '' || name == '' || address == '' || phone == '' || email == ''">
           <v-btn width="300px" height="50px" @click="error()">가입하기</v-btn>
        </span>
        <span v-else>
          <v-btn type="submit" width="300px" height="50px" @click="Join({username, password, name, address, phone, email})">가입하기</v-btn>
        </span>
     </div>
     <div style="display: inline-block; vertical-align: top; margin-top:95px; margin-left:10px;">
        <span v-if="username == ''">
           <v-btn style="width:80px;" @click="noId()">중복확인</v-btn>
        </span>
        <span v-else>
           <v-btn style="width:80px;" @click="duplicate({username})">중복확인</v-btn>
        </span>
     </div>
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
      ...mapActions(["duplicate"]),
       error(){
        alert('입력되지 않은 정보가 있습니다.')
      },
      noId(){
        alert('아이디를 입력해주세요.')
      }
    },
  }
</script>