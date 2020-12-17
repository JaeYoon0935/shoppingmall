<template>
  <div class="about">
      <v-col><h2 class="pt-2">상품등록</h2></v-col>
    <v-container>
      <v-data-table
        :headers="$store.state.product_header"
        :items="$store.state.productlist"
        :items-per-page="10"
        class="elevation-1"    
        >
         <template v-slot:item="row">
         <tr>     
            <td>
              1
            </td>
            <td>
            </td>
            <td>{{row.item.category}}</td>
            <td>
              
            </td>
            <td></td>
            <td>
            </td>
          </tr>
        </template>
      </v-data-table>
    </v-container>
  </div>
</template>
<style scoped>
@media (min-width: 1200px) {
  /* .container 클래스는 위의 코드상에는 없지만, 
    vuetify에서 v-container에 자동으로 적용시키는 클래스이다.
    이 부분을 F12개발자 도구의 style 부분에서 찾아서 수정한 것이다. */
  .container {
    max-width:100%;
  }
}

.container, .container-fluid, .container-lg, .container-md, .container-sm, .container-xl {
    width: 100%;
    padding-right: 0px;
    padding-left: 0px;
    margin-right: auto;
    margin-left: auto;
}
img{
  width:90%; height:100px;
}

</style>
<script>
import { mapState, mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('ProductList')
    },
    data () {
      return {     
        address:'',
        // image: 
        // {
        //   livingLamp40: require("@/assets/livingLamp40.jpg"),
        //   roomLamp: require("@/assets/roomLamp.jpg"),
        //   diningLamp: require("@/assets/diningLamp.jpg"),
        //   lightStand: require("@/assets/lightStand.jpg"),
        //   ledStand: require("@/assets/ledStand.jpg"),
        //   multiPlug: require("@/assets/multiPlug.jpg"),
        //   switchTwo: require("@/assets/switchTwo.jpg"),
        // },
      }
    },
    methods:{
      priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
      image(image){
        //경로를 조합해줄 메서드.
        return require('@/assets/'+ image +'.jpg');
      },
    }
  }
  </script>