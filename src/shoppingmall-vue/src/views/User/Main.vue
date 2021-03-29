<template>
  <div class="home">
    <!-- <p style="border:1px solid; margin-bottom:50px;">
      쇼핑몰 메인페이지
    </p> -->
     <hr width = "100%" color = "gray">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Ranking</p>
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
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>

     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Living Room</p>
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
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>

     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Room Lamp</p>
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
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>


     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-bottom:30px; margin-left:15px">Electrical Materials</p>
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
      <span>
        <router-link to="product">
          <img :src="require('@/images/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>

     <hr width = "100%" color = "gray" style="margin-top:100px">
     <p class="size24" style="text-align:left; margin-left:15px">Information</p>
     <p class="size18" style="margin-bottom:0px;">
       <span>CEO  홍길동</span>
       <span>Business License  000-00-00000</span>
       <span>Address  대구광역시 중구 대봉로 260</span>
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
  color:grey
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