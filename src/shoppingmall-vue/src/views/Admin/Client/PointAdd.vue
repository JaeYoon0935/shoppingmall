<template>
  <div class="about">
   <h2 class="pt-2">포인트 내역추가</h2>
    <table class="table" style="width:800px;">
      <tbody>
        <tr>     
          <th>회원 아이디</th>
          <td><input style="height:100%; width:100%;" v-model="username"></td>
        </tr>
        <tr>
          <th>포인트</th>
          <td><input style="height:100%; width:100%;" v-model="point"></td>
        </tr>
        <tr style="height:300px;">
          <th>포인트 내역</th>
          <td><input style="height:100%; width:100%;" v-model="content"></td>
        </tr>
    </tbody>
  </table>
  <v-row style="width:800px;">
    <v-row>
          <v-col
            cols="7"
            lg="7"
            sm="7"
            md="5"
          >
        </v-col>
        <v-col
            cols="5"
            lg="5"
            sm="5"
            md="5"
          >
        <span v-if="username == '' || content =='' || point == '' ">
             <v-btn  width="100px" class="mr-5 ml-10"  @click="error()">등록</v-btn>
        </span>
        <span v-else>
           <v-btn width="100px" class="mr-5 ml-10" @click="PointAdd({username, content, point})"> 등록</v-btn>
        </span>      
           <v-btn width="100px" router :to="{name:'Point'}">취소</v-btn>
        </v-col> 
    </v-row>
  </v-row>
  </div>
</template>
<style scoped>
@media (min-width: 1200px) {
  .container {
    max-width:100%;
  }
}
th{
  width:200px;
  height:100px;
  border: 0.5px solid;
  border-color: rgb(200, 200, 200);
}
td{
  border: 0.5px solid;
  height:100px;
  border-color: rgb(200, 200, 200);
}

.container, .container-fluid, .container-lg, .container-md, .container-sm, .container-xl {
    width: 100%;
    padding-right: 0px;
    padding-left: 0px;
    margin-right: auto;
    margin-left: auto;
}
img{
  width:90%; height:100px;
}

</style>
<script>
import {mapActions} from "vuex"
  export default {
    data () {
      return {     
        username:'', 
        content: '',
        point: '',
      }
    },
    methods:{
      ...mapActions(["PointAdd"]),
    error(){
        alert('입력되지 않은 정보가 있습니다.')
      }
    }
  }
  </script>