<template>
  <div class="home">
    <p style="border:1px solid; margin-bottom:50px;">
      쇼핑몰 메인페이지
    </p>
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
  </div>
</template>

<style scoped>
div[data-v-fae5bece] {
    width: 1500px;
    height: 1000px;
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