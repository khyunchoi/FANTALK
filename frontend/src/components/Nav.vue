<template>
  <v-toolbar>
    <router-link style="text-decoration: none; color: #797BF8; font-size: 1.2em; font-weight: bold;" :to="{name: 'Index'}">
        FANTALK
    </router-link>
    <v-toolbar-items class="container">
      <!-- <v-btn style="background-color: #FFFFFF;">팬미팅 입장</v-btn> -->
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'MeetingList'}">
        팬미팅 입장
      </router-link>
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CommunityList'}">
        팬 커뮤니티
      </router-link>
      <router-link style="text-decoration: none; color: #000000; font-weight: bold" :to="{name: 'CheckManager'}">
        팬미팅 신청
      </router-link>
    </v-toolbar-items>
    <v-btn style="background-color: #FFFFFF; font-weight: bold">
      <div v-if="userId == 0" style="display: flex;">
        <img src="../assets/google.png" alt="logo" width="20px" height="20px">
        <router-link :to="{name: 'Login'}">
          로그인
        </router-link>
      </div>
      <div v-else>
        <router-link :to="{name: 'Login'}">
          로그아웃
        </router-link>
      </div>
    </v-btn>
  </v-toolbar>
</template>

<script>
  export default {
    name: 'Nav',
    data: function() {
      return {
        userId: 0
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