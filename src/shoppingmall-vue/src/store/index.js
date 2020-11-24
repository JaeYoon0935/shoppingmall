import Vue from 'vue'
import Vuex from 'vuex'
import router from "../router";

Vue.use(Vuex)

export default new Vuex.Store({
  state: {
    userlist_headers: [
      { text: '아이디', value: 'id'},
      { text: '비밀번호', value: 'password' },
      { text: '이름', value: 'name' },
      { text: '주소', value: 'address' },
      { text: '전화번호', value: 'phone' },
      { text: '이메일', value: 'email' },
      { text: '포인트', value: 'point' },
      { text: '관리', value: 'management' },
    ],
    userlist: [
      {
        id: 'wefwee',
        password: 159,
        name: '김모씨',
        address: '대구시 중구',
        phone: '010-1234-1234',
        email: 'abcde@naver.com',
        point: '3000',
        management: '수정/탈퇴',
      },
      {
        id: 'Idd1',
        password: 237,
        name: '김모씨1',
        address: '대구시 수성구',
        phone: '010-1234-1114',
        email: 'abefwede@naver.com',
        point: '500',
        management: '수정/탈퇴',
      },
      {
        id: 'sfeir',
        password: 262,
        name: '김모씨2',
        address: '대구시 남구',
        phone: '010-1234-1111',
        email: 'wefwecde@naver.com',
        point: '2000',
        management: '수정/탈퇴',
      },
      {
        id: 'abc123',
        password: 305,
        name: '이모씨',
        address: '대구시 달서구',
        phone: '010-1114-1234',
        email: 'aewfewfe@naver.com',
        point: '1000',
        management: '수정/탈퇴',
      },
      {
        id: 'aaa',
        password: 356,
        name: '이모씨1',
        address: '대구시 동구',
        phone: '010-1231-1111',
        email: 'sfede@naver.com',
        point: '0',
        management: '수정/탈퇴',
      },
      {
        id: 'bbbean',
        password: 375,
        name: '박모씨2',
        address: '대구시 동구',
        phone: '010-1234-1234',
        email: 'wefwefewe@naver.com',
        point: '2000',
        management: '수정/탈퇴',
      },
      {
        id: 'Ldfbifdbp',
        password: 392,
        name: '박모씨3',
        address: '대구시 동구',
        phone: '010-1234-1234',
        email: 'dsvdsve@naver.com',
        point: '1000',
        management: '수정/탈퇴',
      },
      {
        id: 'bfb',
        password: 408,
        name: '이모씨5',
        address: '대구시 중구',
        phone: '010-1234-1234',
        email: 'sdvdsvsde@naver.com',
        point: '1000',
        management: '수정/탈퇴',
      },
      {
        id: 'xbt',
        password: 452,
        name: 'avcsdf',
        address: '대구시 동구',
        phone: '010-1234-1234',
        email: 'sdvdsvde@naver.com',
        point: '1000',
        management: '수정/탈퇴',
      },
      {
        id: 'vt',
        password: 518,
        name: 'sdc',
        address: '대구시 동구',
        phone: '010-1234-1234',
        email: 'wefwde@naver.com',
        point: '1000',
        management: '수정/탈퇴',
      },
    ],
    ranking_header: [
      { text: '순위', value: 'name', },
      { text: '상품명', value: 'product' },
    ],
    ranking_item: [
      {
        name: '1',
        product: '주방등',
      },
      {
        name: '2',
        product: '거실등',
      },
      {
        name: '3',
        product: '콘센트',
      },
      {
        name: '4',
        product: '멀티탭',
      },
      {
        name: '5',
        product: '매입등',
      },
      {
        name: '6',
        product: '기타품목',
      },
      {
        name: '7',
        product: '기타품목',
      },
      {
        name: '8',
        product: '기타품목',
      },
      {
        name: '9',
        product: '기타품목',
      },
      {
        name: '10',
        product: '기타품목',
      },
    ],
    category_headers: [
      {
        text: '분류코드',
        align: 'start',
        sortable: false,
        value: 'id',
      },
      { text: '분류명', value: 'name' },
      { text: '상품수', value: 'password' },
      { text: '관리', value: 'management' },

    ],
    category_item: [
      {
        id: '20',
        name: '상의/하의',
        password: 9,
        management: '추가/수정',

      },
      {
        id: '2010',
        name: '상의',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '201010',
        name: '셔츠',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '20101010',
        name: '체크/슬림',
        password: 2,
        management: '추가/수정',

      },
      {
        id: '2010101010',
        name: '체크',
        password: 1,
        management: '추가/수정',

      },
      {
        id: '2010101020',
        name: '슬림',
        password: 1,
        management: '추가/수정',

      },
      {
        id: '201020',
        name: '맨투맨',
        password: 4,
        management: '추가/수정',

      },
      {
        id: '2020',
        name: '하의',
        password: 3,
        management: '추가/수정',

      },
      {
        id: '30',
        name: '테스트',
        password: 999999999,
        management: '추가/수정',
      },
    ],
  },
  mutations: {
  },
  actions: {
  },
  modules: {
  }
})
