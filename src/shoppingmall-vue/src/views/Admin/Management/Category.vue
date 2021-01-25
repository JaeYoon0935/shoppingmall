<template>
  <div class="about">
    <h2 class="pt-2">분류관리</h2>
      <v-data-table
        :headers="$store.state.category_headers"
        :items="$store.state.categorylist"
        :items-per-page="20"
        class="elevation-1"
      >
      <template v-slot:item="row">
        <tr>
          <td>{{row.item.id}}</td>
          <td>
            <span v-if="temp == row.item.id">
                <input :style="{width:'150px'}" v-model="name">
            </span>
          <span v-else>{{row.item.name}}</span>
          </td>
          <td>{{row.item.product_count}}</td>
          <td>          
            <span v-if="temp != row.item.id">
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1" @click="Update(row.item)">분류명 수정</v-btn>
                  <v-btn dark small color="grey" class="ma-1" @click="CategoryAdd(row.item)">하위분류 추가</v-btn>
                </v-card-actions>
            </span>
            <span v-else>
                <v-card-actions class="justify-start">  
                  <v-btn dark small color="grey" class="ma-1"
                    @click="[CategoryUpdate({id, name, product_count}),reload()]">수정완료</v-btn>
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
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('CategoryList')
    },
    methods:{
      ...mapActions(["CategoryUpdate"]),
      ...mapActions(["CategoryAdd"]),
      Update(category) {
          this.temp = category.id
          this.id = category.id
          this.name = category.name
          this.product_count = category.product_count
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
        id:'',  
        name:'',
        product_count:'',
        temp:'554564',
      }
    },
  }
</script>