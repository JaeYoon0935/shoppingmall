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
          <td>{{row.item.name}}</td>
          <td>{{row.item.product_count}}</td>
          <td>
            <v-btn dark small color="grey" class="ma-1">분류명 수정</v-btn>
            <v-btn dark small color="grey" class="ma-1">하위분류 추가</v-btn>
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
    data () {
      return {     
      }
    },
  }
</script>