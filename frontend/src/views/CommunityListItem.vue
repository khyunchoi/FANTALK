<template>
  <div>

    <div class="community-list-articles-container">
      <h1>{{ communinityId }}번 게시판</h1>
      <hr>
      <h1>커뮤니티 게시글 목록</h1>

      
    </div>

  </div>
</template>

<script>

  const communinityId = document.location.pathname.slice(13, 20)
  export default {
    name: 'CommunityListItem',
    components: {
    },

    data: function() {
      return {
        communinityId: communinityId,
        CommunityListItem: {}
      }
    },
  }
</script>

<style>
  .community-list-articles-container {
    width: 100%;
    display: flex;
    padding: 5%;
    flex-direction: column;
    align-items: left;
  }
</style>