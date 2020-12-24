<template>
  <div class="about">
    <h2 class="pt-2">주문내역</h2>
      <v-data-table
        :headers="$store.state.order_headers"
        :items="$store.state.orderlist"
        :items-per-page="10"
        class="elevation-1"
      ></v-data-table>
  </div>
</template>

<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('OrderList')
    },
    data () {
      return {     
      }
    },
  }
</script>