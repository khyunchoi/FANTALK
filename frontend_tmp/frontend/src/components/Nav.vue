<template>
  <v-container>
    <router-link style="text-decoration: none; color: #797BF8" :to="{name: 'Index'}">
      FANTALK
    </router-link>
    <v-tabs centered>
      <v-tab>
        팬미팅 입장
      </v-tab>
      <v-tab>
        <router-link style="text-decoration: none;" :to="{name: 'CommunityList'}">
          팬 커뮤니티
        </router-link>
      </v-tab>
      <v-tab>
        팬미팅 신청
      </v-tab>
    </v-tabs>
    <v-btn color="#FFFFFF">로그인</v-btn>

  </v-container>
</template>

<script>
  export default {
    name: 'Nav',

    data: () => ({
    }),
  }
</script>