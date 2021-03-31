<template>
  <div class="about">
    <v-row>
      <v-col style="padding-top:0px; padding-bottom:20px;"><h2 class="pt-2">상품관리</h2></v-col>
      <v-col class="mr-7 mt-4">
        <v-btn color="indigo lighten-1" absolute right router :to="{name:'ProductRegistration'}">
          <span class="white--text">상품등록</span>
        </v-btn>
      </v-col>
    </v-row>
    <v-container>
      <v-data-table
        :headers="$store.state.product_headers"
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
                    <img :src="image(row.item.image)"/>
                  </v-col>
                  <v-col>
                    <div>상품명: {{row.item.name}}</div>
                    <div>가격: {{priceToString(row.item.price)}}원</div>
                  </v-col>
                </v-row>
            </td>
            <td>{{row.item.category}}</td>
            <td>
              <!-- <div>조회수: {{row.item.views}}회</div> -->
              <div>판매량: {{checkNull(row.item.order_count)}}개</div>
            </td>
            <td>{{row.item.quantity}}개</td>
            <td>
                <div>  
                  <v-btn dark small color="grey" class="ma-2" @click="ProductUpdate(row.item)">수정</v-btn>
                </div>
                <div>  
                 <v-btn dark small color="grey" class="ml-2" router :to="{name: 'Product_User', params: {id: row.item.id}}">상품보기</v-btn>
                </div>
                <div>
                  <v-btn dark small color="grey" class="ma-2" @click="ProductDelete(row.item)">삭제</v-btn>
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
import { mapActions } from "vuex"
  export default {
    created(){
      this.$store.dispatch('ProductList')
    },
    beforeUpdate(){
      this.$store.state.temp = 1
      console.log('beforeUpdate')
    },
    data () {
      return {     
         temp:' ',
      }
    },
    methods:{
      ...mapActions(["ProductDelete"]),
      ...mapActions(["ProductUpdate"]),
      priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
      image(image){
        //경로를 조합해줄 메서드.
        if(image == null){
          return require('@/images/null.jpg');
        }
        /* 랜더링 되는 시간 때문에 발생하는 오류를 try, catch로 잡아준다. */
        try {
          return require('@/images/'+ image +'.jpg')
        } catch (e) {
          return require('@/images/null.jpg')
        }
      },
      checkNull(count){
        if(count == null)
          return 0
        else
          return count
      }
    }
  }
  </script>