<template>
  <div class="about">
    <h2 class="pt-2">상품관리</h2>
    <v-container>
      <v-data-table
        :headers="$store.state.product_header"
        :items="$store.state.productlist"
        :items-per-page="10"
        class="elevation-1"    
        >
         <template v-slot:item="row">
         <tr>     
            <td style="padding-left:50px;width:150px;">
              {{row.item.id}}
            </td>
            <td style="width:420px;">
                <v-row style="display:flex; width:420px; align-items:center;"> 
                  <v-col style="overflow:hidden;">
                    <!-- <img v-if="row.item.name=='거실등'" :src="image.livingLamp40"/>
                    <img v-else-if="row.item.name=='방등'" :src="image.roomLamp"/>
                    <img v-else-if="row.item.name=='식탁등'" :src="image.diningLamp"/>
                    <img v-else-if="row.item.name=='형광등 스탠드'" :src="image.lightStand" />
                    <img v-else-if="row.item.name=='LED 스탠드'" :src="image.ledStand"/>
                    <img v-else-if="row.item.name=='2구 스위치'" :src="image.switchTwo" />
                    <img v-else-if="row.item.name=='멀티탭'" :src="image.multiPlug"/> -->

                    <!-- row.item.image로 DB에 저장되어있는 image파일 이름을 불러오면 됨 -->
                    <img :src="image(row.item.image)"/>
                    <!-- <img :src="image('livingLamp40')"/> -->
                  </v-col>
                  <v-col>
                    <div>상품명: {{row.item.name}}</div>
                    <div>가격: {{priceToString(row.item.price)}}원</div>
                  </v-col>
                </v-row>
            </td>
            <td>{{row.item.category}}</td>
            <td>
              <div>조회수: {{row.item.views}}회</div>
              <div>판매량: {{row.item.order_count}}개</div>
            </td>
            <td>{{row.item.quantity}}개</td>
            <td>
                <div>
                  <v-btn dark small color="grey" class="ma-2" @click="Update(row.item)">수정</v-btn>
                </div>
                <div>
                 <v-btn dark small color="grey" class="ml-2" @click="UserDelete(row.item)">상품보기</v-btn>
                </div>
                <div>
                  <v-btn dark small color="grey" class="ma-2" @click="UserDelete(row.item)">삭제</v-btn>
                </div>
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