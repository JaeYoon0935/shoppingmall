<template>
  <div class="home">

     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Living Room
       <span class="text">LED 거실등 전체</span>
       <a href="#30" style="color:white;">맨 위로</a>
     </p>
     <div style="text-align:left; margin-left:15%; margin-top:30px; margin-bottom:100px;">
        <span v-for="(item, index) in $store.state.imageLiving" v-bind:key="index">
          <router-link :to="{name: 'Product_User', params: {id: item.id}}">
            <span v-if="index <4">
              <img :src="image(item.image)" class="image">
            </span>
          </router-link> 
        </span>
     </div>

    <div id="30" style="margin-top:1000px;">
        30평형
    </div>


     <!-- 사용자 정보 -->
     <hr width = "80%" color = "gray" style="margin: auto;">
     <p class="size24" style="text-align:left; margin-top:30px; margin-left:10%">Information
       <span class="text">판매자 정보</span>
     </p>
     <p class="size18" style="margin-bottom:0px; margin-top:50px; font-weight:560">
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
.image{
  width:12%; 
  height:12%;
}
div[data-v-fae5bece] {
    width: 2200px;
    height: 100%;
    
    background-color: rgb(255, 255, 255);
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

.text{
    font-family: "Spoqa Han Sans",sans-serif;
    color: #807f7f;
    font-size: 14px;
    margin-left:10px;
    font-weight:700;
}


</style>


<script>
export default {
   created(){
    this.$store.dispatch('imageLiving')
  },
  data: () => ({ 
      Living: [
        { title: '거실등 30평형', name:'Product'},
        { title: '거실등 40평형', name:'Product'},
      ],
    }),
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
    prepare(){
      alert('준비 중입니다.')
    }
  }
}
</script>