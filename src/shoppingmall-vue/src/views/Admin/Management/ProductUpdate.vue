<template>
  <div class="about">
   <h2 class="pt-2">상품수정</h2>
    <table class="table">
      <tbody>
        <tr>     
          <th>상품코드</th>
          <td><input style="height:100%; width:100%;" v-model="id"></td>
        </tr>
        <tr>
          <th>상품명</th>
          <td><input style="height:100%; width:100%;" v-model="name"></td>
        </tr>
         <tr>
          <th>상품설명</th>
          <td><input style="height:100%; width:100%;" v-model="text"></td>
        </tr>
        <tr>
          <th>가격</th>
          <td><input style="height:100%; width:100%;" v-model="price"></td>
        </tr>
        <tr>
          <th>분류코드</th>
          <td><input style="height:100%; width:100%;" v-model="category"></td>
        </tr>
        <tr>
          <th>재고</th>
          <td><input style="height:100%; width:100%;" v-model="quantity"></td>
        </tr>    
        <tr>
          <th>이미지 업로드</th>
          <td>
              <v-file-input id="file" ref="file" v-model="fileinput"></v-file-input>
          </td>
        </tr>
    </tbody>
  </table>
    <v-row>
        <v-col
            cols="9"
            lg="9"
            sm="8"
            md="8"
          >
        </v-col>
        <v-col
            cols="3"
            lg="3"
            sm="4"
            md="4"
          >
           <span v-if="fileinput != null">
             <v-btn width="100px" class="mr-5 ml-10" @click="ProductDataUpdate({fileinput, id, name, price, category, quantity, text})"> 수정완료</v-btn>
           </span>
           <span v-else>
             <v-btn width="100px" class="mr-5 ml-10" @click="ProductDataUpdate({id, name, price, category, quantity, text})"> 수정완료</v-btn>
           </span>
           <v-btn width="100px" router :to="{name:'Product'}">취소</v-btn>
        </v-col> 
    </v-row>
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
th{
  width:200px;
  height:100px;
  border: 0.5px solid;
  border-color: rgb(200, 200, 200);
}
td{
  border: 0.5px solid;
  height:100px;
  border-color: rgb(200, 200, 200);
}
.v-text-field{
  width: 250px;
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
        id: this.$store.state.product[0].id, 
        name: this.$store.state.product[0].name,
        price: this.$store.state.product[0].price,
        category: this.$store.state.product[0].category,
        quantity: this.$store.state.product[0].quantity,
        text: this.$store.state.product[0].text,
        file: [],
        fileinput:null
      }
    },
      computed: {
      ...mapState(["product"])
    },
    methods:{
      ...mapActions(["ProductDataUpdate"]),
      priceToString(price) {
         return price.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ',');
      },
      image(image){
        //경로를 조합해줄 메서드.
        return require('@/images/'+ image +'.jpg');
      },
    }
  }
  </script>