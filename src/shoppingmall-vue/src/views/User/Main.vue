<template>
  <div class="home">
    <p style="border:1px solid;">
      쇼핑몰 메인페이지
    </p>
     <span>
        <router-link to="product">
          <img :src="require('@/assets/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/assets/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/assets/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
      <span>
        <router-link to="product">
          <img :src="require('@/assets/null.jpg')" router :to="{name:'Product_User'}">
        </router-link>
      </span>
  </div>
</template>

<style scoped>
span{
  margin:30px;
}
</style>