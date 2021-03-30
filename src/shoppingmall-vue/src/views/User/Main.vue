<template>
  <div class="home">
     <!-- 판매랭킹 사진 -->
     <hr width = "100%" color = "gray">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Ranking</p>
     <div style="text-align:left; margin-left:100px;">
        <span v-for="(item, index) in $store.state.imageByRank" v-bind:key="item">
          <router-link to="preparing">
            <span v-if="index <4">
                <img :src="image(item.image)" style="width:200px;" >
            </span>
          </router-link>
        </span>
     </div>
     <!-- 거실등 사진 -->
     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Living Room</p>
     <div style="text-align:left; margin-left:100px;">
        <span v-for="(item, index) in $store.state.imageLiving" v-bind:key="item">
          <router-link to="preparing">
            <span v-if="index <4">
              <img :src="image(item.image)" style="width:200px;" >
            </span>
          </router-link>
        </span>
     </div>
     <!-- 방등 사진 -->
     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Room Lamp</p>
     <div style="text-align:left; margin-left:100px;">
        <span v-for="(item, index) in $store.state.imageRoom" v-bind:key="item">
          <router-link to="preparing">
            <span v-if="index <4">
              <img :src="image(item.image)" style="width:200px;" >
            </span>
          </router-link>
        </span>
     </div>

     <!-- 전기재료 사진 -->
     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Electrical Materials</p>
     <span>
        <router-link to="preparing">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
     <!-- 사용자 정보 -->
     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-left:15px">Information</p>
     <p class="size18" style="margin-bottom:0px;">
       <span>CEO  홍길동</span>
       <span>Business License  000-00-00000</span>
       <span>Location  대구광역시 중구 대봉로 260</span>
       <span></span>
     </p>
     <p class="size18"> 
       <span>Tel  0000-0000</span>
       <span>E-Mail  abc@abcde.com</span>
       <span>FAX  0000-000-0000</span> 
     </p>

  </div>
</template>

<style scoped>
.size18{
  font-size:18px;
  color:rgb(90, 90, 90)
}

.size24{
  font-size:24px;
}

div[data-v-fae5bece] {
    width: 1500px;
    height: 1600px;
    background-color: rgb(245, 240, 235);
    margin: 0 auto;
    padding: 0px;
    border-radius: 10px;
    font-size: 32px;
    color: black;
    font-family: 'Times New Roman', Times, serif;
    font-weight: 700;
    line-height: 50px;
    text-align: center;
}
span{
  margin:30px;
}
</style>


<script>
import { mapState } from "vuex"
export default {
   created(){
    this.$store.dispatch('imageByRank')
    this.$store.dispatch('imageLiving')
    this.$store.dispatch('imageRoom')
  },
  methods:{
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
  }
}
</script>