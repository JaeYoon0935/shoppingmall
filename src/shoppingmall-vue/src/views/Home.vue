<template>
  <div class="home">
    <h2 class="pt-2">메인페이지</h2>
    <p>
    주요 상세페이지 3가지 정도
    관리자 페이지에 메인으로 띄워주기 
    </p>
    <v-img src="@/assets/livingLamp40.jpg" 
       width="100"></v-img>
  </div>
</template>

<script>
// @ is an alias to /src
import HelloWorld from '@/components/HelloWorld.vue'

export default {
  name: 'Home',
  components: {
    HelloWorld
  }
}
</script>
