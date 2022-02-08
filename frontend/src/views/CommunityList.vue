<template>
 <div>
   <CommunityListSearch />
   <CommunityListCards />
 </div>
</template>

<script>
  import CommunityListCards from '../components/CommunityListCards.vue'
  import CommunityListSearch from '../components/CommunityListSearch.vue'
  export default {
    name: 'CommunityList',
    components: {
      CommunityListCards, CommunityListSearch
    },

    data: () => ({
    }),
  }
</script>
