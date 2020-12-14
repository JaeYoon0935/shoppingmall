<template>
  <div class="home">
    <h2 class="pt-2">관리자페이지</h2>  <hr />
    <p>
        블로그 CSS
    </p>
  </div>
</template>

<style scoped>
  div {
      width:1000px;    /* 가로 너비 */
      height:1000px;   /* 세로 너비 */
      background-color:rgb(146, 146, 151);    /* 선택자 배경 색 */
      margin:0 auto;     /* 바깥 여백 
      / margin 속성을 0 auto로 지정하면 위, 아래 여백은 0, 왼쪽 오른쪽은 auto로 가운데 정렬 */
      padding:20px;    /* 내부 여백 */
      border:1px solid blue;  /* 테두리 */
      border-radius: 10px;    /* 테두리 곡선 */
      font-size:20px;       /* 글자 크기 */
      color:white;       /* 글자 색 */
      font-family: 'Times New Roman', Times, serif;       /* 글자체 */
      font-style: italic;       /* 글자 모양 */
      font-weight: 700;       /* 글자 굵기 */
      line-height:100px;       /* 글자 높이 */
      text-align: center;       /* 글자 정렬 */
 }
</style>

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
