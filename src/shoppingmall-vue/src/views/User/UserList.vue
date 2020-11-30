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
              <span v-if="temp == row.item.username"><input v-model="inputText"></span>
              <span v-else>{{row.item.username}}</span>
            </td>
            <td>
              <span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.password}}</span>
            </td>
            <td><span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.name}}</span></td>
            <td><span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.address}}</span></td>
            <td>
              <span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.phone}}</span>
            </td>
            <td><span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.email}}</span></td>
            <td>
              <span v-if="temp == row.username"><input v-model="inputText"></span>
              <span v-else>{{row.point}}</span>
            </td>
            <td>
              <v-card-actions class="justify-start">  
                <v-btn dark small color="grey" @click="Update(row.item.username)">수정</v-btn>
                <v-btn dark small color="grey" @click="UserDelete(row.item)">탈퇴</v-btn>
              </v-card-actions>
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
        index: 100,
        inputText: ''
      }
    },
    computed: {
      ...mapState(["userlist","userlist_headers"])
    },
    methods:{
        ...mapActions(["UserDelete"]),
        Update(username) {
        // this.index = i
         this.temp=username
        //alert(username)
        },
        Updata_cancle: function(){
        this.temp=true  
        }
    },
  }
</script>