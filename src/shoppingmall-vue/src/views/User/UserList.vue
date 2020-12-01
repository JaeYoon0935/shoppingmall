<template>
  <div class="about">
    <h1>회원관리 페이지입니다.</h1>
     <v-data-table
      class="elevation-1"
       :headers="userlist_headers"
       :items="userlist"
     >
        <template v-slot:item="row">
         <tr>     
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'82px'}" v-model="username">
              </span>
              <span v-else>{{row.item.username}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'95px'}" v-model="password">
              </span>
              <span v-else>{{row.item.password}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'69px'}" v-model="name">
              </span>
              <span v-else>{{row.item.name}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'104px'}" v-model="address">
              </span>
              <span v-else>{{row.item.address}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'107px'}" v-model="phone">
              </span>
              <span v-else>{{row.item.phone}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'131px'}" v-model="email">
              </span>
              <span v-else>{{row.item.email}}</span>
            </td>
            <td>
              <span v-if="temp == row.item.username">
                <input :style="{width:'83px'}" v-model="point">
              </span>
              <span v-else>{{row.item.point}}</span>
            </td>
            <td>
              <span v-if="temp != row.item.username">
              <v-card-actions class="justify-start">  
                <v-btn dark small color="grey" @click="Update(row.item.username)">수정</v-btn>
                <v-btn dark small color="grey" @click="UserDelete(row.item)">탈퇴</v-btn>
              </v-card-actions>
              </span>
              <span v-else>
                <v-card-actions class="justify-start">  
                <!-- <v-btn dark small color="grey" 
                @click="UserUpdate(username, password, name, address, phone, email, point)">수정완료</v-btn> -->
                <v-btn dark small color="grey" 
                @click="UserUpdate({username, password, name, address, phone, email, point})">수정완료</v-btn>
                <v-btn dark small color="grey" @click="Updata_cancle()">취소</v-btn>
              </v-card-actions>
              </span>
            </td>
          </tr>
        </template>
     </v-data-table>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('UserList')
    },
    data () {
      return {     
        temp:0,
        username: '',
        password: '',
        name:'',
        address:'',
        phone:'',
        email:'',
        point:''
      }
    },
    computed: {
      ...mapState(["userlist","userlist_headers"])
    },
    methods:{
        ...mapActions(["UserDelete"]),
        ...mapActions(["UserUpdate"]
        ),
        Update(username) {
         this.temp=username
        },
        Updata_cancle: function(){
        this.temp=true  
        }
    },
  }
</script>