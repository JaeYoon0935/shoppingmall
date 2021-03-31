<template>
  <div class="about">
    <h2 class="pt-2">분류관리</h2>
      <v-data-table
        :headers="$store.state.category_headers"
        :items="$store.state.categorylist"
        :items-per-page="20"
        :footer-props="footerProps"
        class="elevation-1"
      >
      <template v-slot:item="row">
        <tr>
          <td>{{row.item.cg_id}}</td>
          <td>
            <span v-if="temp == row.item.cg_id">
                <input :style="{width:'150px'}" v-model="name">
            </span>
          <span v-else>{{row.item.name}}</span>
          </td>
          <td>{{row.item.product_count}}</td>
          <td>          
            <span v-if="temp != row.item.cg_id">
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1" @click="Update(row.item)">분류명 수정</v-btn>
                  <v-btn dark small color="grey" class="ma-1" @click="CategoryAdd(row.item)">하위분류 추가</v-btn>
                </v-card-actions>
            </span>
            <span v-else>
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1"
                    @click="[CategoryUpdate({cg_id, name, product_count}),reload()]">수정완료</v-btn>
                  <v-btn dark small color="grey" class="ma-1" @click="Update_cancle()">취소</v-btn>
                </v-card-actions>
            </span>           
          </td>
        </tr>
      </template>
      </v-data-table>
  </div>
</template>

<script>
import {mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('CategoryList')
    },
    methods:{
      ...mapActions(["CategoryUpdate"]),
      ...mapActions(["CategoryAdd"]),
      Update(category) {
          var login_flag = this.$store.state.login_flag
          if(login_flag == true){
            // var auth = this.$store.state.Userinfo.User_auth[0].authority
            var login_auth = this.$store.state.Userinfo.User_auth[0]
            var refresh_auth = this.$store.state.Userinfo.User_auth[0].authority
            
            if (login_auth == 'ROLE_ADMIN' || refresh_auth == 'ROLE_ADMIN'){
                this.temp = category.cg_id
                this.cg_id = category.cg_id
                this.name = category.name
                this.product_count = category.product_count
              }else{
                alert('권한이 없습니다.')
              }
          }else{
            alert('권한이 없습니다.')
          }
        },
      Update_cancle: function(){
        this.temp = 123456
      },
      reload() {
        window.location.reload()
      },
    }, 
    data(){
      return {   
        cg_id:'',  
        name:'',
        product_count:'',
        temp:'554564',
        footerProps: {'items-per-page-options': [20, 40, 60, 80]}
      }
    },
  }
</script>