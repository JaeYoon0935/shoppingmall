<template>
  <div class="about">
    <h1>회원관리 페이지입니다.</h1>
      <v-data-table
        :headers="$store.state.userlist_headers"
        :items="$store.state.userlist"
        :items-per-page="10"
        class="elevation-1"
      >
      <template v-slot:item="row" >
          <tr>     
            <td>{{row.item.username}}</td>
            <td>{{row.item.password}}</td>
            <td>{{row.item.name}}</td>
            <td>{{row.item.address}}</td>
            <td>{{row.item.phone}}</td>
            <td>{{row.item.email}}</td>
            <td>{{row.item.point}}</td>

            <td>
            <!-- <v-card-actions class="justify-center">   -->
              <v-card-actions class="justify-start">  
                <v-btn dark small color="grey" @click="onButtonClick(row.item)">수정</v-btn>
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
      }
    },
    methods:{
        ...mapActions(["UserDelete"])
    },
  }
</script>