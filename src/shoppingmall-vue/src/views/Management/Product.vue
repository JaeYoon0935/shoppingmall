<template>
  <div class="about">
    <h2 class="pt-2">상품관리</h2>
      <v-data-table
        :headers="$store.state.product_header"
        :items="$store.state.productlist"
        :items-per-page="10"
        class="elevation-1">
         <template v-slot:item="row">
         <tr>     
            <td>
              <span>{{row.item.id}}</span>
            </td>
            <td>
              <span>{{row.item.image}}</span>
            </td>
            <td>
              <div>상품명: {{row.item.name}}</div>
              <div>가격: {{row.item.price}}원</div>
            </td>
            <td>
              <span>{{row.item.category}}</span>
            </td>
            <td>
              <div>조회수: {{row.item.views}}회</div>
              <div>판매량: {{row.item.order_count}}개</div>
            </td>
            <td>
              <span>{{row.item.quantity}}</span>
            </td>
            <td>
                <div>
                  <v-btn dark small color="grey" class="ma-1" @click="Update(row.item)">수정</v-btn>
                </div>
                <div>
                 <v-btn dark small color="grey" class="ml-1" @click="UserDelete(row.item)">상품보기</v-btn>
                </div>
                <div>
                  <v-btn dark small color="grey" class="ma-1" @click="UserDelete(row.item)">삭제</v-btn>
                </div>
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
      this.$store.dispatch('ProductList')
    },
    data () {
      return {     
      }
    },
  }
  </script>