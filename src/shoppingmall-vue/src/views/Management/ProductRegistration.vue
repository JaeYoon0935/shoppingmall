<template>
  <div class="about">
   <h2 class="pt-2">상품등록</h2>
    <table  class="table">
      <tbody>
        <tr>     
          <th>상품코드</th>
          <td><input style="height:100%; width:100%;"></td>
        </tr>
        <tr>
          <th>상품명</th>
          <td><input style="height:100%; width:100%;"></td>
        </tr>
        <tr>
          <th>가격</th>
          <td><input style="height:100%; width:100%;"></td>
        </tr>
          <tr>
          <th>분류코드</th>
          <td><input style="height:100%; width:100%;"></td>
        </tr>
        <tr>
          <th>재고</th>
          <td><input style="height:100%; width:100%;"></td>
        </tr>
        <tr>
          <th>이미지 업로드</th>
          <td>이미지 업로드 버튼 구현하기</td>
        </tr>
    </tbody>
  </table>
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