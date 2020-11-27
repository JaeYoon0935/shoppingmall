<template>
  <div class="about">
    <h1>분류관리 페이지입니다.</h1>
      <v-data-table
        :headers="$store.state.category_headers"
        :items="$store.state.categoryList"
        :items-per-page="10"
        class="elevation-1"
      ></v-data-table>
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