<template>
  <v-toolbar>
    <router-link style="text-decoration: none; color: #797BF8; font-size: 1.2em; font-weight: bold;" :to="{name: 'Index'}">
        FANTALK
    </router-link>
    <v-toolbar-items class="container">
      <v-btn style="background-color: #FFFFFF;">팬미팅 입장</v-btn>
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CommunityList'}">
        팬 커뮤니티
      </router-link>
      <v-btn style="background-color: #FFFFFF;">팬미팅 신청</v-btn>
    </v-toolbar-items>
    <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CreateCommunity'}">
      커뮤니티생성
    </router-link>
    <v-btn style="background-color: #FFFFFF; font-weight: bold">
      <img src="../assets/google.png" alt="logo" width="20px" height="20px">
      <a href="/oauth2/authorization/google" class="login" style="text-decoration: none;" hrefclass="login">로그인</a>
    </v-btn>
  </v-toolbar>
</template>

<script>
  export default {
    name: 'Nav',
    data: function() {
      return {
      }
    },
  }
</script>

<style scoped>
  .container {
    width: 90%;
    display: flex-start;
    justify-content: space-between;
    padding: 1.2% 15%;
  }
  
  .login {
    padding: 0 5%;
    font-size: 1em;
  }
</style>