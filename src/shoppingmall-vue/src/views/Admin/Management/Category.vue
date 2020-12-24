<template>
  <div class="about">
    <h2 class="pt-2">분류관리</h2>
      <v-data-table
        :headers="$store.state.category_headers"
        :items="$store.state.categorylist"
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