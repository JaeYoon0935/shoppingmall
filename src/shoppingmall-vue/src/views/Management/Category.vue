<template>
  <div class="about">
    <h1>분류관리 페이지입니다.</h1>
      <v-data-table
        :headers="$store.state.category_headers"
        :items="$store.state.category_item"
        :items-per-page="10"
        class="elevation-1"
      ></v-data-table>
  </div>
</template>

<script>
  export default {
    data () {
      return {    
      }
    },
  }
</script>