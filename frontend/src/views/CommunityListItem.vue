<template>
  <div>
    <CommunityListItemArticle />

  </div>
</template>

<script>
  import CommunityListItemArticle from '../components/CommunityListItemArticle.vue'
  export default {
    name: 'CommunityListItem',
    components: {
      CommunityListItemArticle
    },

    data: function() {
      return {
      }
    },
  }
</script>